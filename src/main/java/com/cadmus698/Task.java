package com.cadmus698;

import java.time.LocalDate;

public class Task {
    
    String title;
    Chapter chapter;
    int priority;
    LocalDate dueDate;
    int length;
    String description;

    public Task(String t, Chapter cha, int pri, LocalDate due, int len, String desc){
        title = t;
        chapter = cha;
        priority = pri;
        dueDate = due;
        length = len;
        description = desc;
    }
    
}
