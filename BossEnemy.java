
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import javax.imageio.ImageIO;


// 2ns subclass of Enemy - extends to Enemy
// End level Boss in Galaxy Shooter
public class BossEnemy extends Enemy{

    // shoot timer — boss shoots faster than BasicEnemy
    private static final int W = 64;
    private static final int H = 64;
    private int shootTimer = 0;
    private static final int SHOOT_INTERVAL = 50; // fires every 50 frames

    // reference to the game's bullet list so shoot() can add to it
    private List<Bullet> gameBullets;
    private final BufferedImage sprite;

    // Constructor
    public BossEnemy(int x, int y, List<Bullet> gameBullets) {
        super(x, y);                     // calls Enemy(int x, int y) constructor
        this.health      = 10;           // inherited from Enemy — takes 10 hits to kill
        this.maxHealth   = 10;
        this.speed       = 2;            // inherited from Enemy — moves 2px side to side
        this.gameBullets = gameBullets;  // shared bullet list passed in from GamePanel

        BufferedImage loaded = null;
        try {
            loaded = ImageIO.read(getClass().getResource("/images/bossEnemy.png"));
        } catch (Exception e) {
            // fallback
        }
        this.sprite = loaded;
    }

    // Abstract
    @Override
    public void update() {
        // move logic — boss moves side to side across the screen
        x += speed;  // x and speed inherited from Enemy

        // reverse direction when hitting screen edges
        if (x + W >= 700 || x <= 0) speed = -speed;

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
        gameBullets.add(new EnemyBullet(x + 8,  y + H));
        gameBullets.add(new EnemyBullet(x + W - 14, y + H));
    }

    @Override
    public int getPoints() {
        return 500;  // 500 points awarded <--- we can change this
    }

    @Override
    public BufferedImage getSprite() {
        return sprite;
    }

    
    @Override
    public void draw(Graphics2D g) {
        if (sprite != null) {
            g.drawImage(sprite, x, y, W, H, null);
        } else {
            // Red body with wings and yellow eyes
            g.setColor(new Color(220, 0, 0));
            g.fillRect(x, y, W, H);
            g.setColor(new Color(255, 80, 80));
            g.fillRect(x - 14, y + 20, 14, 24); // left wing
            g.fillRect(x + W,  y + 20, 14, 24); // right wing
            g.setColor(new Color(255, 220, 0));
            g.fillOval(x + 12, y + 18, 14, 14);
            g.fillOval(x + 38, y + 18, 14, 14);
            g.setColor(Color.BLACK);
            g.fillOval(x + 16, y + 22, 6, 6);
            g.fillOval(x + 42, y + 22, 6, 6);
        }
        drawHealthBar(g, x, y - 10, W);
    }

 
    @Override
    public Rectangle getBounds() { return new Rectangle(x, y, W, H); }

}












