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
import main.Stage.Stage1;
import main.Stage.Stage2;
import main.Stage.Stage3;

//this calss is used to run the game in infinite loop using AnimListener
//display, init, reshape and displayChanged  are abtract method from GLeventLisener into AnimListener class
// this class is attached with Gameplay to run the code
public class MainCode extends AnimListener {
    float textScale = 0.1f;
    //deault Objects
    public static GL gl;
    GLCanvas canvas;
    Entity e = new Entity();
    Stage stage = new Stage();

    Timing time = new Timing();
    String playerStage = "stage1";

    ReadImages read = new ReadImages();
    //Key setting
    HandleKeys key = new HandleKeys();

    public TextureReader.Texture texture[] = new TextureReader.Texture[e.textureNames.length];
    public int textures[] = new int[e.textureNames.length];

    //player setting
    Player player = new Player(gl, key);

    //EnemyAI ai = new EnemyAI();
    public static int stage1 = 24;
    public static int stage2 = 40;
    public static int stage3 = 15;
    boolean StageOneOn = true;
    boolean enemyKey = true;
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
        time.start();
    }

    //Run the code in this method
    @Override
    public void display(GLAutoDrawable glad) {
        gl = glad.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

        //Draw the background
        e.drawBackground(gl, textures);

        //Draw the player
        playerActions(gl);

        //-------------------------NewClearCode-------------------------------//
        if (StageOneOn) {
            if (time.seconds < 2) {
                e.drawText(gl, textScale, 3, textures);
                textScale = (textScale + 0.002f) % 1f;
            } else if (time.seconds < 5) {
                e.drawText(gl, 0.3f, 3, textures);
            }
            if (enemyKey) {
                Stage1 s1 = new Stage1(stage1);
                enemyKey = false;
            }
            stage.drawEnemy(gl, player, 1);
            if (Entity.EnemyStage_1.size() <= 0) {
                StageOneOn = false;
                StageTwoOn = true;
                enemyKey = true;
                time.stop();
                textScale = 0.1f;

            }
        } else if (StageTwoOn) {
            time.start();
            playerStage = "stage2";
            if (time.seconds < 2) {
                e.drawText(gl, textScale, 4, textures);
                textScale = (textScale + 0.002f) % 1f;
            } else if (time.seconds < 5) {
                e.drawText(gl, 0.3f, 4, textures);
            }
            if (enemyKey) {
                Stage2 s2 = new Stage2(stage2);
                enemyKey = false;
            }
            stage.drawEnemy(gl, player, 2);
            if (Entity.EnemyStage_2.size() <= 0) {
                StageTwoOn = false;
                StageThreeOn = true;
                enemyKey = true;
                time.stop();
                textScale = 0.1f;
            }

        } else if (StageThreeOn) {
            time.start();
            playerStage = "stage3";

            System.out.println("in Stage 3");
            if (time.seconds < 2) {
                e.drawText(gl, textScale, 5, textures);
                textScale = (textScale + 0.002f) % 1f;
            } else if (time.seconds < 5) {
                e.drawText(gl, 0.3f, 5, textures);
            }
            if (enemyKey) {
                Stage3 s3 = new Stage3(stage3);
                enemyKey = false;
            }
            stage.drawEnemy(gl, player, 3);
            if (Entity.EnemyStage_3_01.size() <= 0) {
                StageThreeOn = false;
                enemyKey = true;
            }
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
      
       // drawenemyBullets(gl, enemyBullets);
        //drawBossBullets(gl, Entity.bossStorage);
        
    }
    private void playerActions(GL gl) {
        player.drawPlayer(gl);
        player.move();
        player.drawPlayerBullet(gl, playerStage);
    }
    
    private void drawBossBullets(GL gl, ArrayList<Bullet> bullets) {
        for (int i = 0; i < bullets.size(); i++) {
            if ("SpecilEnemyBullet".equals(bullets.get(i).typeBullet)) {
                bullets.get(i).drawFollowingBullet(gl,
                        player.getScaledXWorld(),
                        player.getScaledYWorld());

            } else {
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

    public void createEnemy(ArrayList<Enemy> enemyList, float x, float y, int health) {
        Enemy enemy = new Enemy(x, y, health);
        //EnemyBoss enemy = new EnemyBoss(gl, x, y);
        enemyList.add(enemy);
    }

    private void initDefaultValues(GLAutoDrawable glad) {
        gl = glad.getGL();
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);    //This Will Clear The Background Color To Black
        //gl.glOrtho(1, , , , 0, 0);
        gl.glEnable(GL.GL_TEXTURE_2D);  // Enable Texture Mapping
        gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
        read.readTexture(e.textureNames, textures, texture,"/player/" );
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
