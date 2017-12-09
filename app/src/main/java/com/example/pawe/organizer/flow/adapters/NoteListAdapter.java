package com.example.pawe.organizer.flow.adapters;


import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pawe.organizer.R;
import com.example.pawe.organizer.flow.activities.NoteStatsActivity;
import com.example.pawe.organizer.models.Note;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class NoteListAdapter extends ArrayAdapter<Note> {

    private Activity mContext;
    private List<Note> mNotes = new ArrayList<Note>();

    private TextView mNoteTitleTv;
    private ImageView mNoteStatsIv;

    public NoteListAdapter(Activity context, List<Note> notes) {
        super(context, R.layout.note_list_item, notes);
        mContext = context;
        mNotes = notes;
    }

    public View getView(final int position, View view, @NonNull ViewGroup parent) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.note_list_item, parent, false);

        mNoteTitleTv = ButterKnife.findById(view, R.id.note_list_title_tv);
        mNoteStatsIv = ButterKnife.findById(view, R.id.note_stats_iv);
        mNoteTitleTv.setHintTextColor(mContext.getResources().getColor(R.color.colorAccent));
        mNoteTitleTv.setText(mNotes.get(position).getTitle());

        mNoteStatsIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NoteStatsActivity.startActivity(mContext, mNotes.get(position).getCharCounter(), mNotes.get(position).getDateCreated(), mNotes.get(position).getLastUpdated());
            }
        });

        return view;
    }
}
