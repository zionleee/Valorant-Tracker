package persistence;


import model.MatchHistory;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

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
    void testReaderEmptyWorkRoom() {
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
//    @Test
//    void testReaderGeneralWorkRoom() {
//        JsonReader reader = new JsonReader("./data/testReaderGeneralWorkRoom.json");
//        try {
//            WorkRoom wr = reader.read();
//            assertEquals("My work room", wr.getName());
//            List<Thingy> thingies = wr.getThingies();
//            assertEquals(2, thingies.size());
//            checkThingy("needle", Category.STITCHING, thingies.get(0));
//            checkThingy("saw", Category.WOODWORK, thingies.get(1));
//        } catch (IOException e) {
//            fail("Couldn't read from file");
//        }
//    }
}
