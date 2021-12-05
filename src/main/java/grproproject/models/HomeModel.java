package grproproject.models;

import grproproject.managers.mediaManager.Media;
import grproproject.managers.mediaManager.MediaManager;
import java.util.List;

public class HomeModel implements Model {

    private List<Media> media;

    public HomeModel() {
        // TODO: Insert information from profilesController about user e.g. if it is a family user, name picture and so on
        media = MediaManager.getInstance().getMedia();
    }

    public List<Media> getMedia() {
        return media;
    }
}
