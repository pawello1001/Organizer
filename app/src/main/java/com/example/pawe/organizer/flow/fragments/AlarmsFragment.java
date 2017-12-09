package com.example.pawe.organizer.flow.fragments;


import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.pawe.organizer.R;
import com.example.pawe.organizer.flow.activities.SingleAlarmActivity;
import com.example.pawe.organizer.flow.adapters.AlarmListAdapter;
import com.example.pawe.organizer.flow.services.AlarmReceiver;
import com.example.pawe.organizer.models.Alarm;
import com.example.pawe.organizer.utils.CustomSnackBar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.ButterKnife;

import static android.content.Context.ALARM_SERVICE;

public class AlarmsFragment extends Fragment {

    private Button siema;
    private ListView mAlarmsLv;

    private int hour;
    private int minute;
    private int songId;
    private boolean isEnabled;

    private List<Alarm> mAlarms;
    private ArrayAdapter<Alarm> mAdapter;

    private static Alarm sAlarm;
    private static AlarmManager sAlarmManager;
    private static Intent sReceiverIntent;
    private static PendingIntent sPendingIntent;

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Date date = Calendar.getInstance().getTime();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String curDate = format.format(date);

            float timePlaying = intent.getFloatExtra("timePlaying", 0);

            sAlarm.setTimesCalled(sAlarm.getTimesCalled() + 1);
            sAlarm.setLastUsed(curDate);
            sAlarm.setTimePlayedCounter(sAlarm.getTimePlayedCounter() + timePlaying);
            sAlarm.save();
        }
    };

    public static void setAlarm(Activity context, Alarm alarm) {
        sAlarm = alarm;
        sAlarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        sReceiverIntent = new Intent(context, AlarmReceiver.class);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, alarm.getHour());
        calendar.set(Calendar.MINUTE, alarm.getMinute());

        int hour = alarm.getHour();
        int minute = alarm.getMinute();
        String sHour = String.valueOf(hour);
        String sMinute = String.valueOf(minute);

        if (minute < 10) {
            sMinute = "0" + String.valueOf(minute);
        }
        if (hour < 10) {
            sHour = "0" + String.valueOf(hour);
        }

        CustomSnackBar.makeErrorSnackBar(context, context.getResources().getString(R.string.alarm_set_on) + sHour + ":" + sMinute, false);
        //Toast.makeText(context, context.getResources().getString(R.string.alarm_set_on)+ sHour + ":" + sMinute, Toast.LENGTH_LONG).show();

        sReceiverIntent.putExtra("extra", "alarm on");
        sReceiverIntent.putExtra("songId", alarm.getSongId());

        sPendingIntent = PendingIntent.getBroadcast(context, 0, sReceiverIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        sAlarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sPendingIntent);
    }

    public static void cancelAlarm(Activity context) {
//        Calendar calendar = Calendar.getInstance();
//        int hour = calendar.get(Calendar.HOUR_OF_DAY);
//        int minute = calendar.get(Calendar.MINUTE);
//        Alarm alarm = Alarm.getCurrentAlarm(hour, minute);
        if (sAlarm != null) {
            sAlarm.setEnabled(false);
            sAlarm.save();
        }
        if (sPendingIntent == null || sReceiverIntent == null) {
            context.finish();
        }
        else {
            sAlarmManager.cancel(sPendingIntent);
            sReceiverIntent.putExtra("extra", "alarm off");
            sReceiverIntent.putExtra("songId", 0);
            context.sendBroadcast(sReceiverIntent);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_alarms, container, false);

        ButterKnife.bind(rootView);

        siema = ButterKnife.findById(rootView, R.id.nowy_alarm);
        mAlarmsLv = ButterKnife.findById(rootView, R.id.alarms_lv);

        mAlarms = Alarm.getAllAlarms();
        mAdapter = new AlarmListAdapter(getActivity(), mAlarms);
        mAdapter.notifyDataSetChanged();
        mAlarmsLv.setAdapter(mAdapter);

        mAlarmsLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                hour = mAlarms.get(position).getHour();
                minute = mAlarms.get(position).getMinute();
                songId = mAlarms.get(position).getSongId();
                isEnabled = mAlarms.get(position).getIsEnabled();
                SingleAlarmActivity.startActivity(getActivity().getApplicationContext(), hour, minute, songId, isEnabled);
            }
        });


        siema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hour = -1;
                minute = -1;
                songId = -1;
                isEnabled = false;
                SingleAlarmActivity.startActivity(getActivity(), hour, minute, songId, isEnabled);
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mAlarms = Alarm.getAllAlarms();
        mAdapter = new AlarmListAdapter(getActivity(), mAlarms);
        mAlarmsLv.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(broadcastReceiver, new IntentFilter("NOW"));
    }

    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(broadcastReceiver);
    }
}
