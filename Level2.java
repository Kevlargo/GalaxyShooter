import java.util.List;

// Level2.java
 // Concrete subclass of Level — extends Level.
 //The second level of Galaxy Shooter — harder than Level1.
 //Spawns two rows of BasicEnemies plus one BossEnemy in the center.
 //Level is complete when ALL enemies including the boss are dead.
 //Inherits from Level: enemies list, levelNumber,
 // getEnemies(), getLevelNumber().

public class Level2 extends Level {

    private static final int KILLS_REQUIRED = 1;
    private static final int MAX_ON_SCREEN = 6;
    private static final int SPAWN_INTERVAL = 90;
    private static final int BOSS_SPAWN_AT = 10;
    private static final int MIN_SPACING = 80;
    // reference to the game's bullet list so enemies can shoot into it
    private List<Bullet> gameBullets;
    private int killCount = 0;
    private int spawnTimer = 0;
    private boolean bossSpawned = false;

    // Constructor
    //Creates Level2.
     // Passes level number 2 to the Level constructor.

     // @param gameBullets  the shared bullet list from GamePanel
     //passed into each enemy so they can shoot

    public Level2(List<Bullet> gameBullets) {
        super(2);                        // level number = 2
        this.gameBullets = gameBullets;  // store bullet list for enemy creation
    }

    // Abstract Method
    @Override
    public void spawnEnemies() {
       int cols = MAX_ON_SCREEN;
       int colW = (700-48) / cols;
       for (int i = 0; i < cols; i++) {
        int x = i * colW + (int)(Math.random() * (colW - 48));
        int y = -60 - i * 60;
        enemies.add(new BasicEnemy(x,y,gameBullets));
       }
    }

    private void spawnBasic() {
        int x = findSafeX();
        enemies.add(new BasicEnemy(x,-60,gameBullets));
    }

    private int findSafeX() {
        int attempts = 20;
        while (attempts-- > 0) {
            int candidate = (int)(Math.random() * (700-48));
            boolean clear = true;
            for (Enemy e : enemies) {
                if (Math.abs(e.getX() - candidate) < MIN_SPACING) { clear = false; break;}
            }
            if (clear) return candidate;
        }
        return (int)(Math.random() * (700-48));
    }

    @Override
    public void update() {
        if (isComplete()) return;
        enemies.removeIf(e -> e.getY() > 820);

        if (!bossSpawned & killCount >= BOSS_SPAWN_AT) {
            enemies.add(new BossEnemy(268, -80, gameBullets));
            bossSpawned = true;
        }

        spawnTimer++;
        if (spawnTimer >= SPAWN_INTERVAL) {
            spawnTimer = 0;
            boolean bossAlive = enemies.stream().anyMatch(e -> e instanceof BossEnemy);
            int cap = bossAlive ? MAX_ON_SCREEN -1 : MAX_ON_SCREEN;
            if (enemies.size() < cap) spawnBasic();
        }
    }

    @Override public void onEnemyKilled() { killCount++; }
    @Override public int getKillCount() { return killCount; }
    @Override public int getKillsRequired() { return KILLS_REQUIRED;}
    @Override
    public boolean isComplete() {
        return killCount >= KILLS_REQUIRED;
    }

} 