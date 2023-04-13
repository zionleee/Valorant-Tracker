package model;

import java.util.ArrayList;
import java.util.List;

// The MatchHistory class represents all recorded Matches
public class MatchHistory {
    private List<Match> matches;
    private final EventLog logger;


    //EFFECTS: creates a MatchHistory with no matches recorded with initial kdaRatio & winRate of 0
    public MatchHistory() {
        matches = new ArrayList<>();
        logger = EventLog.getInstance();
    }

    //EFFECTS: adds a match to matchHistory
    //         logs the event of adding a match and/or reloading previously saved matches
    //MODIFIES: this
    public void addMatch(Match match) {
        matches.add(match);
        logger.logEvent(new Event("Match recorded for date: " + match.getDate() + "\n"
                + "  ==> Victory:" + match.isWin() + " Agent:" + match.getAgent() + " Map:" + match.getMap()
                + " K/D/A:" + match.getKda()));
    }


    public List<Match> getMatchHistory() {
        return matches;
    }

    public int getNumTotalGames() {
        return matches.size();
    }

    //EFFECTS: returns average k/d/a from all matches played
    //          (kills + assists)/ deaths , for your kill-deaths/assists ratio.
    public double calculateKda() {
        double current = 0;

        for (Match match : matches) {
            current += ((match.getKill() + match.getAssist()) / match.getDeath());
        }

        return current / matches.size();
    }


    //REQUIRES: MatchHistory must not be empty
    //EFFECTS: calculate average win rate from all matches played
    //    To calculate a win rate percentage by total wins/total games * 100
    public double calculateWinRate() {
        double wins = 0; //initial wins=0

        for (Match match : matches) {
            if (match.isWin()) {
                wins++;
            }
        }

        return (wins / matches.size()) * 100;   //add % later in ui &
    }

    //REQUIRES: MatchHistory is not empty
    //EFFECTS: given a date, returns a list of matches played on that date
    //         logs the event of searching for a match
    public List<Match> findMatch(String date) {

        List<Match> matchList = new ArrayList<>();

        for (Match match : matches) {
            if (match.getDate().equals(date)) {
                matchList.add(match);
                logger.logEvent(new Event("Match searched for date: " + date + "\n"
                        + "  ==> Victory:" + match.isWin() + " Agent:" + match.getAgent() + " Map:" + match.getMap()
                        + " K/D/A:" + match.getKda()));
            }
        }

        if (matchList.size() == 0) {
            System.out.println("no matches found");
            logger.logEvent(new Event("Match search unsuccessful for date: " + date));
        }

        return matchList;
    }


}




