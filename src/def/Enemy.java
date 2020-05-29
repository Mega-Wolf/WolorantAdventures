package def;

public class Enemy {

    // Variables of enemy
    private int x;
    private int y;
    private boolean isAlive;
    private final int squareDistanceAlarm;

    // Constructor
    public Enemy(int xCoordinate, int yCoordinate, int squareDistanceAlarm) {
        x = xCoordinate;
        y = yCoordinate;
        isAlive = true;
        this.squareDistanceAlarm = squareDistanceAlarm;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public int getSquareDistanceAlarm() {
        return squareDistanceAlarm;
    }

    public void setX(int newX) {
        x = newX;
    }

    public void setY(int newY) {
        y = newY;
    }

    public void kill() {
        isAlive = false;
    }

}