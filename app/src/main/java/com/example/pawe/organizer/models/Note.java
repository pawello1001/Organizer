package com.example.pawe.organizer.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;


@Table(name = "Note")
public class Note extends Model {
    @Column(name = "remote_id", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
    private long remoteId;

    @Column(name = "title")
    private String title;

    @Column(name = "text")
    private String text;

    public Note() {
        super();
    }

    public Note(int remoteId, String title, String text) {
        super();
        this.remoteId = remoteId;
        this.title = title;
        this.text = text;
    }

}
