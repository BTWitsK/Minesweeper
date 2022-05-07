package minesweeper;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("How many mines do you want on the field?");
        MineField field = new MineField(scanner.nextInt());
        while (field.isWon()) {
            field.printField();
            System.out.print("Set/delete mines marks (x and y coordinates):");
            field.guessMine(scanner.nextInt(), scanner.nextInt());
        }
    }
}