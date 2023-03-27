package com.cadmus698;

import javax.swing.*;

import com.formdev.flatlaf.*;
public class MainWindow extends JFrame{
    private JPanel panel1;
    private JList list1;
    private JButton addTasksButton;
    private JButton startSessionButton;
    private JPanel calendarPanel;
    private JPanel sessionPanel;
    private JPanel musicPanel;

    public MainWindow(){
        setContentPane(panel1);
    }

    public static void main(String[] args) {
        FlatDarkLaf.setup();
        MainWindow gui = new MainWindow();
        gui.setDefaultCloseOperation(EXIT_ON_CLOSE);
        gui.setTitle("Grade Management");
        gui.setVisible(true);
        gui.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
}
