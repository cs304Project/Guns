package main.Players;

import main.Entity;
import main.Keys.HandleKeys;
import java.util.ArrayList;
import javax.media.opengl.GL;
import main.Enemys.Enemy;
import main.Sound;

public class Player {

    public Entity e = new Entity();
    public Collision c;
    public healthBar bar;
    // Player setting
    float xWorld;
    float yWorld;
    public float scale = 0.1f;
    public float speed = 0.09f;
    public boolean damege = false;
    public boolean isDestroy = false;

    public int powerUp = 1;
    public static int fireRate;
    public static int score = 0;
    public int liveScore = 5;
    public Sound player_bulletsound = new Sound();
    // projectile
    public static ArrayList<Bullet> bullets = new ArrayList<>();
    // Keyboard orders
    HandleKeys key;
    // default object to attach the class with the maincode class

    GL gl;

    int textureIndex = 2;
    public boolean specialHitted;

    public boolean isSpecialHitted() {
        return specialHitted;
    }

    public void setSpecialHitted(boolean specialHitted) {
        this.specialHitted = specialHitted;
    }

    public void setliveScore(float liveScore) {
        liveScore = liveScore;
    }

    public float getliveScore() {
        return this.liveScore;
    }

    public Player(GL gl, HandleKeys key, float x, float y, float xBar ,float yBar ) {
        this.key = key;
        this.gl = gl;
        c = new Collision(xWorld * speed * scale, yWorld * speed * scale, 0.09f);
        bar = new healthBar(e.imageIdx, 0.8f, 0.9f);

        xWorld = x;
        yWorld = y;
        speed = 0.09f;

    }

    public Player(GL gl, HandleKeys key) {
        this.key = key;
        this.gl = gl;
        c = new Collision(xWorld * speed * scale, yWorld * speed * scale, 0.09f);

        xWorld = 0f;
        yWorld = -100f;
        speed = 0.09f;
    }

    public void setXWorld(float x) {
        xWorld = x;
    }

    public float getXWorld() {
        return this.xWorld;
    }

    public float getScaledXWorld() {
        return getXWorld() * speed * scale;
    }

    public void setYWorld(float y) {
        yWorld = y;
    }

    public float getYWorld() {
        return this.yWorld;
    }

    public float getScaledYWorld() {
        return getYWorld() * speed * scale;
    }

    public void drawPlayer(GL gl) {

        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textureIndex); // Turn Blending On

        // gl.glColor3f(1, 1, 0);
        gl.glPushMatrix();

        gl.glTranslated((xWorld) * scale * speed, (yWorld) * scale * speed, 1);
        // System.out.println("xWorld: " + xWorld + ", yWorld: " + yWorld );
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
        c.drawCirclie(gl, xWorld * scale * speed, yWorld * scale * speed);

    }

    public void drawPlayer(GL gl, float x, float y) {

        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textureIndex); // Turn Blending On

        // gl.glColor3f(1, 1, 0);
        gl.glPushMatrix();

        gl.glTranslated(x, y, 1);
        // System.out.println("xWorld: " + xWorld + ", yWorld: " + yWorld );
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
        c.drawCirclie(gl, xWorld * scale * speed, yWorld * scale * speed);

    }

    public void move() {

        if (key.isKeyPressed(key.UP)) {
            if (yWorld < 100) {

                yWorld++;
                // textureIndex++;
                // textureIndex = (textureIndex % 4) + 1;

            }
        } else if (key.isKeyPressed(key.DOWN)) {
            if (yWorld > -100) {

                yWorld--;
                // textureIndex++;
                /// textureIndex = (textureIndex % 4) + 1;
            }
        } else if (key.isKeyPressed(key.LEFT)) {
            if (xWorld + 5 > -100) {

                xWorld--;
                // textureIndex = (textureIndex % 4) + 1;
            }
        } else if (key.isKeyPressed(key.RIGHT)) {
            if (xWorld < 100) {

                xWorld++;
                // textureIndex = (textureIndex % 4) + 1;
            }
        }

        if (key.isKeyPressed(key.SPACE) && fireRate > 10) {

            createBullet();
            player_bulletsound.PlaySoundEffect(2);
        }
    }
