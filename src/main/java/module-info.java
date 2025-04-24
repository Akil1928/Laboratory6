module ucr.lab.laboratory6 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;


    opens ucr.lab.laboratory6 to javafx.fxml;
    exports ucr.lab.laboratory6;
    exports controller;
    opens controller to javafx.fxml;
}