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
    private JMenuBar menuBar;
    private JMenu fileMenu;

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
                addTask();
            }
        });
        startSessionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateList();
            }
        });

    }

    public static void main(String[] args) throws IOException {
        FlatDarkLaf.setup();
        MainWindow gui = new MainWindow();
        JMenuItem setDates = new JMenuItem("Set Date Availability");
        setDates.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.addTask();
            }
        });
        gui.fileMenu = new JMenu();
        gui.fileMenu.setText("File");
        gui.menuBar = new JMenuBar();
        gui.fileMenu.add(setDates);
        gui.menuBar.add(gui.fileMenu);
        gui.setJMenuBar(gui.menuBar);
        gui.setDefaultCloseOperation(EXIT_ON_CLOSE);
        gui.setTitle("Grade Management");
        gui.calendarPanel.add(new GCalPanel());
        gui.setVisible(true);
        gui.setExtendedState(JFrame.MAXIMIZED_BOTH);

    }

    public void addTask(){
        AddTasksWindow.runWindow(schedule, journal, this);
    }

    public void updateList(){
        //Adds the schedule to a listmodel and applies it to the list.
        DefaultListModel<Day> listModel = new DefaultListModel<Day>();
        listModel.addAll(schedule.days.values());
        list1.setModel(listModel);
        list1.updateUI();
    }
}
