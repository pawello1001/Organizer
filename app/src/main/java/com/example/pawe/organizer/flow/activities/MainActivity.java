package com.example.pawe.organizer.flow.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.example.pawe.organizer.R;
import com.example.pawe.organizer.base.activities.BaseActivity;
import com.example.pawe.organizer.flow.adapters.SectionsPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    private static final int TABS_COUNTER = 3;

    private SectionsPagerAdapter mSectionsPagerAdapter;

    @BindView(R.id.activity_main_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.activity_main_viewpager)
    ViewPager mContainerVP;

    @BindView(R.id.activity_main_tabs)
    TabLayout mTabsTL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), TABS_COUNTER);
        mContainerVP.setAdapter(mSectionsPagerAdapter);

        mTabsTL.setupWithViewPager(mContainerVP);
    }
}
