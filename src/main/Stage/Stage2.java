package main.Stage;

import Textures.TextureReader;
import java.util.ArrayList;
import main.Enemys.Enemy;
import static main.Entity.EnemyStage_2;



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
            separetedEnemy(enemyNumber,20,EnemyStage_2);

    }
    public void separetedEnemy(int enemyNumber, float startPosition, ArrayList<Enemy> list){
        for(int i =0 ;i<enemyNumber ;i++){
            if((enemyNumber - 1) / 4 >= i) {
                startPosition = 20f;
            } else if (2 * (enemyNumber - 1) / 4 >= i) {
                startPosition = 70f;
            } else if (3 * (enemyNumber - 1) / 4 >= i) {
                startPosition = 120f;
            } else {
                startPosition = 170f;
            }
            createEnemy(list, -200 - (i%10 * 50), startPosition);

        }
    }
}
