package main.Stage;

import Textures.TextureReader;
import java.util.ArrayList;
import main.Enemys.Enemy;
import static main.Entity.EnemyStage_2_01;
import static main.Entity.EnemyStage_2_02;
import static main.Entity.EnemyStage_2_03;
import static main.Entity.EnemyStage_2_04;

import main.ReadImages;
/**
 *
 * @author RONY
 */
public class Stage2 extends Stage {

    String[] enemyTextureName = {"enemy1.png", "enemy2.png", "enemy3.png", "enemy4.png", "enemy5.png", "enemy6.png", "enemy7.png", "enemy8.png", "enemy9.png",
        "enemy10.png", "enemy11.png", "enemy12.png", "enemy13.png", "enemy14.png"};
    public static TextureReader.Texture enemyTexture[] = new TextureReader.Texture[14];
    public static int enemyTextures[] = new int[14];
    ReadImages read = new ReadImages();

    public Stage2(int enemyNumber) {
        createStage2Enemys(enemyNumber);
        read.readTexture(enemyTextureName, enemyTextures, enemyTexture, "/enemy/stage2/");
    }
    public void createStage2Enemys(int enemyNumber) {
            separetedEnemy(20,EnemyStage_2_01);
            separetedEnemy(70,EnemyStage_2_02);
            separetedEnemy(120,EnemyStage_2_03);
            separetedEnemy(170,EnemyStage_2_04);
    }
    public void separetedEnemy(float startPosition, ArrayList<Enemy> list){
        for(int i =0 ;i<10 ;i++){
            
        createEnemy(list, -200 - (i * 50), startPosition);

        }
    }
}
