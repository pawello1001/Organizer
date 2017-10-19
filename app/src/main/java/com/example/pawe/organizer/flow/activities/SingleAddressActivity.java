package com.example.pawe.organizer.flow.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.activeandroid.ActiveAndroid;
import com.example.pawe.organizer.R;
import com.example.pawe.organizer.base.activities.BaseActivity;
import com.example.pawe.organizer.flow.fragments.SingleAddressFragment;

import butterknife.ButterKnife;

public class SingleAddressActivity extends BaseActivity {
    private SingleAddressFragment mFragment;

    public static void startActivity(Context context, String name, String address) {
        Intent intent = new Intent(context, SingleAddressActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("address", address);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_address);
        ButterKnife.bind(this);
        ActiveAndroid.initialize(this);
    }
}
