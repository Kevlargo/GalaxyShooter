import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

// EnemyBullet.java
  // Concrete subclass of Bullet — extends Bullet.
  // Fired by BasicEnemy and BossEnemy via their shoot() method.
  // Moves DOWN the screen toward the player.
  // Inherits from Bullet: x, y, speed, damage, active,
  //getX(), getY(), getDamage(), isActive(), deactivate(), getBounds().

public class EnemyBullet extends Bullet {

    // Constructor
    // Creates an EnemyBullet at the given position.
     // Spawned just below the enemy that fired it via shoot().
     //
      // @param x  horizontal position — center of the enemy that fired it
    //  @param y  vertical position — just below the bottom of the enemy sprite

    public EnemyBullet(int x, int y) {
        super(x, y);        // calls Bullet constructor — sets x, y, active = true
        this.speed  = 5;    // moves 5 pixels down per frame — slower than player bullets
        this.damage = 1;    // deals 1 damage to the player on hit
    }

    //Abstract Methods

    // Moves the bullet DOWN the screen by speed pixels each frame.
     // Positive y direction = moving toward bottom of screen in Java Swing.
     // Called by the game loop EVERY FRAME via bullet.update().
     // Player deactivates this bullet when y > screen height

    @Override
    public void update() {
        y += speed;   // moves DOWN toward the player
    }

    // Returns the sprite image for this bullet.
     // Called by paintComponent() EVERY FRAME to draw the bullet.
     //Loads enemyBullet.png from the /images/ resources folder.
     //Returns null if image not found —

    @Override
    public BufferedImage getSprite() {
        try {
            // load enemy bullet image from resources folder
            return ImageIO.read(getClass().getResource("/images/enemyBullet.png"));
        } catch (IOException | IllegalArgumentException e) {
            // image not found
            return null;
        }
    }

}