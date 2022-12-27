
package main.Keys;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.BitSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.Players.Player;

/**
 *
 * @author hossa
 */
public class HandleKeys implements KeyListener {
    
    public final int UP = KeyEvent.VK_UP;
    public final int DOWN = KeyEvent.VK_DOWN;
    public final int LEFT = KeyEvent.VK_LEFT;
    public final int RIGHT = KeyEvent.VK_RIGHT;
    public final int SPACE = KeyEvent.VK_SPACE;
 public final int W = KeyEvent.VK_W;
    public final int S = KeyEvent.VK_S;
    public final int A = KeyEvent.VK_A;
    public final int D = KeyEvent.VK_D;
    public final int E = KeyEvent.VK_E;
    
    
    
    final BitSet keyBits = new BitSet(256);
    
    @Override
    public void keyTyped( final KeyEvent e) {
        
    }

    @Override
    public void keyPressed(final KeyEvent e) {
        int keyCode = e.getKeyCode();
        
        keyBits.set(keyCode);
       

       // System.out.println(Player.fireRate);
        
        
    }

    @Override
    public void keyReleased(final KeyEvent e) {
       int keyCode = e.getKeyCode();
        keyBits.clear(keyCode);
    }
    
    public boolean isKeyPressed(final int keyCode) {
        return keyBits.get(keyCode);
    }
}
