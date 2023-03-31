package ui;

import model.Match;
import model.MatchHistory;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

//represents a panel for recording a match and adding it to match history
public class RecordMatchPanel extends JFrame {

    private ValorantPerformanceApp app;
    private MatchHistory matchHistory;
    private Match match;
    
    @SuppressWarnings({"methodLength", "checkstyle:SuppressWarnings", "checkstyle:OperatorWrap"})
    public RecordMatchPanel(ValorantPerformanceApp app) {
        super("Record Match Panel");
        this.app = app;


        JPanel recordPanel = new JPanel(new GridLayout(8, 2));
        JTextField dateField = new JTextField();
        JCheckBox winCheckBox = new JCheckBox();
        JTextField agentField = new JTextField();
        JTextField mapField = new JTextField();
        JTextField kField = new JTextField();
        JTextField dField = new JTextField();
        JTextField aField = new JTextField();
        recordPanel.add(new JLabel("Date (dd/MM/yyyy): "));
        recordPanel.add(dateField);
        recordPanel.add(new JLabel("Win: "));
        recordPanel.add(winCheckBox);
        recordPanel.add(new JLabel("Agent (b, v, o, h, a, kj, cyp, sa, c, so, k, f, br, sk, phx, j, r, ra, y, n)" +
                " : "));
        recordPanel.add(agentField);
        recordPanel.add(new JLabel("Map: (a=Ascent, f=Fracture, h=Haven, i=Icebox, l=Lotus, p=Pearl , s=Split)"));
        recordPanel.add(mapField);
        recordPanel.add(new JLabel("Kills: "));
        recordPanel.add(kField);
        recordPanel.add(new JLabel("Deaths: "));
        recordPanel.add(dField);
        recordPanel.add(new JLabel("Assists: "));
        recordPanel.add(aField);

        JButton recordBtn = new JButton("Record Match");
        recordBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String date = dateField.getText();
                boolean win = winCheckBox.isSelected();
                String agent = agentField.getText();
                String map = mapField.getText();
                double k = Double.parseDouble(kField.getText());
                double d = Double.parseDouble(dField.getText());
                double a = Double.parseDouble(aField.getText());

                //TODO: this is not adding match to match history!!!!
                match = new Match(date, win, agent, map, k, d, a);
                app.recordMatch(match);
                System.out.println("Recorded match successfully");
//                matchHistory.addMatch(match);
//                recordMatch(date, win, agent, map, k, d, a);

                JOptionPane.showMessageDialog(RecordMatchPanel.this, "Match recorded!");
            }
        });

        JPanel mainPanel = new JPanel(new BorderLayout());
//        mainPanel.add(reloadBtn, BorderLayout.NORTH);
        mainPanel.add(recordPanel, BorderLayout.CENTER);
        mainPanel.add(recordBtn, BorderLayout.SOUTH);

        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }


}
