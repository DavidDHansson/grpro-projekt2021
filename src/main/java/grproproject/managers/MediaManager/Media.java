package grproproject.managers.MediaManager;

import java.util.ArrayList;

public abstract class Media {

    protected MediaManager.Type type;
    protected String title;
    protected int releaseYear;
    protected String[] genres;
    protected double rating;

    public Media(MediaManager.Type type, String title, int releaseYear, String[] genres, double rating) {
        this.type = type;
        this.title = title;
        this.releaseYear = releaseYear;
        this.genres = genres;
        this.rating = rating;
    }
}
