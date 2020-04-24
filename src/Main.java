public class Main {

    public static int black = 30;
    public static int red = 31;
    public static int green = 32;
    public static int yellow = 33;
    public static int blue = 34;
    public static int magenta = 35;
    public static int cyan = 36;
    public static int white = 37;

    public static void setColour(int colourCode) {
        System.out.print("\033[" + colourCode + "m");
    }

    public static void printInfo(String name, int healthPoints) {
        setColour(white);
        System.out.print("Name: ");

        setColour(red);
        System.out.println(name);

        setColour(white);
        System.out.print("Health: ");

        setColour(red);
        System.out.println(healthPoints);
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
        printInfo(name, healthPoints);

        System.out.println();

        int healthPointChange = -10;
        healthPoints = calculateNewHealthPoints(healthPoints, healthPointChange);
        printInfo(name, healthPoints);

        System.out.println();

        healthPointChange = 15;
        healthPoints = calculateNewHealthPoints(healthPoints, healthPointChange);
        printInfo(name, healthPoints);

        System.out.println();

        healthPointChange = -300;
        healthPoints = calculateNewHealthPoints(healthPoints, healthPointChange);
        printInfo(name, healthPoints);


    }

}
