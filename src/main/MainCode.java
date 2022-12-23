package main;

import main.Enemys.Enemy;
import main.Enemys.EnemyAI;
import main.Keys.HandleKeys;
import Textures.AnimListener;
import Textures.TextureReader;
import java.util.ArrayList;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import main.Enemys.EnemyBoss;
import main.Players.Bullet;
import main.Players.Player;

//this calss is used to run the game in infinite loop using AnimListener
//display, init, reshape and displayChanged  are abtract method from GLeventLisener into AnimListener class
// this class is attached with Gameplay to run the code
public class MainCode extends AnimListener {

    //deault Objects
    GL gl;
    GLCanvas canvas;
    Entity e = new Entity();

    //texture setting
    String textureNames[] = {
        "Man1.png", "Man2.png", "Man3.png", "Man4.png",
        "back1.jpg", "24.png",
        "bullet.png"};

    TextureReader.Texture texture[] = new TextureReader.Texture[textureNames.length];
    int textures[] = new int[textureNames.length];

    //Key setting
    HandleKeys key = new HandleKeys();

    //player setting
    Player player = new Player(gl, key);

    //Enemy setting
    public static ArrayList<Enemy> enemyList = new ArrayList<>();

    ArrayList<Bullet> enemyBullets = new ArrayList<>();

    EnemyAI ai = new EnemyAI();
    //public static int stage1 = 24;
    public static int stage2 = 40;
    
    
    

    public void setCanvas(GLCanvas canvas) {
        canvas.addKeyListener(key);
        this.canvas = canvas;
    }

    public GLCanvas getCanvas() {
        return this.canvas;
    }

        
    @Override
    public void init(GLAutoDrawable glad) {
       
        initdefaultvalues(glad);
        
        /*-------------Stage_1------------------*/
//        for (int i = 0; i < stage1; i++) {
//            createEnemy(-200  ,0); 
//        }

        /*-------------Stage_2------------------*/
//        float startPosition;
//        for (int i = 0; i < stage2; i++) {
//            if ((stage2 - 1) / 4 >= i) {
//                startPosition = 20f;
//            } else if (2 * (stage2 - 1) / 4 >= i) {
//                startPosition = 70f;
//            } else if (3 * (stage2 - 1) / 4 >= i) {
//                startPosition = 120f;
//            } else {
//                startPosition = 170f;
//            }
//            createEnemy(enemyList, -200 - (i * 50), startPosition);
//        }
        
        /*-------------BossFight------------------*/
//        createEnemy(enemyList, 0f, 20f);
        
        gl.glLoadIdentity();
 
    }

    //Run the code in this method
    @Override
    public void display(GLAutoDrawable glad) {
        
        gl = glad.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

        //Draw the background
        drawBackground(gl);

        //Draw the player
        playerActions(gl);

        //Draw enemies
        drawEnemy();
        //drawenemyBullets(gl, enemyBullets);
        
        
        drawBossBullets(gl, Entity.bossStorage);
        
        
    }

    private void playerActions(GL gl) {
        player.drawPlayer(gl);
        player.move();
        player.drawPlayerBullet(gl);
    }

    public void drawBackground(GL gl) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[4]);	// Turn Blending On

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

    private void drawEnemy() {
        //ai.createAI01(enemyList , gl , player , enemyBullets);

        //ai.createAI02(enemyList, gl, player, enemyBullets);
        
        //enemyList.get(0).drawEnemy(gl);

    }

    private void drawenemyBullets(GL gl, ArrayList<Bullet> bullets) {
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).drawBullet(gl);
            if (bullets.get(i).getYWorld() < -1) {
                e.destroyBulletFromList(bullets.get(i), bullets);
            }

        }
    }
    
    private void drawBossBullets(GL gl,  ArrayList<Bullet> bullets)
    {
        for (int i = 0; i < bullets.size(); i++) {
            if("SpecilEnemyBullet".equals(bullets.get(i).typeBullet))
            {
                bullets.get(i).drawFollowingBullet(gl, player.getXWorld() * player.scale * player.speed , player.getYWorld()* player.scale * player.speed );
                
            }
            else
            {
                bullets.get(i).drawBullet(gl);
            }
            
            if (bullets.get(i).getYWorld() < -1) {
                e.destroyBulletFromList(bullets.get(i), bullets);
            }

        }
    }

    public void createEnemy(ArrayList<Enemy> enemyList, float x, float y) {
        //Enemy enemy = new Enemy(gl, key, x, y);
        EnemyBoss enemy = new EnemyBoss(gl, x, y);
        enemyList.add(enemy);
        
    }

    private void initdefaultvalues(GLAutoDrawable glad) {
        gl = glad.getGL();
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);    //This Will Clear The Background Color To Black
        //gl.glOrtho(1, , , , 0, 0);
        gl.glEnable(GL.GL_TEXTURE_2D);  // Enable Texture Mapping
        gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
        e.readTexture(gl, textureNames, textures, texture);
    }

    @Override
    public void reshape(GLAutoDrawable glad, int i, int i1, int i2, int i3) {
        //useless method
    }

    @Override
    public void displayChanged(GLAutoDrawable glad, boolean bln, boolean bln1) {
        //useless method
    }

}
