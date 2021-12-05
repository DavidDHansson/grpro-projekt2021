package grproproject.services;

import javafx.scene.control.Alert;
import javafx.stage.Window;

public class CustomAlert {
    public static void showError(String title, Runnable completion) {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Oops... an error occurred. Please try again. \n\"" + title + "\"");

        Window window = alert.getDialogPane().getScene().getWindow();
        window.setOnCloseRequest(e -> completion.run());

        alert.showAndWait().ifPresent(res -> completion.run());
    }

    public static void showInfo(String title, Runnable completion) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, title);

        Window window = alert.getDialogPane().getScene().getWindow();
        window.setOnCloseRequest(e -> completion.run());

        alert.showAndWait().ifPresent(res -> completion.run());
    }

    public static void showInfo(String title) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, title);

        Window window = alert.getDialogPane().getScene().getWindow();
        window.setOnCloseRequest(e -> alert.hide());
        alert.showAndWait();
    }
}
