
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;

public class BasicEnemy extends Enemy{
    // shoot timer — controls how often this enemy fires back
    private int shootTimer = 0;
    private static final int SHOOT_INTERVAL = 90; // fires every 90 frames

    // reference to the game's bullet list so shoot() can add to it
    private List<Bullet> gameBullets;

    public BasicEnemy(int x, int y, List<Bullet> gameBullets) {
        super(x, y);
        this.health      = 2;    // takes 2 player bullets to kill
        this.speed       = 2;    // moves 2 pixels downward per frame
        this.gameBullets = gameBullets;
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
    try {
        return ImageIO.read(getClass().getResource("/images/basicEnemy.png"));
    } catch (IOException | IllegalArgumentException e) {
        // if image not found, returns null —
        return null;
    }
}

@Override
public Rectangle getBounds() {
    // 48x48 hitbox matches the basicEnemy.png sprite size
    return new Rectangle(x, y, 48, 48);
}
}