public void move2()
    {
        if (key.isKeyPressed(key.W)) {
            if (yWorld < 100) {

                yWorld++;
                // textureIndex++;
                // textureIndex = (textureIndex % 4) + 1;

            }
        } else if (key.isKeyPressed(key.S)) {
            if (yWorld > -100) {

                yWorld--;
                // textureIndex++;
                /// textureIndex = (textureIndex % 4) + 1;
            }
        } else if (key.isKeyPressed(key.A)) {
            if (xWorld + 5 > -100) {

                xWorld--;
                // textureIndex = (textureIndex % 4) + 1;
            }
        } else if (key.isKeyPressed(key.D)) {
            if (xWorld < 100) {

                xWorld++;
                // textureIndex = (textureIndex % 4) + 1;
            }
        }

        if (key.isKeyPressed(key.E) && fireRate > 10) {

            createBullet();
            player_bulletsound.PlaySoundEffect(2);
        }
    }

    public void drawPlayerBullet(GL gl, String name, boolean isPause) {
            for (int i = 0; i < this.bullets.size(); i++) {
                // Player.bullets.get(i).setYWorld(Player.bullets.get(i).getYWorld());
                 float x =0;
                 float y=0; 
                if(isPause)
                {
                    x =  Player.bullets.get(i).getXWorld();
                    y =  Player.bullets.get(i).getYWorld();
                     Player.bullets.get(i).drawBullet(gl, x, y);
                }
                else
                {
//                    if(powerUp==1){
                    Player.bullets.get(i).drawBullet(gl);
//                    }
//                    else if(powerUp==2){
//                    x -= 0.03f;
//                    Bullet bullet1 = new Bullet(gl, x, y, 0.05f,"PlayerBullet",0.02f,0);
//                    x += 0.05f;
//                    Bullet bullet2 = new Bullet(gl, x, y,0.05f,"PlayerBullet",0.02f,0);
//                    bullets.add(bullet1);
//                    bullets.add(bullet2);
//                       
//                    Player.bullets.get(i).drawBullet(gl);
//                    Player.bullets.get(i).drawBullet(gl);
//
//                }
//                    else{
//                          x -= 0.03f;
//                    Bullet bullet1 = new Bullet(gl, x, y, 0.05f,"PlayerBullet",0.02f,0);
//                    x += 0.05f;
//                    Bullet bullet2 = new Bullet(gl, x, y,0.05f,"PlayerBullet",0.02f,0);
//                    bullets.add(bullet1);
//                    bullets.add(bullet2);
                       
//                    Player.bullets.get(i).drawBullet(gl);
//                    Player.bullets.get(i).drawBullet(gl);
//                    }
                }
                

                if ("stage1".equals(name)) {
                    CheckEnemyColisionWithBullet(Entity.EnemyStage_1, i);

                } else if ("stage2".equals(name)) {
                    CheckEnemyColisionWithBullet(Entity.EnemyStage_2, i);
                } else if ("stage3".equals(name)) {
                    CheckEnemyColisionWithBullet(Entity.EnemyStage_3_01, i);
                    CheckEnemyColisionWithBullet(Entity.EnemyStage_3_02, i);
                } else if ("stage4".equals(name)) {

                    CheckEnemyColisionWithBullet(Entity.EnemyStage_4, i);
                }

                if (bullets.get(i).isDestroyed == true) {
                    e.destroyBulletFromList(Player.bullets.get(i), Player.bullets);

                } else if (Player.bullets.get(i).getYWorld() > .95f) {
                    e.destroyBulletFromList(Player.bullets.get(i), Player.bullets);

                }
            }
        Player.fireRate += 1;

    }
    
    

    private void CheckEnemyColisionWithBullet(ArrayList<Enemy> list, int bulletNum) {
        for (int j = 0; j < list.size()
                && bullets.get(bulletNum).isDestroyed == false; j++) {
            c.collision(bullets.get(bulletNum), list.get(j), list);
        }
    }

    public void createBullet() {
        Bullet bullet = new Bullet(gl, getScaledXWorld(), getScaledYWorld(),
                0.009f, "PlayerBullet", 90, 0.02f);
        bullets.add(bullet);
        fireRate = 0;
    }
}


