package com.cadmus698;

import java.awt.*;
import java.util.ArrayList;

public class Chapter implements Comparable<Chapter> {
    ArrayList<Task> tasks;
    String title;
    Color color;
    int rank;

    public Chapter(String t, Color c, int r){
        tasks = new ArrayList<>();
        title = t;
        color = c;
        rank = r;
    }

    public void add(Task t){
        tasks.add(t);
    }

    public String toString(){
        return title;
    }

    public int compareTo(Chapter c){
        if(rank < c.rank){
            return 1;
        }
        else if(rank > c.rank){
            return -1;
        }
        return 0;
    }
}
