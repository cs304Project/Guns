package main;

import main.Enemys.Enemy;

import main.Keys.HandleKeys;
import Textures.AnimListener;
import Textures.TextureReader;
import java.util.ArrayList;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import main.Enemys.EnemyEffect;
import main.Players.Bullet;
import main.Players.Player;
import main.Players.PlayerEffect;
import main.Players.ReadPlayerAssets;
import main.Stage.Stage;
import main.Stage.Stage1;
import main.Stage.Stage2;
import main.Stage.Stage3;
import main.Stage.Stage4;

//this calss is used to run the game in infinite loop using AnimListener
//display, init, reshape and displayChanged  are abtract method from GLeventLisener into AnimListener class
// this class is attached with Gameplay to run the code
public class MainCode extends AnimListener {

    public static int xMax;
    public static int yMax;
    float textScale = 0.1f;
    //deault Objects
    public static GL gl;
    GLCanvas canvas;
    Entity e = new Entity();
    Stage stage = new Stage();
    Timing time = new Timing();

    String playerStage = "stage1";
    ReadImages read = new ReadImages();

    //player and key setting
    HandleKeys key = new HandleKeys();
    ArrayList<Player> players = new ArrayList<>();
    
    Player player;
    Player player2;
    public TextureReader.Texture texture[] = new TextureReader.Texture[e.textureNames.length];
    public int textures[] = new int[e.textureNames.length];

    public int level = 0;
    public static int stage1 = 24;
    public static int stage2 = 40;
    public static int stage3 = 30;
    public static int stage4 = 1;
    boolean StageOneOn = true;
    boolean enemyKey = true;
    boolean StageTwoOn = false;
    boolean StageThreeOn = false;
    boolean StageFourOn = false;
    

    public static boolean isPause = false;

    public MainCode(int level) {
        this.level = level;

        switch (this.level) {
            case 1:
                player = new Player(gl, key);
                players.add(player);
                System.out.println("MY LEVEL : " + this.level);
                break;
            case 2:
                player= new Player(gl, key);
                 players.add(player);
                System.out.println("MY LEVEL : " + this.level);
                break;
            case 3:
                
                player= new Player(gl, key);
                player2= new Player(gl, key);
                System.out.println("MY LEVEL : " + this.level);
                players.add(player);
                players.add(player2);
                
                break;
            default:
                System.out.println("NO LEVELS : ");
                break;
        }


    }

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
        player = new Player(gl, key);

        gl.glLoadIdentity();
        time.start();
    }

    //Run the code in this method
    @Override
    public void display(GLAutoDrawable glad) {
        gl = glad.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

        e.drawBackground(gl, textures);

        switch (level) {
            case 1:
                setLevel_1();
                break;
            case 2:
                setLevel_1();
                break;
            case 3:
                setLevel_1();
                break;
            default:
                break;
        }
         
        

    }

    
    public void setLevel_1()
    {
        if (!isPause) {

            //Draw the player
            if(level >= 3 )
            {
                playerActions(gl, 1 , players.get(0));
                playerActions(gl, 3 , players.get(1));
            }
            else
            {
                playerActions(gl, 1 , players.get(0));
            }
           

            //Draw stage
            stageLogic();
        } else {
            float x = player.getScaledXWorld();
            float y = player.getScaledYWorld();

            player.drawPlayer(gl, x, y);

            if (StageOneOn) {
                stage.drawEnemy(gl, players, 1);
                stage.drawEnemyBullet(gl, 1, isPause, players);

            } else if (StageTwoOn) {
                stage.drawEnemy(gl, players, 2);
                stage.drawEnemyBullet(gl, 2, isPause, players);
            } else if (StageThreeOn) {
                stage.drawEnemy(gl, players, 3);
            } else if (StageFourOn) {
//                stage.drawEnemyBullet(gl , 4 , isPause, player);
            }

        }
    }
    
    
    private void stageLogic() {
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
            stage.drawEnemy(gl, players, 1);
            stage.drawEnemyBullet(gl, 1, isPause, players);
            drawEnemyEffects(Entity.EnemyEffects, 1);
            drawPlayerEffects(Entity.PlayerEffects, 2);

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
                ReadPlayerAssets playerassets = new ReadPlayerAssets();

                enemyKey = false;
            }
            stage.drawEnemy(gl, players, 2);

            stage.drawEnemyBullet(gl, 2, isPause, players);
            drawEnemyEffects(Entity.EnemyEffects, 2);
            drawPlayerEffects(Entity.PlayerEffects, 2);

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
            stage.drawEnemy(gl, players, 3);

            drawEnemyEffects(Entity.EnemyEffects, 3);

            if (Entity.EnemyStage_3_01.size() <= 0 && Entity.EnemyStage_3_02.size() <= 0) {
                StageThreeOn = false;
                StageFourOn = true;
                enemyKey = true;
                time.stop();
                textScale = 0.1f;

            }
        } else if (StageFourOn) {
            time.start();
            playerStage = "stage4";
            if (time.seconds < 2) {
                e.drawText(gl, textScale, 6, textures);
                textScale = (textScale + 0.002f) % 1f;
            } else if (time.seconds < 5) {
                e.drawText(gl, 0.3f, 6, textures);
            }
            if (enemyKey) {
                Stage4 s4 = new Stage4(stage4);
                ReadPlayerAssets playerassets = new ReadPlayerAssets();
                enemyKey = false;
            }
            stage.drawEnemy(gl, players, 4);
            drawBossBullets(gl, Entity.bossBullets);
            drawEnemyEffects(Entity.EnemyEffects, 4);
            drawPlayerEffects(Entity.PlayerEffects, 4);

            if (Entity.EnemyStage_4.size() <= 0) {
                StageFourOn = false;
                time.stop();
            } else {
                time.start();
                if (time.seconds < 2) {
                    e.drawText(gl, textScale, 8, textures);
                    textScale = (textScale + 0.002f) % 1f;
                } else if (time.seconds < 10) {
                    e.drawText(gl, 0.6f, 8, textures);
                }
            }
        }

    }

    private void playerActions(GL gl , int level , Player player) {
        if (player != null ) {
            
            player.drawPlayer(gl);
            if( level == 1 || level == 2)
                player.move();
            if(level == 3)
                player.move2();
            
            player.drawPlayerBullet(gl, playerStage);
        }
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

    public void drawEnemyEffects(ArrayList<EnemyEffect> list, int stage) {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).drawEnemyEffects(gl, stage);
        }

    }

    public void drawPlayerEffects(ArrayList<PlayerEffect> list, int stage) {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).drawPlayerEffects(gl, stage);
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
        gl.glOrtho(0, 1000, 0, 1000, 0, 0);
        gl.glEnable(GL.GL_TEXTURE_2D);  // Enable Texture Mapping
        gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
        read.readTexture(e.textureNames, textures, texture, "/player/");
    }

    @Override
    public void reshape(GLAutoDrawable glad, int i, int i1, int i2, int i3) {
    }

    @Override
    public void displayChanged(GLAutoDrawable glad, boolean bln, boolean bln1) {
    }
}
