/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import javax.media.opengl.GL;
import static main.Entity.healthyList;
import static main.Entity.presentsList;
import static main.Entity.specialList;
import main.Players.Bullet;
import main.Players.Collision;
import main.Players.Healthy;
import main.Players.PowerUp;
import main.Players.specialGift;

/**
 *
 * @author hp
 */
public class Loot {
    int timeCountePresent, timeCounteHealthy, timeCountSpecial,
            frameCountPresent, frameCountHealthy, frameCountSpecial;
    Entity e =new Entity(); 
    Collision c =new Collision(); 
    public void checkForTime(GL gl) {
        if ((timeCountePresent) == 15&& timeCountePresent != 0) {
            fillPresentsArray(gl);
            timeCountePresent = 0;
        }
        drawPresent(gl);
        e.destroyPresent();
        if ((timeCountSpecial) == 20 && timeCountSpecial != 0) {
            fillSpecialArray(gl);
            timeCountSpecial = 0;
        }
        drawSpecial( gl);
        e.destroySpecial();
        if ((timeCounteHealthy) == 20 && timeCounteHealthy != 0) {
            fillHealthyArray(gl);
            timeCounteHealthy = 0;
        }
        drawHealthy( gl);
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
    public void createPresent(GL gl,float x, float y, float spead) {
        PowerUp present = new PowerUp(gl, x, y, spead);
        presentsList.add(present);
    }
  public void createSpecial(GL gl ,float x, float y, float spead) {
        specialGift special = new specialGift(gl, x, y, spead);
        specialList.add(special);
    }


    public void createHealthy(GL gl,float x, float y, float spead) {
        Healthy healthy = new Healthy(gl, x, y, spead);
        healthyList.add(healthy);
    }

    public void drawPresent(GL gl) {
        if (presentsList.size() > 0) {
            for (int i = 0; i < presentsList.size(); i++) {
                presentsList.get(i).drawPresent(gl);
                presentsList.get(i).movePresent();
                c.collision(presentsList.get(i), MainCode.player, e.EnemyStage_1);
            }
        }
    }
    public void drawHealthy(GL gl) {

        if (healthyList.size() > 0) {
            for (int i = 0; i < healthyList.size(); i++) {
                healthyList.get(i).drawHealthy(gl);
                healthyList.get(i).moveHealthy();
                c.collision(healthyList.get(i), MainCode.player, e.EnemyStage_1);

            }
        }
    }
    public void drawSpecial(GL gl) {
        if (presentsList.size() > 0) {
            for (int i = 0; i < specialList.size(); i++) {
                specialList.get(i).drawSpecialPresent(gl);
                specialList.get(i).moveSpecial();
               c.collision(specialList.get(i), MainCode.player, e.EnemyStage_1);

            }
        }
    }

    public void fillPresentsArray(GL gl) {
        int max = 1;
        int min = -1;
        float speed = 0.01f;
        float positiony = 1f;
        float positionx = (float) (Math.random() * (max - (min))) + (min);
        createPresent(gl,positionx, positiony, speed);
        speed += 0.5f;

    }
    public void fillHealthyArray(GL gl) {
        int max = 1;
        int min = -1;
        float speed = 0.01f;
        float positiony = 1f;
        float positionx = (float) (Math.random() * (max - (min))) + (min);
        createHealthy(gl,positionx, positiony, speed);
        speed += 0.5f;
        positiony += 0.5;
    }
    public void fillSpecialArray(GL gl) {
        int max = 1;
        int min = -1;
        float speed = 0.01f;
        float positiony = 1f;
        float positionx = (float) (Math.random() * (max - (min))) + (min);
        createSpecial(gl,positionx, positiony, speed);
        speed += 0.5f;
    }
   
}
