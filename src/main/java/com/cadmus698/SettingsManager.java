package com.cadmus698;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SettingsManager extends JFrame{
    private JSpinner availabilitySet;
    private JButton addChap;
    private JButton removeChap;
    private JList list1;
    private JPanel mainPanel;
    private JTextField playlistSelector;
    private JButton editButton;

    private Journal journal;

    public SettingsManager(Journal j){
        journal = j;
        setContentPane(mainPanel);
        updateList();
        //Setup spinner constraints.
        SpinnerModel spinnerModel = new SpinnerNumberModel(240, 30, 720, 30);
        availabilitySet.setModel(spinnerModel);

        addChap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCategory();
            }
        });

        removeChap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Chapter c = (Chapter) list1.getSelectedValue();
                if(journal.getSchedule().containsFromChapter(c)){
                    JOptionPane.showMessageDialog(null,"You cannot remove this Chapter, as it contains tasks.");
                    return;
                }
                journal.removeChapter(c);
                updateList();
            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        availabilitySet.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {;
                Schedule.DEFAULTAVAILABILITY = (int) availabilitySet.getValue();
            }
        });

        playlistSelector.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                journal.setPlaylistURI(playlistSelector.getText());
            }
        });
    }

    public void addCategory(){
        AddCategoryWindow.runWindow(journal, this);
    }

    public void updateList(){
        DefaultListModel<Chapter> listModel = new DefaultListModel<>();
        listModel.addAll(journal.chapters);
        list1.setModel(listModel);
    }

    public static void runWindow(Journal j){
        SettingsManager gui = new SettingsManager(j);
        gui.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        gui.setSize(1200, 600);
        gui.setTitle("Manage Settings");
        gui.setVisible(true);
    }
}
