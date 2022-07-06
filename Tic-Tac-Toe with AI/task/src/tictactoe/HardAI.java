package tictactoe;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HardAI extends Player {

    private final String name = "hard";
    private int[] coordinates = new int[2];

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void turn() {
        int x, y;
        x = coordinates[0];
        y = coordinates[1];
        System.out.println("Making move level \"hard\"");

        Move bestMove = minimax(Field.field, Field.nextPlayer, Field.currentPlayer);
        Field.field[bestMove.index[0]][bestMove.index[1]] = Field.currentPlayer;
        Field.printField();
        Field.isEndOfGame = Field.isEndOfGame();
        turnSwitch();
    }

    /**
     * Implementation of the minimax algorithm
     * @param gameField - the state of the playing field where we are looking for the best move
     * @param callingPlayer - character of the player being checked
     * @param currentPlayer - symbol of current player
     * @return move object - the best move in this state of the playing field
     */
    public Move minimax(char[][] gameField, char callingPlayer, char currentPlayer) {
        List<Move> moves = new ArrayList<>();
        char enemySymbol = (callingPlayer == 'X') ? 'O' : 'X';
        char callingSymbol = (callingPlayer == 'X') ? 'X' : 'O';
        char enemyPlayer = (currentPlayer == 'X') ? 'O' : 'X';

        // Counting the score of this move
        if (isWin(gameField, enemySymbol)) {
            return new Move(-10);
        } else if (isWin(gameField, callingSymbol)) {
            return new Move(10);
        } else if (!isEmptyCellsLeft(gameField)) {
            return new Move(0);
        }

        for (int i = 0; i < Field.SIZE; i++) {
            for (int j = 0; j < Field.SIZE; j++) {
                if (gameField[i][j] == Field.EMPTY) {
                    // let's make a possible move
                    Move move = new Move();
                    move.index = new int[]{i, j};
                    gameField[i][j] = currentPlayer;
                    Move result = minimax(gameField, callingPlayer, enemyPlayer);
                    // save the score for the minimax
                    move.score = result.score;
                    // then revert the occupied place back to empty, so next guesses can go on
                    gameField[i][j] = Field.EMPTY;
                    moves.add(move);
                }
            }
        }

        // Choose the move with the highest score
        int bestMove = 0;

        if (currentPlayer == callingPlayer) {
            int bestScore = -10000;
            for (int i = 0; i < moves.size(); i++) {
                if (moves.get(i).score > bestScore) {
                    bestScore = moves.get(i).score;
                    bestMove = i;
                }
            }
        } else {
            int bestScore = 10000;
            for (int i = 0; i < moves.size(); i++) {
                if (moves.get(i).score < bestScore) {
                    bestScore = moves.get(i).score;
                    bestMove = i;
                }
            }
        }

        // minimax returns the best move to the latest function caller
        return moves.get(bestMove);
    }
}