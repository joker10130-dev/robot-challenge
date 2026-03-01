package model;

public class Robot {
    private int x;
    private int y;
    private Direction direction;
//    Check if place has been added first
    private boolean isPlaced = false;

    public int getX() { return x; }
    public void setX(int x) { this.x = x; }
    public int getY() { return y; }
    public void setY(int y) { this.y = y; }
    public Direction getDirection() { return direction; }
    public void setDirection(Direction direction) { this.direction = direction; }
    public boolean isNotPlaced() { return !isPlaced; }
    public void setPlaced(boolean placed) { isPlaced = placed; }
}