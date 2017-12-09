package com.example.pawe.organizer.flow.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.activeandroid.ActiveAndroid;
import com.example.pawe.organizer.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AlarmStatsActivity extends AppCompatActivity {

    @BindView(R.id.alarm_stats_timesCalled_tv)
    TextView mTimesCalledTv;

    @BindView(R.id.alarm_stats_last_used_tv)
    TextView mLastUsedTv;

    @BindView(R.id.alarm_stats_average_time_play_tv)
    TextView mAverageTimePlayedTv;

    private int mTimesCalled;
    private String mLastUsed;
    private float mAverageTimePlayed;

    public static void startActivity(Activity context, int timesCalled, String lastUsed, float averageTime) {
        Intent intent = new Intent(context, AlarmStatsActivity.class);
        intent.putExtra("timesCalled", timesCalled);
        intent.putExtra("lastUsed", lastUsed);
        intent.putExtra("averageTime", averageTime);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_stats);
        ButterKnife.bind(this);
        ActiveAndroid.initialize(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mTimesCalled = bundle.getInt("timesCalled");
            mLastUsed = bundle.getString("lastUsed");
            mAverageTimePlayed = bundle.getFloat("averageTime");

            mTimesCalledTv.setText(String.valueOf(mTimesCalled));
            mLastUsedTv.setText(mLastUsed);
            mAverageTimePlayedTv.setText(String.valueOf(mAverageTimePlayed));
        }
    }
}
