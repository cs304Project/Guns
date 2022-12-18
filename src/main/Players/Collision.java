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
public class Collision {
    
    float x ;
    float y ;
    float r ;
    public Collision(float x,float y,float radius){
        this.x =x;
        this.y =y;
        this.r = radius;
        
    }
    
    public void drawCirclie(GL gl ,float x,float y) {
        this.x =x;
        this.y =y;
        gl.glBegin(GL.GL_LINE_LOOP);

        for (int i = 0; i < 360; i ++) {
            gl.glVertex2d(r * Math.cos(Math.toRadians(i))+x, r * Math.sin(Math.toRadians(i))+y);

        }
        gl.glEnd();

    }
    
    public float getRadius()
    {
        return this.r;
    }
    
    public float getX()
    {
        return this.x;
    }
    
    public float getY()
    {
        return this.y;
    }

}

