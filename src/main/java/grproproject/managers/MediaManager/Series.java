package grproproject.managers.MediaManager;

import java.util.ArrayList;
import java.util.HashMap;

public class Series extends Media {

    private int endYear;
    private HashMap<Integer, Integer> seasons;

    public Series(MediaManager.Type type, String title, Integer releaseYear, String[] genres, double rating, int endYear, HashMap<Integer, Integer> seasons) {
        super(type, title, releaseYear, genres, rating);
        this.endYear = endYear;
        this.seasons = seasons;
    }

}
