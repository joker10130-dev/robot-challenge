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
            throw new IllegalArgumentException("Placement out of bounds (" + x + "," + y + ").");
        }
    }

    public void moveForward() {
        validatePlaced();

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
            throw new IllegalStateException("Move ignored. Robot would fall at (" + nextX + "," + nextY + ").");        }
    }

    public void turnLeft() {
        validatePlaced();
        robot.setDirection(robot.getDirection().rotateLeft());
    }

    public void turnRight() {
        validatePlaced();
        robot.setDirection(robot.getDirection().rotateRight());
    }

    public String report() {
        if (robot.isNotPlaced()) {
            return null;
        }
        return robot.getX() + "," + robot.getY() + "," + robot.getDirection();
    }

    private void validatePlaced() {
        if (robot.isNotPlaced()) {
            throw new IllegalStateException("Command ignored. Robot is not on the table.");
        }
    }
}
