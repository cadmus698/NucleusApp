package com.cadmus698;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;

public class Schedule {
    public static int DEFAULTAVAILABILITY = 240;
    ArrayList<Task> tasks;
    TreeMap<LocalDate, Day> toDo;

    ArrayList<LocalDate> dates = new ArrayList<>();

    public Schedule(){
        tasks = new ArrayList<>();
        toDo = new TreeMap<LocalDate, Day>();
    }

    public void add(Task t) {
        tasks.add(t);
        if(!dates.contains(t.dueDate)){
            dates.add(t.dueDate);
            Collections.sort(dates);
            System.out.println("Added Date - " + t.dueDate + "\n New status: " + dates);
        }
        assignTasks();
    }


    public void assignTasks(){
        System.out.println("AssignTasks begins here");
        fillDays();
        ArrayList<Task> assigned = new ArrayList<>();
        //Go through assigning the tasks due on each due date one by one
        for(LocalDate nthMin : dates) {
            System.out.println("IN LOOP");
            //Compile all tasks due on or before this date
            ArrayList<Task> curTasks = beforeOrAtDate(nthMin);
            System.out.println("Tasks to assign: " + curTasks);
            //Determine the ratio of work minutes to available minutes
            float ratio = ((float)minsOfWork(curTasks)) / ((float)minsAvailable(nthMin));
            //Remove all already assigned tasks
            curTasks.removeIf(new Predicate<Task>() {
                @Override
                public boolean test(Task task) {
                    return assigned.contains(task);
                }
            });
            //Sort the remaining tasks in the order they will be done
            curTasks.sort(new Comparator<Task>() {
                @Override
                public int compare(Task o1, Task o2) {
                    return o1.compareTo(o2);
                }
            });
            //Fill the days from the left
            for (Day d : toDo.values()) {
                while (!d.isFilled(ratio)) {
                    if(curTasks.isEmpty()){
                        break;
                    }
                    if (d.tryAdd(curTasks.get(0))) {
                        assigned.add(curTasks.get(0));
                        curTasks.remove(0);
                    }
                }
                if(curTasks.isEmpty()){
                    break;
                }
            }
        }
    }

    private void fillDays() {
        LocalDate finalDate = dates.get(dates.size() - 1);
        for(LocalDate i = LocalDate.now(); i.isBefore(finalDate) || i.isEqual(finalDate); i = i.plusDays(1)){
            toDo.put(i, new Day(i));
        }
    }

    public boolean containsFromChapter(Chapter c){
        for(Task t : tasks){
            if(t.chapter == c){
                return true;
            }
        }
        return false;
    }


//    public LocalDate getNthMax(int n){
//        if(n > dates.size() || n < 1 || tasks.size() < 1){
//            return null;
//        }
//        ArrayList<LocalDate> acceptedDates = new ArrayList<>();
//
//        for(int i = 0; i < n; i++){
//            acceptedDates.add(tasks.get(0).dueDate);
//        }
//        for(Task t : tasks){
//            acceptedDates.add(t.dueDate);
//            trimArr(acceptedDates, n);
//
//        }
//        return acceptedDates.get(acceptedDates.size() - 1);
//    }
//    public void trimArr(ArrayList<LocalDate> arr, int size){
//        Collections.sort(arr);
//        if (arr.size() > size) {
//            arr.subList(size, arr.size()).clear();
//        }
//    }

    public ArrayList<Task> beforeOrAtDate(LocalDate date){
        ArrayList<Task> toRet = new ArrayList<>();
        for (Task t : tasks){
            if (t.dueDate.isBefore(date) || t.dueDate.isEqual(date)){
                toRet.add(t);
            }
        }
        return toRet;
    }

    public int minsOfWork(ArrayList<Task> tasksList){
        int toRet = 0;
        for (Task t : tasksList){
            toRet += t.length;
        }
        return toRet;
    }

    public int minsAvailable(LocalDate max){
        int toRet = 0;
        for (Day d : toDo.values()){
            if (d.date.isBefore(max) || d.date.isEqual(max)){
                toRet += d.getAvailableMins();
            }

        }
        return toRet;
    }
}
