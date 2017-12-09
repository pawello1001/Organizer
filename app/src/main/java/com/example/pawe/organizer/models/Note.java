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

    @Column(name = "charCounter")
    private int charCounter;

    @Column(name = "dateCreated")
    private String dateCreated;

    @Column(name = "lastUpdated")
    private String lastUpdated;

    public Note() {
        super();
    }

    public Note(String title, String text, int charCounter, String dateCreated, String lastUpdated) {
        super();
        this.title = title;
        this.text = text;
        this.charCounter = charCounter;
        this.dateCreated = dateCreated;
        this.lastUpdated = lastUpdated;
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
    public int getCharCounter() { return charCounter; }
    public String getDateCreated() { return dateCreated; }
    public String getLastUpdated() { return lastUpdated; }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setText(String text) {
        this.text = text;
    }
    public void setCharCounter(int charCounter) { this.charCounter = charCounter; }
    public void setDateCreated(String dateCreated) { this.dateCreated = dateCreated; }
    public void  setLastUpdated(String lastUpdated) { this.lastUpdated = lastUpdated; }
}
