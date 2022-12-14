package main.Stage;

import Textures.TextureReader;
import static main.Entity.EnemyStage_4;
import main.ReadImages;
/**
 *
 * @author RONY
 */
public class Stage4 extends Stage {

    String[] enemyTextureName = {"enemy1.png", "enemy2.png", "enemy3.png", "enemy4.png", "enemy5.png", "enemy6.png"
        };
    public static TextureReader.Texture enemyTexture[] = new TextureReader.Texture[6];
    public static int enemyTextures[] = new int[6];
    
    public String[] enemyTextureEffectName={ "enemy-effect1.png","enemy-effect2.png","enemy-effect3.png","enemy-effect4.png"
       };
    public static TextureReader.Texture enemyTextureEffect[] = new TextureReader.Texture[4];
    public static int enemyTextureEffects[] = new int[4];
    ReadImages read = new ReadImages();

    public Stage4(int enemyNumber  ) {
        createStage4Enemys(enemyNumber);
        read.readTexture( enemyTextureName, enemyTextures,enemyTexture, "/enemy/stage4/"); 
        read.readTexture( enemyTextureEffectName, enemyTextureEffects,enemyTextureEffect, "/enemy/stage4/");

    }
    private void createStage4Enemys(int enemyNumber) {
        for (int i = 0; i < enemyNumber; i++) {
            createEnemyBoss(EnemyStage_4, 0, 0);
        }
    }
}
