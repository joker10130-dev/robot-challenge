package model;

public class Table {
    private static final int MAX_X = 4;
    private static final int MAX_Y = 4;
    private static final int MIN = 0;

    public boolean isValidPosition(int x, int y) {
        return x >= MIN && x <= MAX_X && y >= MIN && y <= MAX_Y;
    }
}
