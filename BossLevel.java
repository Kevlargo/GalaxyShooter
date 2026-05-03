import java.util.List;

/**
 * Boss Level (level 4) - Final Boss Fight 
 * 
 * One FinalBoss spawns at the top of the screen and bounces side to side
 * No basic enemies. Level ends when the boss is killed.
 * Kill count = 0 or 1 (only used for the HUD display)
 */

public class BossLevel extends Level {

    private final List<Bullet> gameBullets;
    private boolean bossDefeated = false;
    private int killCount = 0;

    public BossLevel(List<Bullet> gameBullets) {
        super(4);
        this.gameBullets = gameBullets;
    }

    @Override
    public void spawnEnemies() {
        // Center the boss in the screen
        int x = (700 / 2) - (96 / 2) ;
        enemies.add(new FinalBoss(x, -100, gameBullets));
    }

    @Override
    public void update() {
        if (isComplete()) return;
    }

    @Override
    public void onEnemyKilled() {
        killCount++;
        bossDefeated = true;
    }

    @Override public boolean isComplete() { return bossDefeated; }
    @Override public int getKillCount() { return killCount; }

    /**
     * Returns 1 or the HUD progress bar shows full once the boss is dead
     */
    @Override public int getKillsRequired() { return 1; }

}
