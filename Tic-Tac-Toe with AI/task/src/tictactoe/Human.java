package tictactoe;

public class Human extends Player {

    private final String name = "user";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void turn() {
        String coordinates;
        do {
            coordinates = Input.coordinatesInput();
        } while (Field.isCellOccupied(coordinates));
        int x = Character.getNumericValue(coordinates.charAt(0)) - 1;
        int y = Character.getNumericValue(coordinates.charAt(2)) - 1;
        Field.field[x][y] = Field.currentPlayer;
        Field.printField();
        Field.isEndOfGame = Field.isEndOfGame();
        turnSwitch();
    }
}