package grproproject.controllers;

import grproproject.models.ProfilesModel;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;

public class ProfilesController {

    private ProfilesModel model;

    public void initModel(ProfilesModel model) {
        if (this.model != null) throw new IllegalStateException("Profiles model can only be initialized once");
        this.model = model;
    }

    @FXML
    private HBox mainHBox;
}
