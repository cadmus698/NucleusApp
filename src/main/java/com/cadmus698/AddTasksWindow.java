package com.cadmus698;

import com.formdev.flatlaf.FlatDarkLaf;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Date;

public class AddTasksWindow {
    private JButton addButton;
    private JTextField titleField;
    private JComboBox categoryPicker;
    private JSlider prioritySlider;
    private JSpinner lengthPicker;
    private JTextArea descBox;
    private DatePicker dueDatePicker;

    public AddTasksWindow(){
        FlatDarkLaf.setup();
        SpinnerModel model = new SpinnerNumberModel(30, 5, 180, 5);
        lengthPicker.setModel(model);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args) throws IOException{
        //generateCard();
    }
    public static void generateCard() throws IOException {

    }
}
