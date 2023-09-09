package com.cadmus698;

import javax.swing.*;

import com.cadmus698.nucleusfx.GCalPanel;
import com.formdev.flatlaf.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class MainWindow extends JFrame{
    public JPanel mainPanel;
    public JButton addTasksButton;
    public JButton startSessionButton;
    public JPanel calendarPanel;
    public JTabbedPane tabbedPane1;
    public JScrollPane sessionHistory;
    public JMenuBar menuBar;
    public JMenu fileMenu;
    public Journal journal;

    public MainWindow(Journal j){
        setContentPane(mainPanel);
        journal = j;
        addTasksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTask();
            }
        });
        startSessionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SessionGUI.runWindow(journal.getSchedule().toDo.get(LocalDate.now()));
            }
        });
    }
    public MainWindow(){
        setContentPane(mainPanel);
        journal = new Journal();
        journal.add(new Chapter("Default", new Color(255,255,255), 1));
        addTasksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTask();
            }
        });
        startSessionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SessionGUI.runWindow(journal.getSchedule().toDo.get(LocalDate.now()));
            }
        });

    }

    public static void run() throws IOException {
        FlatDarkLaf.setup();
        MainWindow gui = new MainWindow();
        //Setting up menu
        JMenuItem setDates = new JMenuItem("Set Date Availability");
        setDates.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DateAvailabilityManager.runWindow(gui.journal.getSchedule());
            }
        });
        JMenuItem settings = new JMenuItem("Settings");
        settings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SettingsManager.runWindow(gui.journal);
            }
        });
        JMenuItem save = new JMenuItem("Save");
        gui.fileMenu = new JMenu();
        gui.fileMenu.setText("File");
        gui.menuBar = new JMenuBar();
        gui.fileMenu.add(setDates);
        gui.fileMenu.add(settings);
        gui.menuBar.add(gui.fileMenu);
        gui.setJMenuBar(gui.menuBar);
        gui.setDefaultCloseOperation(EXIT_ON_CLOSE);
        gui.setTitle("Work Management");
        gui.calendarPanel.add(new GCalPanel());
        gui.setVisible(true);
        gui.setExtendedState(JFrame.MAXIMIZED_BOTH);

    }

    public void addTask(){
        //Runs task addition window
        AddTasksWindow.runWindow(journal, this);
    }

    public void updateList(){
        //Refreshes tabs to have one up-to-date tab per day
        tabbedPane1.removeAll();
        for(Day d : journal.getSchedule().toDo.values()){
            tabbedPane1.addTab(d.date.toString(), new DaySchedule(d));
        }
        tabbedPane1.revalidate();
        tabbedPane1.repaint();
    }
}
