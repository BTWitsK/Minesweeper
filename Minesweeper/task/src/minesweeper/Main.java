package minesweeper;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("How many mines do you want on the field?");
        MineField field = new MineField(scanner.nextInt());
        field.printField();
    }
}