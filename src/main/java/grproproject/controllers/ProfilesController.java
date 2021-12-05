package grproproject.controllers;

import grproproject.models.HomeModel;
import grproproject.models.ProfilesModel;
import grproproject.services.router.Router;
import grproproject.services.router.Routes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ProfilesController implements Controller {

    private ProfilesModel model;

    @FXML
    public void initialize() {

    }

    @FXML
    void buttonClicked(ActionEvent event) {
        Router.goTo(Routes.HOME, new HomeModel(), true);
    }

}
