module projekt.grproprojekt {
    requires javafx.controls;
    requires javafx.fxml;


    opens grproproject to javafx.fxml;
    exports grproproject;
    exports grproproject.controllers;
    opens grproproject.controllers to javafx.fxml;
}