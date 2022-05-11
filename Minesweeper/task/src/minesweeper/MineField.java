package minesweeper;
import java.util.*;

class MineField {
    Random random = new Random();
    Cell[][] field = new Cell[9][9];
    int boardSize = field.length - 1;
    int currentMines = 0;
    private int safeCells = field.length * field[0].length;
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
        if (currentCell.isGuessed()) {
            if (currentCell.isMine() > 0) {
                currentMines--;
            }
        } else {
            if (currentCell.isMine() > 0) {
                currentMines++;
            }
        }
    }

    public void exploreCell(int x, int y) {
        Cell currentCell = field[x - 1][y - 1];

        if (currentCell.exploreCell() == -1) {
            failed = true;
        } else {
            freeCells(currentCell);
        }
    }
    //floodfill algorithm
    public void freeCells(Cell currentCell) {
        if (currentCell.isVisited()) {
            return;
        }
        if (currentCell.isMine() > 0) {
            return;
        }
        currentCell.markVisited();
        if (currentCell.getMinesAround() > 0) {
            return;
        }
        currentCell.getNeighbors().forEach(this::freeCells);
        safeCells--;
    }

    public boolean notOver() {
        isWon = currentMines < 1 || safeCells < 1;
        if (isWon) {
            System.out.println("Congratulations! You found all the mines!");
            printField();
        }
        if (failed) {
            System.out.println("You stepped on a mine and failed!");
            printField();
            isWon = true;
        }
        return !isWon;
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