package persistence;


import model.MatchHistory;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest {
    public static String TEST_PATH = "./data/MatchHistoryTest.json";
    public static String TEST_PATH_INVALID = "./data/MatchHistoryTest1.json";
    public static String TEST_PATH_EMPTY = "./data/MatchHistoryTestEmpty.json";


    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader(TEST_PATH_INVALID);
        try {
            MatchHistory matchHistory = reader.loadMatchHistory();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyMatchHistory() {
        JsonReader reader = new JsonReader(TEST_PATH_EMPTY);
        try {
            MatchHistory matchHistory = reader.loadMatchHistory();
            assertEquals(matchHistory.getMatchHistory().size(), 0);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
    @Test
    void testReaderOneMatch() {
        JsonReader reader = new JsonReader(TEST_PATH);
        try {
            MatchHistory matchHistory = reader.loadMatchHistory();
            assertEquals(matchHistory.getMatchHistory().size(), 1);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
