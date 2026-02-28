# Toy Robot Simulator

A console-based simulation of a toy robot moving on a 5x5 square table.

## Requirements
* Java JDK 17 or higher
* JUnit 5 (for running tests)

## Features
* **PLACE X,Y,F**: Places the robot on the table at position (X,Y) and direction F (NORTH, SOUTH, EAST, WEST).
* **MOVE**: Moves the robot one unit forward in the current direction.
* **LEFT / RIGHT**: Rotates the robot 90 degrees left or right.
* **REPORT**: Outputs the current position and direction of the robot.
* **Boundary Checking**: The robot is prevented from falling off the table.
* **Invalid Command Handling**: Commands are ignored until a valid PLACE command is executed.
* **Case Insensitivity**: Commands and directions are case-insensitive (e.g., `place`, `Place`, `NORTH`, `north` are all valid).
* **Multiple Command Handling**: The application can process multiple commands in sequence, allowing for complex movements and rotations.

## Setup and Run

### Compilation
Navigate to the `src` folder in your terminal and compile the Java files:

```bash
cd src
javac Model/*.java Service/*.java Main.java
```

### Running the Application
After compilation, run the application using:

```bash
java Main
```

### Example Input and Output
You can copy and paste the following commands directly into the console:

```bash
PLACE 0,0,NORTH
MOVE
REPORT
```
### Output:

```bash
0,1,NORTH
```

## Running Tests
This project includes unit tests for business logic validation using JUnit 5.

### Using IntelliJ IDEA
1. Open the project in IntelliJ IDEA.
2. Right-click on the test.Service folder.
3. Select Run 'All Tests'.

## Project Structure
```text
RobotChallenge/
├── src/
│   ├── Main.java           # Handles input parsing and application flow
│   ├── Model/
│   │   ├── Direction.java  # Enum for directions (NORTH, SOUTH, EAST, WEST)
│   │   ├── Robot.java      # Robot state object
│   │   └── Table.java      # Table boundaries and validation
│   ├── Service/
│   │   └── RobotService.java # Business Logic (Movement, Rotation, Placement)
│   └── test/
│       └── Service/
│           └── RobotServiceTest.java # Unit tests for RobotService
└── README.md
```