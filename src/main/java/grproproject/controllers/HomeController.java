package grproproject.controllers;

import grproproject.models.HomeModel;
import grproproject.managers.mediaManager.*;
import grproproject.models.MediaViewerModel;
import grproproject.services.CustomAlert;
import grproproject.services.router.Router;
import grproproject.services.router.Routes;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.List;

public class HomeController implements Controller {

    private HomeModel model;

    @FXML
    private GridPane mainGridPane;

    @FXML
    private Label activeUserLabel;

    @FXML
    private ChoiceBox<String> genreChoiceBox;

    @FXML
    private TextField searchTextField;

    @FXML
    public void initialize() { }

    public void initModel(HomeModel model) {
        if (this.model != null) throw new IllegalStateException("Home model can only be initialized once");
        this.model = model;
        activeUserLabel.setText(model.getUserName());
        updateGridPane();
        setupGenreChoiceBox();
    }

    private void updateGridPane() {

        mainGridPane.getChildren().clear();

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

            Button addToListButton = new Button(model.isMediaFavorite(m.getTitle()) ? "Remove from favorites" : "Add to favorites");
            addToListButton.setOnMouseClicked(e ->  {
                boolean isFavorite = model.isMediaFavorite(m.getTitle());

                addToListButton.setText(!isFavorite ? "Remove from favorites" : "Add to favorites");
                if(isFavorite) model.removeFavorite(m);
                if (!isFavorite) model.addFavorite(m);
            });

            VBox box = new VBox(titleLabel, yearLabel, genreLabel, starsLabel, posterImageView, addToListButton);
            box.setAlignment(Pos.CENTER);
            box.setBorder(new Border(new BorderStroke(Color.BLACK,  BorderStrokeStyle.SOLID, new CornerRadii(20), BorderWidths.DEFAULT)));
            box.setPadding(new Insets(10, 10, 10, 10));
            box.setCursor(Cursor.HAND);
            box.setOnMouseClicked(e ->
                    Router.goTo(Routes.MEDIAVIEWER, new MediaViewerModel(m), false, 600, 350));

            mainGridPane.add(box, index % 4, Math.floorDiv(index, 4));
            mainGridPane.setHgap(10);
            mainGridPane.setVgap(10);
            index++;
        }
    }

    private void setupGenreChoiceBox() {
        genreChoiceBox.getItems().add("Show all");
        for(String genre : model.getGenres()) {
            genreChoiceBox.getItems().add(genre);
        }

        genreChoiceBox
                .getSelectionModel()
                .selectedIndexProperty()
                .addListener((observableValue, number, t1) -> {
                    try {
                        model.setActiveGenre(t1.intValue());
                        updateGridPane();
                    } catch(Exception e) {
                        CustomAlert.showError(e.getLocalizedMessage());
                    }
                });

        genreChoiceBox.setValue("Show all");
    }

    @FXML
    void changeUserAction() {
        Router.goTo(Routes.PROFILES, null, true);
    }

    @FXML
    void toggleFavorite() {
        model.toggleFavoriteOnly();
        updateGridPane();
    }

    @FXML
    void searchTextFieldTextChanged(KeyEvent event) {
        model.searchWithQuery(searchTextField.getText());
        updateGridPane();
    }

}