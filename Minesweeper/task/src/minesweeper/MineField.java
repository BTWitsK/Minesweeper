package minesweeper;
import java.util.*;

class MineField {
    Random random = new Random();
    Cell[][] field = new Cell[9][9];
    int boardSize = field.length - 1;
    int currentMines = 0;
    private int safeCells = field.length * field.length;
    private boolean isWon = false;
    private boolean failed = false;



    public MineField(int mines) {
        field = Cell.loadField(boardSize);
        while (currentMines < mines) {
            int i = random.nextInt(boardSize + 1);
            int j = random.nextInt(boardSize + 1);

            if (1 != field[i][j].isMine()) {
                field[i][j].makeMine();
                currentMines++;
            }
            safeCells -= mines;
        }
        checkMines();
    }

    public void markMine(int x, int y) {
        Cell currentCell = field[x - 1][y - 1];
        //if (currentCell.toString().matches("\\d")) {
        //    System.out.println("\nThere is a number here!");
        //} else {
            if (currentCell.isGuessed()) {
                if (currentCell.isMine() > 0) {
                    currentMines--;
                }
            } else {
                if (currentCell.isMine() > 0) {
                    currentMines++;
                }
            }
        //}
    }
    public void exploreCell(int x, int y) {
        Cell currentCell = field[x - 1][y - 1];

        if (currentCell.exploreCell() == -1) {
            failed = true;
        }
    }

    public boolean notOver() {
        isWon = currentMines > 0;
        if (!isWon) {
            System.out.println("Congratulations! You found all the mines!");
        }
        if (!failed) {
            System.out.println("You stepped on a mine and failed!");
        }
        return isWon;
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
        System.out.println();
        System.out.println(" |123456789|");
        System.out.println("-|---------|");
        for (Cell[] row : field) {
            System.out.printf("%d|", rowNumber++);
            for (Cell cell : row) {
                System.out.print(cell);
            }
            System.out.println("|");
        }
        System.out.println("-|---------|");
    }
}