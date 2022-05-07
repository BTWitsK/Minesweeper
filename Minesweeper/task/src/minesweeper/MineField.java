package minesweeper;
import java.util.*;

class MineField {
    Random random = new Random();
    Cell[][] field = new Cell[9][9];
    int boardSize = field.length - 1;

    public MineField(int mines) {
        field = Cell.loadField(boardSize);
        int currentMines = 0;
        while (currentMines < mines) {
            int i = random.nextInt(boardSize + 1);
            int j = random.nextInt(boardSize + 1);

            if (1 != field[i][j].isMine()) {
                field[i][j].makeMine();
                currentMines++;
            }
        }
        checkMines();
    }

    public void checkMines() {
        Cell currentCell;
        for (int i = 0; i <= boardSize; i++) {
            for (int j = 0; j <= boardSize; j++) {
                currentCell = field[i][j];
                currentCell.addNeighbors(field);
                currentCell.setMinesAround();
            }
        }
    }

    public void printField() {
        int rowNumber = 1;
        System.out.println(" |123456789|");
        System.out.println("_|_________|");
        for (Cell[] row : field) {
            System.out.printf("%d|", rowNumber++);
            for (Cell cell : row) {
                System.out.print(cell);
            }
            System.out.println("|");
        }
        System.out.println("_|_________|");
    }
}