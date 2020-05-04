import static helpers.Terminal.*;

public class Main {

    public static final int FOREGROUND_BLACK = 30;
    public static final int FOREGROUND_RED = 31;
    public static final int FOREGROUND_GREEN = 32;
    public static final int FOREGROUND_YELLOW = 33;
    public static final int FOREGROUND_BLUE = 34;
    public static final int FOREGROUND_MAGENTA = 35;
    public static final int FOREGROUND_CYAN = 36;
    public static final int FOREGROUND_WHITE = 37;

    public static final int WIDTH = 80;
    public static final int HEIGHT = 40;

    public static void printInfo(String name, int healthPoints) {
        printWithColour("Name: ", FOREGROUND_WHITE);
        printLnWithColour(name, FOREGROUND_RED);
        printWithColour("Health: ", FOREGROUND_WHITE);
        printLnWithColour(healthPoints + "", FOREGROUND_RED);
        System.out.println();
    }

    public static void printHealthPointSword(int healthPoints) {
        int greenEqualSigns = (healthPoints + 3) / 4;

        printLnWithColour("            //", FOREGROUND_WHITE);
        printWithColour("()=========>>", FOREGROUND_WHITE);

        // Print green equals signs
        for (int i = 0; i < greenEqualSigns; i++) {
            printWithColour("=", FOREGROUND_GREEN);
        }

        // Print remaining red equal signs
        for (int i = 0; i < 25 - greenEqualSigns; i++) {
            printWithColour("=", FOREGROUND_RED);
        }

        printLnWithColour("--", FOREGROUND_WHITE);
        printLnWithColour("            \\\\", FOREGROUND_WHITE);
    }

    public static int calculateNewHealthPoints(int currentHealthPoints, int healthPointChange) {
        int result = currentHealthPoints + healthPointChange;

        if (result > 100) {
            return 100;
        } else if (result < 0) {
            return 0;
        } else {
            return result;
        }
    }

    public static void shortDelay() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        /*
        String name = "Wolorant";
        int healthPoints = 100;
        printHealthPointSword(healthPoints);

        printHealthPointSword(3);
        printHealthPointSword(1);


        while(healthPoints > 0) {
            int damage = (int) (-10 * Math.random()) - 1;
            printWithColour("Damage: ", FOREGROUND_WHITE);
            printLnWithColour(damage + "", FOREGROUND_BLUE);
            healthPoints = calculateNewHealthPoints(healthPoints, damage);
            printHealthPointSword(healthPoints);
            System.out.println();
        }
        System.out.println("Er ist tot, Jim!");
        */

        // Setup
        clearScreen();
        disableTextCursor();



        // Create Wall
        TileType[][] tileType = new TileType[HEIGHT][WIDTH];
        for (int x = 0; x < WIDTH; x++) {
            tileType[0]         [x] = TileType.WALL;
            tileType[HEIGHT - 1][x] = TileType.WALL;
        }
        for (int y = 0; y < HEIGHT; y++) {
            tileType[y][0]         = TileType.WALL;
            tileType[y][WIDTH - 1] = TileType.WALL;
        }

        // Render Wall
        setColour(FOREGROUND_WHITE);
        for (int y = 0; y < HEIGHT; y++) {
            setCursor(y , 0);
            for (int x = 0; x < WIDTH; x++) {
                if (tileType[y][x] == TileType.WALL) {
                    System.out.print("#");
                } else {
                    System.out.print(" ");
                }
            }
        }

        int positionX = 4;
        int positionY = 8;

        boolean isPlaying = true;
        while(isPlaying) {
            // processInputs

            int speedX = 0;
            int speedY = 0;

            // Generate random direction
            int direction = (int) (Math.random() * 4);
            switch (direction) {
                case 0: {
                    speedX = 1;
                    break;
                }
                case 1: {
                    speedX = -1;
                    break;
                }
                case 2: {
                    speedY = -1;
                    break;
                }
                default: {
                    speedY = 1;
                    break;
                }
            }

            // clear screen
            setCursor(positionY, positionX);
            printWithColour(" ", FOREGROUND_GREEN);

            // game logic
            int targetPositionX = positionX + speedX;
            int targetPositionY = positionY + speedY;
            switch (tileType[targetPositionY][targetPositionX]) {
                case EMPTY: {
                    // Nothing shall happen here
                    break;
                }
                case WALL: {
                    // Don't actually walk
                    targetPositionX = positionX;
                    targetPositionY = positionY;
                    break;
                }
                case LAVA: {
                    // TODO(Tobi): burn Wolorant
                    System.out.println("BURN!!!");
                    break;
                }
                case ICE: {
                    // TODO(Tobi): set automatic slide velocity
                    System.out.println("SLIDE!!!");
                    break;
                }
                case STAIRCASE: {
                    System.out.println("Du bist da!");
                    isPlaying = false;
                    break;
                }
            }
            positionX = targetPositionX;
            positionY = targetPositionY;


            // render Wolorant
            setCursor(positionY, positionX);
            printWithColour("W", FOREGROUND_GREEN);

            shortDelay();
        }

        // For the following programs
        clearScreen();
        resetColours();
    }

}
