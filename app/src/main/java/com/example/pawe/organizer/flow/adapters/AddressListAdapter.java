package com.example.pawe.organizer.flow.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pawe.organizer.MapsActivity;
import com.example.pawe.organizer.R;
import com.example.pawe.organizer.models.Address;
import com.example.pawe.organizer.utils.CustomSnackBar;
import com.example.pawe.organizer.utils.OnlineChecker;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class AddressListAdapter extends ArrayAdapter<Address> {

    private Activity mContext;
    private List<Address> mAddresses = new ArrayList<Address>();

    private TextView mAddressNameTv;
    private TextView mAddressTv;
    private ImageView mAddressLocationIv;

    public AddressListAdapter(Activity context, List<Address> addresses) {
        super(context, R.layout.address_list_item, addresses);
        this.mContext = context;
        this.mAddresses = addresses;
    }

    public View getView(final int position, View view, @NonNull ViewGroup parent) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.address_list_item, parent, false);

        mAddressNameTv = ButterKnife.findById(view, R.id.address_name_tv);
        mAddressTv = ButterKnife.findById(view, R.id.address_address_tv);
        mAddressLocationIv = ButterKnife.findById(view, R.id.address_location_iv);

        mAddressNameTv.setText(mAddresses.get(position).getName());
        mAddressTv.setText(mAddresses.get(position).getAddress());

        mAddressLocationIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (OnlineChecker.isOnline(mContext)) {
                    Intent intent = new Intent(mContext, MapsActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("name", mAddresses.get(position).getName());
                    intent.putExtra("address", mAddresses.get(position).getAddress());
                    intent.putExtra("lat", mAddresses.get(position).getLatitude());
                    intent.putExtra("long", mAddresses.get(position).getLongitude());
                    mContext.startActivity(intent);
                } else {
                    CustomSnackBar.makeErrorSnackBar(mContext, mContext.getResources().getString(R.string.no_internet), true);
                }

            }
        });

        return view;
    }
}
