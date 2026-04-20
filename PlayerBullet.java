import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

// PlayerBullet.java
 //Concrete subclass of Bullet — extends Bullet.
 // Fired by the Player when the spacebar is pressed.
  // Moves UP the screen toward enemies.
// Inherits from Bullet: x, y, speed, damage, active,
 //  getX(), getY(), getDamage(), isActive(), deactivate(), getBounds().
public class PlayerBullet extends Bullet {

// Constructor

     //Creates a PlayerBullet at the given position.
     // Spawned just above the center of the player

     // @param x  horizontal position — should be center of player
     // @param y  vertical position — should be just above the player

    public PlayerBullet(int x, int y) {
        super(x, y);        // calls Bullet constructor — sets x, y, active = true
        this.speed  = 8;    // moves 8 pixels up per frame — fast and snappy
        this.damage = 1;    // deals 1 damage to enemy on hit
    }

// Abstract method

     //Moves the bullet UP the screen by speed pixels each frame.
     //Negative y direction = moving toward top of screen in Java Swing.
     //Called by the game loop EVERY FRAME via bullet.update().
     //Player  deactivates this bullet when y < 0

    @Override
    public void update() {
        y -= speed;   // moves UP toward enemies
    }


     // Returns the sprite image for this bullet.
     //Called by paintComponent() EVERY FRAME to draw the bullet.
     //Loads playerBullet.png from the /images/ resources folder.
     //Returns null if image not found —

    @Override
    public BufferedImage getSprite() {
        try {
            // load player bullet image from resources folder
            return ImageIO.read(getClass().getResource("/images/playerBullet.png"));
        } catch (IOException | IllegalArgumentException e) {
            // image not found
            return null;
        }
    }

}

