package tictactoe;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Player {

    private String name;

    public String getName() {
        return name;
    }

    static Player[] possiblePlayers = {
            new Human(),
            new EasyAI(),
            new MediumAI(),
            new HardAI()
    };
    public abstract void turn();

    public void turnSwitch() {
        if (Field.currentPlayer == Field.CROSS) {
            Field.currentPlayer = Field.ZERO;
            Field.nextPlayer = Field.CROSS;
        } else {
            Field.currentPlayer = Field.CROSS;
            Field.nextPlayer = Field.ZERO;
        }
    }

    public static Player[] playersChoose() {
        Player[] actualPlayers = new Player[2];
        String[] input = Input.gameModeInput();
        if ("exit".equals(input[0])) {
            System.exit(0);
        }
        for (Player player : possiblePlayers) {
            if (input[1].equals(player.getName()))
                actualPlayers[0] = player;
            if (input[2].equals(player.getName()))
                actualPlayers[1] = player;
        }
        return actualPlayers;
    }

    protected static boolean isEmptyCellsLeft(char[][] gameField) {
        boolean gotEmptiesCells = false;
        for (int i = 0; i < Field.SIZE; i++) {
            for (int j = 0; j < Field.SIZE; j++) {
                if (gameField[i][j] == Field.EMPTY) {
                    gotEmptiesCells = true;
                    break;
                }
            }
        }
        return gotEmptiesCells;
    }

    /**
     * Check possible win on this game board for this player
     * @param gameField - this game board
     * @param playerSymbol - checked player symbol
     * @return boolean true if player win
     */
    protected static boolean isWin(char[][] gameField, char playerSymbol) {
        boolean leftRightDiag = true;
        boolean rightLeftDiag = true;

        for (int i = 0; i < Field.SIZE; i++) {
            leftRightDiag &= (gameField[i][i] == playerSymbol);
            rightLeftDiag &= (gameField[Field.SIZE - i - 1][i] == playerSymbol);
        }

        boolean cols = false;
        boolean rows = false;

        for (int col = 0; col < Field.SIZE; col++) {
            cols = true;
            rows = true;

            for (int row = 0; row < Field.SIZE; row++) {
                cols &= (gameField[col][row] == playerSymbol);
                rows &= (gameField[row][col] == playerSymbol);
            }
            if (cols || rows) {
                break;
            }
        }

        return leftRightDiag || rightLeftDiag || cols || rows;
    }
}