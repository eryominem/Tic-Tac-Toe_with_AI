package tictactoe;

import java.util.Scanner;

public class Input {

    public static String coordinatesInput() {
        Scanner sc = new Scanner(System.in);
        String coordinates;
        do {
            System.out.print("Enter the coordinates: ");
            System.out.println();
            coordinates = sc.nextLine();
        } while (isCoordinatesIncorrect(coordinates));
        return coordinates;
    }

    public static boolean isCoordinatesIncorrect(String coordinates) {
        if (coordinates.length() != 3 || coordinates.charAt(1) != ' ') {
            System.out.println("Incorrect input, try again!");
            return true;
        } else if (!Character.toString(coordinates.charAt(0)).matches("\\d")
                || !Character.toString(coordinates.charAt(2)).matches("\\d")) {
            System.out.println("You should enter numbers!");
            return true;
        } else if (!Character.toString(coordinates.charAt(0)).matches("[1-3]")
                || !Character.toString(coordinates.charAt(2)).matches("[1-3]")) {
            System.out.println("Coordinates should be from 1 to 3!");
            return true;
        }
        return false;
    }

    public static String[] gameModeInput() {
        Scanner sc = new Scanner(System.in);
        String[] input;
        while (true) {
            System.out.print("Input command: ");
            input = sc.nextLine().split(" ");
            if ("exit".equals(input[0])) {
                break;
            }
            if (input.length == 3) {
                if (!"start".equals(input[0])) {
                    System.out.println("Bad parameters!");
                    continue;
                }
                if (!"user".equals(input[1]) && !"easy".equals(input[1]) && !"medium".equals(input[1]) && !"hard".equals(input[1])) {
                    System.out.println("Bad parameters!");
                    continue;
                }
                if (!"user".equals(input[2]) && !"easy".equals(input[2]) && !"medium".equals(input[2]) && !"hard".equals(input[2])) {
                    System.out.println("Bad parameters!");
                } else {
                    break;
                }
            } else {
                System.out.println("Bad parameters!");
            }
        }
        return input;
    }
}