/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import Textures.TextureReader;
import java.io.IOException;
import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

/**
 *
 * @author hp
 */
public class ReadImages {

    //Set the texture and read it 
    
    public void readTexture( String[] textureNames,
            int[] textureContainer, TextureReader.Texture texture[], String restPath ) {

        MainCode.gl.glGenTextures(textureNames.length, textureContainer, 0);

        for (int i = 0; i < textureNames.length; i++) {
            try {

                    String pathName = "src/Assets";
                    texture[i] = TextureReader.readTexture(pathName +restPath+ textureNames[i], true);

                MainCode.gl.glBindTexture(GL.GL_TEXTURE_2D, textureContainer[i]);

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
