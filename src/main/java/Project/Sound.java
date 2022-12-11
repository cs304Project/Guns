
package Project;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author hossa
 */
public class Sound {
    
    Clip clip;
    File soundFile[]  = new File[5];
    
    public Sound()
    {
        String pathName = "src/main/java/Assets/Sounds";
        soundFile[0] =  new File(pathName +"Catch_it.wav");
        soundFile[1] =  new File(pathName +"Taratata.wav");
    }
    
    
    private void setFile(int i)
    {
        try{
           
           AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile[i]);
           clip = AudioSystem.getClip();
           clip.open(audioStream);
        }
        catch(IOException | LineUnavailableException | UnsupportedAudioFileException e)
        {
            System.out.println(e);
        }
        catch (Exception e)
        {
            System.out.println("OUT OF CONTROL");
        }
        
         
        
    }
    
    
    
    private void start()
    {
        clip.start();
    }
    
    
    private void loop()
    {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    
    private void stop()
    {
        clip.stop();
    }
    
    public void playSound(int i)
    {
        this.setFile(i);
        this.start();
        this.loop();
    }
    
    public void stopSound()
    {
        this.stop();
    }
    
    public void PlaySoundEffect()
    {
        this.start();
    }
} 
