package com.example.pawe.organizer.flow.fragments;

import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pawe.organizer.R;
import com.example.pawe.organizer.flow.activities.MainActivity;

import butterknife.ButterKnife;

public class NotesFragment extends Fragment {

    private EditText mNoteText;
    private TextView mNoteTitle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_notes, container, false);

        ButterKnife.bind(rootView);
        mNoteText = ButterKnife.findById(rootView, R.id.note_text_et);
        mNoteTitle = ButterKnife.findById(rootView, R.id.note_title_tv);

        mNoteText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                if (mNoteText.getLayout() != null && mNoteText.getLayout().getLineCount() > getResources().getInteger(R.integer.number_of_lines_notes)) {
                    mNoteText.getText().delete(mNoteText.getText().length() - 1, mNoteText.getText().length());
                }
            }
        });

        mNoteTitle.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());

                final EditText input = new EditText(getActivity());
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                input.setLayoutParams(lp);

                alertDialog.setView(input);
                alertDialog.setTitle(getString(R.string.note_alert_dialog_title));
                alertDialog.setPositiveButton(getString(R.string.dialog_positive_button),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mNoteTitle.setText(input.getText());
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

        return rootView;
    }
}
