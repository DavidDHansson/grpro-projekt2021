package grproproject.models;

import grproproject.managers.mediaManager.Media;
import grproproject.managers.mediaManager.MediaManager;

import java.util.List;

public class HomeModel implements Model {

    private List<Media> media;
    private String userName;

    public HomeModel(String userName) {
        this.userName = userName;
        media = MediaManager.getInstance().getMedia();
    }

    public List<Media> getMedia() {
        return media;
    }
}
