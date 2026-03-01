import model.Command;
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
                String[] tokens = input.split("\\s+", 2);
                Command cmd = Command.fromString(tokens[0]);

                if (cmd == null) {
                    System.err.println("ERROR: Unknown command: " + tokens[0]);
                    continue;
                }

                if (cmd.requiresParameters() && tokens.length > 1) {
                    cmd.execute(robotService, tokens[1]);
                } else if (cmd.requiresParameters()) {
                    System.err.println("ERROR: " + cmd + " requires parameters.");
                } else {
                    cmd.execute(robotService);
                }

            } catch (IllegalArgumentException | IllegalStateException e) {
                System.err.println("ERROR: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("ERROR: Invalid command format.");
            }
        }
        System.out.println("Simulator terminated.");
        scanner.close();
    }
}
