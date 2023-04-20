package com.cadmus698;

import com.formdev.flatlaf.FlatDarkLaf;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AddTasksWindow {
    private JButton addButton;
    private JTextField titleField;
    private JComboBox<Chapter> chapterPicker;
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
                Task t = new Task(titleField.getText(), (Chapter) chapterPicker.getSelectedItem(), prioritySlider.getValue(), dueDatePicker.getDate(), (int) lengthPicker.getValue(), descBox.getText());
                t.chapter.add(t);
                System.out.println(t.title + " - " + t.chapter + " - " + t.priority + " - " + t.dueDate + " - " + t.length + " - " + t.description);
            }
        });
    }
    public static void generateCard() throws IOException {

    }
}
