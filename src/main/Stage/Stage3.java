package main.Stage;

import Textures.TextureReader;
import static main.Entity.EnemyStage_3_01;
import static main.Entity.EnemyStage_3_02;
import main.ReadImages;
/**
 *
 * @author RONY
 */
public class Stage3 extends Stage {

    String[] enemyTextureName = {"enemy1.png", "enemy2.png", "enemy3.png", "enemy4.png"};
    public static TextureReader.Texture enemyTexture[] = new TextureReader.Texture[4];
    public static int enemyTextures[] = new int[4];
    public String[] enemyTextureEffectName={ "enemy-effect1.png","enemy-effect2.png","enemy-effect3.png","enemy-effect4.png"
       };
    public static TextureReader.Texture enemyTextureEffect[] = new TextureReader.Texture[4];
    public static int enemyTextureEffects[] = new int[4];
    
    ReadImages read = new ReadImages();
    public Stage3(int enemyNumber,float enemySpeed,int health) {
        createStage3Enemys(enemyNumber,enemySpeed,health);
        read.readTexture(enemyTextureName, enemyTextures, enemyTexture, "/enemy/stage3/");
        read.readTexture( enemyTextureEffectName, enemyTextureEffects,enemyTextureEffect, "/enemy/stage3/");

    }

    public void createStage3Enemys(int enemyNumber,float enemySpeed,int health) {
        for (int i = 0; i < enemyNumber; i++) {
            if (i < enemyNumber / 2) {
                createEnemy(EnemyStage_3_01, -i * 50, 220,enemySpeed,health*2);
            } else {
                createEnemy(EnemyStage_3_02, i * 50, 220,enemySpeed,health*3);
            }
        }
    }

}
