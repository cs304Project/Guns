/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.Players;

import javax.media.opengl.GL;
import main.Stage.Stage1;
import main.Timing;

/**
 *
 * @author RONY
 */
public class PlayerEffect {

    public int index = 0;
    int t = 0;
    Timing time = new Timing();
    protected float xWorld;
    protected float yWorld;
   float scale =0.1f;

    public PlayerEffect(float x, float y) {
        time.start();

        xWorld = x;
        yWorld = y;
    }

   
    public void setXWorld(float x) {
        xWorld = x;
    }

    public float getXWorld() {
        return this.xWorld;
    }

    public void setYWorld(float y) {
        yWorld = y;
    }

    public float getYWorld() {
        return this.yWorld;
    }

    public void drawPlayerEffects(GL gl, int stage) {
        if (time.seconds < 0.3) {
            drawPlayerEffect(gl, stage);
        }
    }

    private void drawPlayerEffect(GL gl, int stage) {

        gl.glEnable(GL.GL_BLEND);
        t++;
        if (t % 2 == 0) {

                    index = ((index + 1) % 4);
                }
        switch (stage) {
            case 1:
                
                gl.glBindTexture(GL.GL_TEXTURE_2D, ReadPlayerAssets.playerTextures[index]); // Turn Blending On
                break;
            case 2:
                
                gl.glBindTexture(GL.GL_TEXTURE_2D, ReadPlayerAssets.playerTextures[index]); // Turn Blending On
                break;
            case 3:
                
                gl.glBindTexture(GL.GL_TEXTURE_2D, ReadPlayerAssets.playerTextures[index]); // Turn Blending On
                break;
            case 4:
               
                gl.glBindTexture(GL.GL_TEXTURE_2D, ReadPlayerAssets.playerTextures[index]); // Turn Blending On
                break;
        }
        //gl.glColor3f(1, 1, 0);
        gl.glPushMatrix();
        gl.glTranslated((xWorld ), (yWorld ), 1);
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
