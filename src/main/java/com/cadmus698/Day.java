package com.cadmus698;

import java.time.LocalDate;
import java.util.ArrayList;

public class Day {
    LocalDate date;
    int minsAvailable = 240;
    ArrayList<Task> tasks;

    public Day(LocalDate d){
        date = d;
        tasks = new ArrayList<>();
    }

    public Day(LocalDate d, int mins){
        date = d;
        tasks = new ArrayList<>();
        minsAvailable = mins;
    }

    public void add(Task t){
        tasks.add(t);
    }

    public void setMinsAvailable(int mins){
        minsAvailable = mins;
    }

    @Override
    public String toString() {
        return date + "\n" + tasks;
    }
}
