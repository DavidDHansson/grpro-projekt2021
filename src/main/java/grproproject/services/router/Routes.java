package grproproject.services.router;

import grproproject.Constants;

public enum Routes {
    PROFILES(Constants.viewPath + "ProfilesView.fxml"),
    HOME(Constants.viewPath + "HomeView.fxml"),
    MEDIAVIEWER(Constants.viewPath + "MediaViewerView.fxml");

    private String path;

    Routes(String path) {
        this.path = path;
    }

    public String getPath() { return this.path; }
}