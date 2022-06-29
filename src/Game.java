import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Game extends JComponent {

    public static final int VIER_GEWINNT = 4;
    public static final int ROWS = 6;
    public static final int COLUMNS = 7;
    public static final int NUMBER_OF_PLAYERS = 2;
    public static String nameSpieler1;
    public static String nameSpieler2;
    public static String noName = "no name";
    private static final int[][] EMPTY_BOARD = {
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0}
    };
    public static void getSpielerName() {
        nameSpieler1 = NameEingabe.player1Name.getText();
        nameSpieler2 = NameEingabe.player2Name.getText();
    }
    public static String getAktuelleSpieler() {
        if (activePlayer == 1)
            return nameSpieler1;
        if (activePlayer == 2)
            return nameSpieler2;
        return noName;
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        try {
            g2D.setFont(Font.createFont(Font.TRUETYPE_FONT, new File("/Users/thomascesar/Downloads/Aclonica/Aclonica-Regular.ttf")).deriveFont(Font.PLAIN, 20));
        } catch (Exception ex) {
            ex.printStackTrace();
            g2D.setFont(new Font("Default", Font.PLAIN, 20));
        }
    }

    public static int[][] board = EMPTY_BOARD;

    public static int activePlayer = 1;
    public static int winner = 0;

    public static String statusMessage = "";

    public static void startNewGame() {
        getSpielerName();
        activePlayer = 1;
        winner = 0;
        statusMessage = "";
        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                board[row][column] = 0;
            }
        }

    }

    public static void playChip(int column) {
        if (winner > 0) {
            return;
        }

        int numberOfChipsInColumn = getNumberOfChipsInColumn(column);
        if (numberOfChipsInColumn < 6) {

            int rowForChip = 5 - numberOfChipsInColumn;
            board[rowForChip][column] = activePlayer;

            if (activePlayer == 1) {
                activePlayer = 2;
            } else {
                activePlayer = 1;
            }

            checkBoard();
        }
    }

    private static int getNumberOfChipsInColumn(int column) {
        int numberOfChipsInColumn = 0;
        for (int row = 5; row >= 0; row--) {
            if (board[row][column] > 0 && board[row][column] < 5) {
                numberOfChipsInColumn = numberOfChipsInColumn + 1;
            }
            if (row != Board.previewRow && column != Board.previewCol) {

            }
        }
        return numberOfChipsInColumn;
    }

    protected static int getRowForPreview(int Column) {
        int numberOfChipsInColumn = getNumberOfChipsInColumn(Column);
        if (numberOfChipsInColumn < 6) {
            int rowForChip = 5 - numberOfChipsInColumn;
            return rowForChip;
        }
        return -1;
    }

    private static int checkForDraw() {
        if (getNumberOfChipsInColumn(0) == 6 && getNumberOfChipsInColumn(1) == 6
                && getNumberOfChipsInColumn(2) == 6 && getNumberOfChipsInColumn(3) == 6
                && getNumberOfChipsInColumn(4) == 6 && getNumberOfChipsInColumn(5) == 6
                && getNumberOfChipsInColumn(6) == 6) {
            return 3;
        }
        return 0;
    }

    private static int checkRowsForWinner() {
        for (int row = 0; row < 6; row++) {
            for (int column = 0; column < 4; column++) {
                if (board[row][column] == 1 && board[row][column + 1] == 1 && board[row][column + 2] == 1 && board[row][column + 3] == 1) {
                    board[row][column] = 3;
                    board[row][column + 1] = 3;
                    board[row][column + 2] = 3;
                    board[row][column + 3] = 3;
                    return 1;
                }
                if (board[row][column] == 2 && board[row][column + 1] == 2 && board[row][column + 2] == 2 && board[row][column + 3] == 2) {
                    board[row][column] = 4;
                    board[row][column + 1] = 4;
                    board[row][column + 2] = 4;
                    board[row][column + 3] = 4;
                    return 2;
                }
            }
        }
        return 0;
    }

    private static int checkColumnsForWinner() {

        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 7; column++) {
                if (board[row][column] == 1 && board[row + 1][column] == 1 && board[row + 2][column] == 1 && board[row + 3][column] == 1) {
                    board[row][column] = 3;
                    board[row + 1][column] = 3;
                    board[row + 2][column] = 3;
                    board[row + 3][column] = 3;
                    return 1;
                }
                if (board[row][column] == 2 && board[row + 1][column] == 2 && board[row + 2][column] == 2 && board[row + 3][column] == 2) {
                    board[row][column] = 4;
                    board[row + 1][column] = 4;
                    board[row + 2][column] = 4;
                    board[row + 3][column] = 4;
                    return 2;
                }
            }
        }
        return 0;
    }


    private static int checkUpperLeftToLowerRightForWinner() {

        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 4; column++) {
                if (board[row][column] == 1 && board[row + 1][column + 1] == 1 && board[row + 2][column + 2] == 1 && board[row + 3][column + 3] == 1) {
                    board[row][column] = 3;
                    board[row + 1][column + 1] = 3;
                    board[row + 2][column + 2] = 3;
                    board[row + 3][column + 3] = 3;
                    return 1;
                }
                if (board[row][column] == 2 && board[row + 1][column + 1] == 2 && board[row + 2][column + 2] == 2 && board[row + 3][column + 3] == 2) {
                    board[row][column] = 4;
                    board[row + 1][column + 1] = 4;
                    board[row + 2][column + 2] = 4;
                    board[row + 3][column + 3] = 4;
                    return 2;
                }
            }
        }
        return 0;
    }

    private static int checkLowerLeftToUpperRight() {

        for (int row = 3; row < 6; row++) {
            for (int column = 0; column < 4; column++) {
                if (board[row][column] == 1 && board[row - 1][column + 1] == 1 && board[row - 2][column + 2] == 1 && board[row - 3][column + 3] == 1) {
                    board[row][column] = 3;
                    board[row - 1][column + 1] = 3;
                    board[row - 2][column + 2] = 3;
                    board[row - 3][column + 3] = 3;
                    return 1;
                }
                if (board[row][column] == 2 && board[row - 1][column + 1] == 2 && board[row - 2][column + 2] == 2 && board[row - 3][column + 3] == 2) {
                    board[row][column] = 4;
                    board[row - 1][column + 1] = 4;
                    board[row - 2][column + 2] = 4;
                    board[row - 3][column + 3] = 4;
                    return 2;
                }
            }
        }
        return 0;
    }
    public static String getWinnerName(int winner) {
        if (winner == 1) {
            return nameSpieler1;
        } else if (winner == 2) {
            return nameSpieler2;
        }
        return noName;
    }
    private static void checkBoard() {
        int noWinner = checkForDraw();
        if (noWinner == 3) {
            statusMessage = "Niemand hat gewonnen";
            winner = noWinner;
            activePlayer = 0;
            return;
        }

        int winnerInRow = checkRowsForWinner();
        getWinnerName(winnerInRow);
        String winnerNameInRow = getWinnerName(winnerInRow);
        if (winnerInRow > 0) {
            statusMessage = winnerNameInRow + " hat gewonnen";
            winner = winnerInRow;
            activePlayer = 0;
            return;
        }

        // check columns for a winner
        int winnerInColumns = checkColumnsForWinner();
        getWinnerName(winnerInColumns);
        String winnerNameInColumns = getWinnerName(winnerInColumns);
        if (winnerInColumns > 0) {
            statusMessage = winnerNameInColumns + " hat gewonnen";
            winner = winnerInColumns;
            activePlayer = 0;
            return;
        }

        // check diagonals for a winner
        int winnerInDiagonals = checkUpperLeftToLowerRightForWinner();
        getWinnerName(winnerInDiagonals);
        String winnerNameInDiagoals = getWinnerName(winnerInDiagonals);
        if (winnerInDiagonals > 0) {
            statusMessage = winnerNameInDiagoals + " hat gewonnen";
            winner = winnerInDiagonals;
            activePlayer = 0;

            return;
        }

        winnerInDiagonals = checkLowerLeftToUpperRight();
        getWinnerName(winnerInDiagonals);
        winnerNameInDiagoals = getWinnerName(winnerInDiagonals);
        if (winnerInDiagonals > 0) {
            getWinnerName(winnerInDiagonals);
            statusMessage = winnerNameInDiagoals + " hat gewonnen";
            winner = winnerInDiagonals;
            activePlayer = 0;
        }
    }
}
