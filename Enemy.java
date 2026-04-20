
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


// Abstract Base Class for the Enemy
//
public abstract class Enemy {

    // Fields
// declare variables
    protected int x; // horizontal position
    protected int y; // vertical position
    protected int health; // current health points
    protected int speed; // movement speed per frame

    // Constructor:  Sets the starting X and Y Position
    public Enemy(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Abstract Methods to force subclasses to implement these classes

    public abstract void update();
    public abstract void shoot(); // fire a bullet
    public abstract int getPoints(); // Game score awarded
    public abstract BufferedImage getSprite(); // sprite image

    // Concrete Methods
    // Returns current X position
    public int getX() { return x; }
    // Returns current Y position
    public int getY() { return y; }
    // Returns current health
    public int getHealth() { return health; }
    // Returns when health reaches 0
    public boolean isDead() { return health <= 0; }
    // Subtract from current health
    public void takeDamage(int amt) { health -= amt; }

    public Rectangle getBounds() {
    // TODO: return actual hitbox once sprite size is decided

        return new Rectangle(x, y, 48, 48);}
}


