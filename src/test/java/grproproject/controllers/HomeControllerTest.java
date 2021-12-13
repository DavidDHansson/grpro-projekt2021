package grproproject.controllers;

import grproproject.managers.mediaManager.Media;
import grproproject.managers.mediaManager.MediaManager;
import grproproject.managers.mediaManager.Movie;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HomeControllerTest {

    private List<Media> media;

    @BeforeEach
    void setUp() {
        String[] genres = {"Action", "Test", "Drama"};
        media.add(new Movie(MediaManager.Type.MOVIE, "Movie test 1", 2021, genres, 10.0));
        media.add(new Movie(MediaManager.Type.MOVIE, "Movie test 2", 2021, genres, 10.0));
        media.add(new Movie(MediaManager.Type.MOVIE, "Movie test 3", 2021, genres, 10.0));
        media.add(new Movie(MediaManager.Type.MOVIE, "Movie test 4", 2021, genres, 10.0));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void searchTextFieldTextChanged() {
    }
}