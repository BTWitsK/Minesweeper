package minesweeper;
import java.util.*;

public class Cell {
    private boolean isMine;
    private final boolean isCorner;
    private final boolean isEdge;
    final int xAxis;
    final int yAxis;
    private int minesAround = 0;
    private String display;
    private final ArrayList<Cell> neighbors = new ArrayList<>();

    public Cell(int x, int y, int boardSize) {
        isMine = false;
        xAxis = x;
        yAxis = y;
        display = ".";
        isCorner = (x == 0 || x == boardSize) && (y == 0 || y == boardSize);
        isEdge = (x == 0 || x == boardSize || y == 0 || y == boardSize) && !isCorner;
    }

    @Override
    public String toString() {
        return minesAround == 0 || isMine ? display : String.valueOf(minesAround);
    }

    public int isMine() {
        return isMine ? 1 : 0;
    }

    public void makeMine() {
        isMine = true;
        display = "X";
    }

    public void setMinesAround() {
        neighbors.forEach(cell -> this.minesAround += cell.isMine());
    }

    public void addNeighbors(Cell[][] field) {
        if (this.isCorner) {
            checkCorner(field);
        }
        else if (this.isEdge) {
            checkEdge(field);
        } else {
            checkMiddle(field);
        }
    }

    public void checkCorner(Cell[][] field) {
        try {
            neighbors.add(field[xAxis + 1][yAxis]);
            try {
                neighbors.add(field[xAxis][yAxis + 1]);
                neighbors.add(field[xAxis + 1][yAxis + 1]);
            } catch (ArrayIndexOutOfBoundsException x) {
                neighbors.add(field[xAxis][yAxis - 1]);
                neighbors.add(field[xAxis + 1][yAxis - 1]);
            }
        } catch (ArrayIndexOutOfBoundsException y) {
            neighbors.add(field[xAxis - 1][yAxis]);
            try {
                neighbors.add(field[xAxis - 1][yAxis + 1]);
                neighbors.add(field[xAxis][yAxis + 1]);
            } catch (ArrayIndexOutOfBoundsException z) {
                neighbors.add(field[xAxis - 1][yAxis - 1]);
                neighbors.add(field[xAxis][yAxis - 1]);
            }
        }

    }

    public void checkEdge(Cell[][] field) {
        try {
            neighbors.add(field[xAxis - 1][yAxis]);
            neighbors.add(field[xAxis + 1][yAxis]);
            try {
                neighbors.add(field[xAxis - 1][yAxis - 1]);
                neighbors.add(field[xAxis][yAxis - 1]);
                neighbors.add(field[xAxis + 1][yAxis - 1]);
            } catch (ArrayIndexOutOfBoundsException x) {
                neighbors.add(field[xAxis - 1][yAxis + 1]);
                neighbors.add(field[xAxis][yAxis + 1]);
                neighbors.add(field[xAxis + 1][yAxis + 1]);
            }
        } catch (ArrayIndexOutOfBoundsException y) {
            neighbors.add(field[xAxis][yAxis - 1]);
            neighbors.add(field[xAxis][yAxis + 1]);
            try {
                neighbors.add(field[xAxis + 1][yAxis - 1]);
                neighbors.add(field[xAxis + 1][yAxis]);
                neighbors.add(field[xAxis + 1][yAxis + 1]);
            } catch (ArrayIndexOutOfBoundsException z) {
                neighbors.add(field[xAxis - 1][yAxis - 1]);
                neighbors.add(field[xAxis - 1][yAxis + 1]);
            }
        }

    }

    public void checkMiddle(Cell[][] field) {
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                neighbors.add(field[xAxis + i][yAxis + j]);
            }
        }
    }

    public static Cell[][] loadField(int boardSize) {
        Cell[][] field = new Cell[boardSize + 1][boardSize + 1];
        for (int i = 0; i <= boardSize; i++) {
            for (int j = 0; j <= boardSize; j++) {
                field[i][j] = new Cell(i, j, boardSize);
            }
        }
        return field;
    }
}