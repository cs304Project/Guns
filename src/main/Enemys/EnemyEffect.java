/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.Enemys;

import javax.media.opengl.GL;
import main.Players.Collision;
import main.Stage.Stage1;
import main.Stage.Stage2;
import main.Stage.Stage3;
import main.Stage.Stage4;
import main.Timing;

/**
 *
 * @author RONY
 */
public class EnemyEffect {

    public int index = 0;
    int t = 0;
    Timing time = new Timing();
    protected float xWorld;
    protected float yWorld;
    public float scale = 0.1f;
    public float speed = 0.05f;

    public EnemyEffect(float x, float y) {
        time.start();

        xWorld = x;
        yWorld = y;
        speed = 0.05f;
    }

    public EnemyEffect() {
        time.start();

        xWorld = -100f;
        yWorld = 100f;
        speed = 0.05f;
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

    public void drawEnemyEffects(GL gl, int stage) {
        if (time.seconds < 0.5) {
            drawEnemyEffect(gl, stage);
        }
    }

    private void drawEnemyEffect(GL gl, int stage) {

        gl.glEnable(GL.GL_BLEND);
        t++;
        switch (stage) {
            case 1:
                if (t % 2 == 0) {

                    index = ((index + 1) % 4);
                }
                gl.glBindTexture(GL.GL_TEXTURE_2D, Stage1.enemyTextureEffects[index]); // Turn Blending On
                break;
            case 2:
                if (t % 2 == 0) {

                    index = ((index + 1) % 4);
                }
                gl.glBindTexture(GL.GL_TEXTURE_2D, Stage2.enemyTextureEffects[index]); // Turn Blending On
                break;
            case 3:
                if (t % 2 == 0) {

                    index = ((index + 1) % 4);
                }
                gl.glBindTexture(GL.GL_TEXTURE_2D, Stage3.enemyTextureEffects[index]); // Turn Blending On
                break;
            case 4:
                if (t % 2 == 0) {

                    index = ((index + 1) % 4);
                }
                gl.glBindTexture(GL.GL_TEXTURE_2D, Stage4.enemyTextures[index]); // Turn Blending On
                break;
        }
        //gl.glColor3f(1, 1, 0);
        gl.glPushMatrix();
        gl.glTranslated((xWorld * scale * speed), (yWorld * scale * speed), 1);
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
