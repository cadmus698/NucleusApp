package com.cadmus698;

import java.time.LocalDate;
import java.util.*;

public class Schedule {
    TreeMap<LocalDate, Day> days;

    public Schedule(){
        days = new TreeMap<>();
    }

    public void add(Task t){
        if(!days.containsKey(t.dueDate)){
            days.put(t.dueDate, new Day(t.dueDate));
        }
        days.get(t.dueDate).add(t);
    }
}
