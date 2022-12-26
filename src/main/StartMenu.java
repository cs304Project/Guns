package main;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import org.json.simple.parser.ParseException;

/**
 *
 * @author hossa
 */
public class StartMenu extends JFrame implements ActionListener {

    JButton easyBtu;
    JButton hardBtu;
    JButton LeaderBoard;
    JButton HowToPlay;
    JButton exitBtu;
    JLabel userText;
    GameManager gameManager;
    int screenWidth = 700;
    int screenHeight = 700;
    int btuWidth = 200;
    int btuHeight = 50;
    int xWorld = 350;
    int yWorld = 200;
    int level;
    Sound buttonssound=new Sound();
    public StartMenu(GameManager gameManager, String UserName) {

        this.gameManager = gameManager;

        easyBtu = new JButton();
        hardBtu = new JButton();
        LeaderBoard = new JButton();
        HowToPlay = new JButton();
        exitBtu = new JButton();
        easyBtu.setBounds(xWorld - 100, yWorld, btuWidth, btuHeight);
        hardBtu.setBounds(xWorld - 100, yWorld + 75, btuWidth, btuHeight);
        LeaderBoard.setBounds(xWorld - 100,yWorld+150,btuWidth,btuHeight);
        HowToPlay.setBounds(xWorld - 100,yWorld+225,btuWidth,btuHeight);
        exitBtu.setBounds(xWorld - 100,yWorld+300 , btuWidth, btuHeight);

        easyBtu = createBtu(easyBtu, "EASY", new Color(0f, 0f, 0f, 0.5f));
        hardBtu = createBtu(hardBtu, "HARD", new Color(0f, 0f, 0f, 0.5f));
        HowToPlay = createBtu(HowToPlay, "How to play", new Color(0f,0f,0f,.5f));
        LeaderBoard = createBtu(LeaderBoard,"Leader Board",new Color(0f,0f,0f,0.5f));
        
        exitBtu = createBtu(exitBtu, "EXIT", new Color(0f, 0f, 0f, 0.5f));
        setContentPane(new JLabel(new ImageIcon("D:/Guns/src/Assets/player/background.jpeg")));
        setLayout(new FlowLayout());

        userText = new JLabel();
        userText.setBounds(100, 100, 400, 16);
        userText.setFont(new Font("", Font.CENTER_BASELINE, 20));
        userText.setText("User Name: " + UserName);

        //Add properties for JFrame screen;
        this.add(easyBtu);
        this.add(hardBtu);
        this.add(LeaderBoard);
        this.add(HowToPlay);
        this.add(exitBtu);
        this.add(userText);

        //Default Setting to view the JFrame screen;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        this.setSize(screenWidth, screenHeight);
        this.setLayout(null);
        this.setVisible(true);
        setLocationRelativeTo(null);

    }

    public final JButton createBtu(JButton btu, String text, Color c) {
        //btu.addMouseListener(this);
        btu.setText(text);
        btu.setFocusable(false);
        btu.setFont(new Font("Monospaced", Font.BOLD, 20));
        btu.setBackground(c);
        btu.setForeground(Color.WHITE);
        btu.addActionListener(this);

        return btu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        
        if (e.getSource() == easyBtu){
           gameManager.sound.stopSound();
           buttonssound.PlaySoundEffect(5);
            
            level = 1; 
            gameManager = new GameManager(false, true, gameManager.userName,gameManager.sound);
           
        }
        else if (e.getSource() == hardBtu){
            gameManager.sound.stopSound();
            buttonssound.PlaySoundEffect(5);
            level = 2;
            gameManager = new GameManager(false, true, gameManager.userName,gameManager.sound);
           
        }
        else if(e.getSource () == exitBtu)
         // buttonssound.PlaySoundEffect(5);
            System.exit(0);

        if(e.getSource() == LeaderBoard){
           buttonssound.PlaySoundEffect(5);
            try {
                gameManager = new GameManager(false,false,true,false,gameManager.userName);
            } catch (IOException ex) {
                Logger.getLogger(StartMenu.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(StartMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.dispose();
        }
        
        if (e.getSource() == HowToPlay){
           buttonssound.PlaySoundEffect(5);
            try {
                gameManager = new GameManager(false,false,false,true,gameManager.userName);
            } catch (IOException ex) {
                Logger.getLogger(StartMenu.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(StartMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }

        gameManager.level=level;
 
        this.dispose();

    }

    
}

