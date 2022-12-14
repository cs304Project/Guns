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
//        gl.glBegin(GL.GL_LINE_LOOP);
//
//        for (int i = 0; i < 360; i++) {
//            gl.glVertex2d(r * Math.cos(Math.toRadians(i)) + x, r * Math.sin(Math.toRadians(i)) + y);
//
//        }
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
                player.liveScore--;
                PlayerEffect playereffect = new PlayerEffect(player.getScaledXWorld(), player.getScaledYWorld());
                Entity.PlayerEffects.add(playereffect);
                System.out.println("live score" + player.liveScore);
                if (player.liveScore <= 0) {
                    player.isDestroy =true;
                    
                }
                
                if(player.liveScore == 0&& MainCode.players.size()==0){
                    MainCode.gameOver = true;
                }
            }
        } else if ((obj2 instanceof Enemy enemy && obj1 instanceof Player player)) {
            if (detectCollision(player.c, enemy.c)) {
                e.destroyEnemyFromList(enemy, eList);
                Player.score += enemy.bonusScore;
                player.liveScore--;
                PlayerEffect playereffect = new PlayerEffect(player.getScaledXWorld(), player.getScaledYWorld());
                Entity.PlayerEffects.add(playereffect);
                System.out.println("live score" + player.liveScore);
                if (player.liveScore <= 0) {
                    player.isDestroy =true;
                    
                }
                
                if(player.liveScore == 0&& MainCode.players.size()==0){
                    MainCode.gameOver = true;
                }
            }

        }
        if ((obj1 instanceof Bullet bullet && obj2 instanceof Enemy enemy)) {
            if (detectCollision(bullet.bullet_collision, enemy.c)) {
                enemy.health--;
                if (enemy.health <= 0) {
                    e.destroyEnemyFromList(enemy, eList);
                    Enemy_Deathsound.PlaySoundEffect(3);
                    Player.score += enemy.bonusScore;

                }
                for(int i=0;i<MainCode.players.size();i++){
                if (MainCode.players.get(i).isSpecialHitted()) {
                    bullet.isDestroyed = false;
                } else {
                    bullet.isDestroyed = true;
                }
                }
//s.PlaySoundEffect(2);

                EnemyEffect enemyeffect = new EnemyEffect(enemy.getXWorld(), enemy.getYWorld());
                Enemy_Deathsound.PlaySoundEffect(3);
                Entity.EnemyEffects.add(enemyeffect);

            }

        } else if ((obj1 instanceof Enemy enemy && obj2 instanceof Bullet bullet)) {
            if (detectCollision(enemy.c, bullet.bullet_collision)) {
                enemy.health--;
                if (enemy.health <= 0) {
                    e.destroyEnemyFromList(enemy, eList);
                    Enemy_Deathsound.PlaySoundEffect(3);
                    Player.score += enemy.bonusScore;
                }
                for(int i=0;i<MainCode.players.size();i++){
                if (MainCode.players.get(i).isSpecialHitted()) {
                    bullet.isDestroyed = false;
                } else {
                    bullet.isDestroyed = true;
                }
                }
            }
        }
        if ((obj1 instanceof Bullet bullet && obj2 instanceof Player player)) {
            if (detectCollision(bullet.bullet_collision, player.c)) {
                bullet.isDestroyed = true;
                player.liveScore--;
                PlayerEffect playereffect = new PlayerEffect(player.getScaledXWorld(), player.getScaledYWorld());
                Entity.PlayerEffects.add(playereffect);
                if (player.liveScore <= 0) {
                    player.isDestroy =true;
                    
                }
                
                if(player.liveScore == 0&& MainCode.players.size()==0){
                    MainCode.gameOver = true;
                }
            }
        } else if ((obj2 instanceof Player player && obj1 instanceof Bullet bullet)) {
            if (detectCollision(player.c, bullet.bullet_collision)) {
                bullet.isDestroyed = true;
                player.liveScore--;
                PlayerEffect playereffect = new PlayerEffect(player.getScaledXWorld(), player.getScaledYWorld());
                Entity.PlayerEffects.add(playereffect);
                if (player.liveScore <= 0) {
                    player.isDestroy =true;
                    
                }
                
                if(player.liveScore == 0&& MainCode.players.size()==0){
                    MainCode.gameOver = true;
                }

            }

        }
        if ((obj1 instanceof PowerUp power && obj2 instanceof Player player)) {
            if (detectCollision(power.c, player.c)) {
                Entity.destroy((PowerUp) power);
                power.isDestroyed = true;
                player.powerUp++;
                Player.score+=(int)(Math.random()*20)+1;
                player.specialHitted = false;
            }
        } else if ((obj2 instanceof Player player && obj1 instanceof PowerUp power)) {
            if (detectCollision(player.c, power.c)) {
                Entity.destroy((PowerUp) power);
                player.powerUp++;
                player.specialHitted = false;

            }

        }
        if ((obj1 instanceof Healthy health && obj2 instanceof Player player)) {
            if (detectCollision(health.c, player.c)) {
                Entity.destroy((Healthy) health);
                player.specialHitted = false;
                if (player.liveScore < 5) {
                    player.liveScore++;
                }
                if(player.liveScore == 0&& MainCode.players.size()==0){
                    MainCode.gameOver = true;
                }
                System.out.println("live score" + player.liveScore);
            }
        } else if ((obj2 instanceof Player player && obj1 instanceof Healthy health)) {
            if (detectCollision(player.c, health.c)) {
                Entity.destroy((Healthy) health);
                player.specialHitted = false;
                if (player.liveScore < 5) {
                    player.liveScore++;
                }
                System.out.println("live score" + player.liveScore);
            }

        }
        if ((obj1 instanceof specialGift special && obj2 instanceof Player player)) {
            if (detectCollision(special.c, player.c)) {
                Entity.destroy((specialGift) special);
                player.specialHitted = true;
            }
        } else if ((obj2 instanceof Player player && obj1 instanceof specialGift special)) {
            if (detectCollision(player.c, special.c)) {
                Entity.destroy((specialGift) special);
                player.specialHitted = true;
            }

        }

    }

}
