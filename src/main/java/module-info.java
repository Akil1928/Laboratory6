module ucr.lab.laboratory6 {
    requires javafx.controls;
    requires javafx.fxml;


    opens ucr.lab.laboratory6 to javafx.fxml;
    exports ucr.lab.laboratory6;
}