import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

// EnemyBullet.java
  // Concrete subclass of Bullet — extends Bullet.
  // Fired by BasicEnemy and BossEnemy via their shoot() method.
  // Moves DOWN the screen toward the player.
  // Inherits from Bullet: x, y, speed, damage, active,
  //getX(), getY(), getDamage(), isActive(), deactivate(), getBounds().

public class EnemyBullet extends Bullet {

    private final BufferedImage sprite;
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

        BufferedImage loaded = null;
        try {
            loaded = ImageIO.read(getClass().getResource("/images/enemyBullet.png"));
        } catch (Exception e) {
            // fallback will be used in draw()
        }
        this.sprite = loaded;
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
      return sprite;
    }
    
    @Override
    public void draw(Graphics2D g) {
        if (sprite != null) {
            g.drawImage(sprite, x, y, 6, 16, null);
        } else {
            // Orange-red bullet with a soft glow
            g.setColor(new Color(255, 140, 0, 80));
            g.fillRect(x - 2, y - 2, 10, 20);
            g.setColor(new Color(255, 80, 0));
            g.fillRect(x, y, 6, 16);
        }
    }
}

