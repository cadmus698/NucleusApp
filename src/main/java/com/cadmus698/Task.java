package com.cadmus698;

import java.time.LocalDate;

public class Task implements Comparable<Task> {
    
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

    public void selfInit(){
        chapter.add(this);
    }

    public String toString(){
        return title + " - " + length + "min - " + "due " + dueDate;
    }

    public void initTask(){
        chapter.add(this);
    }

    public int compareTo(Task other){
        //Task sorting system, priority > length > due date > chapter (no tiebreaker needed)
        if(priority > other.priority){
            return 1;
        }
        else if(priority == other.priority){
            if(length > other.length){
                return 1;
            }
            else if(length == other.length){
                if(dueDate.isBefore(other.dueDate)){
                    return 1;
                }
                else if(dueDate.isEqual(other.dueDate)){
                    return chapter.compareTo(other.chapter);
                }
                else{
                    return -1;
                }
            }
            else{
                return -1;
            }
        }
        else{
            return -1;
        }

    }
    
}
