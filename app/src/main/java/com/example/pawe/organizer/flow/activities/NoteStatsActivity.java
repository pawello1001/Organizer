package com.example.pawe.organizer.flow.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.activeandroid.ActiveAndroid;
import com.example.pawe.organizer.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NoteStatsActivity extends AppCompatActivity {

    @BindView(R.id.note_stats_char_counter_tv)
    TextView mCharCounterTv;

    @BindView(R.id.note_stats_date_created_tv)
    TextView mDateCreatedTv;

    @BindView(R.id.note_stats_last_updated_tv)
    TextView mLastUpdatedTv;

    private int mCharCounter;
    private String mDateCreated;
    private String mLastUpdated;

    public static void startActivity(Activity context, int charCounter, String dateCreated, String lastUpdated) {
        Intent intent = new Intent(context, NoteStatsActivity.class);
        intent.putExtra("charCounter", charCounter);
        intent.putExtra("dateCreated", dateCreated);
        intent.putExtra("lastUpdated", lastUpdated);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_stats);
        ButterKnife.bind(this);
        ActiveAndroid.initialize(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mCharCounter = bundle.getInt("charCounter");
            mDateCreated = bundle.getString("dateCreated");
            mLastUpdated = bundle.getString("lastUpdated");
        }

        mCharCounterTv.setText(String.valueOf(mCharCounter));
        mDateCreatedTv.setText(mDateCreated);
        mLastUpdatedTv.setText(mLastUpdated);
    }
}
