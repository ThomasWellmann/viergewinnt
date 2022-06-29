import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Board extends JComponent {
    public static final int chipDiameter = 70;
    public static final int spacing = 7;
    private static final Color backgroundColor = new Color(86, 199, 165);
    private static final Color schriftColor = new Color(207, 58, 25);
    private int Opacity;
    private int fontSize;
    private int a = 0;

    public static int previewCol = -1;
    public static int previewRow = -1;

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        try {
            g2D.setFont(Font.createFont(Font.TRUETYPE_FONT, new File("/Users/thomascesar/Downloads/Aclonica/Aclonica-Regular.ttf")).deriveFont(Font.PLAIN, fontSize));
        } catch (Exception ex) {
            ex.printStackTrace();
            g2D.setFont(new Font("Default", Font.PLAIN, fontSize));
        }

        g2D.setColor(backgroundColor);
        g2D.fillRect(0,0,800,600);

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
        g2D.fillPolygon( xP1,  yP1,  nP1);
        g2D.fillPolygon( xP2,  yP2,  nP1);
        g2D.fillPolygon( xP3,  yP3,  nP1);
        g2D.fillPolygon( xP4,  yP4,  nP1);

        g2D.fillPolygon( xP5,  yP5,  nP2);
        g2D.fillPolygon( xP6,  yP6,  nP2);
        g2D.fillPolygon( xP7,  yP7,  nP2);
        g2D.fillPolygon( xP8,  yP8,  nP2);

        renderBoard(g2D, Game.board);
        printStatusMessage(g2D, Game.statusMessage);
        renderActivePlayer(g2D, Game.activePlayer);
        repaint();
    }
    private void printStatusMessage(Graphics g2D, String message1){
        g2D.setColor(schriftColor);
        if(Game.winner == 0) {
            fontSize = 20;
            Opacity = 255;
            g2D.drawString(message1, 190, 60);
        } else if (Game.winner == 1) {

            g2D.setColor(new Color(79, 96, 249));
            g2D.fillOval(355, 250, 90, 90);

            g2D.setColor(new Color(23, 41, 204));
            g2D.fillOval(370, 265, 60, 60);

            g2D.setColor(new Color(24, 36, 136));
            g2D.fillOval(385, 280, 30, 30);
        } else if (Game.winner == 2) {

            g2D.setColor(new Color(232, 79, 76));
            g2D.fillOval(355, 250, 90, 90);

            g2D.setColor(new Color(205, 42, 38));
            g2D.fillOval(370, 265, 60, 60);

            g2D.setColor(new Color(161, 6, 3));
            g2D.fillOval(385, 280, 30, 30);
        } else if (Game.winner == 3) {

            g2D.setColor(Color.white);
            g2D.fillOval(355, 250, 90, 90);

            g2D.setColor(new Color(230, 230, 230));
            g2D.fillOval(370, 265, 60, 60);

            g2D.setColor(new Color(210, 210, 210));
            g2D.fillOval(385, 280, 30, 30);

            g2D.setColor(Color.darkGray);
        }
        if (Game.winner > 0) {
            fontSize = 40;
            Opacity = 100;
            a = 50;
            g2D.drawString(message1, 140, 200);
        }
    }
    private void renderActivePlayer(Graphics g2D, int player){
        g2D.setColor(schriftColor);

        String playerName = Game.getAktuelleSpieler();

        if (player > 0){
            g2D.drawString(playerName + " ist am Zug.", 310, 60);

            if(player == 1) {
                g2D.setColor(new Color(79, 96, 249));
                g2D.fillOval(270, 40, 30, 30);

                g2D.setColor(new Color(23, 41, 204));
                g2D.fillOval(275, 45, 20, 20);

                g2D.setColor(new Color(24, 36, 136));
                g2D.fillOval(280, 50, 10, 10);
            }
            if(player == 2) {
                g2D.setColor(new Color(232, 79, 76));
                g2D.fillOval(270, 40, 30, 30);

                g2D.setColor(new Color(205, 42, 38));
                g2D.fillOval(275, 45, 20, 20);

                g2D.setColor(new Color(161, 6, 3));
                g2D.fillOval(280, 50, 10, 10);
            }
        }


    }
    private void renderBoard(Graphics g2D, int[][] board) {

        int xPosition = 130;
        int yPosition = 90;

        for (int row = 0; row < Game.ROWS; row++) {
            for (int column = 0; column < Game.COLUMNS; column++) {


                if (column == previewCol && row == previewRow && Game.winner == 0) {
                    renderChip(g2D, xPosition + (column * chipDiameter) + (column * spacing), yPosition + (row * chipDiameter) + (row * spacing), Game.activePlayer +4);
                } else {
                    renderChip(g2D, xPosition + (column * chipDiameter) + (column * spacing), yPosition + (row * chipDiameter) + (row * spacing), board[row][column]);
                }
            }
        }
    }

    private void renderChip(Graphics g2D, int x, int y, int player) {

        g2D.setColor(new Color(255, 255, 255, Opacity));
        g2D.fillOval(x, y, chipDiameter, chipDiameter);

        g2D.setColor(new Color(230, 230, 230, Opacity));
        g2D.fillOval(x + 12, y + 12, chipDiameter / 3 * 2, chipDiameter / 3 * 2);

        g2D.setColor(new Color(210, 210, 210, Opacity));
        g2D.fillOval(x + 23, y + 23, chipDiameter / 3, chipDiameter / 3);

        g2D.setColor(new Color(0, 0, 0, Opacity));
        g2D.drawOval(x, y, chipDiameter, chipDiameter);

        if (player == 1) {
            g2D.setColor(new Color(79, 96, 249, Opacity));
            g2D.fillOval(x, y, chipDiameter, chipDiameter);

            g2D.setColor(new Color(23, 41, 204, Opacity));
            g2D.fillOval(x + 12, y + 12, chipDiameter / 3 * 2, chipDiameter / 3 * 2);

            g2D.setColor(new Color(24, 36, 136, Opacity - a));
            g2D.fillOval(x + 23, y + 23, chipDiameter / 3, chipDiameter / 3);
        }

        if (player == 2) {
            g2D.setColor(new Color(232, 79, 76, Opacity));
            g2D.fillOval(x, y, chipDiameter, chipDiameter);

            g2D.setColor(new Color(205, 42, 38, Opacity));
            g2D.fillOval(x + 12, y + 12, chipDiameter / 3 * 2, chipDiameter / 3 * 2);

            g2D.setColor(new Color(161, 6, 3, Opacity - a));
            g2D.fillOval(x + 23, y + 23, chipDiameter / 3, chipDiameter / 3);
        }

        if (player == 3) {
            g2D.setColor(new Color(30, 30, 255, Opacity));
            g2D.fillOval(x, y, chipDiameter, chipDiameter);

            g2D.setColor(new Color(22, 185, 39, Opacity));
            g2D.fillOval(x + 23, y + 23, chipDiameter / 3, chipDiameter / 3);

            g2D.setColor(new Color(39, 160, 52, Opacity));
            g2D.fillOval(x + 12, y + 12, chipDiameter / 3 * 2, chipDiameter / 3 * 2);
        }

        if (player == 4) {
            g2D.setColor(new Color(255, 30, 30, Opacity));
            g2D.fillOval(x, y, chipDiameter, chipDiameter);

            g2D.setColor(new Color(22, 185, 39, Opacity));
            g2D.fillOval(x + 12, y + 12, chipDiameter / 3 * 2, chipDiameter / 3 * 2);

            g2D.setColor(new Color(39, 160, 52, Opacity));
            g2D.fillOval(x + 23, y + 23, chipDiameter / 3, chipDiameter / 3);
        }

        if (player == 5) {
            g2D.setColor(new Color(79, 96, 249, 100));
            g2D.fillOval(x, y, chipDiameter, chipDiameter);

            g2D.setColor(new Color(23, 41, 204, 100));
            g2D.fillOval(x + 12, y + 12, chipDiameter / 3 * 2, chipDiameter / 3 * 2);

            g2D.setColor(new Color(24, 36, 136, 100));
            g2D.fillOval(x + 23, y + 23, chipDiameter / 3, chipDiameter / 3);
        }

        if (player == 6) {
            g2D.setColor(new Color(232, 79, 76, 100));
            g2D.fillOval(x, y, chipDiameter, chipDiameter);

            g2D.setColor(new Color(205, 42, 38, 100));
            g2D.fillOval(x + 12, y + 12, chipDiameter / 3 * 2, chipDiameter / 3 * 2);

            g2D.setColor(new Color(161, 6, 3, 100));
            g2D.fillOval(x + 23, y + 23, chipDiameter / 3, chipDiameter / 3);
        }
    }
}
