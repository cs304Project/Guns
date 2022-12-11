
package Project;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author hossa
 */
public class Gameplay extends JFrame implements ActionListener {
    
    JButton menuBtu;
    GameManager gameManager;
    
    public Gameplay(JLabel textTime , GameManager gameManager)
    {
        super("GamePlay");
        
        this.gameManager = gameManager;
        
        menuBtu = new JButton();
        menuBtu.setBounds(10, 10, 60, 40);
        menuBtu = createBtu(this.menuBtu, "MENU");
        
        JLabel title = new JLabel("DROP YOUR CODE");
        title.setBounds(140,200 , 400 , 40);
        title.setFont(new Font("", Font.BOLD, 20));
        
        add(textTime);
        add(menuBtu);
        add(title);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(500, 500);
        this.setVisible(true);
        setLocationRelativeTo(null);
    }
    
    public final JButton createBtu(JButton btu , String text)
    {
        
        btu.setText(text);
        btu.setFocusable(false);
        btu.setFont(new Font("", Font.BOLD, 12));
        btu.setBackground(Color.lightGray);
        btu.setForeground(Color.black);
        btu.addActionListener(this);
        btu.setBorder(BorderFactory.createEtchedBorder());
        

        return btu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == menuBtu)
        {
            gameManager.sound.stopSound();
            gameManager =  new GameManager(true, false);
            gameManager.time.stop();
            this.dispose();
        }
        
        
        
        
    }
}
