package grproproject.managers.mediaManager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MediaManagerTest {

    @BeforeEach
    void setUp()  {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getInstance() {
    }

    @Test
    void getShuffledMedia() {
        assertNotNull(MediaManager.getInstance().getShuffledMedia());
    }

    @Test
    void getGenresSorted() {
        assertNotNull(MediaManager.getInstance().getGenresSorted());
    }
}