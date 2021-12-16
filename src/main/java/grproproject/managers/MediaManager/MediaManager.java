package grproproject.managers.mediaManager;

import grproproject.Constants;
import grproproject.services.CustomAlert;
import grproproject.services.Helper;

import java.util.*;

public class MediaManager {

    public enum Type {
        MOVIE, SERIES
    }

    private static MediaManager instance;
    private List<Media> media;
    private List<String> genres;
    public boolean isDoneLoading = false;

    private MediaManager() {
        try {
            List<Media> newMedia = new ArrayList<>();
            newMedia.addAll(loadAndGetMedia(Type.MOVIE));
            newMedia.addAll(loadAndGetMedia(Type.SERIES));
            media = newMedia;
            genres = loadAndGetGenres(newMedia);
        } catch (Exception e) {
            CustomAlert.showError("WARNING: MediaManager failed to load media");
        }
    }

    public static MediaManager getInstance() {
        if (instance == null) instance = new MediaManager();
        return instance;
    }

    private List<Media> loadAndGetMedia(Type type) {

        List<Media> newMedia = new ArrayList<>();

        String path = type == Type.MOVIE ? Constants.moviePath : Constants.seriesPath;
        Scanner s = new Scanner(MediaManager.class.getResourceAsStream(path), Constants.encoding);
        s.useLocale(Locale.ENGLISH);

        switch (type) {
            case MOVIE:
                while (s.hasNextLine()) {
                    String[] line = s.nextLine().split(";");
                    String[] genres = line[2].trim().split(", ");
                    int year = Helper.tryParseInt(line[1]);
                    double rating = Helper.tryParseToDouble(line[3]);

                    newMedia.add(new Movie(Type.MOVIE, line[0], year, genres, rating));
                }
            case SERIES:
                while (s.hasNextLine()) {
                    String[] line = s.nextLine().split(";");
                    String[] genres = line[2].trim().split(", ");
                    HashMap<Integer, Integer> seasons = Helper.convertSeasonsStringToHashMap(line[4]);
                    double rating = Helper.tryParseToDouble(line[3]);
                    int releaseYear;
                    int endYear;

                    if (line[1].contains("-")) {
                        // Start and end or still ongoing
                        String[] years = line[1].trim().split("-");
                        releaseYear = Helper.tryParseInt(years[0]);
                        endYear = years.length > 1 ? Helper.tryParseInt(years[1]) : Calendar.getInstance().get(Calendar.YEAR);
                    } else {
                        // Only one running year
                        String[] years = line[1].trim().split("-");
                        releaseYear = Helper.tryParseInt(years[0]);
                        endYear = releaseYear;
                    }
                    
                    newMedia.add(new Series(Type.SERIES, line[0], releaseYear, genres, rating, endYear, seasons));
                }
        }

        s.close();
        isDoneLoading = true;
        return newMedia;
    }

    private List<String> loadAndGetGenres(List<Media> mediaLocal) {
        List<String> genresLocal = new ArrayList<>();
        for(Media m : mediaLocal) {
            for(String g : m.getGenres()) {
                if(!genresLocal.contains(g)) genresLocal.add(g);
            }
        }

        return genresLocal;
    }

    public List<Media> getShuffledMedia() {
        List<Media> shuffledMedia = new ArrayList<>(media);
        Collections.shuffle(shuffledMedia);
        return shuffledMedia;
    }

    public List<String> getGenresSorted() {
        List<String> sortedGenres = new ArrayList<>(genres);
        Collections.sort(sortedGenres);
        return sortedGenres;
    }
}