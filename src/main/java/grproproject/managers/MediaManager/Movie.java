package grproproject.managers.mediaManager;

import java.util.ArrayList;

public class Movie extends Media {

    public Movie(MediaManager.Type type, String title, int releaseYear, String[] genres, Double rating) {
        super(type, title, releaseYear, genres, rating);
    }

}
