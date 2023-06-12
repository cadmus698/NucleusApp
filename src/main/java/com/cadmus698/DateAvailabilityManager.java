package com.cadmus698;

import com.formdev.flatlaf.FlatDarkLaf;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.optionalusertools.DateChangeListener;
import com.github.lgooddatepicker.zinternaltools.DateChangeEvent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class DateAvailabilityManager extends JFrame{
    private JSpinner spinner1;
    private DatePicker datePicker;
    private JButton setButton;
    private JPanel mainPanel;
    private Schedule schedule;

    private LocalDate pickedDate;
    public DateAvailabilityManager(Schedule s){
        schedule = s;
        FlatDarkLaf.setup();
        setContentPane(mainPanel);
        datePicker.addDateChangeListener(new DateChangeListener() {
            @Override
            public void dateChanged(DateChangeEvent dateChangeEvent) {
                pickedDate = dateChangeEvent.getNewDate();
                if(s.toDo.containsKey(pickedDate)){
                    spinner1.setValue(schedule.toDo.get(pickedDate).minsAvailable);
                }
                else{
                    spinner1.setValue(Schedule.DEFAULTAVAILABILITY);
                }

            }
        });

        setButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int mins = (int) spinner1.getValue();
                if(!s.toDo.containsKey(pickedDate)){
                    s.toDo.put(pickedDate, new Day(pickedDate, mins));
                }
                s.toDo.get(pickedDate).setMinsAvailable(mins);
            }
        });
    }

    public static void runWindow(Schedule schedule) {
        DateAvailabilityManager gui = new DateAvailabilityManager(schedule);
        gui.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        gui.setSize(500, 300);
        gui.setTitle("Manage Availability");
        gui.setVisible(true);
    }
}
