package com.example.pawe.organizer.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

@Table(name = "Alarm")
public class Alarm extends Model {

    @Column(name = "hour")
    private int hour;

    @Column(name = "minute")
    private int minute;

    @Column(name = "songId")
    private int songId;

    @Column(name = "isEnabled")
    private boolean isEnabled;

    @Column(name = "timesCalled")
    private int timesCalled;

    @Column(name = "lastUsed")
    private String lastUsed;

    @Column(name = "timePlayedCounter")
    private float timePlayedCounter;

    public Alarm() { super();}

    public Alarm(int hour, int minute, int songId, boolean isEnabled) {
        this.hour = hour;
        this.minute = minute;
        this.songId = songId;
        this.isEnabled = isEnabled;
    }

    public static List<Alarm> getAllAlarms() {
        return new Select().from(Alarm.class).execute();
    }

    public static Alarm getAlarm(int hour, int minute, int songId) {
        return new Select()
                .from(Alarm.class)
                .where("hour = ?", hour)
                .and("minute = ?", minute)
                .and("songId = ?", songId)
                .executeSingle();
    }

    public static Alarm getCurrentAlarm(int hour, int minute) {
        return new Select()
                .from(Alarm.class)
                .where("hour = ?", hour)
                .and("minute = ?", minute)
                .executeSingle();
    }

    public int getHour() { return hour; }
    public int getMinute() { return minute; }
    public int getSongId() { return songId; }
    public boolean getIsEnabled() { return isEnabled; }
    public int getTimesCalled() { return timesCalled; }
    public String getLastUsed() { return lastUsed; }
    public float getTimePlayedCounter() { return timePlayedCounter; }

    public void setHour(int hour) { this.hour = hour; }
    public void setMinute(int minute) {this.minute = minute; }
    public void setSongId(int songId) {this.songId = songId; }
    public void setEnabled(boolean isEnabled) { this.isEnabled = isEnabled; }
    public void setTimesCalled(int timesCalled) { this.timesCalled = timesCalled; }
    public void setLastUsed(String lastUsed) { this.lastUsed = lastUsed; }
    public void setTimePlayedCounter(float timePlayedCounter) { this.timePlayedCounter = timePlayedCounter; }
}
