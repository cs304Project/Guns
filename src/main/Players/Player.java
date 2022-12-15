package main.Players;

import Main.Keys.HandleKeys;
import javax.media.opengl.GL;

public class Player {

    //Enemy setting
    float xWorld;
    float yWorld;
    public float scale = 0.1f;
    public float speed = 0.09f;

    //Keyboard orders
    HandleKeys key;
    //default object to attach the class with the maincode class
    GL gl;

    int textureIndex = 1;

    public Player(GL gl, HandleKeys key, float x, float y) {
        this.key = key;
        this.gl = gl;

        xWorld = x;
        yWorld = y;
        speed = 0.09f;

    }

    public Player(GL gl, HandleKeys key) {
        this.key = key;
        this.gl = gl;

        xWorld = 0f;
        yWorld = 0f;
        speed = 0.09f;

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

    public void drawPlayer(GL gl) {

        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textureIndex);	// Turn Blending On

        //gl.glColor3f(1, 1, 0);
        gl.glPushMatrix();

        gl.glTranslated((xWorld) * scale * speed, (yWorld) * scale * speed, 1);
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

    public void move(double maxX, double maxY) {

        if (key.isKeyPressed(key.UP)) {
            if (yWorld < maxY ) {

                yWorld++;
                textureIndex++;
                textureIndex = (textureIndex % 4) + 1;

            }
        } else if (key.isKeyPressed(key.DOWN)) {
            if (yWorld > -100) {

                yWorld--;
                textureIndex++;
                textureIndex = (textureIndex % 4) + 1;
            }
        } else if (key.isKeyPressed(key.LEFT)) {
            if (xWorld+5 > -100) {

                xWorld--;
                textureIndex = (textureIndex % 4) + 1;
            }
        } else if (key.isKeyPressed(key.RIGHT)) {
            if (xWorld-5 < maxX ) {

                xWorld++;
                textureIndex = (textureIndex % 4) + 1;
            }
        }

    }

}
