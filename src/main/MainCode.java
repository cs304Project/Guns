
package Main;


import Main.Enemys.Enemy;
import Main.Keys.HandleKeys;
import Textures.AnimListener;
import Textures.TextureReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.glu.GLU;

//this calss is used to run the game in infinite loop using AnimListener
//display, init, reshape and displayChanged  are abtract method from GLeventLisener into AnimListener class
// this class is attached with Gameplay to run the code

public class MainCode extends AnimListener{

    
    //deault Objects
    GL gl;
    GLCanvas canvas; 
    
    //texture setting
    String textureNames[] = {"back1.jpg" , "24.png"};
    TextureReader.Texture texture[] = new TextureReader.Texture[textureNames.length];
    int textures[] = new int[textureNames.length];
    
    //Key setting
    HandleKeys key = new HandleKeys();
    
    //player setting
    
    //Enemy setting
    ArrayList<Enemy> enemyList = new ArrayList(1) ;
    
    
    
    @Override
    public void init(GLAutoDrawable glad) {
        
        gl = glad.getGL();
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);    //This Will Clear The Background Color To Black

        gl.glEnable(GL.GL_TEXTURE_2D);  // Enable Texture Mapping
        gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
        readTexture(textureNames ,textures, texture);
       
        gl.glLoadIdentity();
    }
    
   

    
    //Run the code in this method
    @Override
    public void display(GLAutoDrawable glad)
    {
        gl = glad.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);       //Clear The Screen And The Depth Buffer
        
        //Draw the background
        drawBackground(gl);
        
        //Draw the player
        
        //Draw enemies
        drawEnemy( 0);
        
    }

    public  void setCanvas(GLCanvas canvas)
    {
        canvas.addKeyListener(key);
        this.canvas = canvas;
    }
    
    public GLCanvas getCanvas()
    {
        return this.canvas; 
    }
    
    public void drawBackground(GL gl) {
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
    
    public void drawEnemy( int enemyNum)
    {
        
        if(enemyList.size() - 1 < enemyNum)
        {
            createEnemy(50,50);
        }
        enemyList.get(enemyNum).move();
        float x = enemyList.get(enemyNum).getXWorld();
        float y = enemyList.get(enemyNum).getYWorld();
        enemyList.get(enemyNum).setXWorld(x);
        enemyList.get(enemyNum).setYWorld(y);
        enemyList.get(enemyNum).drawSprite(gl);
    }
    
    
    public void createEnemy(float x, float y)
    {
        Enemy enemy = new Enemy(gl,key, x, y);
        enemyList.add(enemy);
        
    }
    
    //Set the texture and read it 
    public void readTexture(String[] textureNames , int[] textureContainer , TextureReader.Texture texture[])
    {
        gl.glGenTextures(textureNames.length, textureContainer, 0);

        for (int i = 0; i < textureNames.length; i++) {
            try {
                
                String pathName = "src/Assets/Photos/";
                texture[i] = TextureReader.readTexture(pathName + textureNames[i], true);
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
    
     
}


