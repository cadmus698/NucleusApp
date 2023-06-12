package com.cadmus698;

import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.awt.*;

public class DaySchedule extends JPanel {
    private JLabel dayTitle;

    public DaySchedule(Day day) {
        setLayout(new BorderLayout());

        dayTitle = new JLabel(day.date.getDayOfWeek().toString());
        add(dayTitle, BorderLayout.NORTH);

        DefaultListModel<Task> listModel = new DefaultListModel<>();
        listModel.addAll(day.tasks);
        JList<Task> taskList = new JList<>(listModel);
        taskList.setCellRenderer(new TaskListRenderer());
        add(new JScrollPane(taskList), BorderLayout.CENTER);
    }
}

class TaskListRenderer extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel taskLabel = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        Task task = (Task) value;
        Color color = task.chapter.color;
        taskLabel.setBackground(color);
        taskLabel.setForeground((299 * color.getRed() + 587 * color.getGreen() + 114 * color.getBlue()) / 1000 >= 128 ? Color.black : Color.white);
        return taskLabel;
    }
}