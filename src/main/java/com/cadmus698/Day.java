package com.cadmus698;

import java.time.LocalDate;
import java.util.ArrayList;

public class Day {
    LocalDate date;
    int minsAvailable;
    ArrayList<Task> tasks;

    public Day(LocalDate d){
        date = d;
        tasks = new ArrayList<>();
    }

    public void add(Task t){
        tasks.add(t);
    }

    @Override
    public String toString() {
        return date + " - " + tasks;
    }
}
