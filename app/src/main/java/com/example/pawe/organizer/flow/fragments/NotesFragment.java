package com.example.pawe.organizer.flow.fragments;

import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.pawe.organizer.R;
import com.example.pawe.organizer.flow.activities.SingleNoteActivity;
import com.example.pawe.organizer.flow.adapters.NoteListAdapter;
import com.example.pawe.organizer.models.Note;

import java.util.List;

import butterknife.ButterKnife;

public class NotesFragment extends Fragment {

    private ImageButton mNoteAddNewIb;

    private ListView mNotesLv;
    private List<Note> mNotes;
    private ArrayAdapter<Note> mAdapter;

    public String title;
    public String text;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_notes, container, false);

        ButterKnife.bind(rootView);

        title = "";
        text = "";

        mNoteAddNewIb = ButterKnife.findById(rootView, R.id.new_note_ib);
        mNotesLv = ButterKnife.findById(rootView, R.id.notes_lv);

        mNotes = Note.getAllNotes();
        mAdapter = new NoteListAdapter(getActivity(), mNotes);
        mAdapter.notifyDataSetChanged();
        mNotesLv.setAdapter(mAdapter);

        mNotesLv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity(), R.style.AlertDialogTheme);
                alertDialog.setMessage(R.string.dialog_delete_note);
                alertDialog.setTitle(R.string.dialog_delete_note_title);
                alertDialog.setPositiveButton(getString(R.string.dialog_positive_button),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mNotes = Note.getAllNotes();
                                mNotes.get(position).delete();
                                mAdapter.notifyDataSetChanged();
                                mNotes = Note.getAllNotes();
                                mAdapter = new NoteListAdapter(getActivity(), mNotes);
                                mNotesLv.setAdapter(mAdapter);
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
        mNotesLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                title = mNotes.get(position).getTitle();
                text = mNotes.get(position).getText();
                SingleNoteActivity.startActivity(getActivity(), title, text);
            }
        });

        mNoteAddNewIb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = "";
                text = "";
                SingleNoteActivity.startActivity(getActivity(), title, text);
            }
        });



        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mNotes = Note.getAllNotes();
        mAdapter = new NoteListAdapter(getActivity(), mNotes);
        mNotesLv.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

}
