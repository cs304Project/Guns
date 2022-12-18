package main.Enemys;

import main.Entity;
import main.Timing;
import java.util.ArrayList;
import javax.media.opengl.GL;
import main.Players.Bullet;
import main.Players.Player;

/**
 *
 * @author hossa
 */
public class EnemyAI {

    Entity e = new Entity();
    Timing timer = new Timing();
    public float delta;
    public long currentTime;
    public long lastTime;
    float speed = 0.2f;
    float angle = 0;

    public EnemyAI() {
        timer.start();

    }

    //target to reach a position 1 and translate to another position
    //
    
    public void createAI01(ArrayList<Enemy> eList, GL gl, Player player,
            ArrayList<Bullet> enemyBullets) {
        int rowStep = 0;
        int colStep = 0;
        for (int i = 0; i < eList.size(); i++) {
            if (i % 8 == 0) {
                if (i == 0) rowStep = 0;
                else  rowStep++;
                colStep = 0;

            } else {
                colStep++;
            }

            float x = eList.get(i).getXWorld();
            float y = eList.get(i).getYWorld();
            float xTarget = (150 - 43 * colStep);
            float yTagget = 60 + (rowStep * 50);

            if ((timer.seconds < 6)) {

                if (y > yTagget) {
                    speed = (10f / (i + 1));
                    eList.get(i).setYWorld(y - timer.seconds * speed);
                } else if (x < xTarget) {
                    speed = (10f / (i + 1));
                    eList.get(i).setXWorld(x + timer.seconds * speed);
                }
                eList.get(i).drawSprite(gl);
            } else if (timer.seconds >= 6) {
                //r* angle
                angle = angle + 1f;
                angle = angle % 360;
                float a1 = 20 * (float) Math.cos(Math.toRadians(angle));
                float a2 = 20 * (float) Math.sin(Math.toRadians(angle));
                System.out.println(a1);
                eList.get(i).drawSprite_AnimationAI(gl, x + a1, y + a2 , "AI01");
                eList.get(i).AutoAttack(gl, enemyBullets);
            }
            
            e.collision(eList.get(i), player);


        }

    }
    
    public void createAI02(ArrayList<Enemy> eList ,GL gl, Player player 
            , ArrayList<Bullet> enemyBullets) {

        for(int i = 0; i < eList.size(); i++)
        {
             
            AI02(eList ,gl,i);

            eList.get(i).AutoAttack(gl, enemyBullets);
            e.collision(eList.get(i), player);
            
        }       
    }
    
    
    private void AI02(ArrayList<Enemy> eList ,GL gl , int index)
    {
        float startPosition = eList.get(index).getYWorld(); 
        float curretX = eList.get(index).getXWorld() + 2f; //y = sin(x) // PI/180 Math.PI/ 3.14. 
        float omega = 2f;
        float currentY =  startPosition + 20 * (float) Math.sin(omega * Math.toRadians(curretX));
        eList.get(index).drawSprite_AnimationAI(gl, curretX  , currentY , "AI02" );
        eList.get(index).setXWorld(curretX >= 200? -curretX:curretX);
    }
    
  
}
