package main;
import main.Enemys.Enemy;
import java.util.ArrayList;
import javax.media.opengl.GL;
import main.Players.Bullet;


public class Entity {

    //public int currTime = 0;
    public static ArrayList<Bullet> bossStorage = new ArrayList<>();
    public static ArrayList<Enemy> EnemyStage_1 = new ArrayList<>();
    public static ArrayList<Enemy> EnemyStage_2 = new ArrayList<>();
    public static ArrayList<Enemy> EnemyStage_3_01 = new ArrayList<>();
    public static ArrayList<Enemy> EnemyStage_3_02 = new ArrayList<>();
    public static ArrayList<Bullet> enemyBullets = new ArrayList<>();
    //texture setting
    public String textureNames[] = {"background.jpeg","player.png","playerBullet.png","stage1.png","stage2.png","stage3.png"};  
    public void destroy(Object obj) {

        obj = null;
    }
    public void destroyBulletFromList(Bullet bullet, ArrayList<Bullet> list) {

        list.remove(bullet);
    }
    public void destroyEnemyFromList(Enemy enemy, ArrayList<Enemy> list) {

        list.remove(enemy);
    }
    public void drawBackground(GL gl , int textures[]) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[0]);	// Turn Blending On
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
    public void drawText(GL gl,float textScale ,int index,int textures[]) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[index]);	// Turn Blending On
        //gl.glColor3f(0, 0f, 0f);
        gl.glPushMatrix();
        gl.glScaled(textScale, textScale, 1);
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
               Player.score += enemy.getBonusScore();
               bullet.isDestroyed=true;
               //destroyBulletFromList(bullet, Player.bullets);
           
            }
            
        }
        else if ((obj2 instanceof Enemy enemy && obj1 instanceof Bullet bullet)) {
            if (detectCollision(enemy.c,bullet.bullet_collision)) {
                enemy.health--;
                if(enemy.health<=0){
                    destroyEnemyFromList(enemy, eList);
                    Player.score += enemy.getBonusScore();
                }
                bullet.isDestroyed=true;
                //destroyBulletFromList(bullet, Player.bullets);
             
            }
            
        }
     
    }
    

}
