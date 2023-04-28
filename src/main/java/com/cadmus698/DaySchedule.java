package com.cadmus698;

import javax.swing.*;
import java.time.LocalDate;

public class DaySchedule extends JPanel{
    private JList list1;
    private JLabel dayTitle;

    public DaySchedule(Day day){
        dayTitle.setText(day.date.toString());
        DefaultListModel<Task> listModel = new DefaultListModel<Task>();
        listModel.addAll(day.tasks);
        list1.setModel(listModel);
        list1.updateUI();
    }
}
