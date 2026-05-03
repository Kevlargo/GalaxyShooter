import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

// PlayerBullet.java
 //Concrete subclass of Bullet — extends Bullet.
 // Fired by the Player when the spacebar is pressed.
  // Moves UP the screen toward enemies.
// Inherits from Bullet: x, y, speed, damage, active,
 //  getX(), getY(), getDamage(), isActive(), deactivate(), getBounds().
public class PlayerBullet extends Bullet {

    private final BufferedImage sprite;
// Constructor

     //Creates a PlayerBullet at the given position.
     // Spawned just above the center of the player

     // @param x  horizontal position — should be center of player
     // @param y  vertical position — should be just above the player

    public PlayerBullet(int x, int y) {
        super(x, y);        // calls Bullet constructor — sets x, y, active = true
        this.speed  = 8;    // moves 8 pixels up per frame — fast and snappy
        this.damage = 1;    // deals 1 damage to enemy on hit

        BufferedImage loaded = null;
        try {
            loaded = ImageIO.read(getClass().getResource("/images/playerBullter.png"));
        } catch (Exception e) {
            // fallback
        }
        this.sprite = loaded;
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


    @Override
    public BufferedImage getSprite() {
       return sprite;
    }


    @Override
    public void draw(Graphics2D g) {
        if (sprite != null) {
            g.drawImage(sprite,x,y, 6,16,null);
        } else {
            g.setColor(new Color(255,255,150,80));
            g.fillRect(x-2,y-2,10,20);
            g.setColor(new Color(255,255,0));
            g.fillRect(x,y,6,16);
        }
    }

}

