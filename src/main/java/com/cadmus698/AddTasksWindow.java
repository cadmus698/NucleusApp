package com.cadmus698;

import com.cadmus698.nucleusfx.GCalPanel;
import com.formdev.flatlaf.FlatDarkLaf;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class AddTasksWindow extends JFrame{
    private JButton addButton;
    private JTextField titleField;
    private JComboBox<Chapter> chapterPicker;
    private JSlider prioritySlider;
    private JSpinner lengthPicker;
    private JTextArea descBox;
    private DatePicker dueDatePicker;
    private JPanel mainPanel;

    public AddTasksWindow(Schedule schedule, Journal journal, MainWindow mainWindow){
        FlatDarkLaf.setup();
        setContentPane(mainPanel);
        SpinnerModel spinnerModel = new SpinnerNumberModel(30, 5, 180, 5);
        lengthPicker.setModel(spinnerModel);
        DefaultComboBoxModel<Chapter> comboBoxModel = new DefaultComboBoxModel<>(journal.chapters.toArray(new Chapter[0]));;
        chapterPicker.setModel(comboBoxModel);


        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
                Task t = new Task(titleField.getText(), (Chapter) chapterPicker.getSelectedItem(), prioritySlider.getValue(), dueDatePicker.getDate(), (int) lengthPicker.getValue(), descBox.getText());
                t.selfInit();
                schedule.add(t);
                mainWindow.updateList();
                System.out.println(t.title + " - " + t.chapter + " - " + t.priority + " - " + t.dueDate + " - " + t.length + " - " + t.description);
            }
        });
    }

    public static void runWindow(Schedule schedule, Journal journal, MainWindow mainWindow){
        AddTasksWindow gui = new AddTasksWindow(schedule, journal, mainWindow);
        gui.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        gui.setSize(500, 300);
        gui.setTitle("Add Task");
        gui.setVisible(true);
    }
    public static void generateCard() throws IOException {

    }
}
