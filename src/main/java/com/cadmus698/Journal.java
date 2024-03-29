package com.cadmus698;

import java.io.Serializable;
import java.util.ArrayList;

public class Journal implements Serializable {
    public ArrayList<Chapter> chapters;

    private Schedule schedule;

    private String playlistURI;

    public Journal(){
        chapters = new ArrayList<>();
        schedule = new Schedule();
        playlistURI = "";
    }

    public void add(Chapter c){
        chapters.add(c);
    }

    public void removeChapter(Chapter c){
        chapters.remove(c);
    }

    public void setPlaylistURI(String s){
        playlistURI = s;
    }

    public String getPlaylistURI(){
        return playlistURI;
    }

    public void addTask(Task t){
        schedule.add(t);
    }

    public Schedule getSchedule(){
        return schedule;
    }
}
