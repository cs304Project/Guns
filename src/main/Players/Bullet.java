/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.Players;

import main.Keys.HandleKeys;
import javax.media.opengl.GL;


/**
 *
 * @author hp
 */
public class Bullet {
    
    float xWorld;
    float yWorld;
    float scale = 0.02f;
    String typeBullet;
    final float speed;
    HandleKeys key ;
    
    //default object to attach the class with the maincode class
    GL gl;
    
    final int textureIndex = 7;
    
    public Bullet(GL  gl,  float x, float y , float speed, String typeBullet)
    {
       this.gl = gl;
       xWorld = x;
       yWorld = y;
       this.typeBullet = typeBullet;
       this.speed = speed;
       
    }

    public float getXWorld()
    {
        return this.xWorld;
    }

    public float getYWorld()
    {
        return this.yWorld;
    }
    
    
    
    public void drawBullet(GL gl , String typePlayer)
    {
        
        
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textureIndex);	// Turn Blending On
        
        //gl.glColor3f(1, 1, 0);
        gl.glPushMatrix();
        
        if("PlayerBullet".equals(typePlayer)) yWorld = yWorld + (speed);
        else if("EnemyBullet".equals(typePlayer)) yWorld = yWorld - (speed) ;
  
        gl.glTranslated(xWorld, yWorld , 1);
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
            