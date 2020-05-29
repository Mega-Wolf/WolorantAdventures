import def.Enemy;
import def.TileType;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import static def.Constants.*;
import static helpers.Terminal.*;

public class Main {


    // Variables
    public static int[][] renderColour;
    public static char[][] renderText;

    public static int width;
    public static int height;

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

    // Render functions

    public static void renderPixel(int y, int x, char letter, int colourCode) {
        renderColour[y][x] = colourCode;
        renderText[y][x] = letter;
    }

    public static void clearRenderTexture() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                renderText[y][x] = ' ';
                renderColour[y][x] = BACKGROUND_BLACK;
            }
        }
    }

    public static boolean isAdjacent(int x1, int y1, int x2, int y2) {
        int xDif = Math.abs(x1 - x2);
        int yDif = Math.abs(y1 - y2);

        return ((xDif + yDif) == 1);
    }

    public static int squareDistance(int x1, int y1, int x2, int y2) {
        int xDif = x1 - x2;
        int yDif = y1 - y2;

        return xDif * xDif + yDif * yDif;
    }


    
    public static void main(String[] args) {
        int healthPoints = 100;

        /*
        String name = "Wolorant";

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
        {
            clearScreen();
            disableTextCursor();
            setColour(BACKGROUND_BLACK);
        }

        // Create Level
        /*
        TileType[][] tileType = new TileType[HEIGHT][WIDTH];
        {
            for (int y = 0; y < HEIGHT; y++) {
                for (int x = 0; x < WIDTH; x++) {
                    tileType[y][x] = TileType.EMPTY;
                }
            }
            for (int x = 0; x < WIDTH; x++) {
                tileType[0][x] = TileType.WALL;
                tileType[HEIGHT - 1][x] = TileType.WALL;
            }
            for (int y = 0; y < HEIGHT; y++) {
                tileType[y][0] = TileType.WALL;
                tileType[y][WIDTH - 1] = TileType.WALL;
            }

            tileType[10 + (int) (Math.random() * 15)][10 + (int) (Math.random() * 15) ] = TileType.STAIRCASE;
        }

        int positionX = 2;
        int positionY = 2;

        Enemy[] enemies = new Enemy[20];
        for (int e_i = 0; e_i < enemies.length; ++e_i) {
            enemies[e_i] = new Enemy(5 + (int) (Math.random() * 20), 5 + (int) (Math.random() * 20),20 + (int) (Math.random() * 50));
        }

        */

        // Load level
        TileType[][] tileType = new TileType[0][0];
        Enemy[] enemies = new Enemy[0];
        int positionX = 2;
        int positionY = 2;
        {
            try (Scanner levelScanner = new Scanner(new BufferedReader(new FileReader(new File("levels/Level0.txt"))));)
            {
                levelScanner.nextLine(); // version number
                String dimensionLine = levelScanner.nextLine();
                String[] dimensionsAsSStrings = dimensionLine.split(",");
                width = Integer.parseInt(dimensionsAsSStrings[0]);
                height = Integer.parseInt(dimensionsAsSStrings[1]);

                renderColour = new int[height][width];
                renderText = new char[height][width];
                tileType = new TileType[height][width];
                for (int y = 0; y < height; y++) {
                    for (int x = 0; x < width; x++) {
                        tileType[y][x] = TileType.EMPTY;
                    }
                }

                for (int h_i = 0; h_i < height; h_i++) {
                    String line = levelScanner.nextLine();
                    for (int w_i = 0; w_i < width; w_i++) {
                        char c = Character.toUpperCase(line.charAt(w_i));
                        switch (c) {
                            case '#': {
                                tileType[h_i][w_i] = TileType.WALL;
                                break;
                            }
                            case 'W': {
                                positionX = w_i;
                                positionY = h_i;
                                break;
                            }
                            case 'S': {
                                tileType[h_i][w_i] = TileType.STAIRCASE;
                                break;
                            }
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }


        Scanner input = new Scanner(System.in);


        boolean isPlaying = true;
        while(isPlaying) {

            boolean isHitting = false;

            // ProcessInputs
            int speedX = 0;
            int speedY = 0;
            {
                String inputText = input.nextLine();

                switch (inputText) {
                    case "w": {
                        speedY = -1;
                        break;
                    }
                    case "a": {
                        speedX = -1;
                        break;
                    }
                    case "s": {
                        speedY = 1;
                        break;
                    }
                    case "d": {
                        speedX = 1;
                        break;
                    }

                    case " ": {
                        isHitting = true;
                        break;
                    }


                    case "o": {
                        isPlaying = false;
                    }
                }
            }



            // Game Logic
            {
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
                        isPlaying = false;
                        System.out.println("ou made it through with " + healthPoints + " health points.");
                        break;
                    }
                }

                // Monster interaction
                for (int e_i = 0; e_i < enemies.length; e_i++) {
                    if (enemies[e_i].isAlive()) {
                        int squareDistance = squareDistance(targetPositionX, targetPositionY, enemies[e_i].getX(), enemies[e_i].getY());

                        if (squareDistance <= 1) {
                            if (!isHitting) {
                                healthPoints = calculateNewHealthPoints(healthPoints, -20);
                            }

                            enemies[e_i].kill();
                        } else {
                            if (squareDistance < enemies[e_i].getSquareDistanceAlarm()) {
                                // enemy walks towards Wolorant

                                int xDif = positionX - enemies[e_i].getX();
                                int yDif = positionY - enemies[e_i].getY();

                                int walkX = 0;
                                int walkY = 0;

                                if (Math.abs(xDif) > Math.abs(yDif)) {
                                    walkX = (int) Math.signum(xDif);
                                } else {
                                    walkY = (int) Math.signum(yDif);
                                }

                                enemies[e_i].setX(enemies[e_i].getX() + walkX);
                                enemies[e_i].setY(enemies[e_i].getY() + walkY);
                            }
                        }
                    }
                }





                positionX = targetPositionX;
                positionY = targetPositionY;
            }

            // Render
            {
                clearRenderTexture();

                // Render Tiles
                for (int y = 0; y < height; y++) {
                    for (int x = 0; x < width; x++) {
                        if (tileType[y][x] != TileType.EMPTY) {
                            renderPixel(y, x, tileType[y][x].letter, tileType[y][x].colour);
                        }
                    }
                }

                // Render Wolorant
                renderPixel(positionY, positionX, 'W', FOREGROUND_GREEN + BRIGHT);

                // Render First enemy
                for (int e_i = 0; e_i < enemies.length; e_i++) {
                    if (enemies[e_i].isAlive()) {
                        renderPixel(enemies[e_i].getY(), enemies[e_i].getX(), 'E', FOREGROUND_RED + BRIGHT);
                    }
                }

                // Generate and show output render
                // TODO(Tobi): Only set colour when it is different than the previous one and I don't write a space
                String outputString = "\033[1;1f";

                for (int y = 0; y < height; y++) {
                    for (int x = 0; x < width; x++) {
                        outputString += "\033[" + renderColour[y][x] + "m" + renderText[y][x];
                    }
                    outputString += System.lineSeparator();
                }
                setColour(BACKGROUND_BLACK);
                System.out.print(outputString);

                printHealthPointSword(healthPoints);

                if (healthPoints == 0) {
                    isPlaying = false;
                }
            }

        }

        // Cleanup
        {
            clearScreen();
            resetColours();
        }
    }

}
