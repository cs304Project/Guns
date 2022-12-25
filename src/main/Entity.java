package main;
import main.Enemys.Enemy;
import java.util.ArrayList;
import javax.media.opengl.GL;
import main.Players.Bullet;

public class Entity {

    //public int currTime = 0;
    public static ArrayList<Bullet> bossStorage = new ArrayList<>();
    public static ArrayList<Enemy> EnemyStage_1 = new ArrayList<>();
    public static ArrayList<Enemy> EnemyStage_2_01 = new ArrayList<>();
    public static ArrayList<Enemy> EnemyStage_2_02 = new ArrayList<>();
    public static ArrayList<Enemy> EnemyStage_2_03 = new ArrayList<>();
    public static ArrayList<Enemy> EnemyStage_2_04 = new ArrayList<>();
    public static ArrayList<Enemy> EnemyStage_3_01 = new ArrayList<>();
    public static ArrayList<Enemy> EnemyStage_3_02 = new ArrayList<>();
    public static ArrayList<Enemy> EnemyStage_4 = new ArrayList<>();
    public static ArrayList<Bullet> enemyBullets = new ArrayList<>();
    //texture setting
    public String textureNames[] = {"background.jpeg","player.png","playerBullet.png","stage1.png",
        "stage2.png","stage3.png","stage4.png","gameover.png","won.png"};  
    public void destroy(Object obj) {

        obj = null;
    }
    public void destroyBulletFromList(Bullet bullet, ArrayList<Bullet> list) {

        list.remove(bullet);
    }
    public void destroyEnemyFromList(Enemy enemy, ArrayList<Enemy> list) {

        list.remove(enemy);
    }
    public void drawBackground(GL gl , int textures[]) {
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
    public void drawText(GL gl,float textScale ,int index,int textures[]) {
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
}