package main.Enemys;

import main.Entity;
import main.Keys.HandleKeys;
import main.Timing;
import java.util.ArrayList;
import javax.media.opengl.GL;
import main.Players.Bullet;
import main.Players.Collision;

public class Enemy {

    //default object to attach the class with the maincode class
    Entity e = new Entity();
    GL gl;

    //Enemy setting
    protected float xWorld;
    protected float yWorld;
    protected float scale = 0.1f;
    protected float speed = 0.05f;
    float angle = 0f;
    float verticalAnimation = 0;
    float accumelation = 0.1f;
    protected float collidingRaduis = 0.09f;
    float bulletScale = 0.02f;
    public int health=1;

    //Keyboard orders

    int fireRate = 500 + (int) (Math.random() * 20000000);

    public Collision c;

    final int textureIndex = 6;

    public Enemy(GL gl, float x, float y) {
        this.gl = gl;
        xWorld = x;
        yWorld = y;
        speed = 0.05f;
        angle = 0;
        c = new Collision(xWorld * speed * scale, yWorld * speed * scale, collidingRaduis);
    }

    public Enemy(GL gl) {

        this.gl = gl;
        xWorld = -100f;
        yWorld = 100f;
        speed = 0.05f;
        angle = 0;
        c = new Collision(xWorld * speed * scale, yWorld * speed * scale, collidingRaduis);
    }
    
    public Enemy(GL gl,float x, float y,int health){
        this.gl = gl;
        xWorld = x;
        yWorld = y;
        speed = 0.05f;
        angle = 0;
        this.health = health;
        c = new Collision(xWorld * speed * scale, yWorld * speed * scale, collidingRaduis);
    }
    public void setXWorld(float x) {
        xWorld = x;
    }

    public float getXWorld() {
        return this.xWorld;
    }

    public void setYWorld(float y) {
        yWorld = y;
    }

    public float getYWorld() {
        return this.yWorld;
    }

    public void drawEnemy(GL gl) {

        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textureIndex);	// Turn Blending On

        //gl.glColor3f(1, 1, 0);
        gl.glPushMatrix();
        gl.glTranslated((xWorld * scale * speed), (yWorld * scale * speed), 1);
        gl.glScaled(scale, scale, 1);
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

        c.drawCirclie(gl, xWorld * speed * scale, yWorld * speed * scale);

    }

    public void drawEnemy_AnimationAI(GL gl, float x, float y, String s) {

        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textureIndex);	// Turn Blending On
        //gl.glColor3f(1, 1, 0);
        gl.glPushMatrix();

        if ("AI01".equals(s)) {
            verticalAnimation = (verticalAnimation + accumelation);
            if (verticalAnimation > 5) {
                accumelation = -accumelation;
            } else if (verticalAnimation < 0f) {
                accumelation = -accumelation;
            }
            translateEnemy(x, y + (speed * scale * verticalAnimation));
        } else if ("AI02".equals(s)) {
            translateEnemy(x, y);
        }

        gl.glScaled(scale, scale, 1);
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
        c.drawCirclie(gl, x * speed * scale, y * speed * scale + (speed * scale * verticalAnimation));
    }

    private void translateEnemy(float x, float y) {
        gl.glTranslated(x * speed * scale, y * speed * scale, 1);
    }

    public void AutoAttack(GL gl, ArrayList<Bullet> bullets) {

        if (fireRate == 500) {
            Bullet bullet = new Bullet(gl, xWorld * scale * speed, yWorld * scale * speed, 0.005f, "EnemyBullet", 270, bulletScale);
            bullets.add(bullet);
        }

        fireRate = (fireRate + 1) % 501;

    }

    public void createBullet(GL gl, ArrayList<Bullet> bullets, float angle, String typBullet, float bulletScale) {

        Bullet bullet = new Bullet(gl, xWorld * scale * speed, (yWorld - 10) * scale * speed, 0.005f, typBullet, angle, bulletScale);
        bullets.add(bullet);
    }

}
