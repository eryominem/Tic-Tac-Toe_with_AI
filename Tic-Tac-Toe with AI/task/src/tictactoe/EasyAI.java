package tictactoe;

import java.util.Random;

public class EasyAI extends Player {

    private final String name = "easy";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void turn() {
        Random random = new Random();
        int x;
        int y;
        do {
            x = random.nextInt(3);
            y = random.nextInt(3);
        } while (Field.field[x][y] != Field.EMPTY);
        System.out.println("Making move level \"easy\"");
        Field.field[x][y] = Field.currentPlayer;
        Field.printField();
        Field.isEndOfGame = Field.isEndOfGame();
        turnSwitch();
    }
}