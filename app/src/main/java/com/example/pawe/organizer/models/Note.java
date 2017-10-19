package com.example.pawe.organizer.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;


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

    public static List<Note> getAllNotes() {
        return new Select().from(Note.class).orderBy("title").execute();
    }

    public static Note getNote(String title, String text) {
        return new Select().from(Note.class).where("title = ?", title).and("text = ?", text).executeSingle();
    }

    public String getTitle() {
        return title;
    }
    public String getText() {
        return text;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setText(String text) {
        this.text = text;
    }
}
