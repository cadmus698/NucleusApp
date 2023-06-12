package com.cadmus698;

import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCategoryWindow extends JFrame {
    private JPanel mainPanel;
    private JTextField titleField;
    private JSpinner rankPicker;
    private JButton addButton;
    private JColorChooser colorChooser;

    public AddCategoryWindow(Journal journal, SettingsManager settingsManager){
        FlatDarkLaf.setup();
        setContentPane(mainPanel);
        SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, 10, 1);
        rankPicker.setModel(spinnerModel);


        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
                Chapter c = new Chapter(titleField.getText(), colorChooser.getColor(), (int) rankPicker.getValue());
                journal.add(c);
                settingsManager.updateList();
            }
        });
    }

    public static void runWindow(Journal journal, SettingsManager settingsManager){
        AddCategoryWindow gui = new AddCategoryWindow(journal, settingsManager);
        gui.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        gui.setSize(500, 300);
        gui.setTitle("Add Category");
        gui.setVisible(true);
    }
}
