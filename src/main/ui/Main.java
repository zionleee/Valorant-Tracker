package ui;

import model.EventLog;

import javax.swing.*;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> printLog()));
        new ValorantPerformanceApp();

    }

    public static void printLog() {
        System.out.println("Printing log");
        Iterator it = EventLog.getInstance().iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
