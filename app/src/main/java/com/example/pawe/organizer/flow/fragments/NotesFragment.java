package com.example.pawe.organizer.flow.fragments;

import android.content.DialogInterface;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pawe.organizer.R;
import com.example.pawe.organizer.flow.activities.MainActivity;
import com.example.pawe.organizer.flow.activities.SingleNoteActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NotesFragment extends Fragment {

    private Button siema;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_notes, container, false);

        ButterKnife.bind(rootView);

        siema = ButterKnife.findById(rootView, R.id.siema);

        siema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SingleNoteActivity.startActivity(getActivity());
            }
        });
        Log.d("logss", "fragment");
        return rootView;
    }
}
