package service;

import model.Direction;
import model.Robot;
import model.Table;

public class RobotService {
    private final Robot robot;
    private final Table table;
    public RobotService(Robot robot, Table table) {
        this.robot = robot;
        this.table = table;
    }

    public void place(int x, int y, Direction direction) {
        if (table.isValidPosition(x, y)) {
            robot.setX(x);
            robot.setY(y);
            robot.setDirection(direction);
            robot.setPlaced(true);
        } else {
            System.err.println("ERROR: Placement out of bounds.");
        }
    }

    public void moveForward() {
        if (!checkPlaced()) return;

        int nextX = robot.getX();
        int nextY = robot.getY();

        switch (robot.getDirection()) {
            case NORTH -> nextY++;
            case EAST -> nextX++;
            case SOUTH -> nextY--;
            case WEST -> nextX--;
        }

        if (table.isValidPosition(nextX, nextY)) {
            robot.setX(nextX);
            robot.setY(nextY);
        } else {
            System.err.println("ERROR: Move ignored. Robot would fall at (" + nextX + "," + nextY + ").");
        }
    }

    public void turnLeft() {
        if (!checkPlaced()) return;
        Direction currentDir = robot.getDirection();
        switch (currentDir) {
            case NORTH -> robot.setDirection(Direction.WEST);
            case EAST -> robot.setDirection(Direction.NORTH);
            case SOUTH -> robot.setDirection(Direction.EAST);
            case WEST -> robot.setDirection(Direction.SOUTH);
        }
    }

    public void turnRight() {
        if (!checkPlaced()) return;
        Direction currentDir = robot.getDirection();
        switch (currentDir) {
            case NORTH -> robot.setDirection(Direction.EAST);
            case EAST -> robot.setDirection(Direction.SOUTH);
            case SOUTH -> robot.setDirection(Direction.WEST);
            case WEST -> robot.setDirection(Direction.NORTH);
        }
    }

    public String report() {
        if (!checkPlaced()) return null;
        return robot.getX() + "," + robot.getY() + "," + robot.getDirection();
    }

    private boolean checkPlaced() {
        if (!robot.isPlaced()) {
            System.err.println("ERROR: Command ignored. Robot is not on the table.");
            return false;
        }
        return true;
    }
}
