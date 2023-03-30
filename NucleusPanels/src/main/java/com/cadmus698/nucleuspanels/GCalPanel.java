package com.cadmus698.nucleuspanels;

import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class GCalPanel extends JFXPanel{
    public GCalPanel() throws IOException{
        init();
    }

    private void init() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("CalendarScene.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        setScene(scene);
    }
}
