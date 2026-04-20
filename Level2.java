import java.util.List;
import java.util.List;
import java.util.ArrayList;
// Level2.java
 // Concrete subclass of Level — extends Level.
 //The second level of Galaxy Shooter — harder than Level1.
 //Spawns two rows of BasicEnemies plus one BossEnemy in the center.
 //Level is complete when ALL enemies including the boss are dead.
 //Inherits from Level: enemies list, levelNumber,
 // getEnemies(), getLevelNumber().

public class Level2 extends Level {

    // reference to the game's bullet list so enemies can shoot into it
    private List<Bullet> gameBullets;

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

    //Spawns two rows of BasicEnemies and one BossEnemy.
     // Called ONCE by the game when Level 2 starts loading.

    //  Row 1 — 4 BasicEnemies at y = 60  (top row)
     // Row 2 — 4 BasicEnemies at y = 130 (second row below)
     //Boss at y = 220 (center of screen, below both rows)
     // All enemies are added to the inherited enemies list.
    @Override
    public void spawnEnemies() {
        // first row — 4 BasicEnemies near the top
        for (int i = 0; i < 4; i++) {
            int spawnX = 80 + i * 120;  // spaced 120px apart across the screen
            int spawnY = 60;            // top row
            enemies.add(new BasicEnemy(spawnX, spawnY, gameBullets));
        }

        // second row — 4 more BasicEnemies slightly lower
        for (int i = 0; i < 4; i++) {
            int spawnX = 80 + i * 120;  // same spacing as row 1
            int spawnY = 130;           // 70px below the first row
            enemies.add(new BasicEnemy(spawnX, spawnY, gameBullets));
        }

        // boss — spawned in the center below both rows
        // x = 268 centers a 64px wide boss on a 600px wide screen
        enemies.add(new BossEnemy(268, 230, gameBullets));
    }

    //Returns true when every enemy including the boss is dead.
     // Called by the game loop EVERY FRAME to check win condition.
     // When true, the game can show a win screen or load Level3.

     // @return boolean — true when all enemies have health <= 0

    @Override
    public boolean isComplete() {
        // stream through all enemies — level is complete when every one isDead()
        return enemies.stream().allMatch(e -> e.isDead());
    }

} 