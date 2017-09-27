package com.example.pawe.organizer.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;


@Table(name = "Note")
public class Note extends Model {

    @Column(name = "title")
    private String title;

    @Column(name = "text")
    private String text;

    public Note() {
        super();
    }

    public Note(String title, String text) {
        super();
        this.title = title;
        this.text = text;
    }

}
