package main;

import main.Enemys.Enemy;
import main.Keys.HandleKeys;
import Textures.AnimListener;
import Textures.TextureReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.glu.GLU;
import main.Players.Bullet;
import main.Players.Collision;
import main.Players.Player;
import main.Players.PowerUp;

//this calss is used to run the game in infinite loop using AnimListener
//display, init, reshape and displayChanged  are abtract method from GLeventLisener into AnimListener class
// this class is attached with Gameplay to run the code
public class MainCode extends AnimListener {

    //deault Objects
    public double minX = 0;
    public double maxX = 100;
    double minY = 0;
    double maxY = 100;
    GL gl;
    GLCanvas canvas;

    //texture setting
    String textureNames[] = {
        "Man1.png", "Man2.png", "Man3.png", "Man4.png",
        "back1.jpg", "24.png",
        "bullet.png", "present.png", "healthy.png"};

    TextureReader.Texture texture[] = new TextureReader.Texture[textureNames.length];
    int textures[] = new int[textureNames.length];

    //Key setting
    HandleKeys key = new HandleKeys();

    //player setting
    Player player = new Player(gl, key);
    static ArrayList<Bullet> bullets = new ArrayList<>();

    //Enemy setting
    public static ArrayList<Enemy> enemyList = new ArrayList<>();
    public static int stage1 = 4;
    
    static ArrayList<PowerUp> presents = new ArrayList<>();
    @Override
    public void init(GLAutoDrawable glad) {

        gl = glad.getGL();
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);    //This Will Clear The Background Color To Black
        gl.glOrtho(minX, maxX, minY, maxY, 0, 0);
        gl.glEnable(GL.GL_TEXTURE_2D);  // Enable Texture Mapping
        gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
        readTexture(textureNames, textures, texture);

        for (int i = 0; i < stage1; i++) {
            createEnemy(20 * i, 20 * i);
        }

        gl.glLoadIdentity();

    }

    //Run the code in this method
    @Override
    public void display(GLAutoDrawable glad) {
        gl = glad.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);       //Clear The Screen And The Depth Buffer

        //Draw the background
        drawBackground(gl);

        //Draw the player
        player.drawPlayer(gl);
        player.move(maxX, maxY);
        drawBullet();
        //Draw enemies
        drawEnemy(stage1);

        fillPresentsArray(2);
        drawPresent(0);
        drawPresent(1);
    }

    public void setCanvas(GLCanvas canvas) {
        canvas.addKeyListener(key);
        this.canvas = canvas;
    }

    public GLCanvas getCanvas() {
        return this.canvas;
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

    public void drawEnemy(int stage1) {

        for (int i = 0; i < enemyList.size(); i++) {
            enemyList.get(i).setXWorld(enemyList.get(i).getXWorld());
            enemyList.get(i).setYWorld(enemyList.get(i).getYWorld());
            enemyList.get(i).drawSprite(gl);
            player.resolveColision(enemyList.get(i));
        }
    }

    public void drawBullet() {
        if (key.isKeyPressed(key.SPACE)) {
            float bulletX = player.getXWorld() * player.scale * player.speed;
            float bulletY = player.getYWorld() * player.scale * player.speed;
            Bullet bullet = new Bullet(gl, bulletX, bulletY);
            bullets.add(bullet);
        }
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).setYWorld(bullets.get(i).getYWorld() + bullets.get(i).speed);
            bullets.get(i).drawBullet(gl);
            if (bullets.get(i).getYWorld() > maxY) {
                destroy(bullets.get(i));
            }
        }
    }

    public void drawPresent(int present) {

        presents.get(present).drawPresent(gl);
        presents.get(present).movePresent();
        if (presents.get(present).getyPresent() < minY - 1) {
            float speed = presents.get(present).getSpeed();
            destroy(presents.get(present));
        }
        player.resolveColision(presents.get(present));
        //System.out.println("score: "+ presents.get(present).getPowerScore());
    }

    public static void destroy(Object ob) {
        if (ob instanceof Enemy) {

            enemyList.remove(ob);

        }
        if (ob instanceof Bullet) {
            bullets.remove(ob);
        }
         if (ob instanceof PowerUp) {
            presents.remove(ob);
        }
    }

    public void createEnemy(float x, float y) {
        Enemy enemy = new Enemy(gl, key, x, y);
        enemyList.add(enemy);

    }

    public void createPresent(float x, float y, float spead, boolean hitted) {
        PowerUp present = new PowerUp(gl, x, y, spead, hitted);
        presents.add(present);
    }

    //Set the texture and read it 
    public void readTexture(String[] textureNames, int[] textureContainer, TextureReader.Texture texture[]) {

        gl.glGenTextures(textureNames.length, textureContainer, 0);

        for (int i = 0; i < textureNames.length; i++) {
            try {

                if (textureNames[i] == "back1.jpg" || textureNames[i] == "24.png") {
                    String pathName = "src/Assets/Photos/";
                    texture[i] = TextureReader.readTexture(pathName + textureNames[i], true);

                } else {
                    String pathName = "src/PlayerAssets/man/";
                    texture[i] = TextureReader.readTexture(pathName + textureNames[i], true);

                }

                gl.glBindTexture(GL.GL_TEXTURE_2D, textures[i]);

//                mipmapsFromPNG(gl, new GLU(), texture[i]);
                new GLU().gluBuild2DMipmaps(
                        GL.GL_TEXTURE_2D,
                        GL.GL_RGBA, // Internal Texel Format,
                        texture[i].getWidth(), texture[i].getHeight(),
                        GL.GL_RGBA, // External format from image,
                        GL.GL_UNSIGNED_BYTE,
                        texture[i].getPixels() // Imagedata
                );

            } catch (IOException e) {
                System.out.println(e);
                e.printStackTrace();
            }
        }
    }

    @Override
    public void reshape(GLAutoDrawable glad, int i, int i1, int i2, int i3) {
        //useless method
    }

    @Override
    public void displayChanged(GLAutoDrawable glad, boolean bln, boolean bln1) {
        //useless method
    }

    public void fillPresentsArray(int n) {
        int max = 1;
        int min = -1;
        for (int i = 0; i < n; i++) {
            float speed = 0.01f;
            float positiony = 1f;
            float positionX = (float) (Math.random() * (max - (min))) + (min);
            createPresent(positionX, positiony, speed, false);
            speed += 0.5f; //does not change
            positiony += 0.5;
        }
//        for (int i = 0; i < n; i++) {
//            System.out.println("i: " + i);
//            System.out.println("speed:" + presents.get(i).getSpeed());
//            System.out.println("y: " + presents.get(i).getyPresent());
//            System.out.println("x: " + presents.get(i).getxPresent());
//        }
//        createPresent(0.1f, 1f, 0.5f, false);
//        createPresent(0.1f, 1f, 0.5f, false);
//        createPresent(1f,   1f, 2f, false);
//        createPresent(0.3f, 2f, 0.4f, false);
//        createPresent(-0.3f, 2f, 0.4f, false);
    }
}
