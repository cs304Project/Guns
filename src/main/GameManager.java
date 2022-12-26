
package main;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.parser.ParseException;


public class GameManager {
    
    public boolean menuOn;
    public boolean gameplayOn;
    public boolean leaderBoardOn;
    //public  Timing time;
    public Sound sound = new Sound();
    public String userName = "";
    public int level;
    
    public GameManager(boolean menuOn , boolean gameplayOn ,String userName)
    {
        
        this.menuOn = menuOn;
        this.gameplayOn = gameplayOn;
        this.leaderBoardOn = false;
        this.userName = userName;
        //time = new Timing();
        
        // Active the menu windows
        if(this.menuOn)
        {
            StartMenu  menu = new StartMenu(this , userName);
            
            //sound.playSound(0);

        }
        
        // Active the gameplay windows
        if(this.gameplayOn)
        {
            //Gameplay gameplay = new Gameplay(time.timeText , this);
            Gameplay gameplay = new Gameplay( this ,level);
            //sound.playSound(1);
            sound.PlaySoundEffect(2);
        }
        
    }
    public GameManager(boolean menuOn , boolean gameplayOn,boolean leaderBoardOn ,String userName) throws IOException, FileNotFoundException, ParseException
    {
        
        this.menuOn = menuOn;
        this.gameplayOn = gameplayOn;
        this.leaderBoardOn = leaderBoardOn;
        this.userName = userName;
        //time = new Timing();
        
        // Active the menu windows
        if(this.menuOn)
        {
            StartMenu  menu = new StartMenu(this , userName);
            
            //sound.playSound(0);

        }
        
        // Active the gameplay windows
        
        if(this.leaderBoardOn){
            LeaderBoard leaderBoard = new LeaderBoard(this);
        }
        
    }
    
    public GameManager()
    {
        menuOn = true;
        gameplayOn = false;
        StartMenu  menu = new StartMenu(this, userName);
        
    }
    
    
    public void setGamePlayOn(boolean gameplayOn)
    {
        this.gameplayOn = gameplayOn;
    }
    
    public boolean getGamePlayOn()
    {
        return this.gameplayOn;
    }
}
