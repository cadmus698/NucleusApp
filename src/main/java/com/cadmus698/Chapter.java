package com.cadmus698;

import java.awt.*;
import java.util.ArrayList;

public class Chapter {
    ArrayList<Task> tasks;
    String title;
    Color color;

    public Chapter(String t, Color c){
        tasks = new ArrayList<>();
        title = t;
        color = c;
    }

    public void add(Task t){
        tasks.add(t);
    }

    public String toString(){
        return title;
    }
}
