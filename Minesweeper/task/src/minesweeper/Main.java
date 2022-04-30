package minesweeper;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        String[][] field = new String[9][9];
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < 9; i++) {
            for  (int j = 0; j < 9; j++) {
                field[i][j] = ".";
            }
        }

        System.out.println("How many mines do you want on the field?");
        int numberOfMines = scanner.nextInt();
        int currentMine = 0;

        while (currentMine < numberOfMines) {
            int i = random.nextInt(9);
            int j = random.nextInt(9);

            if (!field[i][j].equals("X")){
                field[i][j] = "X";
                currentMine++;
            }
        }

        for (String[] row : field) {
            for (String cell : row) {
                System.out.print(cell);
            }
            System.out.println();
        }
    }
}