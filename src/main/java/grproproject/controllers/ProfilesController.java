package grproproject.controllers;

import grproproject.managers.mediaManager.MediaManager;
import grproproject.models.HomeModel;
import grproproject.models.ProfilesModel;
import grproproject.services.CustomAlert;
import grproproject.services.router.Router;
import grproproject.services.router.Routes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;

public class ProfilesController implements Controller {

    private ProfilesModel model;

    @FXML
    public void initialize() { }

    @FXML
    void buttonClicked(ActionEvent event) throws IOException {
        if (!MediaManager.getInstance().isDoneLoading) {
            CustomAlert.showInfo("The media is still loading");
            return;
        }

        Router.goTo(Routes.HOME, new HomeModel(), true);
    }

}
