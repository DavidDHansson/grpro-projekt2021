package grproproject;

import grproproject.managers.mediaManager.MediaManager;
import grproproject.managers.usermanager.User;
import grproproject.managers.usermanager.UserManager;
import grproproject.services.CustomAlert;
import grproproject.services.router.Router;
import grproproject.services.router.Routes;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        try {

            // Setup router
            Router.setup(stage, "Title", 650, 600);

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