package grproproject;

import grproproject.controllers.HomeController;
import grproproject.models.HomeModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/HomeView.fxml"));
        Scene scene = new Scene(loader.load(), 650, 600);

        HomeController controller = loader.getController();
        HomeModel model = new HomeModel();
        controller.initModel(model);

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}