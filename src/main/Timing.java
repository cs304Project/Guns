
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
    public int seconds = 0;
    public float melliseconds = 0;
    String secondText = String.format("%02d", seconds);;
    public int consumingTime = 0;
    
    
    public Timer time = new Timer(100 , new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                consumingTime += 1000;
                //1000 ms = 1 sec;
                seconds = consumingTime / 1000;
                secondText = String.format("%02d", seconds);
                
                
                timeText.setText(secondText);
            }
        }); 
   public Timer melliTime = new Timer(1,new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            melliseconds++;
        }
   });
    
    public Timing(){
        
        
        timeText  = new JLabel();
        timeText.setText(secondText);
        timeText.setBounds(230,120 , 400 , 40);
        timeText.setFont(new Font("", Font.BOLD, 20));   
        
    }
    
    
    public void start(Timer t)
    {
        
        t.start();
        Println("GAME IS RUNNING");
    }
    
    
    public void stop(Timer t)
    {
        
        t.stop();
        Println("GAME IS STOPPING");
        
    }
    
    public void reset()
    {
        time.restart();
    }
    
    public final void Println(String s)
    {
        System.out.println(s);
    }
    
}
