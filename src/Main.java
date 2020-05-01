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
            Thread.sleep(250);
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



        int position = 4;

        boolean[] isWall = new boolean[20];
        isWall[1] = true;
        isWall[10] = true;
        isWall[17] = true;

        while(true) {
            // processInputs
            int speed = (int) (Math.random() * 3) - 1;

            // game logic
            if (speed == -1) { // go left
                if (!isWall[position - 1]) {
                    position = position - 1;
                }
            } else if (speed == 1) {
                if (!isWall[position + 1]) {
                    position = position + 1;
                }
            }

            // render
            setColour(FOREGROUND_WHITE);
            setCursor(1 , 1);
            for (int i = 1; i < isWall.length; i++) {
                if (isWall[i]) {
                    System.out.print("#");
                } else {
                    System.out.print(" ");
                }
            }

            setCursor(1, position);
            printWithColour("W", FOREGROUND_GREEN);

            shortDelay();
        }

        // For the following programs
        //resetColours();
    }

}
