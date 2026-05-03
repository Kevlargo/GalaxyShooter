
import java.awt.*;
import java.awt.image.BufferedImage;


// Abstract Base Class for the Enemy
//
public abstract class Enemy {

    // Fields
// declare variables
    protected int x; // horizontal position
    protected int y; // vertical position
    protected int health; // current health points
    protected int maxHealth;
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

    /** Draw this enemy and its health bar */
    public abstract void draw(Graphics2D g);

    // Shared health bar helper available to all subclasses
    protected void drawHealthBar(Graphics2D g, int x, int y, int w) {
        if (maxHealth <=0) return;
        double ratio = Math.max(0.0, (double) health / maxHealth);
        g.setColor(Color.DARK_GRAY);
        g.fillRect(x,y,w,4);
        g.setColor(ratio > 0.5 ? new Color(0,200,0) : new Color(220,50,0));
        g.fillRect(x,y, (int)(w*ratio),4);
    }

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

        return new Rectangle(x, y, 48, 48);}
}


