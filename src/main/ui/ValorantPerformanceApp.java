package ui;

import model.Match;
import model.MatchHistory;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;



//Valorant Performance Application
public class ValorantPerformanceApp extends JFrame {
    private MatchHistory playerValorantCareer;
    private Scanner input;

    private static final String JSON_STORE = "./data/MatchHistory.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

//    private final JFrame frame;
    private  MatchHistoryPanel historyPanel;
    private  RecordMatchPanel recordMatchPanel;



    @SuppressWarnings("methodlength")
    //EFFECTS: runs the Valorant Performance Tracker Application
    public ValorantPerformanceApp() {
//        SplashScreen.showSplash(); //splash screen
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        SplashScreen.hideSplash();

        //TODO: RELOAD MATCH HISTORY POP! ***UP NOT WORKING***
        // if YES: reload history and do stuff w that
        // if NO: work with empty match history


//        JButton reloadButton = new JButton("Reload");
//        reloadButton.addActionListener(e -> {
//            ReloadMatchHistoryPopup popup = new ReloadMatchHistoryPopup(this);
//            popup.setVisible(true);
//
//            if (popup.shouldReload()) {
//                reloadSession();
//                JOptionPane.showMessageDialog(this, "Match history reloaded successfully.");
//            }
//        });
        //historyPanel = new MatchHistoryPanel(playerValorantCareer);
        //recordMatchPanel = new RecordMatchPanel(playerValorantCareer);

//        frame = new JFrame("Valorant Performance Tracker");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(600, 400);
//        frame.setLayout(new BorderLayout());
//        // Add panels to frame
//        JPanel panel = new JPanel(new GridLayout(2, 1));
//        panel.add(recordMatchPanel);
//        panel.add(historyPanel);
//        frame.add(panel, BorderLayout.CENTER);
//
//        // Display the frame
//        frame.setVisible(true);

//        createGUI();
        //TODO: SHOW MATCH HISTORY NOT WORKING
//        showMatchesPanel();
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                new RecordMatchPanel(playerValorantCareer);
//            }
//        });


        //UP TO HERE
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runTracker();

    }

    public MatchHistory getMatchHistory() {
        return playerValorantCareer;
    }

//    public void createGUI() {
//        // Create the JFrame and set properties
//        JFrame frame = new JFrame("Valorant Performance");
//        frame.setSize(800, 600);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        // Create the MatchHistoryPanel and add it to the frame
//        MatchHistoryPanel matchHistoryPanel = new MatchHistoryPanel(playerValorantCareer.getMatchHistory());
//        frame.add(matchHistoryPanel);
//
//        // Create the RecordMatchPanel and add it to the frame
//        RecordMatchPanel recordMatchPanel = new RecordMatchPanel(playerValorantCareer.getMatchHistory());
//        frame.add(recordMatchPanel);
//
//        // Set the frame to be visible
//        frame.setVisible(true);
//    }

//    private void showMatchesPanel() {
//        if (playerValorantCareer != null) {
//            JFrame frame = new JFrame("Valorant Performance");
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frame.setSize(500, 500);
//            // creating an instance of MatchHistoryPanel
//            MatchHistoryPanelTwo matchHistoryPanel = new MatchHistoryPanelTwo(playerValorantCareer);
//            // adding the matchHistoryPanel to the JFrame
//            frame.add(matchHistoryPanel);
//            // setting the JFrame visible
//            frame.setVisible(true);
//        }
//    }




