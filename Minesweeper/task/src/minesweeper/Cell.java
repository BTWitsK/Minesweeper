package minesweeper;
import java.util.*;

public class Cell {
    private boolean isMine;
    private final boolean isCorner;
    private final boolean isEdge;
    private boolean isGuessed;
    private boolean isFree;
    private boolean isVisited;
    final int xAxis;
    final int yAxis;
    private int minesAround = 0;
    private String display;
    private final ArrayList<Cell> neighbors = new ArrayList<>();
    private final ArrayList<Cell> mines = new ArrayList<>();

    public Cell(int x, int y, int boardSize) {
        isMine = false;
        isGuessed = false;
        isVisited = false;
        isFree = true;
        xAxis = x;
        yAxis = y;
        display = ".";
        isCorner = (x == 0 || x == boardSize) && (y == 0 || y == boardSize);
        isEdge = (x == 0 || x == boardSize || y == 0 || y == boardSize) && !isCorner;
    }

    @Override
    public String toString() {
        return display;
    }

    public int exploreCell() {
        if (isMine) {
            mines.forEach(cell -> cell.display = "X");
            return -1;
        }
        if (isFree && minesAround != 0) {
            display = String.valueOf(minesAround);
        } else if (isFree) {
            display = "/";
        }
        return 1;
    }

    public int isMine() {
        return isMine ? 1 : 0;
    }

    public int getMinesAround() {
        return minesAround;
    }

    public boolean isGuessed() {
        isGuessed = !isGuessed;
        display = isGuessed ? "*":".";
        return isGuessed;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void markVisited() {
        isVisited = true;
        display = minesAround > 0 ? String.valueOf(minesAround) : "/";
    }

    public void makeMine() {
        isMine = true;
        isFree = false;
        mines.add(this);
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

    public ArrayList<Cell> getNeighbors() {
        return neighbors;
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