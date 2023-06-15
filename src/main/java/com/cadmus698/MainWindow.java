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
    private JPanel mainPanel;
    private JButton addTasksButton;
    private JButton startSessionButton;
    private JPanel calendarPanel;
    private JTabbedPane tabbedPane1;
    private JScrollPane sessionHistory;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private Journal journal;
    private ArrayList<Task> tasks;

    public MainWindow(){
        setContentPane(mainPanel);
        journal = new Journal();
        tasks = new ArrayList<>();
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

    public static void main(String[] args) throws IOException {
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
        //Refreshes tabs to have one up do date tab per day
        tabbedPane1.removeAll();
        for(Day d : journal.getSchedule().toDo.values()){
            tabbedPane1.addTab(d.date.toString(), new DaySchedule(d));
        }
        tabbedPane1.revalidate();
        tabbedPane1.repaint();
    }
}
