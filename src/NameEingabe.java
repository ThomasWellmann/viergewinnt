import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class NameEingabe extends JComponent {
    private static final Color backgroundColor = new Color(86, 199, 165);
    private static final Color schriftColor = new Color(207, 58, 25);
    static JTextField player2Name = new JTextField("Spieler 2");
    static JTextField player1Name = new JTextField("Spieler 1");

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        try {
            g2D.setFont(Font.createFont(Font.TRUETYPE_FONT, new File("/Users/thomascesar/Downloads/Aclonica/Aclonica-Regular.ttf")).deriveFont(Font.PLAIN, 40));
        } catch (Exception ex) {
            ex.printStackTrace();
            g2D.setFont(new Font("Default", Font.PLAIN, 20));
        }
        g2D.setColor(backgroundColor);
        g2D.fillRect(0, 0, 800, 600);

        int nP1 = 4;
        int nP2 = 3;

        int[] xP1 = {69, 400, 730, 400};
        int[] yP1 = {300, 12, 300, 588};

        int[] xP2 = {112, 400, 687, 400};
        int[] yP2 = {300, -30, 300, 630};

        int[] xP3 = {166, 603, 633, 196};
        int[] yP3 = {66, 96, 533, 503};

        int[] xP4 = {196, 633, 603, 166};
        int[] yP4 = {96, 66, 503, 533};

        int[] xP5 = {0, 100, 0};
        int[] yP5 = {0, 0, 250};

        int[] xP6 = {800, 800, 700};
        int[] yP6 = {0, 500, 0};

        int[] xP7 = {800, 700, 800};
        int[] yP7 = {600, 600, 350};

        int[] xP8 = {0, 0, 100};
        int[] yP8 = {600, 100, 600};

        g2D.setColor(new Color(41, 160, 125, 100));
        g2D.fillPolygon(xP1, yP1, nP1);
        g2D.fillPolygon(xP2, yP2, nP1);
        g2D.fillPolygon(xP3, yP3, nP1);
        g2D.fillPolygon(xP4, yP4, nP1);

        g2D.fillPolygon(xP5, yP5, nP2);
        g2D.fillPolygon(xP6, yP6, nP2);
        g2D.fillPolygon(xP7, yP7, nP2);
        g2D.fillPolygon(xP8, yP8, nP2);

        g2D.setColor(schriftColor);
        g2D.drawString("4 GEWINNT", 275, 225);

        g2D.setColor(new Color(79, 96, 249));
        g2D.fillOval(280, 255, 30, 30);

        g2D.setColor(new Color(23, 41, 204));
        g2D.fillOval(285, 260, 20, 20);

        g2D.setColor(new Color(24, 36, 136));
        g2D.fillOval(290, 265, 10, 10);

        g2D.setColor(new Color(232, 79, 76));
        g2D.fillOval(280, 315, 30, 30);

        g2D.setColor(new Color(205, 42, 38));
        g2D.fillOval(285, 320, 20, 20);

        g2D.setColor(new Color(161, 6, 3));
        g2D.fillOval(290, 325, 10, 10);

        g2D.setColor(Color.white);
        g2D.fillRoundRect(322, 247, 195, 45, 10, 10);
        g2D.fillRoundRect(322, 307, 195, 45, 10, 10);

        g2D.setColor(Color.darkGray);
        g2D.drawRoundRect(322, 247, 195, 45, 10, 10);
        g2D.drawRoundRect(322, 307, 195, 45, 10, 10);

        Game.getSpielerName();
    }
    public static void setTextFild() {
        player1Name.setBounds(320, 245, 200, 50);
        player1Name.setForeground(Color.gray);
        player1Name.setBackground(new Color(0,0,0,0));
        UserInterface.nameEingabeWindow.add(player1Name);

        player2Name.setBounds(320, 305, 200, 50);
        player2Name.setForeground(Color.gray);
        player2Name.setBackground(new Color(0,0,0,0));
        UserInterface.nameEingabeWindow.add(player2Name);
    }
}