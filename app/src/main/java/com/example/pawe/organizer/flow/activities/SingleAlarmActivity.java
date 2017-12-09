package com.example.pawe.organizer.flow.activities;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TimePicker;

import com.activeandroid.ActiveAndroid;
import com.example.pawe.organizer.R;
import com.example.pawe.organizer.flow.fragments.AlarmsFragment;
import com.example.pawe.organizer.models.Alarm;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.lang.reflect.Field;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SingleAlarmActivity extends AppCompatActivity {

    private int hour;
    private int minute;
    private int songId;
    private boolean isEnabled;
    private int oldSongId;

    private String[] songs;

    @BindView(R.id.timePicker)
    TimePicker mTimePicker;

    @BindView(R.id.songPicker_mbs)
    MaterialBetterSpinner mSongPicker;

    @OnClick(R.id.alarm_add_cancel_iv)
    public void cancel() {
        AlarmsFragment.cancelAlarm(this);
        this.finish();
    }

    @OnClick(R.id.alarm_add_ok_iv)
    public void add() {
        Alarm alarm;
        if ((hour < 0 || minute < 0 || songId < 0) && isEnabled == false) {
            hour = mTimePicker.getHour();
            minute = mTimePicker.getMinute();
            alarm = new Alarm(hour, minute, songId, true);
            alarm.save();
        } else {
            alarm = Alarm.getAlarm(hour, minute, oldSongId);
            hour = mTimePicker.getHour();
            minute = mTimePicker.getMinute();
            alarm.setHour(hour);
            alarm.setMinute(minute);
            alarm.setSongId(songId);
            alarm.setEnabled(true);
            alarm.save();
        }
        this.finish();
        AlarmsFragment.setAlarm(this, alarm);
    }

    public static void startActivity(Context context, int hour, int minute, int songId, boolean isEnabled) {
        Intent intent = new Intent(context, SingleAlarmActivity.class);
        intent.putExtra("hour", hour);
        intent.putExtra("minute", minute);
        intent.putExtra("songId", songId);
        intent.putExtra("isEnabled", isEnabled);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_alarm);
        ButterKnife.bind(this);
        ActiveAndroid.initialize(this);

        final Bundle extras = getIntent().getExtras();
        if (extras != null) {
            hour = extras.getInt("hour");
            minute = extras.getInt("minute");
            songId = extras.getInt("songId");
            oldSongId = songId;
            isEnabled = extras.getBoolean("isEnabled");

            if (songId < 0) songId = 0;

            if (hour > 0 && minute > 0) {
                mTimePicker.setHour(hour);
                mTimePicker.setMinute(minute);
            }
        }

        mTimePicker.setIs24HourView(true);
        setTimePickerColor(mTimePicker);
        mTimePicker.setDrawingCacheBackgroundColor(getResources().getColor(R.color.colorAccent));
        songs = getResources().getStringArray(R.array.ringtones);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.ringtones, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        mSongPicker.setAdapter(adapter);
        mSongPicker.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                songId = position;
            }
        });
        mSongPicker.setText(songs[songId]);
    }

    private void setTimePickerColor(TimePicker timePicker) {
        Resources system = Resources.getSystem();
        int hour = system.getIdentifier("hour", "id", "android");
        int minute = system.getIdentifier("minute", "id", "android");

        NumberPicker hourPicker = (NumberPicker) timePicker.findViewById(hour);
        NumberPicker minutePicker = (NumberPicker) timePicker.findViewById(minute);

        setNumberPickerColor(hourPicker);
        setNumberPickerColor(minutePicker);
    }
    private void setNumberPickerColor(NumberPicker numberPicker) {
        final int count = numberPicker.getChildCount();
        final int color = getResources().getColor(R.color.colorAccent);

        for (int i = 0; i < count; i++) {
            View child = numberPicker.getChildAt(i);
            try {
                Field wheelPaintField = numberPicker.getClass().getDeclaredField("mSelectorWheelPaint");
                wheelPaintField.setAccessible(true);
                ((Paint)wheelPaintField.get(numberPicker)).setColor(color);
                ((EditText)child).setTextColor(color);
                numberPicker.invalidate();
            } catch (Exception ex) {
                Log.w("setNumberPickerColor", ex);
            }
        }
    }
}
