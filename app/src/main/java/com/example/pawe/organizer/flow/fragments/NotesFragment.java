package com.example.pawe.organizer.flow.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.pawe.organizer.R;

import butterknife.ButterKnife;

public class NotesFragment extends Fragment {

    private EditText mNoteEditText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_notes, container, false);

        ButterKnife.bind(rootView);
        mNoteEditText = ButterKnife.findById(rootView, R.id.note_et);

        mNoteEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                if (mNoteEditText.getLayout() != null && mNoteEditText.getLayout().getLineCount() > getResources().getInteger(R.integer.number_of_lines_notes)) {
                    mNoteEditText.getText().delete(mNoteEditText.getText().length() - 1, mNoteEditText.getText().length());
                }
            }
        });

        return rootView;
    }
}
