package main;

import main.Enemys.Enemy;
import Textures.TextureReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;
import main.Players.Bullet;
import main.Players.Collision;
import main.Players.Player;

public class Entity {

    //public int currTime = 0;
    public static ArrayList<Bullet> bossStorage = new ArrayList<>();

    public void destroy(Object obj) {

        obj = null;
        System.out.println("obj is Deleted");
    }

    public void destroyBulletFromList(Bullet bullet, ArrayList<Bullet> list) {

        list.remove(bullet);
        //System.out.println("Bullet is Deleted");
    }

    private void destroyEnemyFromList(Enemy enemy, ArrayList<Enemy> list) {

        list.remove(enemy);
        System.out.println("Enemy is Deleted");
    }

    public void collision(Object obj1, Object obj2) {
        if ((obj1 instanceof Enemy enemy && obj2 instanceof Player player)) {
            if (detectCollision(enemy.c, player.c)) {
                destroyEnemyFromList(enemy, MainCode.enemyList);
            }

        } else if ((obj2 instanceof Enemy enemy && obj1 instanceof Player player)) {
            if (detectCollision(player.c, enemy.c)) {
                destroyEnemyFromList(enemy, MainCode.enemyList);
            }

        }

    }

    private boolean detectCollision(Collision c1, Collision c2) {
        double offset = 0.01;
        double r = (c1.getRadius() - offset) + (c2.getRadius() - offset);
        return (Math.abs(c1.getX() - c2.getX()) <= r) && (Math.abs(c1.getY() - c2.getY()) <= r);
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

    public void collision(Object obj1, Object obj2,ArrayList<Enemy> eList)
    {
        if ((obj1 instanceof Enemy enemy && obj2 instanceof Player player) )
        {
            if(detectCollision(enemy.c , player.c)){
                destroyEnemyFromList(enemy, eList);
            }
            
        }
        else if ((obj2 instanceof Enemy enemy && obj1 instanceof Player player))
        {
            if(detectCollision(player.c , enemy.c ))
                destroyEnemyFromList(enemy, eList);
   
        }
        if ((obj2 instanceof Bullet bullet && obj1 instanceof Enemy enemy)) {
            if (detectCollision(bullet.bullet_collision, enemy.c)) {
               destroyEnemyFromList(enemy, eList);
               bullet.isDestroyed=true;
               //destroyBulletFromList(bullet, Player.bullets);
           
            }
            
        }
        else if ((obj2 instanceof Enemy enemy && obj1 instanceof Bullet bullet)) {
            if (detectCollision(enemy.c,bullet.bullet_collision)) {
                destroyEnemyFromList(enemy, eList);
                bullet.isDestroyed=true;
                //destroyBulletFromList(bullet, Player.bullets);
             
            }
            
        }
     
    }
    
    
    private boolean detectCollision(Collision c1 , Collision c2)
    {
        double offset = 0.01;
        double r = (c1.getRadius() - offset) + (c2.getRadius()- offset);
        return (Math.abs(c1.getX() - c2.getX()) <= r) && (Math.abs(c1.getY() - c2.getY()) <= r);
    }

}
