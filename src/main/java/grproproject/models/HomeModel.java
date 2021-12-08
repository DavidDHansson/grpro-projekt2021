package grproproject.models;

import grproproject.managers.mediaManager.Media;
import grproproject.managers.mediaManager.MediaManager;
import grproproject.managers.usermanager.User;
import grproproject.managers.usermanager.UserManager;

import java.util.ArrayList;
import java.util.List;

public class HomeModel implements Model {

    private List<Media> media;
    private List<Media> favoriteMedia;
    private List<String> favoriteMediaString;
    private String userName;

    public HomeModel(String userName) {
        this.userName = userName;
        media = MediaManager.getInstance().getMedia();
        favoriteMediaString = UserManager.getInstance().getFavoritesFromActiveUser();
    }

    public List<Media> getMedia() {
        return media;
    }

    public List<Media> getFavoriteMedia() { return favoriteMedia; }

    public List<String> getFavoriteMediaString() { return favoriteMediaString; }

    public String getUserName() { return userName; }

    public void addFavorite(String mediaTitle) {
        UserManager.getInstance().addFavoriteToActiveUser(mediaTitle);
        loadFavoriteMedia();
    }

    public void removeFavorite(String mediaTitle) {
        UserManager.getInstance().removeFavoriteFromActiveUser(mediaTitle);
        loadFavoriteMedia();
    }

    private void loadFavoriteMedia() {
        favoriteMedia = new ArrayList<>();
        favoriteMediaString = UserManager.getInstance().getFavoritesFromActiveUser();

        for(Media m : getMedia()) {
            if (favoriteMediaString.contains(m.getTitle())) favoriteMedia.add(m);
        }
    }

}
