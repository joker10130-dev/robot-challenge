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
* **Space Handling**: The application ignores extra spaces in commands, ensuring that commands like `  PLACE   0,0,NORTH  ` are processed correctly.
* **Multiple Command Handling**: The application can process multiple commands in sequence, allowing for complex movements and rotations.

## Setup and Run

Since this project does not use Build Tools like Maven or Gradle for dependency management (per the constraints), you must download and configure the JUnit Library manually via IntelliJ IDEA:

### 1. Download JUnit JAR Files
You need to download the following `.jar` files (version 5.10.2 is recommended):
* `junit-jupiter-api-5.10.2.jar`
* `junit-platform-commons-1.10.2.jar`
* `junit-platform-engine-1.10.2.jar`

Place all downloaded files into a **`lib/`** folder within the project root.

### 2. Configure Project Structure in IntelliJ IDEA
1.  Open the project in IntelliJ IDEA.
2.  Go to **File** > **Project Structure...** (or press `Ctrl+Alt+Shift+S`).
3.  Select **Modules** on the left panel.
4.  Select the **Dependencies** tab.
5.  Click the **+** button (on the right) > **JARs or Directories...**
6.  Select the `lib` folder you created and click **OK**.
7.  In the **Scope** column for the newly added JAR files, change the scope to **Test** to ensure these libraries are not used in the production code.
8.  Click **Apply** and **OK**.

### 3. Mark Root Directories
1. Right-click the **`src/test`** folder.
2. Select **Mark Directory as** > **Test Sources Root**.

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
2. Right-click on the test.service folder.
3. Select Run 'All Tests'.

## Project Structure
```text
RobotChallenge/
├── src/
│   ├── Main.java           # Handles input parsing and application flow
│   ├── model/
│   │   ├── Direction.java  # Enum for directions (NORTH, SOUTH, EAST, WEST)
│   │   ├── Robot.java      # Robot state object
│   │   └── Table.java      # Table boundaries and validation
│   ├── service/
│   │   └── RobotService.java # Business Logic (Movement, Rotation, Placement)
│   └── test/
│       └── service/
│           └── RobotServiceTest.java # Unit tests for RobotService
└── README.md
```
