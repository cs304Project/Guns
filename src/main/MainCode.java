package main;

import main.Enemys.Enemy;

import main.Keys.HandleKeys;
import Textures.AnimListener;
import Textures.TextureReader;
import java.util.ArrayList;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import main.Players.Bullet;
import main.Players.Player;
import main.Stage.Stage;

//this calss is used to run the game in infinite loop using AnimListener
//display, init, reshape and displayChanged  are abtract method from GLeventLisener into AnimListener class
// this class is attached with Gameplay to run the code
public class MainCode extends AnimListener {

    //deault Objects
    GL gl;
    GLCanvas canvas;
    Entity e = new Entity();
    Stage stage = new Stage();
    Timing time = new Timing();

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

   

    
    //EnemyAI ai = new EnemyAI();
    public static int stage1 = 24;
    public static int stage2 = 40;
    public static int stage3 = 15;
    boolean StageOneOn = true;
    boolean StageAnimation = true;
    boolean StageTwoOn = false;
    boolean StageThreeOn = false;
    


    public void setCanvas(GLCanvas canvas) {
        canvas.addKeyListener(key);
        this.canvas = canvas;
    }

    public GLCanvas getCanvas() {
        return this.canvas;
    }

        
    @Override
    public void init(GLAutoDrawable glad) {
       
        initDefaultValues(glad);
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

        
        
        //-------------------------NewClearCode-------------------------------//
        if(StageOneOn)
        {
            if (StageAnimation) {
                
                
                e.createStage1Enemys(stage1);
                
                //if(time < 2)
                //drawText(jlabel) 1 --> 2
                //else if(time > 2
                StageAnimation = !StageAnimation;
                
            }
            
            stage.drawEnemy(gl, player, 1);
            
        }
        else if(StageTwoOn)
        {
            if (StageAnimation) {
                e.createStage1Enemys(stage2);
                StageAnimation = !StageAnimation;   
            }else
            {
                stage.drawEnemy(gl, player, 2);
            }
            
            
        }
        else if(StageThreeOn)
        {
            if (StageAnimation) {
                e.createStage1Enemys(stage3);
                StageAnimation = !StageAnimation;   
            }
            
            stage.drawEnemy(gl, player, 3);
        }
//        else if(StageFourOn)
//        {
//            if (StageAnimation) {
//                e.createStage1Enemys(stage1);
//                StageAnimation = !StageAnimation;   
//            }
//            
//            stage.drawEnemy(gl, player, 4);
//        }
        
            
        //Draw enemies
        //drawEnemy(gl);
        //drawenemyBullets(gl, enemyBullets);
        
        
        //drawBossBullets(gl, Entity.bossStorage);
        
        
    }

    private void playerActions(GL gl) {
        player.drawPlayer(gl);
        player.move();
        player.drawPlayerBullet(gl , "BossFight");
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

  

    private void drawenemyBullets(GL gl, ArrayList<Bullet> bullets) {
        for (int i = 0; i < bullets.size(); i++) {

            bullets.get(i).drawBullet(gl);
            if (bullets.get(i).getYWorld() < -1 || bullets.get(i).getYWorld() > 1) {
                e.destroyBulletFromList(bullets.get(i), bullets);
            }

        }
    }
    
    private void drawBossBullets(GL gl,  ArrayList<Bullet> bullets)
    {
        for (int i = 0; i < bullets.size(); i++) {
            if("SpecilEnemyBullet".equals(bullets.get(i).typeBullet))
            {
                bullets.get(i).drawFollowingBullet(gl,
                        player.getScaledXWorld() 
                        , player.getScaledYWorld() );
                
                
            }
            else
            {
                bullets.get(i).drawBullet(gl);
            }
            
            if (bullets.get(i).getYWorld() < -1) {

            bullets.get(i).drawBullet(gl);
            if (bullets.get(i).getYWorld() < -1 || bullets.get(i).getYWorld() > 1) {

                e.destroyBulletFromList(bullets.get(i), bullets);
            }

        }
    }
}
   
    public void createEnemy(ArrayList<Enemy> enemyList, float x, float y ,int health) {
        Enemy enemy = new Enemy( x, y,health);
        //EnemyBoss enemy = new EnemyBoss(gl, x, y);
        enemyList.add(enemy);
        
    }

    private void initDefaultValues(GLAutoDrawable glad) {
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
