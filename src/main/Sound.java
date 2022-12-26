package main;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

//this class is going to use for sound effects in the game
public class Sound {

    Clip clip;
    File soundFile[] = new File[10];

    public Sound() {
        String pathName = "src/Assets/Sounds/";

        soundFile[0] = new File(pathName + "start menu.wav");
        soundFile[1] = new File(pathName + "game level.wav");
        soundFile[2] = new File(pathName + "player bullet.wav");
        soundFile[3] = new File(pathName + "Enemy death effect.wav");
        soundFile[4] = new File(pathName + "Enemy bullet.wav");
        soundFile[5] = new File(pathName + "button sound 3.wav");
        soundFile[6] = new File(pathName + "enemy_boss bullet effect.wav");
        soundFile[7] = new File(pathName + "Game over.wav");
        soundFile[8] = new File(pathName + "Enemy boss death effect.wav");

    }

    private void setFile(int i) {
        try {

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile[i]);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println("The sound has error");
        }

    }

    private void start() {
        clip.start();
    }

    private void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    private void stop() {
        clip.stop();
    }

    public void playSound(int i) {
        this.setFile(i);
        this.start();
        this.loop();
    }

    public void stopSound() {
        this.stop();
    }

    public void PlaySoundEffect(int i) {
        this.setFile(i);
        this.start();
    }
}
