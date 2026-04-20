
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


     // Returns true when this level is finished and the next should load.
     // Called by the game loop EVERY FRAME to check win condition.
    public abstract boolean isComplete();

    // Concrete methods

    // Returns the full list of enemies for this level.
     // Called by the game loop EVERY FRAME to:
     // 1. call update() on each enemy — moves them and handles shooting
     // 2. check collisions with player bullets via getBounds().intersects()
     //  3. remove dead enemies via removeIf(Enemy::isDead)
     //  4. draw each enemy via paintComponent using getSprite()

    public List<Enemy> getEnemies() { return enemies; }

    // Returns which level number this is (1, 2, 3...).
    public int getLevelNumber() { return levelNumber; }
}