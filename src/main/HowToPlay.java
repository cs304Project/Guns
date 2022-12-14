package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

/**
 *
 * @author youse
 */
public class HowToPlay extends JFrame implements ActionListener{
    JButton menuButton;
    JLabel [] labels = new JLabel[10];
    String[] text = {"Instructions","arrows   to move player 1","W,A,S,D  to move player 2","space    shoot for player 1","E        shoot for player 2","Esc      PauseMenu"};
    int screenWidth = 700;
    int screenHeight = 700;
    int xWorld = 350;
    int yWorld = 200;
    GameManager gameManager;
    Sound buttonssound;
    public HowToPlay(GameManager gameManager){
        this.gameManager = gameManager;
        buttonssound=gameManager.sound;
        menuButton = new JButton();
        menuButton.setBounds(xWorld-330, yWorld-180, 100, 40);
        createBtu(menuButton, "menu", new Color(0f, 0f, 0f, 0.5f));
        this.add(menuButton);
         labels[0]=new JLabel();
        labels[0].setBounds(xWorld-200,100,500,50);
        labels[0].setText(text[0]);
        labels[0].setFont(new Font("Monospaced",Font.BOLD,50));
        labels[0].setForeground(new Color(0f,0f,0f,1f));
        this.add(labels[0]);
        for(int i=1;i<text.length;i++){
            labels[i]=new JLabel();
            labels[i].setBounds(xWorld-200,100+50*(i+1),500,50);
            createLabel(labels[i],text[i],new Color(0f, 0f, 0f, 0.5f));
            this.add(labels[i]);
        }
        setTitle("How to play");
        setSize(screenWidth,screenHeight);
        setResizable(false);
        setLocationRelativeTo(null);
        this.setLayout(null);
        setVisible(true);
        setFocusable(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
    public  final JButton createBtu(JButton btu, String text, Color c) {
        //btu.addMouseListener(this);
        btu.setText(text);
        btu.setFocusable(false);
        btu.setFont(new Font("Monospaced", Font.BOLD, 20));
        btu.setBackground(c);
        btu.setForeground(Color.WHITE);
        btu.addActionListener(this);
        return btu;
    }
    public final JLabel createLabel(JLabel label,String text,Color c){
        label.setText(text);
        label.setFont(new Font("Monospaced",Font.BOLD,30));
        label.setBackground(c);
        
        return label;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == menuButton){
        
            //gameManager.sound.stopSound();
             buttonssound.PlaySoundEffect(5);
            gameManager = new GameManager(true, false, gameManager.userName, 0 ,gameManager.sound);
            
            this.dispose();
        }
    }
}