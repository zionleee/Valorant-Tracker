package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

//represents a SplashScreen image
public class SplashScreen extends JFrame {

    private static SplashScreen splash;
    private JPanel contentPane;
    private static final Color BG_COLOR = new Color(44, 44, 44);

    public SplashScreen() {
        setUndecorated(true);
        setBackground(BG_COLOR);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(1, 1, 1, 1));
        contentPane.setLayout(new BorderLayout(0, 16));
        contentPane.setPreferredSize(new Dimension(990, 900));
        setContentPane(contentPane);

        JLabel imageLabel = new JLabel("");
        imageLabel.setIcon(new ImageIcon("SplashScreen.jpeg"));
        contentPane.add(imageLabel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
    }

    //EFFECTS: displays SplashScreen image
    public static void showSplash() {
        SwingUtilities.invokeLater(() -> {
            splash = new SplashScreen();
            splash.setVisible(true);
        });
    }

    //EFFECTS: hides SplashScreen image
    public static void hideSplash() {
        if (splash != null) {
            splash.setVisible(false);
            splash.dispose();
            splash = null;
        }
    }


}

