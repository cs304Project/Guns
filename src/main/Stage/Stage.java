
package main.Stage;

import java.util.ArrayList;
import javax.media.opengl.GL;
import main.Enemys.Enemy;
import main.Enemys.EnemyAI;
import main.Entity;
import static main.Entity.EnemyStage_1;
import static main.Entity.EnemyStage_2;
import static main.Entity.EnemyStage_3_01;
import static main.Entity.EnemyStage_3_02;
import main.Players.Player;

/**
 *
 * @author hossa
 */
public class Stage {
    EnemyAI ai;
    Entity e;

    
    public Stage()
    {
        this.ai = new EnemyAI();
        this.e = new Entity();
    }
    //Draw Enemy;
    public void drawEnemy(GL gl , Player player , int stageNum)
    {
        switch(stageNum)
        {
            case 1:
                ai.createAI01(Entity.EnemyStage_1 , gl , player , Entity.enemyBullets);
                break;
            case 2:
                ai.createAI02(Entity.EnemyStage_2, gl, player, Entity.enemyBullets);
                break;
            case 3:
                 ai.createAI03(Entity.EnemyStage_3_01,Entity.EnemyStage_3_02, gl, player, Entity.enemyBullets,stageNum);
                break;
            case 4:
//                if(!enemyList.isEmpty())
//                    enemyList.get(0).drawEnemy(gl);
                break;
            default:
                System.out.println("I am so sorry I cannot find you right Stage");
                break;
                    
        }
    }
    public void createEnemy(ArrayList<Enemy> enemyList,float x, float y)
    {
        Enemy enemy = new Enemy(x, y);
        enemyList.add(enemy);
 
    }
   
    //Draw enemy bullets;    //Draw enemy bullets;
}
