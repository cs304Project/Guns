package Main.Enemys;


import Main.Entity;
import Main.Keys.HandleKeys;
import Main.MainCode;
import Main.Timing;
import java.util.ArrayList;
import javax.media.opengl.GL;
import main.Players.Bullet;
import main.Players.Collision;




public class Enemy  {
    
    //default object to attach the class with the maincode class
    Entity e = new Entity();
    GL gl;

    //Enemy setting
    float xWorld;
    float yWorld;
    public float scale = 0.1f;
    private final float speed;
    float angle;
    float startAngle = 90;
    float verticalAnimation = 0;
    float accumelation = 0.1f;
    
    //Time setting
    Timing timer = new Timing();
    
    //Keyboard orders
    HandleKeys key;
    int fireRate=500+(int)(Math.random()*20000000);
    
    public Collision c;
    
    
    final int textureIndex = 6;
    
    public Enemy(GL  gl, HandleKeys key, float x, float y)
    {
       this.key = key;
       this.gl = gl;
       xWorld = x;
       yWorld = y;
       speed = 0.05f;
       angle = 0;
       c = new Collision(xWorld*speed*scale,yWorld*speed*scale,0.09f);
    }   
    
    public Enemy(GL  gl, HandleKeys key)
    {
       this.key = key;
       this.gl = gl;
       xWorld = -100f;
       yWorld = 100f;
       speed = 0.05f;
       angle = 0; 
       
    }
    
    public void setXWorld(float x)
    {
        xWorld = x;
    }
    
    public float getXWorld()
    {
        return this.xWorld;
    }
    
    public void setYWorld(float y)
    {
        yWorld = y;
    }
    
    public float getYWorld()
    {
        return this.yWorld;
    }

    public void drawSprite(GL gl)
    {
        
        
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textureIndex);	// Turn Blending On
        
        //gl.glColor3f(1, 1, 0);
        gl.glPushMatrix();
        gl.glTranslated((xWorld * scale * speed) , (yWorld * scale * speed), 1); 
        //gl.glRotatef(angle , 0, 0, 1);
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
        
        
        c.drawCirclie(gl,xWorld*speed*scale,yWorld*speed*scale);

    }
    
    
    public void drawSprite_AnimationAI(GL gl , float x , float y , String s)
    {
        

        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textureIndex);	// Turn Blending On
        //gl.glColor3f(1, 1, 0);
        gl.glPushMatrix();
        
        if("AI01".equals(s))
        {
            verticalAnimation = (verticalAnimation+ accumelation);
            if(verticalAnimation > 5) accumelation = -accumelation;
            else if ( verticalAnimation < 0f)  accumelation = -accumelation;
            translateEnemy(x, y + (speed * scale * verticalAnimation));
        }
        else if("AI02".equals(s))
        {
            translateEnemy(x, y);
        }
        

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
        c.drawCirclie(gl,x*speed*scale,y*speed*scale + (speed * scale * verticalAnimation));
    }

    private void translateEnemy(float x,float y)
    {
        gl.glTranslated( x * speed * scale , y * speed * scale, 1);
    }
  
     public void AutoAttack(GL gl,ArrayList<Bullet> bullets){
         
        if(fireRate==500){
            Bullet bullet = new Bullet(gl, xWorld *scale * speed  , yWorld * scale * speed , 0.005f, "EnemyBullet");
            bullets.add(bullet);
        }
        
        
        fireRate = (fireRate+1)%501;
 
    }
     
     
    
    
}
