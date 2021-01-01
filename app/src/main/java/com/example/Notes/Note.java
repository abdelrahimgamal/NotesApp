package com.example.Notes;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "note_table")
public class Note {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String describtion,title;
    private int priority;
    public String date;

    public Note(String describtion, String title, int priority,String date) {
        this.describtion = describtion;
        this.title = title;
        this.priority = priority;
        this.date=date;
    }

    public Note() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public String getDescribtion() {
        return describtion;
    }

    public void setDescribtion(String describtion) {
        this.describtion = describtion;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
