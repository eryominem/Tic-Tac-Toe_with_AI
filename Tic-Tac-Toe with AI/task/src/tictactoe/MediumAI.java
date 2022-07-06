package tictactoe;

import java.util.Random;

public class MediumAI extends Player {

    private final String name = "medium";
    private int[] coordinates = new int[2];
    boolean isCanWin;
    boolean isRivalCanWin;

    @Override
    public String getName() {
        return name;
    }

    public void turn() {
        int x;
        int y;
        checkWin();
        x = coordinates[0];
        y = coordinates[1];
        System.out.println("Making move level \"medium\"");
        Field.field[x][y] = Field.currentPlayer;
        Field.printField();
        Field.isEndOfGame = Field.isEndOfGame();
        turnSwitch();
    }

    public void checkWin() {
        Random random = new Random();
        char[] players = new char[]{Field.currentPlayer, Field.nextPlayer};
        int count;
        boolean isAnyCellEmpty;
        for (char player : players) {
            for (int i = 0; i < Field.SIZE; i++) {
                count = 0;
                isAnyCellEmpty = false;
                for (int j = 0; j < Field.SIZE; j++) {
                    if (Field.field[i][j] == Field.EMPTY) {
                        coordinates[0] = i;
                        coordinates[1] = j;
                        isAnyCellEmpty = true;
                    }
                    if (Field.field[i][j] == player) count++;
                }
                if (count == Field.SIZE - 1 && isAnyCellEmpty) {
                    return;
                }
            }
            for (int i = 0; i < Field.SIZE; i++) {
                count = 0;
                isAnyCellEmpty = false;
                for (int j = 0; j < Field.SIZE; j++) {
                    if (Field.field[j][i] == Field.EMPTY) {
                        coordinates[0] = j;
                        coordinates[1] = i;
                        isAnyCellEmpty = true;
                    }
                    if (Field.field[j][i] == player) count++;
                }
                if (count == Field.SIZE - 1 && isAnyCellEmpty) {
                    return;
                }
            }
            count = 0;
            isAnyCellEmpty = false;
            for (int i = 0; i < Field.SIZE; i++) {
                if (Field.field[i][i] == Field.EMPTY) {
                    coordinates[0] = i;
                    coordinates[1] = i;
                    isAnyCellEmpty = true;
                }
                if (Field.field[i][i] == player) count++;
            }
            if (count == Field.SIZE - 1 && isAnyCellEmpty) {
                return;
            }
            count = 0;
            isAnyCellEmpty = false;
            for (int i = 0; i < Field.SIZE; i++) {
                if (Field.field[i][Field.SIZE - 1 - i] == Field.EMPTY) {
                    coordinates[0] = i;
                    coordinates[1] = Field.SIZE - 1 - i;
                    isAnyCellEmpty = true;
                }
                if (Field.field[i][Field.SIZE - 1 - i] == player) count++;
            }
            if (count == Field.SIZE - 1 && isAnyCellEmpty) {
                return;
            }
        }
        do {
            coordinates[0] = random.nextInt(3);
            coordinates[1] = random.nextInt(3);
        } while (Field.field[coordinates[0]][coordinates[1]] != Field.EMPTY);
    }
}