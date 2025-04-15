package com.example.finale_project_2025;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "notes")
public class Note {
    @PrimaryKey(autoGenerate = true)
    private int id;


    private String username;
    private Integer percent;
    private String sentence;


    public Note(String username, Integer percent, String sentence) {
        this.username = username;
        this.percent = percent;
        this.sentence = sentence;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Integer getPercent() {
        return percent;
    }

    public String getSentence() {
        return sentence;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPercent(Integer percent) {
        this.percent = percent;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }
}