    //MODIFIES: this
    //EFFECTS: processes user input
    private void runTracker() {
        init();
        boolean keepGoing = true;
        String command = "";
        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();
            if (command.equals("x")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nGoodbye!");
    }


    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("r")) {
            recordMatch();
        } else if (command.equals("h")) {
            displayPlayerMatchHistory(); //List
        } else if (command.equals("s")) {
            displaySearchedMatches(); //MatchHistory
        } else if (command.equals("k")) {
            displayKdaRatio();
        } else if (command.equals("w")) {
            displayWinRate();
        } else if (command.equals("sv")) {
            saveSession();
        } else if (command.equals("rl")) {
            reloadSession();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes player tracker profile with empty match history
    private void init() {
        this.playerValorantCareer = new MatchHistory();
//        playerValorantCareer.loadDataFromSave(PATH);
        input = new Scanner(System.in);
        input.useDelimiter("\n");

        SwingUtilities.invokeLater(() -> {
            new MainMenu(this);
        });
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tr -> record a Match");
        System.out.println("\th -> display Match History");
        System.out.println("\ts -> search for Matches");
        System.out.println("\tk -> display KDA Ratio");
        System.out.println("\tw -> display Win Rate");
        System.out.println("\tsv -> save Match History");
        System.out.println("\trl -> reload Match History");
        System.out.println("\tx -> exit app");
    }

    @SuppressWarnings("methodlength")
    //MODIFIES: this
    //EFFECTS: records match to match history with date, WinOrLose, agent, map, and k, d, a
    private void recordMatch() {
        System.out.print("Enter date (dd/MM/yyyy): ");
        String date = input.next();
        System.out.print("Enter true for Win, false for Lose: ");
        boolean win = input.nextBoolean();
        System.out.print("Enter agent pick: \n"
                + "SMOKES: b=Brimstone, v=Viper, o=Omen, h=Harbor, a=Astra \n"
                + "SENTINELS: kj=Killjoy, cyp=Cypher, sa=Sage, c=Chamber, \n"
                + "INITIATORS: so=Sova, k=KAY/O, f=Fade, br=Breach, sk=Skye, \n"
                + "DUELISTS: phx=Phoenix, j=Jett, r=Reyna, ra=Raze, y=Yoru, n=Neon\t\n");
        String agent = input.next();
        System.out.print("Enter Map a=Ascent, f=Fracture, h=Haven, i=Icebox, l=Lotus, p=Pearl , s=Split: ");
        String map = input.next();
        System.out.print("Enter number of kills: ");
        double k = input.nextDouble();
        System.out.print("Enter number of deaths: ");
        double d = input.nextDouble();
        System.out.print("Enter number of assists: ");
        double a = input.nextDouble();
        Match match = new Match(date, win, agent, map, k, d, a);
        this.playerValorantCareer.addMatch(match);
        if (playerValorantCareer.getNumTotalGames() > 0) {
            System.out.println("===> Match has been recorded");
        }
    }

    public void recordMatch(Match match) {
        this.playerValorantCareer.addMatch(match);
    }


    //EFFECTS: displays players full match history
    private void displayPlayerMatchHistory() { //List
        displayMatches(playerValorantCareer.getMatchHistory());
    }

    //TODO: problem-> "No matches found because match history is not being saved
    // MUST FIX THIS AND MAKE IT WORK!!!!!!!!!!!!!!!
    //EFFECT: displays matches played on date searched
    private void displaySearchedMatches() {  //MatchHistory --> List<Match>
        System.out.print("Enter search date (dd/MM/yyyy): ");
        String dateSearch = input.next();

        //gives us a new list of matches
        List<Match> matchesSearched = playerValorantCareer.findMatch(dateSearch);

        //testing
        System.out.println(dateSearch);
        System.out.println(playerValorantCareer.getNumTotalGames());

        displayMatches(matchesSearched);
    }

    //EFFECTS: displays matches
    public void displayMatches(List<Match> matches) {
        String isWin;

        for (Match m : matches) {
            int k = (int) m.getKill();
            int d = (int) m.getDeath();
            int a = (int) m.getAssist();

            if (m.isWin()) {
                isWin = "VICTORY";
            } else {
                isWin = "DEFEAT";
            }
            System.out.println("\n" + "MATCH: " + m.getDate() + " " + isWin
                    + " AGENT:" + m.getAgent() + " MAP:" + m.getMap() + " K/D/A: " + k + "/" + d + "/" + a + "\n");
        }
    }

    //EFFECTS: displays player kda Ratio from all matches played
    private void displayKdaRatio() {
        double kda = playerValorantCareer.calculateKda();
        DecimalFormat df = new DecimalFormat("#.00");

        System.out.println("PLAYER K/D/A RATIO: " + df.format(kda));
    }

    //EFFECTS: displays player win rate from all matches played
    private void displayWinRate() {
        double winRate = playerValorantCareer.calculateWinRate();
        DecimalFormat df = new DecimalFormat("#.00");

        System.out.println("WIN RATE: " + df.format(winRate) + "%");
    }


    // EFFECTS: saves the match history to file
    public void saveSession() {
        try {
            jsonWriter.open();
            jsonWriter.write(playerValorantCareer);
            jsonWriter.close();
            System.out.println("Saved matches to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads match history from file
    public void reloadSession() {
        try {
            playerValorantCareer = jsonReader.loadMatchHistory();
            if (playerValorantCareer == null) {
                System.out.println("Error loading file");
            }
            System.out.println("Loaded " + playerValorantCareer.getMatchHistory().size() + " from " + JSON_STORE);
        } catch (Exception ex) {
            System.out.println("Failed to load match history");
            ex.printStackTrace();
        }
    }


}














