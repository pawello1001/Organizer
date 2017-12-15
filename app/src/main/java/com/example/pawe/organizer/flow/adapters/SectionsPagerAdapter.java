package com.example.pawe.organizer.flow.adapters;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.pawe.organizer.R;
import com.example.pawe.organizer.flow.fragments.AddressesFragment;
import com.example.pawe.organizer.flow.fragments.AlarmsFragment;
import com.example.pawe.organizer.flow.fragments.NotesFragment;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private int mTabsCounter;
    Activity mActivity;

    public SectionsPagerAdapter(Activity activity, FragmentManager fragmentManager, int tabs) {
        super(fragmentManager);
        mActivity = activity;
        mTabsCounter = tabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new AddressesFragment();
            case 1:
                return new AlarmsFragment();
            case 2:
                return new NotesFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mTabsCounter;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return mActivity.getResources().getString(R.string.address);
            case 1:
                return mActivity.getResources().getString(R.string.alarm);
            case 2:
                return mActivity.getResources().getString(R.string.note);
            default:
                return null;
        }
    }
}
