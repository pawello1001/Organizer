package com.example.pawe.organizer.flow.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.pawe.organizer.R;
import com.example.pawe.organizer.flow.activities.SingleAlarmActivity;
import com.example.pawe.organizer.flow.fragments.AlarmsFragment;
import com.example.pawe.organizer.models.Alarm;
import com.example.pawe.organizer.utils.KeyboardHider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AlarmListAdapter extends ArrayAdapter<Alarm> {

    private Activity mContext;
    private List<Alarm> mAlarms = new ArrayList<Alarm>();

    private TextView mAlarmHourTv;
    private Switch mAlarmEnabled;

    @Nullable
    @BindView(R.id.alarms_lv)
    ListView mAlarmsLv;

    public AlarmListAdapter(Activity context, List<Alarm> alarms) {
        super(context, R.layout.alarm_list_item, alarms);
        mContext = context;
        mAlarms = alarms;
    }

    public View getView(final int position, View view, @NonNull ViewGroup parent) {
        view = LayoutInflater.from(mContext).inflate(R.layout.alarm_list_item, parent, false);
        mAlarmHourTv = ButterKnife.findById(view, R.id.alarm_list_hour_tv);
        mAlarmEnabled = ButterKnife.findById(view, R.id.alarm_list_enabled_sw);

        if (mAlarms != null && mAlarms.size() > 0) {
            if (mAlarms.get(position).getMinute() < 10 && mAlarms.get(position).getHour() < 10) {
                mAlarmHourTv.setText("0" + mAlarms.get(position).getHour() + ":0" + mAlarms.get(position).getMinute());
            } else if (mAlarms.get(position).getMinute() < 10 && mAlarms.get(position).getHour() >= 10) {
                mAlarmHourTv.setText(mAlarms.get(position).getHour() + ":0" + mAlarms.get(position).getMinute());
            } else if (mAlarms.get(position).getMinute() >= 10 && mAlarms.get(position).getHour() < 10) {
                mAlarmHourTv.setText("0" + mAlarms.get(position).getHour() + ":" + mAlarms.get(position).getMinute());
            } else mAlarmHourTv.setText(mAlarms.get(position).getHour() + ":" + mAlarms.get(position).getMinute());
            mAlarmEnabled.setChecked(mAlarms.get(position).getIsEnabled());
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SingleAlarmActivity.startActivity(mContext, mAlarms.get(position).getHour(), mAlarms.get(position).getMinute(), mAlarms.get(position).getSongId(), mAlarms.get(position).getIsEnabled());
            }
        });

        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
                alertDialog.setMessage(R.string.dialog_delete_alarm);
                alertDialog.setTitle(R.string.dialog_delete_alarm_title);
                alertDialog.setPositiveButton(R.string.dialog_positive_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        KeyboardHider.hideKeyboard(mContext);
                        mAlarms = Alarm.getAllAlarms();
                        mAlarms.get(position).delete();
                        notifyDataSetChanged();
                        mAlarms = Alarm.getAllAlarms();
                        mAlarmsLv = (ListView) ((Activity)mContext).findViewById(R.id.alarms_lv);
                        mAlarmsLv.setAdapter(new AlarmListAdapter(mContext, mAlarms));
                    }
                });
                alertDialog.setNegativeButton(R.string.dialog_negative_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        KeyboardHider.hideKeyboard(mContext);
                        dialog.cancel();
                    }
                });
                alertDialog.show();
                notifyDataSetChanged();
                return true;
            }
        });

        mAlarmEnabled.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Alarm alarm = Alarm.getAlarm(mAlarms.get(position).getHour(), mAlarms.get(position).getMinute(), mAlarms.get(position).getSongId());
                alarm.setEnabled(!alarm.getIsEnabled());
                alarm.save();

                if(mAlarms.get(position).getIsEnabled() == true) {
                    //set up alarm!!!!
                    AlarmsFragment.setAlarm(mContext, alarm);
                }
                else {
                    //cancel alarm!!!!
                    AlarmsFragment.cancelAlarm(mContext);
                }
            }
        });

        return view;
    }

}
