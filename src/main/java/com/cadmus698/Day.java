package com.cadmus698;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;

public class Day implements Serializable {
    LocalDate date;
    int minsAvailable;
    ArrayList<Task> tasks;

    public Day(LocalDate d){
        date = d;
        tasks = new ArrayList<>();
        minsAvailable = 240;
    }

    public Day(LocalDate d, int mins){
        date = d;
        tasks = new ArrayList<>();
        minsAvailable = mins;
    }

    public void add(Task t){
        tasks.add(t);
        tasks.sort(new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                return o1.compareTo(o2);
            }
        });
    }

    public boolean tryAdd(Task t){
        add(t);
        return true;
    }

    public int getAvailableMins(){
        return minsAvailable;
    }

    public int getUsedMins(){
        int toRet = 0;
        for(Task t : tasks){
            toRet += t.length;
        }
        return toRet;
    }
    public void setMinsAvailable(int mins){
        minsAvailable = mins;
    }

    @Override
    public String toString() {
        return date + "\n" + tasks;
    }

    public boolean isFilled(float r){
        return ((float) getUsedMins())/((float) getAvailableMins()) >= r;
    }
}
