
package main.Stage;

import java.util.ArrayList;
import java.util.Random;
import javax.media.opengl.GL;
import main.Enemys.Enemy;
import main.Enemys.EnemyAI;
import main.Enemys.EnemyBoss;
import main.Entity;
import main.Players.Collision;
import main.Players.Player;

/**
 *
 * @author hossa
 */
public class Stage {
    EnemyAI ai;
    Entity e;    
    Collision  c = new Collision();
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
                ai.createAI02(Entity.EnemyStage_2,
                        gl, player, Entity.enemyBullets);
                break;
            case 3:
                 ai.createAI03(Entity.EnemyStage_3_01,Entity.EnemyStage_3_02, gl, player, Entity.enemyBullets);
                break;
            case 4:
                
                //if(!enemyList.isEmpty())
                // enemyList.get(0).
                ai.createAI04(Entity.EnemyStage_4, gl,player);
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
   public void createEnemyBoss(ArrayList<Enemy> enemyList,float x, float y)
    {
        EnemyBoss enemy = new EnemyBoss(x, y);
        enemyList.add(enemy);
 
    }
    
   public void drawEnemyBullet(GL gl , int stage , boolean isPause , Player player)
   {
       if(isPause)
       {
           for (int i = 0; i < Entity.enemyBullets.size(); i++) {
               
                float x = Entity.enemyBullets.get(i).getXWorld();
                float y = Entity.enemyBullets.get(i).getYWorld();
                Entity.enemyBullets.get(i).drawBullet(gl,x,y);
                if (Entity.enemyBullets.get(i).getYWorld() < -1 || Entity.enemyBullets.get(i).getYWorld() > 1) {

                       e.destroyBulletFromList(Entity.enemyBullets.get(i), Entity.enemyBullets);
                   }

           }
       }
       else
       {
           for (int i = 0; i < Entity.enemyBullets.size(); i++) {
               
                Entity.enemyBullets.get(i).drawBullet(gl);
                
                c.collision( Entity.enemyBullets.get(i), player, Entity.EnemyStage_1);
                //System.out.println("bullet Position y" + Entity.enemyBullets.get(i).getYWorld());
                //System.out.println("player Position y" +  player.getScaledXWorld() );
//                if(player.getScaledXWorld()== Entity.enemyBullets.get(i).getXWorld())
//                {
//                    
//                    System.out.println("Hi I am in poistion");
//                }
                
               if (Entity.enemyBullets.get(i).getYWorld() < -1 || Entity.enemyBullets.get(i).getYWorld() > 1) {

                   e.destroyBulletFromList(Entity.enemyBullets.get(i), Entity.enemyBullets);
               }

               
           }
       }
       
       
       
       if(Entity.enemyBullets.size() <= 0)
       {
           switch (stage) {
               case 1:
                   detectTheEnemy(Entity.EnemyStage_1,stage);
                   break;
               case 2:
                   detectTheEnemy(Entity.EnemyStage_2, stage);

                   break;
               case 3:
                   detectTheEnemy(Entity.EnemyStage_2, stage);
                   break;

               default:
                   System.out.println("draw enemy bullet has a problem");
                   break;

           }
       }
       
       
   }
   
   
   
   private void detectTheEnemy(ArrayList<Enemy> list, int stage)
   {
       int tmp = 0;
       switch(stage)
       {
           case 1:
                tmp = 4;
           break;
           case 2:
                tmp = 5;
           break;
           case 3:
               tmp = 2;
           break;
           
       }
       for(int i = 0; i < tmp; i++)
           {
               Random r = new Random();

               if(list.size() >= 1)
               {
                   Entity.randmicList[i] = r.nextInt(list.size());
               }
  
           }
       
       for(int i = 0; i < Entity.randmicList.length; i++) {
           
           if(list.get(Entity.randmicList[i]) != null)
          list.get(Entity.randmicList[i]).isFire = true;
       }
       
       
   }
}
