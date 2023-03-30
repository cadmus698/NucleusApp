module com.cadmus698.nucleuspanels {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;


    opens com.cadmus698.nucleuspanels to javafx.fxml;
    exports com.cadmus698.nucleuspanels;
}