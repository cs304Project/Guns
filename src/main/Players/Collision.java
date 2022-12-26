
package main.Players;

import java.util.ArrayList;
import javax.media.opengl.GL;
import main.Enemys.Enemy;
import main.Enemys.EnemyEffect;
import main.Entity;
import static main.MainCode.gl;
import main.Timing;

public class Collision {

    float x;
    float y;
    float r;
    Entity e;   
    Timing time = new Timing();
    public Collision(float x, float y, float radius) {
        this.x = x;
        this.y = y;
        this.r = radius;
        e = new Entity();

    }
    public Collision()
    {
        x = 0;
        y = 0;
        r = 0;
        e = new Entity();
    }

    public void drawCirclie(GL gl, float x, float y) {
        this.x = x;
        this.y = y;
//        gl.glBegin(GL.GL_LINE_LOOP);
//
//        for (int i = 0; i < 360; i++) {
//            gl.glVertex2d(r * Math.cos(Math.toRadians(i)) + x, r * Math.sin(Math.toRadians(i)) + y);
//
//        }
//        gl.glEnd();
//
    }

    public float getRadius() {
        return this.r;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }
    
    
    public void collision(Object obj1, Object obj2) {
        if ((obj1 instanceof Enemy enemy && obj2 instanceof Player player)) {
            if (detectCollision(enemy.c, player.c)) {
                e.destroyEnemyFromList(enemy, Entity.EnemyStage_1);
                Player.score+=enemy.bonusScore;
            }

        } else if ((obj2 instanceof Enemy enemy && obj1 instanceof Player player)) {
            if (detectCollision(player.c, enemy.c)) {
                e.destroyEnemyFromList(enemy, Entity.EnemyStage_1);
                Player.score+=enemy.bonusScore;
            }

        }

    }

    private boolean detectCollision(Collision c1, Collision c2) {
        double offset = 0.01;
        double r = (c1.getRadius() - offset) + (c2.getRadius() - offset);
        return (Math.abs(c1.getX() - c2.getX()) <= r) && (Math.abs(c1.getY() - c2.getY()) <= r);
    }
    public void collision(Object obj1, Object obj2, ArrayList<Enemy> eList) {
        if ((obj1 instanceof Enemy enemy && obj2 instanceof Player player)) {
            if (detectCollision(enemy.c, player.c)) {
                e.destroyEnemyFromList(enemy, eList);
                Player.score+=enemy.bonusScore;
            }
        } else if ((obj2 instanceof Enemy enemy && obj1 instanceof Player player)) {
            if (detectCollision(player.c, enemy.c)) {
                e.destroyEnemyFromList(enemy, eList);
                Player.score+=enemy.bonusScore;
            }

        }
        if ((obj1 instanceof Bullet bullet && obj2 instanceof Enemy enemy)) {
            if (detectCollision(bullet.bullet_collision, enemy.c)) {
                Player.score+=enemy.bonusScore;
                bullet.isDestroyed = true;
                EnemyEffect enemyeffect =new EnemyEffect(enemy.getXWorld(),enemy.getYWorld());
                e.destroyEnemyFromList(enemy, eList);
                Entity.EnemyEffects.add(enemyeffect);
            }

        } else if ((obj2 instanceof Enemy enemy && obj1 instanceof Bullet bullet)) {
            if (detectCollision(enemy.c, bullet.bullet_collision)) {
                enemy.health--;
                if (enemy.health <= 0) {
                    e.destroyEnemyFromList(enemy, eList);
                    Player.score+=enemy.bonusScore;
                }
                bullet.isDestroyed = true;
            }
        }
    }
}
