// TODO: LEFT OFF HERE
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

//  Each level (Level1, Level2) extends this class.
// "The game loop only ever calls getEnemies() and isComplete()
public abstract class Level {

    // Fields
    protected List<Enemy> enemies;    // all enemies active in this level
    protected int levelNumber;        // which level this is (1, 2, 3...)

    // Constructor
    // Initialize the enemy list and stores the level number.
     //Subclasses call super(levelNumber).

    public Level(int levelNumber) {
        this.levelNumber = levelNumber;         // store which level this is (1, 2, 3...)
        this.enemies     = new ArrayList<>();   // start empty — spawnEnemies() fills this
    }

    // Abstract methods

   // Populates the enemies list with the enemies for this level.
   // Called ONCE by the game when this level starts loading.
   // Each subclass (Level1, Level2) adds its own Enemy types and positions here.
    public abstract void spawnEnemies();

    /**
     * Called once per frame by GamePanel (after collision + dead-enemy removal).
     * Responsible for: culling off-screen enemies, ticking the spawn timer,
     * and adding new enemies when the timer fires.
     */
    public abstract void update();

    /* called by gamepanel each time a kill is confirmed */
    public abstract void onEnemyKilled();

    /** @return  kills so far -used by the HUG progress bar */
    public abstract int getKillCount();

     // Returns true when this level is finished and the next should load.
     // Called by the game loop EVERY FRAME to check win condition.
    public abstract boolean isComplete();

    /** @return total kills required - usd by the HUD progress bar */
    public abstract int getKillsRequired();


    /**
     * Draws enemey in this level.
     * uses a snapshot copy so drawing can never cause a ConcurrentModificationException
     * even if the enemy list is changed somewhere else in the same frame
     */
    public void draw(Graphics2D g) {
        List<Enemy> snapshot = new ArrayList<>(enemies); // shallow copy
        for (Enemy e : snapshot) {
            e.draw(g);
        }
    }

    // Concrete methods shared by all levels

      /**
     * Returns a snapshot copy of the enemy list.
     * GamePanel iterates this snapshot — it never iterates the live list —
     * so removing dead enemies from the live list mid-frame is always safe.
     */
    public List<Enemy> getEnemiesSnapshot() { return new ArrayList<>(enemies); }

    public void removeDeadEnemies() {
        enemies.removeIf(Enemy::isDead);
    }

    // Returns which level number this is (1, 2, 3...).
    public int getLevelNumber() { return levelNumber; }
}