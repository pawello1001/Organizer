package com.example.pawe.organizer.flow.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pawe.organizer.R;
import com.example.pawe.organizer.flow.services.DataLongOperationAsynchTask;
import com.example.pawe.organizer.models.Address;
import com.example.pawe.organizer.utils.CustomSnackBar;
import com.example.pawe.organizer.utils.KeyboardHider;
import com.example.pawe.organizer.utils.OnlineChecker;
import com.rengwuxian.materialedittext.MaterialEditText;


import butterknife.ButterKnife;

public class SingleAddressFragment extends Fragment {

    private MaterialEditText mAddressMet;
    private TextView mAddressName;
    private ImageView mCancelAddressIv;
    private ImageView mSaveAddressIv;

    private String name;
    private String address;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_single_address, container, false);
        rootView.bringToFront();

        ButterKnife.bind(rootView);
        mAddressMet = ButterKnife.findById(rootView, R.id.address_address_met);
        mAddressName = ButterKnife.findById(rootView, R.id.address_name_tv);
        mCancelAddressIv = ButterKnife.findById(rootView, R.id.address_cancel_iv);
        mSaveAddressIv = ButterKnife.findById(rootView, R.id.address_ok_iv);

        final Bundle extras = getActivity().getIntent().getExtras();
        if (extras != null) {
            name = extras.getString("name");
            address = extras.getString("address");
        }
        mAddressName.setText(name);
        mAddressMet.setText(address);

        mAddressName.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());

                final EditText input = new EditText(getActivity());
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                input.setLayoutParams(lp);

                alertDialog.setView(input);
                alertDialog.setTitle(getString(R.string.address_alert_dialog_name));
                alertDialog.setPositiveButton(getString(R.string.dialog_positive_button),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mAddressName.setText(input.getText());
                            }
                        });
                alertDialog.setNegativeButton(getString(R.string.dialog_negative_button),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                alertDialog.show();
                return true;
            }
        });

        mCancelAddressIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KeyboardHider.hideKeyboard(getActivity());
                getActivity().finish();
            }
        });

        mSaveAddressIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KeyboardHider.hideKeyboard(getActivity());
                if (address.equals("")) {
                    if (!OnlineChecker.isOnline(getActivity())) {
                        CustomSnackBar.makeErrorSnackBar(getActivity(), getActivity().getResources().getString(R.string.no_internet), true);
                    } else {
                        address = mAddressMet.getText().toString();
                        name = mAddressName.getText().toString();
                        DataLongOperationAsynchTask task = new DataLongOperationAsynchTask(getActivity(), address, name);
                        task.execute();
                    }
                } else {
                    Address addr = Address.getAddress(name, address);
                    addr.setName(mAddressName.getText().toString());
                    addr.setAddress(mAddressMet.getText().toString());
                    addr.save();
                    getActivity().finish();
                }
            }
        });

        return rootView;
    }
}
