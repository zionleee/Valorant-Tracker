package model;

import javax.swing.*;
import java.text.SimpleDateFormat;

//The Match class represents a Valorant Match
public class Match {

    private boolean win;
    private String date; // "dd/MM/yyyy"
    private String agent;
    private String map;

    private double kill;
    private double death;
    private double assist;

    private String kda;


    //EFFECTS: creates a Match with date, WinOrLose, agent, map, and k, d, a
    // MAP inputs: a=Ascent, f=Fracture, h=Haven, i=Icebox, l=Lotus, p=Pearl , s=Split
    // AGENT inputs:b=Brimstone, v=Viper, o=Omen, h=Harbor, a=Astra, kj=Killjoy, cyp=Cypher, sa=Sage, c=Chamber,
    //   so=Sova, k=KAY/O, f=Fade, br=Breach, sk=Skye, phx=Phoenix, j=Jett, r=Reyna, ra=Raze, y=Yoru, n=Neon
    public Match(String date, boolean win, String agent, String map, double k, double d, double a) {
        this.date = date;
        this.win = win;
        this.agent = agent;
        this.map = map;

        this.kill = k;
        this.death = d;
        this.assist = a;
    }


    public String getDate() {
        return date;
    }

    public boolean isWin() {
        return win;
    }


    @SuppressWarnings("methodlength")
    //EFFECTS: returns this match's agent type
    //          b=Brimstone, v=Viper, o=Omen, h=Harbor, a=Astra, kj=Killjoy, cyp=Cypher, sa=Sage, c=Chamber,
    //          so=Sova, k=KAY/O, f=Fade, br=Breach, sk=Skye, phx=Phoenix, j=Jett, r=Reyna, ra=Raze, y=Yoru, n=Neon
    public String agentType() {
//        String agentPlayed = agent;
        switch (agent) {
            case "b":
                return "Brimstone";
            case "v":
                return "Viper";
            case "o":
                return "Omen";
            case "h":
                return "Harbor";
            case "a":
                return "Astra";
            case "kj":
                return "Killjoy";
            case "cyp":
                return "Cypher";
            case "sa":
                return "Sage";
            case "c":
                return "Chamber";
            case "so":
                return "Sova";
            case "k":
                return "KAY/O";
            case "f":
                return "Fade";
            case "br":
                return "Breach";
            case "sk":
                return "Skye";
            case "phx":
                return "Phoenix";
            case "j":
                return "Jett";
            case "r":
                return "Reyna";
            case "ra":
                return "Raze";
            case "y":
                return "Yoru";
            case "n":
                return "Neon";
            default:
                return "Unrecognized Valorant Agent";
        }
    }

    public String getAgent() {
        return agentType();
    }

    //EFFECTS: returns this match's map type
    //a=Ascent, f=Fracture, h=Haven, i=Icebox, l=Lotus, p=Pearl , s=Split
    public String mapType() {
//        String mapPlayed = map;
        switch (map) {
            case "a":
                return "Ascent";
            case "f":
                return "Fracture";
            case "h":
                return "Haven";
            case "i":
                return "Icebox";
            case "l":
                return "Lotus";
            case "p":
                return "Pearl";
            case "s":
                return "Split";
            default:
                return "Unrecognized Valorant Map";
        }
    }

    public String getMap() {
        return mapType();
    }

    //TODO: edited kda --> int to double
    public double getKill() {
        return kill;
    }

    public double getDeath() {
        return death;
    }

    public double getAssist() {
        return assist;
    }

    public String getKda() {
        kda = kill + "/" + death + "/" + assist;

        return kda;
    }

//    public String serialize() {
//        return date + "-" + win + "-" + agent + "-" + map + " ";
//    }


}
