package grproproject.controllers;

import grproproject.models.MediaViewerModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MediaViewerController {

    private MediaViewerModel model;

    public void initModel(MediaViewerModel model) {
        if (this.model != null) throw new IllegalStateException("Home model can only be initialized once");
        this.model = model;
    }

    @FXML
    private Label nowPlayingLabel;
}
