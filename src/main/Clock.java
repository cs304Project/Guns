/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import Textures.TextureReader;
import javax.media.opengl.GL;
import main.Stage.Stage1;

/**
 *
 * @author youse
 */
public class Clock {
    
    String[] clockTextureName = {"5.png", "10.png", "15.png", "20.png", "25.png", "30.png","35.png","40.png","45.png","50.png","55.png","60.png"
        };
    public static TextureReader.Texture clockTexture[] = new TextureReader.Texture[12];
    public static int clockTextures[] = new int[12];
    ReadImages read = new ReadImages();
    int time=0;
    int index=0;

    public Clock() {
        read.readTexture(clockTextureName, clockTextures, clockTexture, "/Clock/");
    }
    
    public void drawClock(GL gl){
        gl.glEnable(GL.GL_BLEND);
        time++;
        if (time % (60*5) == 0) {
            index = (index + 1);
            if (index>=12){
                index=11;
            }
        }
        gl.glBindTexture(GL.GL_TEXTURE_2D, clockTextures[index]);
        gl.glPushMatrix();
        gl.glTranslated(0f, 0.8f, 1);
        gl.glScaled(0.2f, 0.2f, 1f);
        gl.glBegin(GL.GL_QUADS);
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
