package grproproject.controllers;

import grproproject.models.HomeModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HomeController {

    private HomeModel model;

    public void initModel(HomeModel model) {
        if (this.model != null) throw new IllegalStateException("Model can only be initialized once");
        this.model = model;
    }

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Hello!");
    }
}