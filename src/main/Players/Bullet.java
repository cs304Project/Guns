/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.Players;
import Main.Keys.HandleKeys;
import Textures.TextureReader;
import java.util.ArrayList;
import java.util.Objects;
import javax.media.opengl.GL;


/**
 *
 * @author hp
 */
public class Bullet {
    float xWorld;
    float yWorld;
    float scale = 0.02f;
   public float speed = 0.02f;

    
    //default object to attach the class with the maincode class
    GL gl;
    
    final int textureIndex = 7;
    
    public Bullet(GL  gl, float x, float y)
    {
       this.gl = gl;
       
       xWorld = x;
       yWorld = y;
       
    }
    
    
    public void setXWorld(float x)
    {
        xWorld = x;
    }
    
    public float getXWorld()
    {
        return this.xWorld;
    }
    
    public void setYWorld(float y)
    {
        yWorld = y;
    }
    
    public float getYWorld()
    {
        return this.yWorld;
    }
    public void drawBullet(GL gl)
    {
        
        
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textureIndex);	// Turn Blending On
        
        //gl.glColor3f(1, 1, 0);
        gl.glPushMatrix();
        
        gl.glTranslated((xWorld ) , (yWorld ) , 1);
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
        
    }
    
   
}
            