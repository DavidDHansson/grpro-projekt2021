package grproproject.controllers;

import grproproject.managers.mediaManager.MediaManager;
import grproproject.managers.usermanager.User;
import grproproject.managers.usermanager.UserManager;
import grproproject.models.HomeModel;
import grproproject.models.ProfilesModel;
import grproproject.services.CustomAlert;
import grproproject.services.router.Router;
import grproproject.services.router.Routes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.IOException;
import java.util.Optional;

public class ProfilesController implements Controller {

    private ProfilesModel model;

    @FXML
    private HBox profilesHBox;

    @FXML
    public void initialize() {
        model = new ProfilesModel();
        fillProfilesHBox();
    }

    private void refreshProfilesHBox() {
        profilesHBox.getChildren().clear();
        fillProfilesHBox();
    }

    private void fillProfilesHBox() {

        // Users
        for(User u : UserManager.getInstance().getUsers()) {
            Label nameLabel = new Label(u.getName());
            nameLabel.setFont(new Font(18));

            Button editUserButton = new Button("Edit name");
            editUserButton.setOnMouseClicked(e -> showEditUserPrompt(u));

            Button deleteButton = new Button("Delete user");
            deleteButton.setOnMouseClicked(e -> {
                model.deleteUser(u.getId());
                refreshProfilesHBox();
            });

            VBox box = createBox(nameLabel, editUserButton, deleteButton);
            box.setCursor(Cursor.HAND);
            box.setOnMouseClicked(e -> model.setUser(u));

            profilesHBox.getChildren().add(box);
        }

        // New user
        TextField nameTextField = new TextField();
        nameTextField.setPromptText("Your name?");
        Button addUserButton = new Button("Add");
        addUserButton.setOnMouseClicked(e -> {
            model.addUser(nameTextField.getText());
            refreshProfilesHBox();
        });
        VBox box = createBox(nameTextField, addUserButton);

        profilesHBox.getChildren().add(box);
        profilesHBox.setSpacing(10);
    }

    private VBox createBox(Node... children) {
        VBox box = new VBox(children);
        box.setAlignment(Pos.CENTER);
        box.setSpacing(5);
        box.setPadding(new Insets(10, 10, 10, 10));
        box.setBorder(new Border(new BorderStroke(Color.BLACK,  BorderStrokeStyle.SOLID, new CornerRadii(20), BorderWidths.DEFAULT)));

        return box;
    }

    private void showEditUserPrompt(User user) {
        TextInputDialog dialog = new TextInputDialog(user.getName());
        dialog.setTitle("Change name");
        dialog.setHeaderText("Change name for: " + user.getName());
        dialog.setContentText("Please enter new name:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(name -> {
            model.editUser(user.getId(), name);
            refreshProfilesHBox();
        });
    }

}
