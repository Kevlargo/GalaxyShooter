import java.util.List;

//  Level1.java
 // Concrete subclass of Level — extends Level.
 // The first level of Galaxy Shooter.
 // Spawns a single row of 5 BasicEnemies across the top of the screen.
 // No boss enemy — designed to ease the player into the game.
 //Inherits from Level: enemies list, levelNumber,
 // getEnemies(), getLevelNumber().

public class Level1 extends Level {
    private static final int KILLS_REQUIRED = 1;
    private static final int MAX_ON_SCREEN = 5;
    private static final int SPAWN_INTERVAL = 120;
    private static final int MIN_SPACING = 90; // min px between enemies
    
    // reference to the game's bullet list so enemies can shoot into it
    private List<Bullet> gameBullets;
    private int killCount = 0;
    private int spawnTimer = 0;

    // Constructor
    // Creates Level1.
     //Passes level number 1 to the Level constructor.
   //  @param gameBullets  the shared bullet list from GamePanel
    //  passed into each enemy so they can shoot

    public Level1(List<Bullet> gameBullets) {
        super(1);                        // level number = 1
        this.gameBullets = gameBullets;  // store bullet list for enemy creation
    }

    // Abstract Metho

    // Spawns 5 BasicEnemies in a horizontal row near the top of the screen, this number can change
     //Called ONCE by the game when Level 1 starts loading.
     //Each enemy is spaced 100px apart starting at x = 80.
     //All enemies are added to the inherited enemies list.

    @Override
    public void spawnEnemies() {
        for (int i = 0; i < 5; i++) {
            int spawnX = 80 + i * 100;  // space enemies 100px apart across the screen
            int spawnY = 60;            // near the top of the 800px tall screen
            // each enemy gets the shared bullet list so their shoot() works
            enemies.add(new BasicEnemy(spawnX, spawnY, gameBullets));
        }
    }

    /* Pick a random X that is at least MIN_SPACING  away from every linve enemy */
    private void spawnOne() {
        int x = findSafeX();
        enemies.add(new BasicEnemy(x,-60,gameBullets));
    }

    private int findSafeX() {
        int attempts = 20;
        while (attempts-- > 0) {
            int candidate = (int)(Math.random() * (700-48));
            boolean clear = true;
            for (Enemy e : enemies) {
                if (Math.abs(e.getX() - candidate) < MIN_SPACING) {
                    clear = false;
                    break;
                }
            }
            if (clear) return candidate;
        }

        // Fallback just pick a random pos
        return (int)(Math.random() * (700-48));
    }

    @Override
    public void update() {
        if (isComplete()) return;
        enemies.removeIf(e -> e.getY() > 820);
        spawnTimer++;
        if (spawnTimer >= SPAWN_INTERVAL){
            spawnTimer = 0;
            if (enemies.size() < MAX_ON_SCREEN) spawnOne();
        }
    }

    //Returns true when every enemy in this level is dead.
    //Called by the game loop EVERY FRAME to check win condition.
     // When true, the game loads Level2.
     //
     // @return boolean — true when all enemies have health <= 0

    @Override public void onEnemyKilled() { killCount++; }
    @Override public int getKillCount() { return killCount; }
    @Override public int getKillsRequired() {return KILLS_REQUIRED; }
    @Override
    public boolean isComplete() {
        // stream through all enemies — level is complete when every one isDead()
        return killCount >= KILLS_REQUIRED;
    }

} // end Level1
 