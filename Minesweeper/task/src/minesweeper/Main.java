package minesweeper;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("How many mines do you want on the field?");
        MineField field = new MineField(Integer.parseInt(scanner.nextLine()));
        do {
            field.printField();
            System.out.print("Set/unset mines marks or claim a cell as free:");
            String[] userGuess = scanner.nextLine().split(" ");
            if (userGuess[2].equals("free")) {
                field.exploreCell(Integer.parseInt(userGuess[1]), Integer.parseInt(userGuess[0]));
            } else {
             field.markMine(Integer.parseInt(userGuess[1]), Integer.parseInt(userGuess[0]));
            }
        }
        while (field.notOver());
    }
}