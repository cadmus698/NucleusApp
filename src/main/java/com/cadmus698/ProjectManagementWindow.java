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

    public ProjectManagementWindow(){
        setContentPane(mainPanel);
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Journal j = new Journal();
                try {
                    JFileChooser jfc = new JFileChooser();
                    jfc.showOpenDialog(null);
                    // Reading the object from a file
                    FileInputStream file = new FileInputStream(jfc.getSelectedFile());
                    ObjectInputStream in = new ObjectInputStream(file);
                    // Method for deserialization of object
                    j = (Journal) in.readObject();

                    in.close();
                    file.close();
                }
                catch (Exception ex){
                    System.out.println(ex.toString());
                }
                runMainWindow(j);
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
        MainWindow.run(journal);
    }
}
