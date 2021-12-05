package grproproject;

import grproproject.controllers.HomeController;
import grproproject.models.HomeModel;
import grproproject.models.ProfilesModel;
import grproproject.services.router.Router;
import grproproject.services.router.Routes;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        try {
            Router.setup(stage, "Title", 650, 600);
            Router.goTo(Routes.PROFILES, null, true);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Oops... an error occurred. Please try again \n\"" + e.getLocalizedMessage() + "\"");
            alert.showAndWait().ifPresent(res -> System.exit(0));
        }
    }

    public static void main(String[] args) {
        launch();
    }

}