package main.Players;

import java.util.ArrayList;
import javax.media.opengl.GL;
import main.Enemys.Enemy;
import main.Enemys.EnemyEffect;
import main.Entity;
import main.MainCode;
import main.Sound;
import static main.MainCode.gl;
import main.Timing;

public class Collision {

    float x;
    float y;
    float r;
    Entity e;
    Sound s = new Sound();
    Timing time = new Timing();
    Sound Enemy_Deathsound = new Sound();

    public Collision(float x, float y, float radius) {
        this.x = x;
        this.y = y;
        this.r = radius;
        e = new Entity();

    }

    public Collision() {
        x = 0;
        y = 0;
        r = 0;
        e = new Entity();
    }

    public void drawCirclie(GL gl, float x, float y) {
        this.x = x;
        this.y = y;
        gl.glBegin(GL.GL_LINE_LOOP);

        for (int i = 0; i < 360; i++) {
            gl.glVertex2d(r * Math.cos(Math.toRadians(i)) + x, r * Math.sin(Math.toRadians(i)) + y);

        }
        gl.glEnd();

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
                Player.score += enemy.bonusScore;
            }

        } else if ((obj2 instanceof Enemy enemy && obj1 instanceof Player player)) {
            if (detectCollision(player.c, enemy.c)) {
                e.destroyEnemyFromList(enemy, Entity.EnemyStage_1);
                Player.score += enemy.bonusScore;
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
                Player.score += enemy.bonusScore;

            }
        } else if ((obj2 instanceof Enemy enemy && obj1 instanceof Player player)) {
            if (detectCollision(player.c, enemy.c)) {
                e.destroyEnemyFromList(enemy, eList);
                Player.score += enemy.bonusScore;
            }

        }
        if ((obj1 instanceof Bullet bullet && obj2 instanceof Enemy enemy)) {
            if (detectCollision(bullet.bullet_collision, enemy.c)) {
                Player.score += enemy.bonusScore;
                bullet.isDestroyed = true;
                s.PlaySoundEffect(2);

                EnemyEffect enemyeffect = new EnemyEffect(enemy.getXWorld(), enemy.getYWorld());
                e.destroyEnemyFromList(enemy, eList);
                Entity.EnemyEffects.add(enemyeffect);

                Enemy_Deathsound.PlaySoundEffect(3);
            }

        } else if ((obj1 instanceof Enemy enemy && obj2 instanceof Bullet bullet)) {
            if (detectCollision(enemy.c, bullet.bullet_collision)) {
                enemy.health--;
                if (enemy.health <= 0) {
                    e.destroyEnemyFromList(enemy, eList);
                    Player.score += enemy.bonusScore;
                    Enemy_Deathsound.PlaySoundEffect(3);
                }
                bullet.isDestroyed = true;
            }
        }
        if ((obj1 instanceof Bullet bullet && obj2 instanceof Player player)) {
            if (detectCollision(bullet.bullet_collision, player.c)) {
                bullet.isDestroyed = true;
                PlayerEffect playereffect = new PlayerEffect(player.getScaledXWorld(), player.getScaledYWorld());
                System.out.println("player x" + player.getScaledXWorld() + " playery " + player.getScaledYWorld());
                Entity.PlayerEffects.add(playereffect);
            }
        } else if ((obj2 instanceof Player player && obj1 instanceof Bullet bullet)) {
            if (detectCollision(player.c, bullet.bullet_collision)) {
                bullet.isDestroyed = true;
                PlayerEffect playereffect = new PlayerEffect(player.getScaledXWorld(), player.getScaledYWorld());
                Entity.PlayerEffects.add(playereffect);

            }

        }

    }
}
