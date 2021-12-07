package grproproject.controllers;

import grproproject.models.MediaViewerModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MediaViewerController implements Controller {

    private MediaViewerModel model;
    private boolean isFullscreen = false;

    @FXML
    private Label nowPlayingLabel;

    @FXML
    private Button fullscreenButton;

    public void initModel(MediaViewerModel model) {
        if (this.model != null) throw new IllegalStateException("Home model can only be initialized once");
        this.model = model;

        nowPlayingLabel.setText("Now playing: " + model.getMedia().getTitle());
    }

    @FXML
    public void initialize() { }

    @FXML
    public void fullscreenButtonClicked(ActionEvent event) {
        Stage stage = (Stage) nowPlayingLabel.getScene().getWindow();
        stage.setFullScreen(!isFullscreen);

        isFullscreen = !isFullscreen;
        fullscreenButton.setText(isFullscreen ? "Minimize screen" : "Maximize screen");
    }

}
