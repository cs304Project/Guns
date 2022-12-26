package main;

import com.sun.opengl.util.Animator;
import com.sun.opengl.util.FPSAnimator;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.media.opengl.GLCanvas;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import main.Players.Player;
import org.json.simple.parser.ParseException;

/**
 *
 * @author hossa
 */
public class Gameplay extends JFrame implements ActionListener {

    PauseMenu pausePanel;
    JButton pauseBtu;

    int maxX = 700;
    int maxY = 700;
    GameManager gameManager;
    MainCode mc;

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

        add(pausePanel);
        //add(textTime);
        add(pauseBtu);
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
        btu.addActionListener(this);
        btu.setBorder(BorderFactory.createEtchedBorder());

        return btu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == pauseBtu) {
            gameManager.sound.stopSound();

            MainCode.isPause = true;
            pausePanel.setVisible(true);
        }

    }
}
