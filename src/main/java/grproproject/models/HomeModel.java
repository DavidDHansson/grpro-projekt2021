package grproproject.models;

import grproproject.managers.mediaManager.Media;
import grproproject.managers.mediaManager.MediaManager;
import grproproject.managers.usermanager.UserManager;

import java.util.ArrayList;
import java.util.List;

public class HomeModel implements Model {

    private List<Media> media;
    private List<Media> favoriteMedia;
    private List<String> favoriteMediaString;
    private String userName;
    private boolean isShowingFavorite = false;

    public HomeModel(String userName) {
        this.userName = userName;
        media = MediaManager.getInstance().getMedia();
        loadFavoriteMedia();
    }

    public List<Media> getMedia() {
        return isShowingFavorite ? favoriteMedia : media;
    }

    public String getUserName() { return userName; }

    public void addFavorite(Media media) {
        UserManager.getInstance().addFavoriteToActiveUser(media.getTitle());
        favoriteMedia.add(media);
        favoriteMediaString.add(media.getTitle());
    }

    public void removeFavorite(Media media) {
        UserManager.getInstance().removeFavoriteFromActiveUser(media.getTitle());
        favoriteMedia.removeIf(title -> title.equals(media));
        favoriteMediaString.removeIf(title -> title.equals(media.getTitle()));
    }

    public boolean isMediaFavorite(String mediaTitle) {
        return favoriteMediaString.contains(mediaTitle);
    }

    public void toggleFavoriteOnly() {
        isShowingFavorite = !isShowingFavorite;
    }

    private void loadFavoriteMedia() {
        favoriteMedia = new ArrayList<>();
        favoriteMediaString = UserManager.getInstance().getFavoritesFromActiveUser();

        for(Media m : getMedia()) {
            if (favoriteMediaString.contains(m.getTitle())) favoriteMedia.add(m);
        }
    }

}
