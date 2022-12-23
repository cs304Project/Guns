/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.Players;


import javax.media.opengl.GL;

/**
 *
 * @author hp
 */
public class Bullet {

    float xWorld;
    float yWorld;
    float scale = 0.02f;
    float angle;
    public final String typeBullet;
    final float speed;

    //default object to attach the class with the maincode class
    GL gl;

    final int textureIndex = 7;

    public Bullet(GL gl, float x, float y, float speed, String typeBullet, float angle, float scale) {
        this.gl = gl;
        xWorld = x;
        yWorld = y;
        this.typeBullet = typeBullet;
        this.speed = speed;
        this.angle = angle;
        this.scale = scale;

    }

    public float getXWorld() {
        return this.xWorld;
    }

    public float getYWorld() {
        return this.yWorld;
    }

    public void drawBullet(GL gl) {

        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textureIndex);	// Turn Blending On

        //gl.glColor3f(1, 1, 0);
        gl.glPushMatrix();

        if ("PlayerBullet".equals(typeBullet)) {
            yWorld = yWorld + (float) (Math.sin(Math.toRadians(angle)) * speed);
        } else if ("EnemyBullet".equals(typeBullet)) {
            yWorld = yWorld + (float) (Math.sin(Math.toRadians(angle)) * speed);
        } else if ("BossBullet".equals(typeBullet)) {
            yWorld = yWorld + (float) (Math.sin(Math.toRadians(angle)) * speed);
            xWorld = xWorld + (float) (Math.cos(Math.toRadians(angle)) * speed);
        }

        gl.glTranslated(xWorld, yWorld, 1);
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

    public void drawFollowingBullet(GL gl, float xPlayer, float yPlayer) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textureIndex);	// Turn Blending On

        //gl.glColor3f(1, 1, 0);
        gl.glPushMatrix();
        float x = xPlayer - xWorld;
        float y = yPlayer - yWorld;
        float lengthVector = (float) Math.sqrt((x * x) + (y * y));
        float newX = (x / lengthVector);
        float newY = (y / lengthVector);
        xWorld += (newX * .01f);
        yWorld += (newY * .01f);
        if (lengthVector > 0) {
            gl.glTranslated(xWorld, yWorld, 1);
        }

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
