package main.Stage;

import Textures.TextureReader;
import javax.media.opengl.GL;
import main.Entity;
import static main.Entity.EnemyStage_1;
import main.ReadImages;

/**
 *
 * @author RONY
 */
public class Stage1 extends Stage {

    String[] enemyTextureName = {"enemy1.png", "enemy2.png", "enemy3.png", "enemy4.png", "enemy5.png", "enemy6.png", "enemy7.png", "enemy8.png", "enemy9.png",
        "enemy10.png", "enemy11.png", "enemy12.png", "enemy13.png", "enemy14.png"};
    public static TextureReader.Texture enemyTexture[] = new TextureReader.Texture[14];
    public static int enemyTextures[] = new int[14];
    ReadImages read = new ReadImages();
    Entity entity = new Entity();

    public Stage1(int enemyNumber  ) {
        

        createStage1Enemys(enemyNumber);
        
        read.readTexture( enemyTextureName, enemyTextures,enemyTexture, "/enemy/stage1/");
        
    }

    private void createStage1Enemys(int enemyNumber) {
        for (int i = 0; i < enemyNumber; i++) {
            createEnemy(EnemyStage_1, -200, 210);
        }
    }

}
