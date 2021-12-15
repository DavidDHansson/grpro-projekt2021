package grproproject.managers.mediaManager;

import java.util.HashMap;

public class Series extends Media {

    private final int endYear;
    private final HashMap<Integer, Integer> seasons;

    public Series(MediaManager.Type type, String title, Integer releaseYear, String[] genres, double rating, int endYear, HashMap<Integer, Integer> seasons) {
        super(type, title, releaseYear, genres, rating);
        this.endYear = endYear;
        this.seasons = seasons;
    }

    public int getEndYear() {
        return endYear;
    }

    public HashMap<Integer, Integer> getSeasons() {
        return seasons;
    }
}
