package com.cadmus698;

import javax.swing.*;

import com.cadmus698.nucleusfx.GCalPanel;
import com.formdev.flatlaf.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class MainWindow extends JFrame{
    private JPanel mainPanel;
    private JList list1;
    private JButton addTasksButton;
    private JButton startSessionButton;
    private JPanel calendarPanel;
    private JPanel sessionPanel;
    private JPanel musicPanel;

    private Schedule schedule;
    private Journal journal;
    private ArrayList<Task> tasks;

    public MainWindow(){
        setContentPane(mainPanel);
        schedule = new Schedule();
        journal = new Journal();
        tasks = new ArrayList<>();
        journal.add(new Chapter("Red", new Color(255,0,0)));
        journal.add(new Chapter("Green", new Color(0,255,0)));
        journal.add(new Chapter("Blue", new Color(0,0,255)));
        addTasksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddTasksWindow.runWindow(schedule, journal);
            }
        });
        startSessionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(schedule.days.values());
                DefaultListModel<Day> listModel = new DefaultListModel<Day>();
                listModel.addAll(schedule.days.values());
                list1.setModel(listModel);
                list1.updateUI();
            }
        });

    }

    public static void main(String[] args) throws IOException {
        FlatDarkLaf.setup();
        MainWindow gui = new MainWindow();
        gui.setDefaultCloseOperation(EXIT_ON_CLOSE);
        gui.setTitle("Grade Management");
        gui.calendarPanel.add(new GCalPanel());
        gui.setVisible(true);
        gui.setExtendedState(JFrame.MAXIMIZED_BOTH);

    }

    public void tasksUpdate(){
        DefaultListModel<Task> listModel = new DefaultListModel<>();
        System.out.println(tasks);
        listModel.addAll(tasks);
        list1.setModel(listModel);
        list1.updateUI();
    }
}
