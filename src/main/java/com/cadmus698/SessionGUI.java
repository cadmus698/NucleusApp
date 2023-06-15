package com.cadmus698;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class SessionGUI  extends JFrame{
    private JButton startButton;
    private JTextField dayField;
    private JTextField timeField;
    private JTextField taskName;
    private JScrollPane dayPanel;
    private JPanel mainPanel;

    private Day day;

    private Task currentTask;

    public SessionGUI(Day d){
        setContentPane(mainPanel);
        day = d;
        currentTask = day.tasks.get(0);
        day.tasks.remove(0);
        taskName.setText(currentTask.toString());
        dayField.setText(day.date.toString() + " - " + day.date.getDayOfWeek().toString());
        updateList();
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startTask();
            }
        });
    }

    public void updateList(){
        dayPanel.add(new DaySchedule(day));
        dayPanel.repaint();
        dayPanel.revalidate();
    }

    public void startTask(){
        Timer timer = new Timer();
        final int[] secsReq = {currentTask.length * 60};
        //Starting timer by counting down required seconds
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                timeField.setText(secsToTime(secsReq[0]));
                secsReq[0]--;
                if(secsReq[0] == 0){
                    currentTask = day.tasks.get(0);
                    day.tasks.remove(0);
                    if(day.tasks.size() > 0){
                        startTask();
                    }
                }
            }
        }, 500, 1000);
    }

    public static void runWindow(Day d){
        SessionGUI gui = new SessionGUI(d);
        gui.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        gui.setSize(1200, 600);
        gui.setTitle("Manage Settings");
        gui.setVisible(true);
    }

    public String secsToTime(int s){
        int hr = s/3600;
        int min = (s-(3600*hr))/60;
        int sec = s - (3600*hr) - (60*min);
        return (hr < 10 ? "0"+hr : hr) + ":" + (min < 10 ? "0"+min : min) + ":" + (sec < 10 ? "0"+sec : sec);
    }
}
