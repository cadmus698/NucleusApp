package com.cadmus698;

import javax.swing.*;

public class AddTasksWindow {
    private JButton addButton;
    private JTextArea textArea1;
    private JTextField textField1;
    private JComboBox comboBox1;
    private JSlider slider1;
    private JSpinner spinner1;

    public AddTasksWindow(){
        SpinnerModel model = new SpinnerNumberModel(30, 5, 180, 5);
        spinner1.setModel(model);
    }
}
