package persistence;


import model.Match;
import model.MatchHistory;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest  {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.



    @Test
    void testWriterInvalidFile() {
        try {
            MatchHistory mh = new MatchHistory();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            MatchHistory history = new MatchHistory();
            JsonWriter writer = new JsonWriter("./data/MatchHistoryTestEmpty1.json");
            writer.open();
            writer.write(history);
            writer.close();

            JsonReader reader = new JsonReader("./data/MatchHistoryTestEmpty1.json");
            history = reader.loadMatchHistory();
            assertEquals(history.getMatchHistory().size(), 0);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            MatchHistory history = new MatchHistory();
            Match match = new Match("1/1/9", true, "b", "a", 1, 1, 1 );
            history.addMatch(match);


            JsonWriter writer = new JsonWriter("./data/MatchHistoryTestWrite.json");
            writer.open();
            writer.write(history);
            writer.close();
//
            JsonReader reader = new JsonReader("./data/MatchHistoryTestWrite.json");
            history = reader.loadMatchHistory();
            assertEquals(history.getMatchHistory().size(), 1);
            List<Match> matches = history.getMatchHistory();
            Match m = matches.get(0);
            assertEquals(m.getDate(), "1/1/9");
//            checkThingy("saw", Category.METALWORK, thingies.get(0));
//            checkThingy("needle", Category.STITCHING, thingies.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
