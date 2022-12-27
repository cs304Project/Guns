/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import Textures.TextureReader;
import javax.media.opengl.GL;

/**
 *
 * @author youse
 */
public class Credits {
     String[] creditsTextureName = {"manar.png", "sohila.png", "specialthanks.png", "hossam.png", "raneen.png", "teamlead.png","yousef.png","supervision.png","drmohamed.png","and.png","drmahmoud.png","guns.png"
        };
    public static TextureReader.Texture creditsTexture[] = new TextureReader.Texture[12];
    public static int creditsTextures[] = new int[12];
    ReadImages read = new ReadImages();
    public float xWorld=0;
    public float yWorld=0;
    public float speed=0.1f;
    public float scale=0.5f;
    public int time=0;
    int index=0;
    
    public Credits(){
        read.readTexture(creditsTextureName, creditsTextures, creditsTexture, "/credits/");
    }
    
    public void drawCredits(GL gl){
        gl.glEnable(GL.GL_BLEND);
        yWorld+=0.01;
        if (yWorld>=1) {
            yWorld=-yWorld;
            index++;
        }
        if(index>=creditsTextureName.length-1){
            index=creditsTextureName.length-1;
            if (yWorld>=0){
                yWorld=0;
            }
        }
        gl.glBindTexture(GL.GL_TEXTURE_2D, creditsTextures[index]);
        gl.glPushMatrix();
        gl.glTranslated(xWorld, yWorld, 1);
        gl.glScaled(0.5f, 0.5f, 1f);
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
