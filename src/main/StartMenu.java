package main;


import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author hossa
 */
public class StartMenu extends JFrame implements ActionListener, MouseListener {

    JButton easyBtu;
    JButton hardBtu;
    JButton exitBtu;
    JLabel userText;
    GameManager gameManager;
    int screenWidth = 700;
    int screenHeight = 700;
    int btuWidth = 200;
    int btuHeight = 50;
    int xWorld = 350;
    int yWorld = 200;
    String level ;


    public StartMenu(GameManager gameManager , String UserName) {

        this.gameManager = gameManager;

        easyBtu = new JButton();
        hardBtu = new JButton();
        exitBtu = new JButton();
        easyBtu.setBounds(xWorld - 100, yWorld, btuWidth, btuHeight);
        hardBtu.setBounds(xWorld - 100, yWorld + 100, btuWidth, btuHeight);
        exitBtu.setBounds(350 - 100, 400, btuWidth, btuHeight);

        easyBtu = createBtu(easyBtu, "EASY", new Color(0f, 0f, 0f, 0.5f));
        hardBtu = createBtu(hardBtu, "HARD", new Color(0f, 0f, 0f, 0.5f));
        exitBtu = createBtu(exitBtu, "EXIT", new Color(0f, 0f, 0f, 0.5f));
        setContentPane(new JLabel(new ImageIcon("D:/Guns/src/Assets/player/background.jpeg")));
        setLayout(new FlowLayout());
        
        userText  = new JLabel();
        userText.setBounds( 100, 100 , 400, 16);
        userText.setFont(new Font("", Font.CENTER_BASELINE, 20));
        userText.setText("User Name: " + UserName);
        
        //Add properties for JFrame screen;
        this.add(easyBtu);
        this.add(hardBtu);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == easyBtu || e.getSource()==hardBtu) {
            level = e.getSource() == easyBtu?"easy":"hard";
            //gameManager.sound.stopSound();
            gameManager = new GameManager(false, true , gameManager.userName);

            //gameManager.time.start();
            this.dispose();

        }

        if (e.getSource() == exitBtu) {
            System.exit(0);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
//        if (e.getSource().equals(startBtu)) {
//            startBtu = createBtu(startBtu, "start", new Color(0f, 0f, 0f, 0.2f));
//        } else if (e.getSource().equals(exitBtu)) {
//            exitBtu = createBtu(exitBtu, "exit", new Color(0f, 0f, 0f, 0.2f));
//        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
//        if (e.getSource().equals(startBtu)) {
//            startBtu = createBtu(startBtu, "start", new Color(0f, 0f, 0f, 0.5f));
//        } else if (e.getSource().equals(exitBtu)) {
//            exitBtu = createBtu(exitBtu, "exit", new Color(0f, 0f, 0f, 0.5f));
//        }

    }

}
