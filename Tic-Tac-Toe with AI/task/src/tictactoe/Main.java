package tictactoe;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Player[] players = Player.playersChoose();
        Field.createField();
        Field.printField();
        while (true) {
            for (Player player : players) {
                player.turn();
                if (Field.isEndOfGame) return;
            }
        }
    }
}
