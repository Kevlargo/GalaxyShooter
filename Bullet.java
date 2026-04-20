
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public abstract class Bullet {

    // Fields
    // declare variables
    protected int x;           // horizontal position
    protected int y;           // vertical position
    protected int speed;       // pixels moved per frame
    protected int damage;      // health removed on hit
    protected boolean active;  // false = remove from game

    // ── Constructor — exactly as specified in the PDF ─────────────────
    // Sets starting position and marks bullet as active.
     // Subclasses call super(x, y) then set their own speed and damage.

    public Bullet(int x, int y) {
        this.x      = x; // starting horizontal position
        this.y      = y; // starting vetical position
        this.active = true;    // PDF specifies active = true in constructor
    }

    // ── Abstract methods — implement in each subclass ─────────────────
    // PDF specifies exactly these two as abstract.

    // Move bullet position each frame — direction depends on subclass */
    public abstract void update();

    // Sprite image drawn by paintComponent every frame */
    public abstract BufferedImage getSprite();

    // Concrete Methods


    // Returns current X position
    public int getX() { return x; }

    // Returns current Y position */
    public int getY() { return y; }

    // Returns damage amount.
     //  Game loop calls this on collision then passes to takeDamage().

    public int getDamage() { return damage; }

    //Returns true while bullet is still in play.
    // Game loop removes bullets where isActive() is false.
    public boolean isActive() { return active; }

    // Marks bullet as inactive.
     // Called by game loop after bullet hits something or goes off screen.

    public void deactivate() { active = false; }

    // Returns hitbox Rectangle for collision detection.
    public Rectangle getBounds() {
        return new Rectangle(x, y, 6, 16);
    }
}