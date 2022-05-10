package minesweeper;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("How many mines do you want on the field?");
        MineField field = new MineField(scanner.nextInt());
        while (field.notOver()) {
            field.printField();
            System.out.print("Set/unset mines marks or claim a cell as free:");
            String[] userGuess = scanner.nextLine().split(" ");
            if (userGuess[2].equals("free")) {
                field.guessFree(Integer.parseInt(userGuess[1]), Integer.parseInt(userGuess[0]));
            } else {
                field.markMine(Integer.parseInt(userGuess[1]), Integer.parseInt(userGuess[0]));
            }
        }
    }
}