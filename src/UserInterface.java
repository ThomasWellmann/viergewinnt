import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.File;

public class UserInterface {
    static JFrame startseiteWindow;
    static JFrame gameWindow;
    static JFrame nameEingabeWindow;

    public UserInterface() {
        startseiteWindow = new JFrame();

        startseiteWindow.setSize(800, 600);
        startseiteWindow.setTitle("VierGewinnt Startseite");
        startseiteWindow.setResizable(false);
    }

    private void initializeButtonsS() {

        JButton buttonS = new JButton("Locales Spiel");
        buttonS.setBounds(325, 250, 150, 50);
        buttonS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startseiteWindow.setVisible(false);

                nameEingabeWindow = new JFrame();
                nameEingabeWindow.setSize(800, 600);
                nameEingabeWindow.setTitle("VierGewinnt");
                nameEingabeWindow.setResizable(false);
                initializeN();
            }
        });
        buttonS.setVisible(true);
        startseiteWindow.add(buttonS);
    }

    private void initializeButtonsN() {

        JButton buttonN = new JButton("Start");
        buttonN.setBounds(350, 365, 100, 50);
        buttonN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameEingabeWindow.setVisible(false);

                gameWindow = new JFrame();
                gameWindow.setSize(800, 600);
                gameWindow.setTitle("VierGewinnt");
                gameWindow.setResizable(false);
                Game.startNewGame();
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
                nameEingabeWindow.setVisible(false);
                startseiteWindow.setVisible(true);
            }
        });
        startseiteButtom.setVisible(true);
        nameEingabeWindow.add(startseiteButtom);
        NameEingabe.setTextFild();
    }

    private void initializeButtons() {

        JButton startseiteButtom = new JButton("Startseite");
        startseiteButtom.setBounds(20, 30, 100, 50);
        startseiteButtom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                    int col;
                    if(currentPoint.getX() > 120 && currentPoint.getX() < 190) {
                        col = 0;
                    } else if(currentPoint.getX() > 195 && currentPoint.getX() < 265) {
                        col = 1;
                    } else if(currentPoint.getX() > 270 && currentPoint.getX() < 340) {
                        col = 2;
                    } else if(currentPoint.getX() > 345 && currentPoint.getX() < 415) {
                        col = 3;
                    } else if(currentPoint.getX() > 420 && currentPoint.getX() < 490) {
                        col = 4;
                    } else if(currentPoint.getX() > 495 && currentPoint.getX() < 565) {
                        col = 5;
                    } else if(currentPoint.getX() > 570 && currentPoint.getX() < 640) {
                        col = 6;
                    } else {
                        return;
                    }
                    row = Game.getRowForPreview(col);
                    Board.previewCol = col;
                    Board.previewRow = row;

                }
            }
        });
        if (Game.winner > 0) {
            return;
        } else {
            board.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (Board.previewCol >= 0) {
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
        gameWindow.setVisible(true);
    }
    public void initializeN() {
        initializeButtonsN();
        initializeNameEingabe();
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