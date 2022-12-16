package main.Players;

import javax.media.opengl.GL;

public class Healthy {

    float xHealthy;
    float yHealthy;
    float scale = 0.08f;
    float speed = 0.0f;
    int healthyScore = 3;
    boolean hitted;
    int healthyTextureIndex = 9;
    GL gl;

    public Healthy(GL gl, float xHealthy, float yHealthy, boolean hitted) {
        this.xHealthy = xHealthy;
        this.yHealthy = yHealthy;
        this.hitted = hitted;
    }

    public float getxHealthy() {
        return xHealthy;
    }

    public void setxHealthy(float xHealthy) {
        this.xHealthy = xHealthy;
    }

    public float getyHealthy() {
        return yHealthy;
    }

    public void setyHealthy(float yHealthy) {
        this.yHealthy = yHealthy;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public int getHealthyScore() {
        return healthyScore;
    }

    public void setHealthyScore(int healthyScore) {
        this.healthyScore = healthyScore;
    }

    public boolean isHitted() {
        return hitted;
    }

    public void setHitted(boolean hitted) {
        this.hitted = hitted;
    }

    public void drawHealthy(GL gl) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, healthyTextureIndex);	// Turn Blending On
        gl.glPushMatrix();
        gl.glTranslated(xHealthy, yHealthy * speed, 1);
        gl.glScaled(scale, scale, 1);
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

    public void moveHealthy() {
        System.out.println("hii  " + yHealthy);
        yHealthy -= 0.09;
        //collision code goes here
        //healty:
        //check for the healty score and increase healty bar (or in player class)
        //if collesion happend decrease healty score 
        //in player class check if dea
    }
}
