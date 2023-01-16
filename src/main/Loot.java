/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.util.ArrayList;
import javax.media.opengl.GL;
import static main.Entity.healthyList;
import static main.Entity.presentsList;
import static main.Entity.specialList;
import main.Players.Collision;
import main.Players.Healthy;
import main.Players.Player;
import main.Players.PowerUp;
import main.Players.specialGift;

/**
 *
 * @author hp
 */
public class Loot {

    int timeCountePresent, timeCounteHealthy, timeCountSpecial,
            frameCountPresent, frameCountHealthy, frameCountSpecial;
    Entity e = new Entity();
    Collision c = new Collision();

    public void checkForTime(GL gl, ArrayList<Player> players , boolean isPause) {
        if ((timeCountePresent) == 10 && timeCountePresent != 0) {
            fillPresentsArray(gl);
            timeCountePresent = 0;
        }
        drawPresent(gl, players, isPause);
        e.destroyPresent();
        if ((timeCountSpecial) == 25 && timeCountSpecial != 0) {
            fillSpecialArray(gl);
            timeCountSpecial = 0;
        }
        drawSpecial(gl, players, isPause);
        e.destroySpecial();
        if ((timeCounteHealthy) == 20 && timeCounteHealthy != 0) {
            fillHealthyArray(gl);
            timeCounteHealthy = 0;
        }
        drawHealthy(gl, players, isPause);
        e.destroyHealthy();
    }

    public void timePerFrame() {
        frameCountPresent++;
        frameCountHealthy++;
        frameCountSpecial++;

        if (frameCountPresent == 60) {
            timeCountePresent++;
            frameCountPresent = 0;
        }
        if (frameCountHealthy == 60) {
            timeCounteHealthy++;
            frameCountHealthy = 0;
        }
        if (frameCountSpecial == 60) {
            timeCountSpecial++;
            frameCountSpecial = 0;
        }
    }

    public void createPresent(GL gl, float x, float y, float spead) {
        PowerUp present = new PowerUp(gl, x, y, spead);
        presentsList.add(present);
    }

    public void createSpecial(GL gl, float x, float y, float spead) {
        specialGift special = new specialGift(gl, x, y, spead);
        specialList.add(special);
    }

    public void createHealthy(GL gl, float x, float y, float spead) {
        Healthy healthy = new Healthy(gl, x, y, spead);
        healthyList.add(healthy);
    }

    public void drawPresent(GL gl, ArrayList<Player> players, boolean isPause) {
        if (!presentsList.isEmpty()) {
            for (int i = 0; i < presentsList.size(); i++) {
                
                if(isPause)
                {
                    float x  = presentsList.get(i).getxPresent();
                    float y  = presentsList.get(i).getyPresent();
                    presentsList.get(i).drawPresent(gl, x , y);
                }
                else
                {
                    presentsList.get(i).drawPresent(gl);
                    presentsList.get(i).movePresent();
                }
                

                for(int j = 0; j <players.size(); j++)
                {
                     c.collision(presentsList.get(i), players.get(j), e.EnemyStage_1);
                     if(presentsList.get(i).isDestroyed)
                     {
                         
                         presentsList.remove(presentsList.get(i));
                         break;
                     }
                }
                
                
               

            }
        }
    }

    public void drawHealthy(GL gl, ArrayList<Player> players, boolean isPause) {

        if (!healthyList.isEmpty()) {
            for (int i = 0; i < healthyList.size(); i++) {
                
                
                
                if(isPause)
                {
                    float x  = healthyList.get(i).getxHealthy();
                    float y  = healthyList.get(i).getyHealthy();
                    healthyList.get(i).drawHealthy(gl, x , y);
                }
                else
                {
                    healthyList.get(i).drawHealthy(gl);
                    healthyList.get(i).moveHealthy();
                }
                
                
                 for(int j = 0; j <players.size() && healthyList.get(i) != null; j++)
                {
                      c.collision(healthyList.get(i), players.get(j), Entity.EnemyStage_1);
                      if(healthyList.get(i).isDestroyed)
                     {
                         
                         healthyList.remove(healthyList.get(i));
                         break;
                     }
                }
               
            }
        }
    }

    public void drawSpecial(GL gl, ArrayList<Player> players , boolean isPause) {
        if (!presentsList.isEmpty()) {
            for (int i = 0; i < specialList.size(); i++) {
                
                
                if(isPause)
                {
                    float x  = specialList.get(i).getxPresent();
                    float y  = specialList.get(i).getyPresent();
                    specialList.get(i).drawSpecialPresent(gl, x , y);
                }
                else
                {
                    specialList.get(i).drawSpecialPresent(gl);
                    specialList.get(i).moveSpecial();
                }
                
                for(int j = 0; j <players.size(); j++)
                {
                     c.collision(specialList.get(i), players.get(j), Entity.EnemyStage_1);
                      if(specialList.get(i).isDestroyed)
                     {
                         
                         specialList.remove(specialList.get(i));
                         break;
                     }
                }
                
                
            }
        }
    }

    public void fillPresentsArray(GL gl) {
        int max = 1;
        int min = -1;
        float speed = 0.01f;
        float positiony = 1f;
        float positionx = (float) (Math.random() * (max - (min))) + (min);
        createPresent(gl, positionx, positiony, speed);
        speed += 0.5f;

    }

    public void fillHealthyArray(GL gl) {
        int max = 1;
        int min = -1;
        float speed = 0.01f;
        float positiony = 1f;
        float positionx = (float) (Math.random() * (max - (min))) + (min);
        createHealthy(gl, positionx, positiony, speed);
        speed += 0.5f;
        positiony += 0.5;
    }

    public void fillSpecialArray(GL gl) {
        int max = 1;
        int min = -1;
        float speed = 0.01f;
        float positiony = 1f;
        float positionx = (float) (Math.random() * (max - (min))) + (min);
        createSpecial(gl, positionx, positiony, speed);
        speed += 0.5f;
    }

}
