package main.Players;

import main.Enemys.Enemy;
import main.Entity;
import main.Keys.HandleKeys;
import java.util.ArrayList;
import javax.media.opengl.GL;
import static main.MainCode.enemyList;

public class Player {
    
   Entity e = new Entity();
    
    
    //Player setting
    float xWorld;
    float yWorld;
    public float scale = 0.1f;
    public float speed = 0.09f;
    public boolean damege = false;
    public int powerUp = 3;
    public static int fireRate;
    
    //projectile 
    public static ArrayList<Bullet> bullets = new ArrayList<>();
    //Keyboard orders
    HandleKeys key;
    //default object to attach the class with the maincode class
    public Collision c = new Collision(0 * speed * scale, 0 * speed * scale, 0.09f);
    
    GL gl;

    int textureIndex = 1;

    public Player(GL gl, HandleKeys key, float x, float y) {
        this.key = key;
        this.gl = gl;

        xWorld = x;
        yWorld = y;
        speed = 0.09f;

    }

    public Player(GL gl, HandleKeys key) {
        this.key = key;
        this.gl = gl;

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

    public void setYWorld(float y) {
        yWorld = y;
    }

    public float getYWorld() {
        return this.yWorld;
    }

    public void drawPlayer(GL gl) {

        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textureIndex);	// Turn Blending On

        //gl.glColor3f(1, 1, 0);
        gl.glPushMatrix();

        gl.glTranslated((xWorld) * scale * speed, (yWorld) * scale * speed, 1);
        //System.out.println("xWorld: " + xWorld +  ", yWorld: " + yWorld );
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
                textureIndex++;
                textureIndex = (textureIndex % 4) + 1;

            }
        } else if (key.isKeyPressed(key.DOWN)) {
            if (yWorld > -100) {

                yWorld--;
                textureIndex++;
                textureIndex = (textureIndex % 4) + 1;
            }
        } else if (key.isKeyPressed(key.LEFT)) {
            if (xWorld + 5 > -100) {

                xWorld--;
                textureIndex = (textureIndex % 4) + 1;
            }
        } else if (key.isKeyPressed(key.RIGHT)) {
            if (xWorld < 100) {

                xWorld++;
                textureIndex = (textureIndex % 4) + 1;
            }
        }
        
        if (key.isKeyPressed(key.SPACE) && fireRate > 10)
        {
            createBullet();
        }
    }

    public void drawPlayerBullet(GL gl) {
 
        for (int i = 0; i < Player.bullets.size(); i++) {
            //Player.bullets.get(i).setYWorld(Player.bullets.get(i).getYWorld());
            Player.bullets.get(i).drawBullet(gl, "PlayerBullet");
            for(int j=0;j<enemyList.size()&&bullets.get(i).isDestroyed==false;j++){
                     
                  e.collision(bullets.get(i),enemyList.get(j));
                  
                    
                 }
            if(bullets.get(i).isDestroyed==true){
                e.destroyBulletFromList(Player.bullets.get(i),Player.bullets);
                
            }
                
          
            else if (Player.bullets.get(i).getYWorld()> 1) {
                e.destroyBulletFromList(Player.bullets.get(i),Player.bullets);
            }
        }
        Player.fireRate += 1;
        
    }
    
    
    public void createBullet()
    {
        
        Bullet bullet = new Bullet(gl,xWorld * scale * speed, yWorld * scale * speed , 0.009f, "PlayerBullet");
        bullets.add(bullet);
        fireRate = 0;
    }
}
