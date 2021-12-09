package grproproject.models;

import grproproject.managers.mediaManager.Media;
import grproproject.managers.mediaManager.MediaManager;
import grproproject.managers.usermanager.UserManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomeModel implements Model {

    private List<Media> media;
    private List<Media> favoriteMedia;
    private List<Media> filteredMedia;
    private List<Media> filteredFavoriteMedia;
    private List<String> favoriteMediaString;

    private final String usersName;

    private boolean isShowingFavorite = false;

    public HomeModel(String usersName) {
        this.usersName = usersName;
        setMedia();
        setFavoriteMedia();
    }

    public List<Media> getMedia() {
        return isShowingFavorite ? filteredFavoriteMedia : filteredMedia;
    }

    public String getUserName() {
        return usersName;
    }

    public List<String> getGenres() {
        return MediaManager.getInstance().getGenresSorted();
    }

    public void addFavorite(Media media) {
        UserManager.getInstance().addFavoriteToActiveUser(media.getTitle());
        favoriteMedia.add(0, media);
        filteredFavoriteMedia.add(0, media);
        favoriteMediaString.add(media.getTitle());
    }

    public void removeFavorite(Media media) {
        UserManager.getInstance().removeFavoriteFromActiveUser(media.getTitle());
        favoriteMedia.removeIf(title -> title.getTitle().equals(media.getTitle()));
        favoriteMediaString.removeIf(title -> title.equals(media.getTitle()));
        filteredFavoriteMedia.removeIf(title -> title.getTitle().equals(media.getTitle()));
    }

    public boolean isMediaFavorite(String mediaTitle) {
        return favoriteMediaString.contains(mediaTitle);
    }

    public void toggleFavoriteOnly() {
        isShowingFavorite = !isShowingFavorite;
    }

    public void setActiveGenre(int index) {

        // "Show all" is the genre, so reset the filters
        if (index <= 0) {
            filteredMedia = new ArrayList<>(media);
            filteredFavoriteMedia = new ArrayList<>(favoriteMedia);
            return;
        }

        String genre = getGenres().get(index -1);
        filteredMedia = new ArrayList<>();
        filteredFavoriteMedia = new ArrayList<>();

        // Filter media
        for(Media m : media) {
            for(String g : m.getGenres()) {
                if(g.contains(genre)) filteredMedia.add(m);
            }
        }

        // Filter favorite media
        for(Media m : favoriteMedia) {
            for(String g : m.getGenres()) {
                if(g.contains(genre)) filteredFavoriteMedia.add(m);
            }
        }
    }

    private void setMedia() {
        media = new ArrayList<>(MediaManager.getInstance().getShuffledMedia());
        filteredMedia = new ArrayList<>(media);
    }

    private void setFavoriteMedia() {
        favoriteMedia = new ArrayList<>();
        favoriteMediaString = new ArrayList<>(UserManager.getInstance().getFavoritesFromActiveUser());

        for(Media m : media) {
            if (favoriteMediaString.contains(m.getTitle())) favoriteMedia.add(m);
        }

        filteredFavoriteMedia = new ArrayList<>(favoriteMedia);

        Collections.reverse(favoriteMedia);
        Collections.reverse(filteredFavoriteMedia);
    }

}
