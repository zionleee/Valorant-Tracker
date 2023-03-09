package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MatchHistoryTest {

    private MatchHistory matchHistory;
    private Match match1;
    private Match match2;
    private Match match3;
    private Match match4;

    @BeforeEach
    public void setUp() {
        //(String date, boolean win, String agent, String map, int k, int d, int a)
        match1 = new Match("11/02/2023", false, "j", "p", 10, 14, 5);
        match2 = new Match("01/02/2023", true, "v", "i", 25, 12, 14);
        match3 = new Match("02/02/2023", true, "f", "h", 10, 15, 10);
        match4 = new Match("02/02/2023", false, "b", "l", 2, 15, 10);

        matchHistory = new MatchHistory();

//        matchHistory.addMatch(match1);
//        matchHistory.addMatch(match2);
//        matchHistory.addMatch(match3);
//        matchHistory.addMatch(match4);

    }

    @Test
    public void constructorTest() {
        assertEquals(0, matchHistory.getNumTotalGames());
        assertEquals(0, matchHistory.getMatchHistory().size());
    }

    @Test
    public void addMatchTest() {
        matchHistory.addMatch(match1);
        assertEquals(1, matchHistory.getNumTotalGames());

        matchHistory.addMatch(match2);
        matchHistory.addMatch(match3);
        assertEquals(3, matchHistory.getNumTotalGames());

    }

    @Test
    public void calculateKdaTest() {
//        matchHistory.addMatch(match1);
//        assertEquals(((10 + 15) / 14) / matchHistory.getNumTotalGames(), matchHistory.calculateKda());
//        matchHistory.addMatch(match2);
//        assertEquals(((10 + 15) / 14 + (25 + 14) / 12) / matchHistory.getNumTotalGames(),
//                matchHistory.calculateKda());
//        matchHistory.addMatch(match3);
//        assertEquals(((10 + 15) / 14 + (25 + 14) / 12 + (10 + 10) / 15) / matchHistory.getNumTotalGames(),
//                matchHistory.calculateKda());

        matchHistory.addMatch(match1);
        assertEquals(1.0714285714285714, matchHistory.calculateKda());

        matchHistory.addMatch(match2);
        assertEquals(2.1607142857142856, matchHistory.calculateKda());

        matchHistory.addMatch(match3);
        assertEquals(1.8849206349206347, matchHistory.calculateKda());

    }

    @Test
    public void calculateWinRateTest() {
        matchHistory.addMatch(match1);
        assertEquals(0, matchHistory.calculateWinRate());

        matchHistory.addMatch(match2);
        assertEquals(50, matchHistory.calculateWinRate());

        matchHistory.addMatch(match3);
        assertEquals(66.66666666666666, matchHistory.calculateWinRate());
    }


    @Test
    public void searchMatchesDateTestOneGame() {
        matchHistory.addMatch(match1);
        matchHistory.addMatch(match2);
        matchHistory.addMatch(match3);
        matchHistory.addMatch(match4);

        List<Match> matchList1 = new ArrayList<>();
        matchList1.add(match1);
        assertEquals(matchList1, matchHistory.findMatch("11/02/2023"));

        List<Match> matchList2 = new ArrayList<>();
        matchList2.add(match2);
        assertEquals(matchList2, matchHistory.findMatch("01/02/2023"));


    }

    @Test
    public void searchMatchesDateTestMultipleGames() {
        matchHistory.addMatch(match1);
        matchHistory.addMatch(match2);
        matchHistory.addMatch(match3);
        matchHistory.addMatch(match4);

        List<Match> matches = new ArrayList<>();
        matches.add(match3);
        matches.add(match4);

        assertEquals(matches, matchHistory.findMatch("02/02/2023"));

    }

    @Test
    public void searchMatchesDateTestNoMatchesFound() {
        matchHistory.addMatch(match1);
        matchHistory.addMatch(match2);
        matchHistory.addMatch(match3);
        matchHistory.addMatch(match4);

        List<Match> emptyMatches = new ArrayList<>();

        assertEquals(0, emptyMatches.size());
        assertEquals(4, matchHistory.getNumTotalGames());

        assertTrue(matchHistory.findMatch("55/55/2055").isEmpty());

    }



}
