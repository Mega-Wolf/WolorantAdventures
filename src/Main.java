public class Main {

    public static final int FOREGROUND_BLACK = 30;
    public static final int FOREGROUND_RED = 31;
    public static final int FOREGROUND_GREEN = 32;
    public static final int FOREGROUND_YELLOW = 33;
    public static final int FOREGROUND_BLUE = 34;
    public static final int FOREGROUND_MAGENTA = 35;
    public static final int FOREGROUND_CYAN = 36;
    public static final int FOREGROUND_WHITE = 37;

    public static void setColour(int colourCode) {
        System.out.print("\033[" + colourCode + "m");
    }

    public static void printWithColour(String text, int colour) {
        setColour(colour);
        System.out.print(text);
    }

    public static void printLnWithColour(String text, int colour) {
        setColour(colour);
        System.out.println(text);
    }

    public static void printInfo(String name, int healthPoints) {
        printWithColour("Name: ", FOREGROUND_WHITE);
        printLnWithColour(name, FOREGROUND_RED);
        printWithColour("Health: ", FOREGROUND_WHITE);
        printLnWithColour(healthPoints + "", FOREGROUND_RED);
        System.out.println();
    }

    public static void printHealthPointSword(int healthPoints) {
        int colouredEqualSigns = healthPoints / 4;

        printLnWithColour("            //", FOREGROUND_WHITE);
        printWithColour("()=========>>", FOREGROUND_WHITE);

        // Print green equals signs
        for (int i = 0; i < colouredEqualSigns; i = i + 1) {
            printWithColour("=", FOREGROUND_GREEN);
        }

        // Print remaining red equal signs
        for (int i = 0; i < 25 - colouredEqualSigns; i = i + 1) {
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

    public static void main(String[] args) {
        String name = "Wolorant";
        int healthPoints = 100;
        printHealthPointSword(healthPoints);

        while(healthPoints > 0) {
            int damage = (int) (-10 * Math.random()) - 1;
            printWithColour("Damage: ", FOREGROUND_WHITE);
            printLnWithColour(damage + "", FOREGROUND_BLUE);
            healthPoints = calculateNewHealthPoints(healthPoints, damage);
            printHealthPointSword(healthPoints);
            System.out.println();
        }

        System.out.println("Er ist tot, Jim!");
    }

}
