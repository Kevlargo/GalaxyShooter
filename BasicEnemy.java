
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import javax.imageio.ImageIO;

public class BasicEnemy extends Enemy{
    // shoot timer — controls how often this enemy fires back
    private int shootTimer = 0;
    private static final int SHOOT_INTERVAL = 90; // fires every 90 frames

    // reference to the game's bullet list so shoot() can add to it
    private final List<Bullet> gameBullets;
    private final BufferedImage sprite;

    public BasicEnemy(int x, int y, List<Bullet> gameBullets) {
        super(x, y);
        this.health      = 2;    // takes 2 player bullets to kill
        this.maxHealth   = 2;
        this.speed       = 2;    // moves 2 pixels downward per frame
        this.gameBullets = gameBullets;

        BufferedImage loaded = null;
        try {
            loaded = ImageIO.read(getClass().getResource("/images/basicEnemy.png"));
        } catch (Exception e) {
            // fallback
        }
        this.sprite = loaded;
    }


@Override
public void update() {
    // move logic — BasicEnemy moves straight down toward the player
    y += speed;

    // increment shoot timer and fire when interval is reached
    shootTimer++;
    if (shootTimer >= SHOOT_INTERVAL) {
        shoot();
        shootTimer = 0;  // reset after firing
    }
}

@Override
public void shoot() {
    // fire logic — spawns an EnemyBullet just below this enemy's center
    // x + 21 centers the 6px wide bullet inside the 48px wide enemy
    // y + 48 spawns it just below the bottom edge of the enemy sprite
    gameBullets.add(new EnemyBullet(x + 21, y + 48));
}

@Override
public int getPoints() {
    return 100;  // player earns 100 points for killing a BasicEnemy
}

@Override
public BufferedImage getSprite() {
    // loads the enemy sprite image from the /images/ resources folder
    return sprite;
}

@Override
public void draw(Graphics2D g) {
    if (sprite != null) {
        g.drawImage(sprite,x,y,48,48,null);
    } else {
        g.setColor(new Color(180,0,220));
        g.fillRect(x,y,48,48);
        g.setColor(new Color(255,50,50));
        g.fillOval(x + 10, y + 14, 10, 10);
        g.fillOval(x + 28, y + 14, 10, 10);
    }
    drawHealthBar(g,x,y-8,48);
}

@Override
public Rectangle getBounds() {
    // 48x48 hitbox matches the basicEnemy.png sprite size
    return new Rectangle(x, y, 48, 48);
}
}
