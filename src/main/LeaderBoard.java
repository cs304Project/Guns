/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.awt.Color;
import java.awt.Font;
 import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import org.json.simple.parser.ParseException;

/**
 *
 * @author youse
 */
public class LeaderBoard extends JFrame implements ActionListener{
    JButton menuButton;
    JLabel [] scores = new JLabel[10];
    ScoreBoard scoreboard;
    int screenWidth = 700;
    int screenHeight = 700;
    int btuWidth = 200;
    int btuHeight = 50;
    int xWorld = 350;
    int yWorld = 200;
    GameManager gameManager;
    Sound sound;
    //Sound leaderbuttonsound=new Sound();

    public LeaderBoard(GameManager gameManager) throws IOException, FileNotFoundException, ParseException{
        this.gameManager = gameManager;
        this.sound=gameManager.sound;
        scoreboard = new ScoreBoard();
        ArrayList<Score> scoresList =  scoreboard.getScores();
        
        menuButton = new JButton();
        menuButton.setBounds(xWorld-330, yWorld-180, 100, 40);
        
        createBtu(menuButton, "menu", new Color(0f, 0f, 0f, 0.5f));
        this.add(menuButton);
        
        for(int i=0;i<scoresList.size();i++){
            scores[i]=new JLabel();
            scores[i].setBounds(xWorld-200,100+50*i,400,50);
            createLabel(scores[i],text(scoresList.get(i).name,scoresList.get(i).score),new Color(0f, 0f, 0f, 0.5f));
            this.add(scores[i]);
        }

        setTitle("Leader Board");
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
        label.setFont(new Font("Monospaced",Font.BOLD,15));
        label.setBackground(c);
        
        return label;
    }
    String text (String name,long score){
        String out=name;
        int space=40-name.length();
        while(space-->=0){
            out+=" ";
        }
        out+=score;
        return out;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       // leaderbuttonsound.PlaySoundEffect(5);
        if(e.getSource() == menuButton){
      //leaderbuttonsound.PlaySoundEffect(5);
           
            //sound.stopSound();
             sound.PlaySoundEffect(5);
            gameManager = new GameManager(true, false, gameManager.userName,sound);
            this.dispose();
        }
    }
}
