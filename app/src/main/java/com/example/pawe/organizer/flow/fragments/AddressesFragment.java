package com.example.pawe.organizer.flow.fragments;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.pawe.organizer.MapsActivity;
import com.example.pawe.organizer.R;

import butterknife.ButterKnife;

public class AddressesFragment extends Fragment {

    Button siemaMapy;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_addresses, container, false);

        ButterKnife.bind(rootView);
        siemaMapy = ButterKnife.findById(rootView, R.id.siemaMapy);
        siemaMapy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), MapsActivity.class));
            }
        });

        return rootView;
    }
}
