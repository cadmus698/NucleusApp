package com.cadmus698;

import java.time.LocalDate;

public class Task {
    
    String title;
    Chapter chapter;
    int priority;
    LocalDate dueDate;
    LocalDate toDoDate;
    int length;
    String description;

    public Task(String t, Chapter cha, int pri, LocalDate due, int len, String desc){
        title = t;
        chapter = cha;
        priority = pri;
        dueDate = due;
        toDoDate = dueDate;
        length = len;
        description = desc;
    }

    public void selfInit(){
        chapter.add(this);
    }

    public String toString(){
        return title;
    }

    public void initTask(){
        chapter.add(this);
    }
    
}
