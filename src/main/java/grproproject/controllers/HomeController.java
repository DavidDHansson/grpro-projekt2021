package grproproject.controllers;

import grproproject.models.HomeModel;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.net.URL;

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
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 100; j++) {
                Label titleLabel = new Label("Movie Title");
                Label yearLabel = new Label("1972");
                Label genreLabel = new Label("Crime, Drama");
                Label starsLabel = new Label("★: 9.2");

                URL imageURL = HomeController.class.getResource("/images/TheGodfather.jpg");
                Image image = new Image(String.valueOf(imageURL));
                ImageView posterImageView = new ImageView(image);

                VBox box = new VBox(titleLabel, yearLabel, genreLabel, starsLabel, posterImageView);
                box.setAlignment(Pos.CENTER);
                box.setPadding(new Insets(10, 10, 10, 10));

                mainGridPane.add(box, i, j);
            }
        }
    }


}