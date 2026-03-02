package test.service;

import model.Direction;
import model.Robot;
import model.Table;
import service.RobotService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RobotServiceTest {

    private Robot robot;
    private Table table;
    private RobotService robotService;

    @BeforeEach
    void setUp() {
        robot = new Robot();
        table = new Table();
        robotService = new RobotService(robot, table);
    }

    @Test
    @DisplayName("Test 1: MOVE north from (0,0) facing NORTH")
    void testExampleA() {
        robotService.place(0, 0, Direction.NORTH);
        robotService.moveForward();
        assertEquals("0,1,NORTH", robotService.report());
    }

    @Test
    @DisplayName("Test 2: LEFT from (0,0) facing NORTH")
    void testExampleB() {
        robotService.place(0, 0, Direction.NORTH);
        robotService.turnLeft();
        assertEquals("0,0,WEST", robotService.report());
    }

    @Test
    @DisplayName("Test 3: Complex Moves from (1,2) facing EAST")
    void testExampleC() {
        robotService.place(1, 2, Direction.EAST);
        robotService.moveForward();
        robotService.moveForward();
        robotService.turnLeft();
        robotService.moveForward();
        assertEquals("3,3,NORTH", robotService.report());
    }

    @Test
    @DisplayName("Test 4: Move and turn before PLACE command")
    void testIgnoreCommandsBeforePlace() {
        assertThrows(IllegalStateException.class, () -> robotService.moveForward());
        assertThrows(IllegalStateException.class, () -> robotService.turnLeft());
        assertNull(robotService.report());
    }

    @Test
    @DisplayName("Test 5: Out of bounds move prevention (North)")
    void testPreventFallingNorth() {
        robotService.place(0, 4, Direction.NORTH);
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> robotService.moveForward());
        assertEquals("Move ignored. Robot would fall at (0,5).", exception.getMessage());
        assertEquals("0,4,NORTH", robotService.report());
    }

    @Test
    @DisplayName("Test 6: Out of bounds move prevention (East)")
    void testPreventFallingEast() {
        robotService.place(4, 0, Direction.EAST);
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> robotService.moveForward());
        assertEquals("Move ignored. Robot would fall at (5,0).", exception.getMessage());
        assertEquals("4,0,EAST", robotService.report());
    }

    @Test
    @DisplayName("Test 7: Place command with out of bounds coordinates")
    void testPlaceOutOfBounds() {
        assertThrows(IllegalArgumentException.class, () -> robotService.place(5, 5, Direction.NORTH));
        assertThrows(IllegalArgumentException.class, () -> robotService.place(-1, 0, Direction.NORTH));
        assertNull(robotService.report());
    }
}