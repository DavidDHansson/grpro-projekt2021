package grproproject;

import grproproject.managers.mediaManager.MediaManager;
import grproproject.services.CustomAlert;
import grproproject.services.router.Router;
import grproproject.services.router.Routes;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        try {

            // Setup router
            Router.setup(stage, "Streaming service", 650, 600);

            // Route to Profiles
            Router.goTo(Routes.PROFILES, null, true);

            // Load media
            MediaManager.getInstance();

        } catch (Exception e) {
            CustomAlert.showError(e.getLocalizedMessage(), () -> System.exit(0));
        }
    }

    public static void main(String[] args) {
        launch();
    }

}