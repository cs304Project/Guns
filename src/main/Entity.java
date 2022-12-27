package main;


import java.util.ArrayList;
import javax.media.opengl.GL;
import main.Enemys.EnemyEffect;
import main.Players.Bullet;
import main.Players.PlayerEffect;
import main.Players.PowerUp;
import main.Players.Healthy;
import main.Enemys.Enemy;
import main.Players.healthBar;
import main.Players.specialGift;
import main.Players.specialBullet;

public class Entity {

    //public int currTime = 0;
    public static ArrayList<Bullet> bossBullets = new ArrayList<>();
    public static ArrayList<Enemy> EnemyStage_1 = new ArrayList<>();
    public static ArrayList<Enemy> EnemyStage_2 = new ArrayList<>();

    public static ArrayList<Enemy> EnemyStage_3_01 = new ArrayList<>();
    public static ArrayList<Enemy> EnemyStage_3_02 = new ArrayList<>();
    public static ArrayList<Enemy> EnemyStage_4 = new ArrayList<>();
    public static ArrayList<EnemyEffect> EnemyEffects = new ArrayList<>();
    public static ArrayList<PlayerEffect> PlayerEffects = new ArrayList<>();

    public static ArrayList<Bullet> enemyBullets = new ArrayList<>();
    public static  int[] randmicList = new int[10];;
//    player gift setting
    //Manar: adding power up image index and increasing val
    public int imageIdx = 13;
    float increasingVal;

    healthBar bar = new healthBar(imageIdx, 0.8f, 0.9f);
    healthBar bar1 = new healthBar(imageIdx, 0.8f, 0.8f);
    public ArrayList<healthBar> bars = new ArrayList<>();
    public static ArrayList<specialBullet> specialbullets = new ArrayList<>();
//Manar: power up and healthy lists
    public static ArrayList<PowerUp> presentsList = new ArrayList<>();
    public static ArrayList<specialGift> specialList = new ArrayList<>();
    public static ArrayList<Healthy> healthyList = new ArrayList<>();

    
    //texture setting
    public String textureNames[] = {"background.jpeg", "player.png", "playerBullet.png", "stage1.png",
        "stage2.png", "stage3.png", "stage4.png", "gameover.png", "won.png", "bullet.png",
        "present.png", "healthy.png", "powerup1.png", "powerup2.png", "powerup3.png", "powerup4.png", "powerup5.png",
        "powerup6.png", "gameover.png", "specialPresent.png", "fire.png"};

    public Entity() {
              bars.add(bar);
              bars.add(bar1);

    }

    public static void destroy(Object ob) {
        if (ob instanceof PowerUp) {
            ((PowerUp) ob).isDestroyed = true;

        }
        if (ob instanceof Healthy) {
            ((Healthy) ob).isDestroyed = true;
        }
        if (ob instanceof specialGift) {
            ((specialGift) ob).isDestroyed = true;
        }
        if (ob instanceof specialBullet) {
            ((specialBullet) ob).isDestroyed = true;
        }
    }

    public void destroyBulletFromList(Bullet bullet, ArrayList<Bullet> list) {

        list.remove(bullet);
    }

    public void destroyEnemyFromList(Enemy enemy, ArrayList<Enemy> list) {

        list.remove(enemy);
    }

    public void drawBackground(GL gl, int textures[]) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[0]);	// Turn Blending On
        //gl.glColor3f(0, 0f, 0f);
        gl.glPushMatrix();
        gl.glBegin(GL.GL_QUADS);
        // Front Face
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);
        gl.glEnd();
        gl.glPopMatrix();

        gl.glDisable(GL.GL_BLEND);
    }

    public void drawText(GL gl, float textScale, int index, int textures[]) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[index]);	// Turn Blending On
        //gl.glColor3f(0, 0f, 0f);
        gl.glPushMatrix();
        gl.glScaled(textScale, textScale, 1);
        gl.glBegin(GL.GL_QUADS);
        // Front Face
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);
        gl.glEnd();
        gl.glPopMatrix();

        gl.glDisable(GL.GL_BLEND);

    }
    //Manar: handling the image with livescore

    public void HealthBarCheck(GL gl, int liveScore) {
        if (liveScore == 5) {
            imageIdx = 13;
        } else if (liveScore == 4) {
            imageIdx = 14;
        } else if (liveScore == 3) {
            imageIdx = 15;
        } else if (liveScore == 2) {
            imageIdx = 16;
        } else if (liveScore == 1) {
            imageIdx = 17;
        } else if (liveScore == 0) {
            imageIdx = 18;
            //drawGameOver(gl);
            //and Stop the game
        }
    }

    public void drawGameOver(GL gl) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, MainCode.textures[18]);	// Turn Blending On
        //gl.glColor3f(0, 0f, 0f);
        gl.glPushMatrix();
        gl.glBegin(GL.GL_QUADS);
        // Front Face
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);
        gl.glEnd();
        gl.glPopMatrix();

        gl.glDisable(GL.GL_BLEND);
    }

    
    

    

    public void destroyPresent() {
        if (presentsList.size() > 0) {
            for (int i = 0; i < presentsList.size(); i++) {
                if (presentsList.get(i).getyPresent() < 0-1) {
                    destroy(presentsList.get(i));
                }
            }
        }
    }

    public void destroySpecial() {
        if (specialList.size() > 0) {
            for (int i = 0; i < specialList.size(); i++) {
                if (specialList.get(i).getyPresent() < 0 - 1) {
                    destroy(specialList.get(i));
                }
            }
        }
    }

    public void destroyHealthy() {
        if (healthyList.size() > 0) {
            for (int i = 0; i < healthyList.size(); i++) {
                if (healthyList.get(i).getyHealthy() <  - 1) {
                    destroy(healthyList.get(i));
                }
            }
        }
    }



}
