package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.Match;
import model.MatchHistory;
import org.json.*;

//Represents a writer that writes JSON representation of workroom to file
// Credits to Paul Carter for the project files at https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }



    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    public MatchHistory loadMatchHistory() throws IOException {
        MatchHistory history = new MatchHistory();
        JSONObject matchHistory = new JSONObject(readFile(source));
        JSONArray arr = matchHistory.getJSONArray("matchHistory");
        for (int index = 0; index < arr.length(); index++) {
            JSONObject matchObj = arr.getJSONObject(index);
            Match match = new Match(matchObj.getString("date"), matchObj.getBoolean("win"),
                    matchObj.getString("agent"),
                    matchObj.getString("map"), matchObj.getDouble("kill"),
                    matchObj.getDouble("death"), matchObj.getDouble("assist"));
            history.addMatch(match);
        }
        return history;
    }


}

