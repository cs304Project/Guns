package main.Enemys;

import main.Entity;
import java.util.ArrayList;
import java.util.Random;
import javax.media.opengl.GL;
import static main.MainCode.gl;
import main.Players.Bullet;
import main.Players.Collision;
import main.Sound;
import main.Stage.Stage1;
import main.Stage.Stage2;
import main.Stage.Stage3;
import main.Stage.Stage4;
import main.Timing;

public class Enemy {

    // default object to attach the class with the maincode class
    Entity e = new Entity();
    public boolean isFire = false;
    // Enemy setting
    protected float xWorld;
    protected float yWorld;
    public float scale = 0.1f;
    public float speed = 0.05f;
    float angle = 0f;
    float verticalAnimation = 0;
    float accumelation = 0.1f;
    protected float collidingRaduis = 0.09f;
    float bulletScale = 0.02f;
    public int health = 1;
    public int bonusScore = 1;

    // Bullet bullet = new Bullet(gl, xWorld, yWorld, speed, "enemy", angle, scale);
    Sound Enemy_bulletsound = new Sound();

    // Keyboard orders
    Bullet bullet = new Bullet(gl, xWorld, yWorld, speed, "enemy", angle, scale);
    Timing time1 = new Timing();
    public Collision c;
    public int index = 0;
    int time = 0;

    public Enemy(float x, float y) {

        xWorld = x;
        yWorld = y;
        speed = 0.05f;
        angle = 0;
        c = new Collision(xWorld * speed * scale, yWorld * speed * scale, collidingRaduis);
    }

    public Enemy() {

        xWorld = -100f;
        yWorld = 100f;
        speed = 0.05f;
        angle = 0;
        c = new Collision(xWorld * speed * scale, yWorld * speed * scale, collidingRaduis);
    }

    public Enemy(float x, float y, int health) {

        xWorld = x;
        yWorld = y;
        speed = 0.05f;
        angle = 0;
        this.health = health;
        c = new Collision(xWorld * speed * scale, yWorld * speed * scale, collidingRaduis);
    }

    public Enemy(float x, float y, int health, int bonusScore) {

        xWorld = x;
        yWorld = y;
        speed = 0.05f;
        angle = 0;
        this.health = health;
        this.bonusScore = bonusScore;
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

    public int getIndexTexture() {
        return index;
    }

    public float getScaledXWorld() {
        return getXWorld() * scale * speed;
    }

    public float getScaledYWorld() {
        return getYWorld() * scale * speed;
    }

    public void drawEnemy(GL gl, int stage) {

        gl.glEnable(GL.GL_BLEND);
        time++;

        switch (stage) {
            case 1:
                if (time % 5 == 0) {
                    index = (index + 1) % 7;
                }

                gl.glBindTexture(GL.GL_TEXTURE_2D, Stage1.enemyTextures[index]); // Turn Blending On
                break;

            case 2:
                if (time % 5 == 0) {
                    index = (index + 1) % 14;
                }
                gl.glBindTexture(GL.GL_TEXTURE_2D, Stage2.enemyTextures[index]); // Turn Blending On
                break;
            case 3:
                if (time % 5 == 0) {
                    index = (index + 1) % 4;
                }
                gl.glBindTexture(GL.GL_TEXTURE_2D, Stage3.enemyTextures[index]); // Turn Blending On
                break;
            case 4:
                if (time % 5 == 0) {
                    index = (index + 1) % 6;
                }
                gl.glBindTexture(GL.GL_TEXTURE_2D, Stage4.enemyTextures[index]); // Turn Blending On
                break;

        }
        // gl.glColor3f(1, 1, 0);
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
        // drawenemyBullets(gl, bullet);

        c.drawCirclie(gl, xWorld * speed * scale, yWorld * speed * scale);

    }

    public void drawEnemy(GL gl, float x, float y, int stageNumber) {

        gl.glEnable(GL.GL_BLEND);
        switch (stageNumber) {
            case 1:
                gl.glBindTexture(GL.GL_TEXTURE_2D, Stage1.enemyTextures[getIndexTexture()]);
                break;

            case 2:
                gl.glBindTexture(GL.GL_TEXTURE_2D, Stage2.enemyTextures[getIndexTexture()]);
                break;
            case 3:
                gl.glBindTexture(GL.GL_TEXTURE_2D, Stage3.enemyTextures[getIndexTexture()]);
                break;
            case 4:
                gl.glBindTexture(GL.GL_TEXTURE_2D, Stage4.enemyTextures[getIndexTexture()]);
                break;
        }

        // gl.glColor3f(1, 1, 0);
        gl.glPushMatrix();
        gl.glTranslated(x, y, 1);
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
        // drawenemyBullets(gl, bullet);

        c.drawCirclie(gl, x, y);

    }

    public void drawEnemy_AnimationAI(GL gl, float x, float y, String AIName, int stage) {
        gl.glEnable(GL.GL_BLEND);
        time++;

        switch (stage) {
            case 1:
                if (time % 7 == 0) {
                    index = (index + 1) % 7;

                }
                gl.glBindTexture(GL.GL_TEXTURE_2D, Stage1.enemyTextures[index]); // Turn Blending On
                break;
            case 2:
                if (time % 7 == 0) {
                    index = (index + 1) % 14;

                }
                gl.glBindTexture(GL.GL_TEXTURE_2D, Stage2.enemyTextures[index]); // Turn Blending On
                break;
            case 3:
                if (time % 7 == 0) {
                    index = (index + 1) % 4;

                }
                gl.glBindTexture(GL.GL_TEXTURE_2D, Stage3.enemyTextures[index]); // Turn Blending On
                break;
            case 4:
                if (time % 7 == 0) {
                    index = (index + 1) % 6;

                }
                gl.glBindTexture(GL.GL_TEXTURE_2D, Stage3.enemyTextures[index]); // Turn Blending On
                break;
        }
        // gl.glColor3f(1, 1, 0);
        gl.glPushMatrix();

        if ("AI01".equals(AIName)) {
            verticalAnimation = (verticalAnimation + accumelation);
            if (verticalAnimation > 5) {
                accumelation = -accumelation;
            } else if (verticalAnimation < 0f) {
                accumelation = -accumelation;
            }
            translateEnemy(gl, x, y + (speed * scale * verticalAnimation));
        } else if ("AI02".equals(AIName)) {
            translateEnemy(gl, x, y);
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
        // drawenemyBullets(gl, bullet);
        c.drawCirclie(gl, x * speed * scale, y * speed * scale + (speed * scale * verticalAnimation));

        if (isFire) {
            createBullet(gl, Entity.enemyBullets, 270, "EnemyBullet", .03f);
            Enemy_bulletsound.PlaySoundEffect(4);
            isFire = false;

        }
    }

    private void translateEnemy(GL gl, float x, float y) {
        gl.glTranslated(x * speed * scale, y * speed * scale, 1);
    }

    public void createBullet(GL gl, ArrayList<Bullet> bullets, float angle, String typBullet, float bulletScale) {

        Bullet bullet = new Bullet(gl, getScaledXWorld(), getScaledYWorld(), 0.005f, typBullet, angle, bulletScale);
        bullets.add(bullet);

    }

}
