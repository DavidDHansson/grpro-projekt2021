package grproproject.services.router;

import grproproject.controllers.Controller;
import grproproject.controllers.HomeController;
import grproproject.controllers.MediaViewerController;
import grproproject.controllers.ProfilesController;
import grproproject.models.HomeModel;
import grproproject.models.MediaViewerModel;
import grproproject.models.Model;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class Router {

    private static Stage stage;
    private static String windowTitle = "";
    private static Double windowWidth = 800.0;
    private static Double windowHeight = 600.0;

    private Router() { }

    public static void setup(Stage primaryStage, String title, double width, double height) {
        stage = primaryStage;
        windowTitle = title;
        windowWidth = width;
        windowHeight = height;
    }

    public static void goTo(Routes route, Model model, Boolean sameWindow) {
        try {
            FXMLLoader loader = new FXMLLoader(new Object() { }.getClass().getResource(route.getPath()));
            Parent root = loader.load();
            Controller controller = loader.getController();

            switch (route) {
                case PROFILES:
                    ProfilesController pc = (ProfilesController) controller;
                    break;
                case HOME:
                    HomeController hc = (HomeController) controller;
                    hc.initModel((HomeModel) model);
                    break;
                case MEDIAVIEWER:
                    MediaViewerController mc = (MediaViewerController) controller;
                    mc.initModel((MediaViewerModel) model);
                    break;
            }

            Stage newStage = sameWindow ? stage : new Stage();
            newStage.setScene(new Scene(root, windowWidth, windowHeight));
            newStage.setTitle(windowTitle);
            newStage.show();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Oops... an error occurred. Please try again \n\"" + e.getLocalizedMessage() + "\"");
            alert.showAndWait().ifPresent(res -> System.exit(0));
        }
    }

}
