package com.example.pawe.organizer.flow.fragments;

import android.content.DialogInterface;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.pawe.organizer.R;
import com.example.pawe.organizer.flow.activities.MainActivity;
import com.example.pawe.organizer.flow.activities.SingleAddressActivity;
import com.example.pawe.organizer.flow.adapters.AddressListAdapter;
import com.example.pawe.organizer.models.Address;
import java.util.List;

import butterknife.ButterKnife;

public class AddressesFragment extends Fragment {


    Button siemaMapy;
    Button lokal;

    private List<Address> mAddresses;
    private ListView mAddressesLv;
    private ArrayAdapter<Address> mAdapter;

    public String name;
    public String address;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_addresses, container, false);

        ButterKnife.bind(rootView);
        name = "";
        address = "";

        siemaMapy = ButterKnife.findById(rootView, R.id.siemaMapy);
        lokal = ButterKnife.findById(rootView, R.id.lokal);
        mAddressesLv = ButterKnife.findById(rootView, R.id.addresses_lv);

        mAddresses = Address.getAllAddresses();
        mAdapter = new AddressListAdapter(getActivity(), mAddresses);
        mAdapter.notifyDataSetChanged();
        mAddressesLv.setAdapter(mAdapter);

        mAddressesLv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                alertDialog.setMessage(R.string.dialog_delete_address);
                alertDialog.setTitle(R.string.dialog_delete_address_title);
                alertDialog.setPositiveButton(getString(R.string.dialog_positive_button),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mAddresses = Address.getAllAddresses();
                                mAddresses.get(position).delete();
                                mAdapter.notifyDataSetChanged();
                                mAddresses = Address.getAllAddresses();
                                mAdapter = new AddressListAdapter(getActivity(), mAddresses);
                                mAddressesLv.setAdapter(mAdapter);
                            }
                        });
                alertDialog.setNegativeButton(getString(R.string.dialog_negative_button),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }
                );
                alertDialog.show();
                return true;
            }
        });

        mAddressesLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                name = mAddresses.get(position).getName();
                address = mAddresses.get(position).getAddress();
                SingleAddressActivity.startActivity(getActivity(), name, address);
            }
        });

        siemaMapy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = "";
                address = "";
                SingleAddressActivity.startActivity(getActivity(), name, address);
            }
        });

        lokal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).setCurrentAddress();
                mAddresses = Address.getAllAddresses();
                mAdapter = new AddressListAdapter(getActivity(), mAddresses);
                mAddressesLv.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mAddresses = Address.getAllAddresses();
        mAdapter = new AddressListAdapter(getActivity(), mAddresses);
        mAddressesLv.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }
}
