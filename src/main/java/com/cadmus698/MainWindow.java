package com.cadmus698;

import javax.swing.*;

import com.cadmus698.nucleusfx.GCalPanel;
import com.formdev.flatlaf.*;

import java.io.IOException;

public class MainWindow extends JFrame{
    private JPanel mainPanel;
    private JList list1;
    private JButton addTasksButton;
    private JButton startSessionButton;
    private JPanel calendarPanel;
    private JPanel sessionPanel;
    private JPanel musicPanel;

    public MainWindow(){
        setContentPane(mainPanel);
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
}
