package main.Enemys;

import main.Entity;
import main.Timing;
import java.util.ArrayList;
import javax.media.opengl.GL;
import main.MainCode;
import main.Players.Bullet;
import main.Players.Collision;
import main.Players.Player;

/**
 *
 * @author hossa
 */
public class EnemyAI {

    Entity e = new Entity();
    Collision collision = new Collision();
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
    public void createAI01(ArrayList<Enemy> eList, GL gl, ArrayList<Player> players,
            ArrayList<Bullet> enemyBullets) {
        int rowStep = 0;
        int colStep = 0;
        for (int i = 0; i < eList.size(); i++) {
            if (i % 8 == 0) {
                if (i == 0) {
                    rowStep = 0;
                } else {
                    rowStep++;
                }
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

                if (MainCode.isPause) {
                    x = eList.get(i).getScaledXWorld();
                    y = eList.get(i).getScaledYWorld();
                    eList.get(i).drawEnemy(gl, x, y, 1);
                } else {
                    eList.get(i).drawEnemy(gl, 1);
                }

            } else if (timer.seconds >= 6) {
                //r* angle
                angle = angle + 1f;
                angle = angle % 360;
                float a1 = 0 * (float) Math.cos(Math.toRadians(angle));
                float a2 = 0 * (float) Math.sin(Math.toRadians(angle));
                if (MainCode.isPause) {
                    x = eList.get(i).getScaledXWorld();
                    y = eList.get(i).getScaledYWorld();
                    eList.get(i).drawEnemy(gl, x, y, 1);
                } else {
                    eList.get(i).drawEnemy_AnimationAI(gl, x + a1, y + a2, "AI01", 1);
                }

            }
            
            for(int j = 0; j < players.size(); j++)
            {
                collision.collision(eList.get(i), players.get(j), Entity.EnemyStage_1);
            }
            

        }

    }

    public void createAI02(ArrayList<Enemy> eList1, GL gl, ArrayList<Player> players,
            ArrayList<Bullet> enemyBullets) {

        for (int i = 0; i < eList1.size(); i++) {

            AI02(eList1, gl, i);

            for(int j = 0; j < players.size(); j++)
            {
                collision.collision(eList1.get(i), players.get(j), Entity.EnemyStage_2);
            }
            

        }

    }

    private void AI02(ArrayList<Enemy> eList, GL gl, int index) {

        float startPosition = eList.get(index).getYWorld();
        float curretX = eList.get(index).getXWorld() + 2f; //y = sin(x) // PI/180 Math.PI/ 3.14. 
        float omega = 2f;
        float currentY = startPosition + 20 * (float) Math.sin(omega * Math.toRadians(curretX));
        if (MainCode.isPause) {
            float x = eList.get(index).getScaledXWorld();
            float y = eList.get(index).getScaledYWorld();
            eList.get(index).drawEnemy(gl, x, y, 2);
        } else {
            eList.get(index).drawEnemy_AnimationAI(gl, curretX, currentY, "AI02", 2);
            eList.get(index).setXWorld(curretX >= 200 ? -curretX : curretX);
        }

    }

    public void createAI03(ArrayList<Enemy> eList, ArrayList<Enemy> eList2, GL gl, ArrayList<Player> players,
            ArrayList<Bullet> enemyBullets) {
        for (int i = 0; i < eList.size(); i++) {
            AI03_01(eList, gl, i, eList.size());
            
           for(int j = 0; j < players.size(); j++)
            {
                collision.collision(eList.get(i), players.get(j), Entity.EnemyStage_3_01);
            }
        }
        for (int i = 0; i < eList2.size(); i++) {
            AI03_02(eList2, gl, i, eList2.size() * 2);
            for(int j = 0; j < players.size(); j++)
            {
                collision.collision(eList2.get(i), players.get(j), Entity.EnemyStage_3_02);
            }

        }
    }

    private void AI03_01(ArrayList<Enemy> eList, GL gl, int index, int enemiesNumber) {

        if (MainCode.isPause) {

            float x = eList.get(index).getScaledXWorld();
            float y = eList.get(index).getScaledYWorld();
            eList.get(index).drawEnemy(gl, x, y, 3);
        } else {
            float currentX = eList.get(index).getXWorld() + (float) Math.cos(Math.toRadians(eList.get(index).angle)) * 2.5f;
            float currentY = eList.get(index).getYWorld() + (float) Math.sin(Math.toRadians(eList.get(index).angle)) * 2.5f;
            eList.get(index).setXWorld(currentX);
            eList.get(index).setYWorld(currentY);

            eList.get(index).drawEnemy_AnimationAI(gl, currentX, currentY, "AI02", 3);
            if (currentX > 210 && eList.get(index).angle != 210) {
                eList.get(index).angle = 210;
            } else if (currentX < -50 * enemiesNumber - 1 && eList.get(index).angle != 90) {
                eList.get(index).angle = 90;
            } else if (currentY >= 220 && eList.get(index).angle != 0) {
                eList.get(index).angle = 0;
            }

        }

    }

    private void AI03_02(ArrayList<Enemy> eList, GL gl, int index, int enemiesNumber) {

        if (MainCode.isPause) {

            float x = eList.get(index).getScaledXWorld();
            float y = eList.get(index).getScaledYWorld();
            eList.get(index).drawEnemy(gl, x, y, 3);
        } else {
            float currentX = eList.get(index).getXWorld() + (float) Math.cos(Math.toRadians(eList.get(index).angle)) * 2.5f;
            float currentY = eList.get(index).getYWorld() + (float) Math.sin(Math.toRadians(eList.get(index).angle)) * 2.5f;
            eList.get(index).setXWorld(currentX);
            eList.get(index).setYWorld(currentY);
            eList.get(index).drawEnemy_AnimationAI(gl, currentX, currentY, "AI02", 3);
            if (currentX < -210 && eList.get(index).angle != -30) {
                eList.get(index).angle = -30;
            } else if (currentX > 50 * enemiesNumber - 1 && eList.get(index).angle != 90) {
                eList.get(index).angle = 90;
            } else if (currentY >= 220 && eList.get(index).angle != 180) {
                eList.get(index).angle = 180;
            }
        }

    }

    public void createAI04(ArrayList<Enemy> eList, GL gl, ArrayList<Player> players) {
        for (int i = 0; i < eList.size(); i++) {
            eList.get(i).drawEnemy(gl, 4);
             for(int j = 0; j < players.size(); j++)
            {
                 collision.collision(eList.get(i), players.get(j), Entity.EnemyStage_4);
            }
           
        }
    }
}
