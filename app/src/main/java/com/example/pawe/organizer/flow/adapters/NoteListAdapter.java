package com.example.pawe.organizer.flow.adapters;


import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.pawe.organizer.R;
import com.example.pawe.organizer.models.Note;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class NoteListAdapter extends ArrayAdapter<Note> {

    private Context mContext;
    private List<Note> mNotes = new ArrayList<Note>();

    private TextView mNoteTitleTv;

    public NoteListAdapter(Context context, List<Note> notes) {
        super(context, R.layout.note_list_item, notes);
        mContext = context;
        mNotes = notes;
        Log.d("notes counter", String.valueOf(mNotes.size()));
    }

    public View getView(int position, View view, @NonNull ViewGroup parent) {
        //LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
        view = LayoutInflater.from(getContext()).inflate(R.layout.note_list_item, parent, false);
        //view = inflater.inflate(R.layout.note_list_item, parent, false);

        mNoteTitleTv = ButterKnife.findById(view, R.id.note_list_title_tv);
        mNoteTitleTv.setText(mNotes.get(position).getTitle());

        return view;
    }
}
