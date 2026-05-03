import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 * A bullet that moves at an angle — used for the FinalBoss spread shot.
 * dx controls horizontal drift per frame; dy controls downward speed.
 */
public class SpreadBullet extends Bullet {
    private final int dx;
    private final int dy;
    private final BufferedImage sprite;

    public SpreadBullet(int x, int y, int dx, int dy) {
        super(x,y);
        this.dx = dx;
        this.dy = dy;
        this.damage = 1;

        BufferedImage loaded = null;
        try {
            loaded = ImageIO.read(getClass().getResource("/images/enemyBullet.png"));
        } catch (Exception e) { /* fallback */}
        this.sprite = loaded;
    }

    @Override
    public void update() {
        x += dx;
        y += dy;
    }

    @Override 
    public BufferedImage getSprite() { return sprite; }

    @Override 
    public void draw(Graphics2D g) {
        if (sprite != null) {
            g.drawImage(sprite, x,y,8,14,null);
        } else {
            // Orange yellow angled bullet
            g.setColor(new Color(255,160,0,80));
            g.fillOval(x-3,y-3,14,14);
            g.setColor(new Color(255,120,0));
            g.fillOval(x,y,8,8);
        }
    }

    @Override
    public Rectangle getBounds() { return new Rectangle(x,y,8,8); }

}
