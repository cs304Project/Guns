/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import main.Enemys.Enemy;
import main.Players.Player;
import org.json.simple.parser.ParseException;

/**
 *
 * @author hossa
 */
public class PauseMenu extends JPanel implements ActionListener{
    JLabel PauseMenuTitle;
    int width = 400;
    int height = 200;
    int xPanel = 150;
    int yPanel = 150;
    
    JPanel panelTop = new JPanel();
    JPanel panelBottom = new JPanel(new FlowLayout(FlowLayout.CENTER, 40 , 10));
    JButton menuBtu;
    JButton cancelBtu;
    GameManager gameManager;
    Gameplay gameplay;
    Sound sound;
    
    public PauseMenu(GameManager gameManager ,Gameplay gameplay,Sound sound)
    {
        this.gameManager = gameManager;
        this.gameplay = gameplay;
        this.sound=sound;
        setBounds(150, 150,width, height);
        setLayout(new BorderLayout());
        panelTop.setPreferredSize(new Dimension(width, height/2));       
        panelBottom.setPreferredSize(new Dimension(width, height/2));
        
        PauseMenuTitle = new JLabel("Pause Menu");
        PauseMenuTitle.setBounds(20,20,20,40);
        PauseMenuTitle.setFont(new Font("", Font.BOLD, 60));
        panelTop.add(PauseMenuTitle);
        
        
        
        menuBtu = new JButton();
        menuBtu.setBounds(20, 20, 40 , 30);
        menuBtu = createBtu(this.menuBtu, "Menu");
        
        cancelBtu = new JButton();
        cancelBtu.setBounds(0, 0, 40 , 30);
        cancelBtu = createBtu(this.cancelBtu, "Cancel");
        panelBottom.add(menuBtu);
        panelBottom.add(cancelBtu);
        
        panelTop.setBackground(Color.ORANGE);
        panelBottom.setBackground(Color.black);
                
        add(panelTop, BorderLayout.NORTH);
        add(panelBottom, BorderLayout.SOUTH);
        
        //add(menuBtu);
        
        
        
    }
    
    
     public final JButton createBtu(JButton btu, String text) {

        btu.setText(text);
        btu.setFocusable(false);
        btu.setFont(new Font("", Font.BOLD, 40));
        btu.setBackground(Color.lightGray);
        btu.setForeground(Color.black);
        btu.addActionListener(this);
        btu.setBorder(BorderFactory.createEtchedBorder());

        return btu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       
        if (e.getSource() == menuBtu) {
           sound.stopSound();
            Entity.EnemyStage_1 = new ArrayList<>();
            Entity.EnemyStage_2 = new ArrayList<>();
            Entity.EnemyStage_3_01 = new ArrayList<>();
            Entity.EnemyStage_3_02= new ArrayList<>();
           
            ScoreBoard scoreboard = new ScoreBoard();
            try {
                scoreboard.addScore(gameManager.userName,Player.score);
            } catch (IOException ex) {
                Logger.getLogger(Gameplay.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(Gameplay.class.getName()).log(Level.SEVERE, null, ex);
            }
            Player.score=0;
            gameManager = new GameManager();
            System.out.println("Went to Menu");
            MainCode.isPause = false;
            this.gameplay.dispose(); 
        }
        else if (e.getSource() == cancelBtu)
        {
            sound.playSound(1);
            this.setVisible(false);
            MainCode.isPause = false;
        }
    }
    
}
