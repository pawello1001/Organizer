package com.example.pawe.organizer.flow.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.TextView;

import com.activeandroid.ActiveAndroid;
import com.example.pawe.organizer.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddressStatsActivity extends AppCompatActivity {

    private int mTimesViewed;
    private float mDistance;
    private String mDateCreated;

    @BindView(R.id.address_stats_timesViewed_tv)
    TextView mTimesViewedTv;

    @BindView(R.id.address_stats_distance_tv)
    TextView mDistanceTv;

    @BindView(R.id.address_stats_dateCreated_tv)
    TextView mDateCreatedTv;


    public static void startActivity(Context context, int timesViewed, float distance, String dateCreated) {
        Intent intent = new Intent(context, AddressStatsActivity.class);
        intent.putExtra("timesViewed", timesViewed);
        intent.putExtra("distance", distance);
        intent.putExtra("dateCreated", dateCreated);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_stats);
        ButterKnife.bind(this);
        ActiveAndroid.initialize(this);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width * 0.75), (int)(height * 0.3));

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mTimesViewed = bundle.getInt("timesViewed");
            mDistance = bundle.getFloat("distance");
            mDateCreated = bundle.getString("dateCreated");
        }

        mTimesViewedTv.setText(String.valueOf(mTimesViewed));
        mDistanceTv.setText(String.valueOf((float)((int)((mDistance / 1000) * 100f)) / 100f));
        mDateCreatedTv.setText(mDateCreated);
    }
}
