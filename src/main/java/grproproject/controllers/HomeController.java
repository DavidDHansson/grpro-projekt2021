package grproproject.controllers;

import grproproject.models.HomeModel;
import grproproject.managers.mediaManager.*;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import java.net.URL;
import java.util.List;

public class HomeController {

    private HomeModel model;

    @FXML
    private GridPane mainGridPane;

    public void initModel(HomeModel model) {
        if (this.model != null) throw new IllegalStateException("Home model can only be initialized once");
        this.model = model;
    }

    @FXML
    public void initialize() {
        fillGridPane();
    }

    private void fillGridPane() {

        List<Media> media = MediaManager.getInstance().getMedia();

        int index = 0;
        for(Media m : media) {
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