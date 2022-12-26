/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.Players;
import main.ReadImages;
import Textures.TextureReader;

/**
 *
 * @author hp
 */
public class ReadPlayerAssets {

    public String[] playerTextureName={ "effect1.png","effect2.png","effect3.png","effect4.png"
       };
    public static TextureReader.Texture playerTexture[] = new TextureReader.Texture[4];
    public static int playerTextures[] = new int[4];
    ReadImages read = new ReadImages();
    public ReadPlayerAssets(  ) {
        read.readTexture( playerTextureName, playerTextures,playerTexture, "/player-effect/");
    }
}
