package grproproject;

import grproproject.services.CustomAlert;
import grproproject.services.router.Router;
import grproproject.services.router.Routes;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        try {
            Router.setup(stage, "Title", 650, 600);
            Router.goTo(Routes.PROFILES, null, true);
        } catch (Exception e) {
            CustomAlert.showError(e.getLocalizedMessage(), () -> System.exit(0));
        }
    }

    public static void main(String[] args) {
        launch();
    }

}