package grproproject.managers.mediaManager;

import grproproject.Constants;

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

    public MediaManager.Type getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public String[] getGenres() {
        return genres;
    }

    public double getRating() {
        return rating;
    }

    public String getImagePath() {
        switch (type) {
            case MOVIE:
                return Constants.movieImagePath + title + ".jpg";
            case SERIES:
                return Constants.seriesImagePath + title + ".jpg";
        }

        return "";
    }

    public String getReleaseYearString() {
        return Integer.toString(releaseYear);
    }

    public String getGenresString() {
        String result = "";
        for(int i = 0; i < genres.length; i++) {
            result += genres[i] + (genres.length - 1 == i ? "" : ", ");
        }

        return result;
    }

}
