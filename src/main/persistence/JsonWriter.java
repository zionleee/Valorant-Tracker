package persistence;

import model.Match;
import model.MatchHistory;
import org.json.JSONArray;
import org.json.JSONObject;


import java.io.*;

//Represents a writer that writes JSON representation of workroom to file
// Credits to Paul Carter for the project files at https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public class JsonWriter {
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter((destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of match history to file
    public void write(MatchHistory matches) {
        System.out.println("in match history");
        JSONObject obj = new JSONObject();
        JSONArray arr = new JSONArray();
        for (Match match : matches.getMatchHistory()) {
            arr.put(match.toJson());
        }
        obj.put("matchHistory", arr);

        System.out.println("matchHistory: " + obj.toString());
        saveToFile(obj.toString());
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }


}
