


import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;


// 2ns subclass of Enemy - extends to Enemy
// End level Boss in Galaxy Shooter
public class BossEnemy extends Enemy{

    // shoot timer — boss shoots faster than BasicEnemy
    private int shootTimer = 0;
    private static final int SHOOT_INTERVAL = 50; // fires every 50 frames

    // reference to the game's bullet list so shoot() can add to it
    private List<Bullet> gameBullets;

    // Constructor
    public BossEnemy(int x, int y, List<Bullet> gameBullets) {
        super(x, y);                     // calls Enemy(int x, int y) constructor
        this.health      = 10;           // inherited from Enemy — takes 10 hits to kill
        this.speed       = 2;            // inherited from Enemy — moves 2px side to side
        this.gameBullets = gameBullets;  // shared bullet list passed in from GamePanel
    }
// Abstract

    @Override
    public void update() {
        // move logic — boss moves side to side across the screen
        x += speed;  // x and speed inherited from Enemy

        // reverse direction when hitting screen edges
        if (x >= 552 || x <= 0) {
            speed = -speed;  // flip direction at x=0 (left edge) and x=552 (right edge)
            // 552 = screen width 600 - boss width 48
        }

        // increment shoot timer every frame
        shootTimer++;
        if (shootTimer >= SHOOT_INTERVAL) {
            shoot();         // fire bullets
            shootTimer = 0;  // reset timer after firing
        }
    }

    @Override
    public void shoot() {
        // boss fires two EnemyBullets side by side — spread shot
        // left bullet — x + 8 offsets from left side of boss sprite
        gameBullets.add(new EnemyBullet(x + 8,  y + 48));
        // right bullet — x + 34 offsets from right side of boss sprite
        gameBullets.add(new EnemyBullet(x + 34, y + 48));
    }

    @Override
    public int getPoints() {
        return 500;  // 500 points awarded <--- we can change this
    }

    @Override
    public BufferedImage getSprite() {
        try {
            // loads bossEnemy.png from the /images/ folder in your project resources
            return ImageIO.read(getClass().getResource("/images/bossEnemy.png"));
        } catch (IOException | IllegalArgumentException e) {
            // returns null if image not found —
            return null;
        }
    }

    @Override
    public Rectangle getBounds() {
        // 64x64 hitbox — boss is bigger than a BasicEnemy (48x48)
        return new Rectangle(x, y, 64, 64);
    }

}












