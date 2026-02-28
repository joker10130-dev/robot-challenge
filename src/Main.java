import model.Direction;
import model.Robot;
import model.Table;
import service.RobotService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Robot robot = new Robot();
        Table table = new Table();
        RobotService robotService = new RobotService(robot, table);

        Scanner scanner = new Scanner(System.in);
        System.out.println("--- Welcome to Robot Movement Simulator ---");
        System.out.println("Enter commands (PLACE X,Y,F | MOVE | LEFT | RIGHT | REPORT):");
        System.out.println("X and Y are integers (0-4), F is direction (NORTH, EAST, SOUTH, WEST).");
        System.out.println("To exit the simulator, type 'EXIT'");

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine().trim();
//            Go new line if empty or exit app
            if (input.isEmpty()) continue;
            if (input.equalsIgnoreCase("EXIT")) break;

            try {
                if (input.toUpperCase().startsWith("PLACE")) {
                    handlePlace(input, robotService);
                } else {
                    handleAction(input, robotService);
                }
            } catch (Exception e) {
                System.err.println("Invalid command format.");
            }
        }
        System.out.println("Simulator terminated.");
        scanner.close();
    }
    private static void handlePlace(String input, RobotService service) {
//    Regex for PLACE to be x, y, direction format
        String[] parts = input.toUpperCase().replace("PLACE", "").trim().split("\\s*,\\s*");
        if (parts.length == 3) {
            int x = Integer.parseInt(parts[0]);
            int y = Integer.parseInt(parts[1]);
            Direction dir = Direction.valueOf(parts[2].toUpperCase());
            service.place(x, y, dir);
        } else {
            System.err.println("ERROR: PLACE requires X,Y,F parameters.");
        }
    }

    private static void handleAction(String input, RobotService service) {
        switch (input.toUpperCase()) {
            case "MOVE" -> service.moveForward();
            case "LEFT" -> service.turnLeft();
            case "RIGHT" -> service.turnRight();
            case "REPORT" -> {
                String result = service.report();
                if (result != null) {
                    System.out.println("Output: " + result);
                } else {
                    System.err.println("ERROR: Robot is not on the table. Use PLACE first.");
                }
            }
            default -> System.err.println("ERROR: Unknown command: " + input);
        }
    }
}
