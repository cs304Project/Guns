package main;

import com.sun.opengl.util.Animator;
import com.sun.opengl.util.FPSAnimator;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.media.opengl.GLCanvas;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


/**
 *
 * @author hossa
 */
public class Gameplay extends JFrame implements KeyListener {

    PauseMenu pausePanel;
    JButton pauseBtu;

    int maxX = 700;
    int maxY = 700;
    GameManager gameManager;
    MainCode mc;
    Timing time = new Timing();

    //public Gameplay(JLabel textTime , GameManager gameManager)
    public Gameplay(GameManager gameManager, int level) {

        this.gameManager = gameManager;
        mc = new MainCode(level);
        pausePanel = new PauseMenu(this.gameManager, this,gameManager.sound);
        pausePanel.setVisible(false);

        GLCanvas glcanvas;
        Animator animator;
        glcanvas = new GLCanvas();
        getContentPane().add(glcanvas, BorderLayout.CENTER);
        glcanvas.addGLEventListener(mc);
        mc.setCanvas(glcanvas);

        pauseBtu = new JButton();
        pauseBtu.setBounds(5, 5, 60, 40);
        pauseBtu = createBtu(this.pauseBtu, "Pause");

        animator = new FPSAnimator(60);
        animator.add(glcanvas);
        animator.start();

        JLabel title = new JLabel("DROP YOUR CODE");
        title.setBounds(140, 200, 400, 40);
        title.setFont(new Font("", Font.BOLD, 20));
        
        glcanvas.addKeyListener(this);
        add(pausePanel);
        //add(textTime);
        //add(pauseBtu);
        //add(title);
        add(glcanvas);
        
        setTitle("Guns");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(maxX, maxY);
        setLocationRelativeTo(null);
        setVisible(true);
        setFocusable(true);
        setResizable(false);
        glcanvas.requestFocus();

    }

    public final JButton createBtu(JButton btu, String text) {

        btu.setText(text);
        btu.setFocusable(false);
        btu.setFont(new Font("", Font.BOLD, 16));
        btu.setBackground(Color.lightGray);
        btu.setForeground(Color.black);
        btu.setBorder(BorderFactory.createEtchedBorder());

        return btu;
    }



    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
         if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            gameManager.sound.stopSound();

            MainCode.isPause = true;
            pausePanel.setVisible(true);
        }
         
 
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
