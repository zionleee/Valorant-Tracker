package ui;

import model.Match;
import model.MatchHistory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

//represents a match history GUI panel that displays Match History
public class MatchHistoryPanel extends JFrame {
    private final List<Match> matchHistory;
    private final JTable table;
    private final DefaultTableModel tableModel;

    public MatchHistoryPanel(List<Match> matchHistory) {
        System.out.println("opening match panel");
        this.matchHistory = matchHistory;
        setSize(500, 500);
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Date", "Win", "Agent", "Map", "Kills", "Deaths", "Assists"}
        );
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);


        refreshTable();
    }


    public void refreshTable() {
        tableModel.setRowCount(0);

        // Add each match to the table model
        for (Match match : matchHistory) {
            Object[] rowData = new Object[]{
                    match.getDate(),
                    match.isWin(),
                    match.getAgent(),
                    match.getMap(),
                    match.getKill(),
                    match.getDeath(),
                    match.getAssist()
            };
            tableModel.addRow(rowData);
        }
    }
}