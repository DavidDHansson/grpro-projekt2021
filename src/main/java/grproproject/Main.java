package grproproject;

import grproproject.controllers.HomeController;
import grproproject.models.HomeModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/HomeView.fxml"));
            Scene scene = new Scene(loader.load(), 650, 600);

            HomeController controller = loader.getController();
            HomeModel model = new HomeModel();
            controller.initModel(model);

            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Oops... an error occurred. Please try again \n\"" + e.getLocalizedMessage() + "\"");
            alert.showAndWait().ifPresent(res -> System.exit(0));
        }
    }

    public static void main(String[] args) {
        launch();
    }
}