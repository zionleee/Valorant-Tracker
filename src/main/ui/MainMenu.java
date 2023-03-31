package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//represents the main menu of the Valorant Performance Application
public class MainMenu extends JFrame implements ActionListener {
    //show match history
    //**show searched matches
    //record match
    //save current history

    private final ValorantPerformanceApp app;

    //EFFECTS: displays buttons
    @SuppressWarnings("methodlength")
    public MainMenu(ValorantPerformanceApp app) {
        this.app = app;
        // Set the title and size of the frame
        setTitle("Valorant Stat Tracker");
        setSize(300, 200);

        JPanel panel = new JPanel();
        // Create a button
        JButton button = new JButton("Load matches!");
        button.addActionListener(this);
        JButton historyButton = new JButton("Show History");
        historyButton.addActionListener(this);
        //added search button
        JButton searchedButton = new JButton("Search Matches");
        searchedButton.addActionListener(this);
        JButton recordButton = new JButton("Record Match");
        recordButton.addActionListener(this);
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(this);
        JButton exit = new JButton("exit");
        exit.addActionListener(this);

        panel.add(button);
        panel.add(historyButton);
        panel.add(searchedButton);
        panel.add(recordButton);
        panel.add(saveButton);
        panel.add(exit);

        this.add(panel);

        setVisible(true);

    }

    //EFFECTS: performs actions when buttons are pressed
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        System.out.println("action performed: " + action);
        switch (action) {
            case "exit":
                System.exit(0);
            case "Load matches!":
                System.out.println("Loading matches");
                app.reloadSession();
                JOptionPane.showMessageDialog(this, "Done Loading Match History");
//                JOptionPane.showOptionDialog(rootPane, "Added","Add new user"
//                        ,JOptionPane.YES_OPTION, JOptionPane.INFORMATION_MESSAGE, null, temp, null);
                break;
            case "Show History":
                MatchHistoryPanel historyPanel = new MatchHistoryPanel(app.getMatchHistory().getMatchHistory());
                historyPanel.setVisible(true);
                System.out.println("showing match history");
                break;
            case "Record Match":
//                ReloadMatchHistoryPopup popup = new ReloadMatchHistoryPopup(this);
//                popup.setVisible(true);
                RecordMatchPanel recordMatch = new RecordMatchPanel(app);
                recordMatch.setVisible(true);

                break;
            case "Search Matches":
                String searchDate = JOptionPane.showInputDialog("Enter Date of Matches. (dd/MM/yyyy)");
                MatchHistoryPanel searchPanel = new MatchHistoryPanel(app.getMatchHistory().findMatch(searchDate));
                searchPanel.setVisible(true);

                break;
            case "Save":
                app.saveSession();
                JOptionPane.showMessageDialog(this, "Match History Saved!");
                break;

        }

    }
}
