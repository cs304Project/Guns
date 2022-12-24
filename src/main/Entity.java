package main;

import main.Enemys.Enemy;
import Textures.TextureReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;
import main.Players.Bullet;


public class Entity {

    //public int currTime = 0;
    public static ArrayList<Bullet> bossStorage = new ArrayList<>();
    public static ArrayList<Enemy> EnemyStage_1 = new ArrayList<>();
    public static ArrayList<Enemy> EnemyStage_2 = new ArrayList<>();
    public static ArrayList<Enemy> EnemyStage_3_01 = new ArrayList<>();
    public static ArrayList<Enemy> EnemyStage_3_02 = new ArrayList<>();
    public static ArrayList<Bullet> enemyBullets = new ArrayList<>();
    
    
    public void createEnemy(ArrayList<Enemy> enemyList,float x, float y)
    {
        Enemy enemy = new Enemy(x, y);
        enemyList.add(enemy);
 
    }

    public void destroy(Object obj) {

        obj = null;
        System.out.println("obj is Deleted");
    }

    public void destroyBulletFromList(Bullet bullet, ArrayList<Bullet> list) {

        list.remove(bullet);
        System.out.println("Bullet is Deleted");
    }

    public void destroyEnemyFromList(Enemy enemy, ArrayList<Enemy> list) {

        list.remove(enemy);
        System.out.println("Enemy is Deleted");
    }

    
    public void createStage1Enemys(int enemyNumber)
    {
        for (int i = 0; i < enemyNumber; i++) {
            createEnemy(EnemyStage_1,-200, 210);
        }
    }
    
    
    public void createStage2Enemys(int enemyNumber)
    {
        for (int i = 0; i < enemyNumber; i++) {
            float startPosition;
            if ((enemyNumber - 1) / 4 >= i) {
                startPosition = 20f;
            } else if (2 * (enemyNumber - 1) / 4 >= i) {
                startPosition = 70f;
            } else if (3 * (enemyNumber - 1) / 4 >= i) {
                startPosition = 120f;
            } else {
                startPosition = 170f;
            }
            createEnemy(EnemyStage_2, -200 - (i * 50), startPosition);
        }
    }
    
    
    public void createStage3Enemys(int enemyNumber)
    {
         for(int i = 0;i<enemyNumber;i++){
            if(i<enemyNumber/2){
                createEnemy(EnemyStage_3_01,-i*50,220);                
            }else{
                createEnemy(EnemyStage_3_02,i*50,220);
            }
        }
    }
    
    
    //Set the texture and read it 
    public void readTexture(GL gl, String[] textureNames,
            int[] textureContainer, TextureReader.Texture texture[]) {

        gl.glGenTextures(textureNames.length, textureContainer, 0);

        for (int i = 0; i < textureNames.length; i++) {
            try {

                if ("back1.jpg".equals(textureNames[i]) || "24.png".equals(textureNames[i])) {
                    String pathName = "src/Assets/Photos/";
                    texture[i] = TextureReader.readTexture(pathName + textureNames[i], true);

                } else {
                    String pathName = "src/PlayerAssets/man/";
                    texture[i] = TextureReader.readTexture(pathName + textureNames[i], true);

                }

                gl.glBindTexture(GL.GL_TEXTURE_2D, textureContainer[i]);

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

}
