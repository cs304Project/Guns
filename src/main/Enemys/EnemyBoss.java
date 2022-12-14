package main.Enemys;

import java.util.ArrayList;
import java.util.Random;
import javax.media.opengl.GL;
import main.Entity;
import main.Players.Bullet;
import main.Players.Collision;
import main.Sound;

import main.Timing;

/**
 *
 * @author hossa
 */
public class EnemyBoss extends Enemy {

    boolean topOn = false;
    boolean leftOn = false;
    boolean rightOn = false;
    boolean bottomOn = false;
    int normalAttackTimer = 0;
    int specialAttackTimer = 0;
    int counter = 0;
    Sound enemyBoss_bulletSound=new Sound();

    Timing t = new Timing();

    public EnemyBoss(float x, float y) {

        super( x, y,0.05f,200);

        scale = .5f;
        collidingRaduis = .28f;
        c = new Collision(xWorld * speed * scale, yWorld * speed * scale, collidingRaduis);

        Random r1 = new Random();
        bulletScale = 0.03f;
        topOn = r1.nextInt(2) == 1;
        rightOn = r1.nextInt(2) == 1;
        topOn = !bottomOn;
        rightOn = !leftOn;
        t.start();
    }

    @Override
    public void drawEnemy(GL gl,int stage) {

        HandleBorders();

        super.drawEnemy(gl,stage);
        if (normalAttackTimer % 5 == 0 && normalAttackTimer != 0 && specialAttackTimer <= 5) {
            bulletScale = 0.02f;
            createBullet(gl, Entity.bossBullets, 270, "BossBullet", bulletScale);
            //enemyBoss_bulletSound.PlaySoundEffect(6);
            normalAttackTimer = 0;

        } else if (specialAttackTimer % 5 == 0 && specialAttackTimer > 5) {
            bulletScale = 0.03f;
            super.createBullet(gl, Entity.bossBullets, angle, "SpecilEnemyBullet", bulletScale);
            //enemyBoss_bulletSound.PlaySoundEffect(6);
            specialAttackTimer = 0;
            normalAttackTimer = 0;
        }

        counter++;
        if (counter % 60 == 0) {
            normalAttackTimer++;
            specialAttackTimer++;
            System.out.println(specialAttackTimer);
        }
    }

    public void HandleBorders() {

        if (yWorld > 30) {
            topOn = true;
            bottomOn = false;

        } else if (yWorld < 0) {
            topOn = false;
            bottomOn = true;
        }

        if (xWorld > 30) {
            rightOn = true;
            leftOn = false;

        } else if (xWorld < -30) {
            rightOn = false;
            leftOn = true;
        }

        if (topOn) {
            yWorld -= .1f;
        }

        if (leftOn) {
            xWorld += .1f;
        }

        if (rightOn) {
            xWorld -= .1f;
        }

        if (bottomOn) {
            yWorld += .1f;
        }

    }

    @Override
    public void createBullet(GL gl, ArrayList<Bullet> storage, float angle, String typeBullet, float bulletScale) {
        for (int i = 0; i < 12; i++) {
            float addingAmount = 5;
            if (i % 2 == 1) {
                addingAmount = -addingAmount;
            }
            super.createBullet(gl, storage, angle + i * addingAmount, typeBullet, bulletScale);

        }
    }
}
