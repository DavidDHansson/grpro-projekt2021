package grproproject.controllers;

import grproproject.models.HomeModel;
import grproproject.managers.mediaManager.*;
import grproproject.models.MediaViewerModel;
import grproproject.services.CustomAlert;
import grproproject.services.router.Router;
import grproproject.services.router.Routes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class HomeController implements Controller {

    private HomeModel model;

    @FXML
    private GridPane mainGridPane;

    @FXML
    private Label activeUserLabel;

    @FXML
    public void initialize() { }

    @FXML
    void changeUserAction(ActionEvent event) {
        Router.goTo(Routes.PROFILES, null, false);
    }

    public void initModel(HomeModel model) {
        if (this.model != null) throw new IllegalStateException("Home model can only be initialized once");
        this.model = model;
        activeUserLabel.setText(model.getUserName());
        fillGridPane();
    }

    private void fillGridPane() {
        List<Media> media = model.getMedia();

        int index = 0;
        for (Media m : media) {
            Label titleLabel = new Label(m.getTitle());
            Label yearLabel = new Label(m.getReleaseYearString());
            Label genreLabel = new Label(m.getGenresString());
            Label starsLabel = new Label("★: " + m.getRating());

            URL imageURL = HomeController.class.getResource(m.getImagePath());
            Image image = new Image(String.valueOf(imageURL));
            ImageView posterImageView = new ImageView(image);

            VBox box = new VBox(titleLabel, yearLabel, genreLabel, starsLabel, posterImageView);
            box.setAlignment(Pos.CENTER);
            box.setPadding(new Insets(10, 10, 10, 10));

            mainGridPane.add(box, index % 3, Math.floorDiv(index, 3));
            index++;
        }
    }

}