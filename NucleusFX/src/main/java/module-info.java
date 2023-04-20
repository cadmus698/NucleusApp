module com.cadmus698.nucleuspanels {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;
    requires javafx.web;


    opens com.cadmus698.nucleusfx to javafx.fxml;
    exports com.cadmus698.nucleusfx;
}