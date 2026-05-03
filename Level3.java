import java.util.List;

/**
 * Level 3 - Fast BasicEnemies + two BossEnemies mid wave.
 * Spawn interval every 1 second (60 frames).
 * First boss spawns at 10 kills second at 20 kills
 * Level ends after 30 kills 
 */

public class Level3 extends Level {
    private static final int KILLS_REQUIRED = 30;
    private static final int MAX_ON_SCREEN = 7;
    private static final int SPAWN_INTERVAL = 60;
    private static final int BOSS1_SPAWN_AT = 10;
    private static final int BOSS2_SPAWN_AT = 20;
    private static final int MIN_SPACING = 70;

    private final List<Bullet> gameBullets;
    private int killCount = 0;
    private int spawnTimer = 0;
    private boolean boss1Spawned = false;
    private boolean boss2Spawned = false;

    public Level3(List<Bullet> gameBullets) {
        super(3);
        this.gameBullets = gameBullets;
    }

    @Override
    public void spawnEnemies() {
        int cols = MAX_ON_SCREEN;
        int colW = (700 - 48) / cols;
        for (int i = 0; i < cols; i++) {
            int x = i * colW + (int)(Math.random() * Math.max(1,colW - 48));
            int y = -60 - i * 50;
            enemies.add(new BasicEnemy(x, y, gameBullets));
        }
    }

    private void spawnBasic() {
        int x = findSafeX();
        enemies.add(new BasicEnemy(x, -60, gameBullets));
    }

    private int findSafeX() {
        int attempts = 20;
        while (attempts-- > 0) {
            int candidate = (int)(Math.random() * (700 - 48));
            boolean clear = true;
            for (Enemy e : enemies) {
                if (Math.abs(e.getX() - candidate) < MIN_SPACING) { 
                    clear = false; break; 
                }
            }
            if (clear) return candidate;
        }
        return (int)(Math.random() * (700 - 48));
    }

    @Override
    public void update() {
        if (isComplete()) return;
        enemies.removeIf(e -> e.getY() > 820);

        // First boss
        if (!boss1Spawned && killCount >= BOSS1_SPAWN_AT) {
            enemies.add(new BossEnemy(100,-80,gameBullets));
            boss1Spawned = true;
        }
        // Second boss (other side)
        if (!boss2Spawned && killCount >= BOSS2_SPAWN_AT) {
            enemies.add(new BossEnemy(400, -80, gameBullets));
            boss2Spawned = true;
        }

        spawnTimer++;
        if (spawnTimer >= SPAWN_INTERVAL) {
            spawnTimer = 0;
            long bossCount = enemies.stream().filter(e -> e instanceof BossEnemy).count();
            int cap = MAX_ON_SCREEN - (int) bossCount;
            long basicAlive = enemies.stream().filter(e -> e instanceof BasicEnemy).count();
            if (basicAlive < cap) spawnBasic();
        }
    }

    @Override public void onEnemyKilled() { killCount++; }
    @Override public boolean isComplete() { return killCount >= KILLS_REQUIRED; }
    @Override public int getKillCount() { return killCount; }
    @Override public int getKillsRequired() { return KILLS_REQUIRED; }
}
