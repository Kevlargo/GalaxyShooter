import java.util.List;
import java.util.List;
import java.util.ArrayList;

//  Level1.java
 // Concrete subclass of Level — extends Level.
 // The first level of Galaxy Shooter.
 // Spawns a single row of 5 BasicEnemies across the top of the screen.
 // No boss enemy — designed to ease the player into the game.
 //Inherits from Level: enemies list, levelNumber,
 // getEnemies(), getLevelNumber().

public class Level1 extends Level {

    // reference to the game's bullet list so enemies can shoot into it
    private List<Bullet> gameBullets;

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

    //Returns true when every enemy in this level is dead.
    //Called by the game loop EVERY FRAME to check win condition.
     // When true, the game loads Level2.
     //
     // @return boolean — true when all enemies have health <= 0

    @Override
    public boolean isComplete() {
        // stream through all enemies — level is complete when every one isDead()
        return enemies.stream().allMatch(e -> e.isDead());
    }

} // end Level1
 