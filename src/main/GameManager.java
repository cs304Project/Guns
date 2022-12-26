
package main;


public class GameManager {
    
    public boolean menuOn;
    public boolean gameplayOn;
    //public  Timing time;
    public Sound sound = new Sound();
    public String userName = "";
 
    
    public GameManager(boolean menuOn , boolean gameplayOn ,String userName , int level)
    {
        
        this.menuOn = menuOn;
        this.gameplayOn = gameplayOn;
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
            Gameplay gameplay = new Gameplay( this , level);
            //sound.playSound(1);
            sound.PlaySoundEffect(2);
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
