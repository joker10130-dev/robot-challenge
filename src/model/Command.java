package model;

import service.RobotService;

public enum Command {
    PLACE(true) {
        @Override
        public void execute(RobotService service, String... args) {
            if (args.length < 1 || args[0].trim().isEmpty()) {
                throw new IllegalArgumentException("PLACE requires parameters (X,Y,F).");
            }

            String[] parts = args[0].toUpperCase().trim().split("\\s*,\\s*");
            if (parts.length == 3) {
                int x = Integer.parseInt(parts[0]);
                int y = Integer.parseInt(parts[1]);
                Direction dir = Direction.valueOf(parts[2].toUpperCase());
                service.place(x, y, dir);
            } else {
                throw new IllegalArgumentException("PLACE requires X,Y,F parameters in comma-separated format.");
            }
        }
    },
    MOVE(false) {
        @Override
        public void execute(RobotService service, String... args) {
            service.moveForward();
        }
    },
    LEFT(false) {
        @Override
        public void execute(RobotService service, String... args) {
            service.turnLeft();
        }
    },
    RIGHT(false) {
        @Override
        public void execute(RobotService service, String... args) {
            service.turnRight();
        }
    },
    REPORT(false) {
        @Override
        public void execute(RobotService service, String... args) {
            String result = service.report();
            if (result != null) {
                System.out.println("Output: " + result);
            } else {
                throw new IllegalStateException("Robot is not on the table. Use PLACE first.");
            }
        }
    };

    private final boolean requiresParameters;

    Command(boolean requiresParameters) {
        this.requiresParameters = requiresParameters;
    }

    public abstract void execute(RobotService service, String... args);

    public boolean requiresParameters() {
        return requiresParameters;
    }

    public static Command fromString(String input) {
        try {
            return Command.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            return null;
        }
    }
}