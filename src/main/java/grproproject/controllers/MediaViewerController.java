package grproproject.controllers;

import grproproject.managers.mediaManager.MediaManager;
import grproproject.managers.mediaManager.Series;
import grproproject.models.MediaViewerModel;
import grproproject.services.CustomAlert;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.HashMap;

public class MediaViewerController implements Controller {

    private MediaViewerModel model;
    private boolean isFullscreen = false;

    @FXML
    private HBox seasonsHBox;

    @FXML
    private Label nowPlayingLabel;

    @FXML
    private Button fullscreenButton;

    public void initModel(MediaViewerModel model) {
        if (this.model != null) throw new IllegalStateException("Home model can only be initialized once");
        this.model = model;

        nowPlayingLabel.setText("Now playing: " + model.getMedia().getTitle());
        fillSeasonsBoxHBox();
    }

    @FXML
    public void initialize() {
    }

    @FXML
    public void fullscreenButtonClicked(ActionEvent event) {
        Stage stage = (Stage) nowPlayingLabel.getScene().getWindow();
        stage.setFullScreen(!isFullscreen);

        isFullscreen = !isFullscreen;
        fullscreenButton.setText(isFullscreen ? "Minimize screen" : "Maximize screen");
    }

    private void fillSeasonsBoxHBox() {
        if (model.getMedia().getType() == MediaManager.Type.MOVIE) return;
        HashMap seasons = ((Series) model.getMedia()).getSeasons();

        for (Object value : seasons.values()) {

            ChoiceBox choiceBox = new ChoiceBox();

            for (int i = 1; i <= (int) value; i++) {
                choiceBox.getItems().add(i);
                if (i == 1) choiceBox.setValue(i);
            }

            seasonsHBox.getChildren().add(choiceBox);
        }
        Button playButton = new Button("▶");
        seasonsHBox.getChildren().add(playButton);
    }

}
