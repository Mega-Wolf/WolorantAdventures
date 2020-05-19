public class Enemy {

    // Variables of enemy
    private int x;
    private int y;
    private int health;

    // Constructor
    public Enemy(int xCoordinate, int yCoordinate, int healthPoints) {
        x = xCoordinate;
        y = yCoordinate;
        health = healthPoints;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int newX) {
        x = newX;
    }

    public void setY(int newY) {
        y = newY;
    }

}