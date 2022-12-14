package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author hossa
 */
public class Login extends JFrame implements ActionListener {

    JTextField textField;
    JPasswordField passwordField;
    JButton loginBtu;
    JButton cancelBtu;
    GameManager gameManger;
    JLabel userText;
    JLabel userPassword;
    int xWorld = 200;
    int yWorld = 200;
    Sound buttonssound=new Sound();

    public static void main(String[] string) {
        new Login();
    }

    public Login() {

        textField = new JTextField();
        textField.setBounds(xWorld, yWorld, 200, 32);
        textField.setFont(new Font("Monospaced", Font.BOLD, 20));
        textField.setCaretColor(Color.blue);

        userText = new JLabel("User Name");
        userText.setBounds(xWorld - 100, yWorld + 16, 100, 16);
        userText.setFont(new Font("", Font.CENTER_BASELINE, 12));

        loginBtu = new JButton("Login");
        loginBtu.setBounds(xWorld, yWorld + 40, 80, 28);
        loginBtu.setFont(new Font("", Font.CENTER_BASELINE, 12));
        loginBtu.addActionListener(this);

        cancelBtu = new JButton("Cancel");
        cancelBtu.setBounds(xWorld + 100, yWorld + 40, 80, 28);
        cancelBtu.setFont(new Font("", Font.BOLD, 12));
        cancelBtu.addActionListener(this);

        //add(passwordField);
        add(textField);
        //add(userPassword);
        add(userText);
        add(loginBtu);
        add(cancelBtu);
        setBackground(Color.PINK);
        
        setTitle("Guns Login");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        this.setSize(500, 500);
        this.setLayout(null);
        this.setVisible(true);
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginBtu) {

           
            buttonssound.PlaySoundEffect(5);
            if(!textField.getText().isEmpty()){
            gameManger = new GameManager(true, false, textField.getText(),0,buttonssound);
                        gameManger.sound=buttonssound;

            }else{
                System.out.println("must enter name");
            }

            this.dispose();
        } else if (e.getSource() == cancelBtu) {
            buttonssound.PlaySoundEffect(5);

            System.exit(0);
        }

    }
}