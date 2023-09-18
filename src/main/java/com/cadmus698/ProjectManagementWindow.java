package com.cadmus698;

import com.cadmus698.nucleusfx.GCalPanel;
import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ProjectManagementWindow extends JFrame{
    private JButton loadButton;
    private JButton newButton;
    private JPanel mainPanel;

    public ProjectManagementWindow() {
        setContentPane(mainPanel);
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc = new JFileChooser();
                jfc.showOpenDialog(null);
                Journal j = null;
                try {
                    // Reading the object from a file
                    FileInputStream file = new FileInputStream(jfc.getSelectedFile());
                    ObjectInputStream in = new ObjectInputStream(file);

                    // Method for deserialization of object
                    j = (Journal) in.readObject();

                    in.close();
                    file.close();
                } catch (Exception ex) {
                    System.out.println("Exception is caught");
                }
                if(j != null){
                    runMainWindow(j);
                }
                setVisible(false);
                dispose();
            }
        });
        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    runMainWindow();
                }
                catch (Exception ignored){}
                setVisible(false);
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        FlatDarkLaf.setup();
        ProjectManagementWindow gui = new ProjectManagementWindow();
        gui.setDefaultCloseOperation(EXIT_ON_CLOSE);
        gui.setTitle("Project Management");
        gui.setVisible(true);
        gui.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    public void runMainWindow() throws IOException {
        MainWindow.run();
    }
    public void runMainWindow(Journal journal){
        FlatDarkLaf.setup();
        MainWindow gui = new MainWindow(journal);
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
}
