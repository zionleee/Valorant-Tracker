package model;


import org.json.JSONArray;
import org.json.JSONObject;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// The MatchHistory class represents all recorded Matches
public class MatchHistory {
    private List<Match> matches;


    //EFFECTS: creates a MatchHistory with no matches recorded with initial kdaRatio & winRate of 0
    public MatchHistory() {
        matches = new ArrayList<>();
    }

    //EFFECTS: adds a match to matchHistory
    //MODIFIES: this
    public void addMatch(Match match) {
        matches.add(match);
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
    public List<Match> findMatch(String date) {

        List<Match> matchList = new ArrayList<>();

        for (Match match : matches) {
            if (match.getDate().equals(date)) {
                matchList.add(match);
            }
        }
        //TODO: delete later--> testing search
        if (matchList.size() == 0) {
            System.out.println("no matches found");
        }

        return matchList;
    }
// prev code added before p1
//    public boolean loadDataFromSave(String path) {
//        try {
//            File file = FileManager.getFile(path);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//
//        return false;
//    }

    //    public boolean saveData(String path) {
//        try {
//            File file = FileManager.getFile(path);
//            FileWriter writer = new FileWriter(file);
//            for (Match match : matches) {
//                writer.append("");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//        return false;
//    }




//TODO:
    // EFFECTS: returns this as JSON object
    public JSONObject toJson() {
        JSONObject obj = new JSONObject();
        JSONArray array = new JSONArray();
        if (matches.size() > 0) {
            for (Match match : matches) {
                array.put(match.toJson());
            }
        }
        obj.put("matches", array);
        return obj;
    }


//    //EFFECTS: returns things in this workroom as a JSON array
//    private JSONArray matchesToJson() {
//        JSONArray jsonArray = new JSONArray();
//
//        for (Match m : matches) {
//            jsonArray.put(m.toJson());
//        }
//        return jsonArray;
//    }
//
//    public void initializeFromJson(JSONObject obj) {
//
//        JSONArray array = obj.getJSONArray("matches");
//        matches.clear();
//        for (int i = 0; i < array.length(); i++) {
//            matches.add(new Match(array.getJSONObject(i)));
//        }
//    }
//
//    public void saveSession() {
//        try {
//            jsonWriter.open();
//            jsonWriter.write(this);
//            jsonWriter.close();
//            logger.logEvent(new Event("Saving " + matches.size() + " sessions to file."));
//        } catch (FileNotFoundException fileEx) {
//            fileEx.printStackTrace();
//            logger.logEvent(new Event("Failed to save session. FileNotFoundException."));
//        }
//    }
//
//    public void loadSession() {
//        try {
//            initializeFromJson(jsonReader.getMatches());
//            logger.logEvent(new Event("Loaded " + matches.size() + " previous sessions."));
////            System.out.println("Loaded pomodoroTimer from " + JSON_STORE);
//        } catch (IOException e) {
//            System.out.println("Unable to read from file: " + JSON_STORE);
//            logger.logEvent(new Event("Failed to load previous sessions. IOException."));
//        }
//    }
}




