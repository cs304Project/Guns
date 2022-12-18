
package Main;


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
    public int seconds = 0;;
    String secondText = String.format("%02d", seconds);;
    int consumingTime = 0;
    public float mSeconds = 0;
    
    
    
    Timer secondsTime = new Timer(1000 , new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                consumingTime += 1000;
                //1000 ms = 1 sec;
                mSeconds = consumingTime / 1000000;
                seconds = consumingTime / 1000;
                secondText = String.format("%02d", seconds);
                
                
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
