package helpers;

public class Terminal {

    public static void setColour(int colourCode) {
        System.out.print("\033[" + colourCode + "m");
    }

    public static void setCursor(int y, int x) {
        System.out.print("\033[" + (y + 1) + ";" + (x + 1) + "f");
    }

    public static void clearScreen() {
        System.out.print("\033[2J");
    }

    public static void disableTextCursor() {
        System.out.print("\033[?25l");
    }

    public static void resetColours() {
        System.out.println("\033[0m");
    }

    public static void printWithColour(String text, int colour) {
        setColour(colour);
        System.out.print(text);
    }

    public static void printLnWithColour(String text, int colour) {
        setColour(colour);
        System.out.println(text);
    }

}
