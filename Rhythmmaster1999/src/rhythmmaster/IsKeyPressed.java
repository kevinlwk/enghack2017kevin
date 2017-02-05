package rhythmmaster;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;

public class IsKeyPressed {
    private static boolean[] keyPressed = new boolean[7];
    
    public static boolean[] isThisPressed() {
        synchronized (IsKeyPressed.class) {
            return keyPressed;
        }
    }

    public static void main(String[] args) {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {

            @Override
            public boolean dispatchKeyEvent(KeyEvent ke) {
                synchronized (IsKeyPressed.class) {
                    switch (ke.getID()) {
                    case KeyEvent.KEY_PRESSED:
                        if (ke.getKeyCode() == KeyEvent.VK_S) {
                            keyPressed[0] = true;
                        }
                        if (ke.getKeyCode() == KeyEvent.VK_D) {
                        	keyPressed[1] = true;
                        }
                        if (ke.getKeyCode() == KeyEvent.VK_F) {
                        	keyPressed[2] = true;
                        }
                        if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
                        	keyPressed[3] = true;
                        }
                        if (ke.getKeyCode() == KeyEvent.VK_J) {
                        	keyPressed[4] = true;
                        }
                        if (ke.getKeyCode() == KeyEvent.VK_K) {
                        	keyPressed[5] = true;
                        }
                        if (ke.getKeyCode() == KeyEvent.VK_L) {
                        	keyPressed[6] = true;
                        }
                        break;

                    case KeyEvent.KEY_RELEASED:
                        if (ke.getKeyCode() == KeyEvent.VK_S) {
                        	keyPressed[0] = false;
                        }
                        if (ke.getKeyCode() == KeyEvent.VK_D) {
                        	keyPressed[1] = false;
                        }
                        if (ke.getKeyCode() == KeyEvent.VK_F) {
                        	keyPressed[2] = false;
                        }
                        if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
                        	keyPressed[3] = false;
                        }
                        if (ke.getKeyCode() == KeyEvent.VK_J) {
                        	keyPressed[4] = false;
                        }
                        if (ke.getKeyCode() == KeyEvent.VK_K) {
                        	keyPressed[5] = false;
                        }
                        if (ke.getKeyCode() == KeyEvent.VK_L) {
                        	keyPressed[6] = false;
                        }
                        break;
                    }
                    return false;
                }
            }            
        });
    }
}
