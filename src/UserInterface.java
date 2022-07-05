import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.File;

public class UserInterface {
    static JFrame startseiteWindow;
    static JFrame gameWindow;
    static JFrame nameEingabeWindow;
    static JButton buttonN = new JButton("Start");

    public UserInterface() {
        startseiteWindow = new JFrame();

        startseiteWindow.setSize(800, 600);
        startseiteWindow.setTitle("4 Gewinnt Startseite");
        startseiteWindow.setResizable(false);
    }
    public static boolean mouseOutOfBoard;

    private void initializeButtonsS() {

        JButton buttonLS = new JButton("Locales Spiel");
        buttonLS.setBounds(325, 250, 150, 50);
        buttonLS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startseiteWindow.dispose();

                nameEingabeWindow = new JFrame();
                nameEingabeWindow.setSize(800, 600);
                nameEingabeWindow.setTitle("4 Gewinnt Name Eingabe");
                nameEingabeWindow.setResizable(false);
                initializeN();
            }
        });
        buttonLS.setVisible(true);
        startseiteWindow.add(buttonLS);

        JButton buttonOS = new JButton("Online Spiel");
        buttonOS.setBounds(325, 310, 150, 50);
        buttonOS.setVisible(true);
        buttonOS.setEnabled(false);
        startseiteWindow.add(buttonOS);
    }

    private void initializeButtonsN() {
        buttonN.setBounds(350, 365, 100, 50);
        buttonN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameEingabeWindow.dispose();

                gameWindow = new JFrame();
                gameWindow.setSize(800, 600);
                gameWindow.setTitle("4 Gewinnt Spiel");
                gameWindow.setResizable(false);
                gameWindow.setVisible(true);
                initialize();
            }
        });
        buttonN.setVisible(true);
        nameEingabeWindow.add(buttonN);

        JButton startseiteButtom = new JButton("Startseite");
        startseiteButtom.setBounds(20, 30, 100, 50);
        startseiteButtom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameEingabeWindow.dispose();
                startseiteWindow.setVisible(true);
            }
        });
        startseiteButtom.setVisible(true);
        nameEingabeWindow.add(startseiteButtom);
        NameEingabe.setTextFild();
    }

    public static void hideStartButton() {
        buttonN.setEnabled(false);
    }

    public static void showStartButton() {
        buttonN.setEnabled(true);
    }

    private void initializeButtons() {

        JButton startseiteButtom = new JButton("Startseite");
        startseiteButtom.setBounds(20, 30, 100, 50);
        startseiteButtom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameWindow.dispose();
                startseiteWindow.setVisible(true);
            }
        });
        startseiteButtom.setVisible(true);
        gameWindow.add(startseiteButtom);

        JButton newGameButton = new JButton("Neues Spiel");
        newGameButton.setBounds(680, 30, 100, 50);
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.startNewGame();
            }
        });
        newGameButton.setVisible(true);
        gameWindow.add(newGameButton);
    }

    private void initializeBoard() {
        Board board = new Board();
        board.setBounds(0,0,800,600);
        gameWindow.add(board);
        board.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseEntered(e);
                Point currentPoint = e.getPoint();
                if (Game.winner > 0) {
                    return;
                } else {
                    int row;
                    int col = 0;
                    if(currentPoint.getX() > 130 && currentPoint.getX() < 203) {
                        col = 1;
                    } else if(currentPoint.getX() > 204 && currentPoint.getX() < 277) {
                        col = 2;
                    } else if(currentPoint.getX() > 278 && currentPoint.getX() < 351) {
                        col = 3;
                    } else if(currentPoint.getX() > 352 && currentPoint.getX() < 425) {
                        col = 4;
                    } else if(currentPoint.getX() > 426 && currentPoint.getX() < 499) {
                        col = 5;
                    } else if(currentPoint.getX() > 500 && currentPoint.getX() < 573) {
                        col = 6;
                    } else if(currentPoint.getX() > 574 && currentPoint.getX() < 647) {
                        col = 7;
                    } else {
                        mouseOutOfBoard = true;
                    }
                    if (col > 0) {
                        mouseOutOfBoard = false;
                        row = Game.getRowForPreview(col - 1);
                        Board.previewCol = col - 1;
                        Board.previewRow = row;
                    }
                }
            }
        });
        if (Game.winner > 0 || mouseOutOfBoard) {
            return;
        } else {
            board.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (Board.previewCol >= 0 && !mouseOutOfBoard) {
                        Board.previewRow = Board.previewRow - 1;
                        Game.playChip(Board.previewCol);
                    }
                }
            });
        }
    }

    public void initialize() {
        initializeButtons();
        initializeBoard();
        Game.startNewGame();
    }
    public void initializeN() {
        initializeButtonsN();
        initializeNameEingabe();
        NameEingabe.player1Name.setText("Spieler 1");
        NameEingabe.player2Name.setText("Spieler 2");
        nameEingabeWindow.setVisible(true);
    }

    public void initializeS() {
        initializeButtonsS();
        initializeStartseite();
        startseiteWindow.setVisible(true);
    }
    private void initializeNameEingabe() {
        NameEingabe nameEingabe = new NameEingabe();
        nameEingabe.setBounds(0,0,800,600);
        nameEingabeWindow.add(nameEingabe);
    }
    private void initializeStartseite() {
        Startseite startseite = new Startseite();
        startseite.setBounds(0,0,800,600);
        startseiteWindow.add(startseite);
    }
}