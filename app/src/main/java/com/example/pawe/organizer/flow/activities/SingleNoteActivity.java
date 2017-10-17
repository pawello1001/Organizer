package com.example.pawe.organizer.flow.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.activeandroid.ActiveAndroid;
import com.example.pawe.organizer.R;
import com.example.pawe.organizer.base.activities.BaseActivity;
import com.example.pawe.organizer.flow.fragments.SingleNoteFragment;

import butterknife.ButterKnife;

public class SingleNoteActivity extends BaseActivity {

    private SingleNoteFragment mFragment;

    public static void startActivity(Context context, String title, String text) {
        Intent intent = new Intent(context, SingleNoteActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("text", text);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_note);
        ButterKnife.bind(this);
        ActiveAndroid.initialize(this);
    }
}
