import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import javax.imageio.ImageIO;

/**
 * The Player's ship.
 * 
 * Movement: left, right, up, down.
 */
public class Player {
    
    public static final int WIDTH = 48;
    public static final int HEIGHT = 48;

    private static final int SPEED = 5;
    private static final int SHOOT_DELAY = 15;
    private static final int INVINCIBLE_FRAMES = 90;

    // unsure if will use
    private static final double UPPER_BOUND_PCT = 0.35;

    private int x,y;
    private int lives;
    private int shootCooldown = 0;
    private int invincibleFrames = 0;

    private final BufferedImage sprite;
    private final List<Bullet> bullets;

    public Player(int x, int y, int lives, List<Bullet> bullets) {
        this.x = x;
        this.y = y;
        this.lives = lives;
        this.bullets = bullets;

        BufferedImage loaded = null;
        try {
            loaded = ImageIO.read(getClass().getResource("/images/player.png"));
        } catch (Exception e) { /* Fallback */ }
        this.sprite = loaded;
    }

    // update
    
    /**
     * @param left left arrow held
     * @param right right arrow held
     * @param up up arrow held
     * @param down down arrow held
     * @param shoot spacebar held
     * @param screenW panel width
     * @param screenH panel height
     */
    public void update(boolean left, boolean right, boolean up, boolean down, 
                        boolean shoot, int screenW, int screenH) {
        
        // Hoitzontal - clamp to screen edges
        if (left && x > 0) x -= SPEED; // 
        if (right && x < screenW - WIDTH) x += SPEED;

        // Vertical - unsure if to use
        int minY = Math.max(65, (int)(screenH * UPPER_BOUND_PCT));
        int maxY = screenH - HEIGHT;
        if (up && y > minY) y -= SPEED;
        if (down && y < maxY) y += SPEED;

        // Shooting
        if (shootCooldown > 0) shootCooldown--;
        if (shoot && shootCooldown == 0) {
            bullets.add(new PlayerBullet(x + WIDTH / 2 - 3, y));
            shootCooldown = SHOOT_DELAY;
        }

        if (invincibleFrames > 0) invincibleFrames--;
    }

    // Draw

    public void draw(Graphics2D g) {
        if (invincibleFrames > 0 && (invincibleFrames % 6) >=3) return;

        if (sprite != null) {
            g.drawImage(sprite,x,y,WIDTH, HEIGHT, null);
        } else {
            g.setColor(new Color(0,220,255));
            int[] xs = { x + WIDTH / 2, x, x + WIDTH};
            int[] ys = { y, y + HEIGHT, y + HEIGHT};
            g.fillPolygon(xs,ys,3);
            g.setColor(new Color(0,100,255,150));
            g.fillOval(x + WIDTH / 2-8, y + HEIGHT -6, 16,12);
        }
    }

    // Damage

    public boolean isVulnerable() { return invincibleFrames == 0; }
    public void takeDamage(int amount) {
        lives -= amount;
        invincibleFrames = INVINCIBLE_FRAMES;
    }

    public boolean isDead() { return lives <= 0; }
    public int getLives() { return lives; }
    public int getX() { return x; }
    public int getY() { return y; }
    public Rectangle getBounds() { return new Rectangle(x,y,WIDTH, HEIGHT); }

    public void reset(int startX, int startY, int startLives) {
        this.x = startX;
        this.y = startY;
        this.lives = startLives;
        this.shootCooldown = 0;
        this.invincibleFrames = 0;
    }
    
}
