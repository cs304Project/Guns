
package main;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;



/**
 *
 * @author hossa
 */
public class Timing  extends JFrame{
    
    public JLabel timeText;
    public float seconds = 0;;
    String secondText = String.format("%03f", seconds);;
    float consumingTime = 0;
 
    
    
    
    Timer secondsTime = new Timer(100 , new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                consumingTime += 100f;
                //1000 ms = 1 sec;
                seconds = consumingTime/1000;
                secondText = String.format("%03f", seconds);
                
                
                timeText.setText(secondText);
            }
        }); 
   
    
    
    public Timing(){
        
        
        timeText  = new JLabel();
        timeText.setText(secondText);
        timeText.setBounds(230,120 , 400 , 40);
        timeText.setFont(new Font("", Font.BOLD, 20));   
        
    }
    
    
    public void start()
    {
        
        this.secondsTime.start();
        
    }
    
    public void stop()
    {
        consumingTime = 0;
        seconds =0;
        this.secondsTime.stop();
        Println("GAME IS STOPPING");
        
    }
    
    public void reset()
    {
        secondsTime.restart();
    }
    
    public final void Println(String s)
    {
        System.out.println(s);
    }
    
}
