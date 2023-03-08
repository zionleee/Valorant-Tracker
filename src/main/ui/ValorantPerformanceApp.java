package ui;

import model.Match;
import model.MatchHistory;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

//Valorant Performance Application
public class ValorantPerformanceApp {

//    private static final String PATH = "save.txt";

    private MatchHistory playerValorantCareer;
    private Scanner input;


    //EFFECTS: runs the Valorant Performance Tracker Application
    public ValorantPerformanceApp() {
        runTracker();
    }

    //MODIFIES: this
    //EFFECTS: processes user input
    private void runTracker() {
        // if instance not created yet --> instantiate new history
        // else, use history already created

//        if (playerValorantCareer == null) {
//            init();
//        } else {
//            input = new Scanner(System.in);
//            input.useDelimiter("\n");
//        }

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
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tr -> record a Match");
        System.out.println("\th -> display Match History");
        System.out.println("\ts -> search for Matches");
        System.out.println("\tk -> display KDA Ratio");
        System.out.println("\tw -> display Win Rate");
        System.out.println("\tx -> exit app");
    }


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


}









