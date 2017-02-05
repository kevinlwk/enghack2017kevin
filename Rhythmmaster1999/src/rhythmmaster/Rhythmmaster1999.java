package rhythmmaster;

import java.awt.*;
import javax.swing.JFrame;

/**
 * Main class for the game
 */
public class Rhythmmaster1999 extends JFrame {

    static JFrame kevin = new JFrame();

    public static void main(String[] args){
        
    	int tempo = 84;
        Runtime game = new Runtime(tempo);
        game.run();
        System.exit(0);
    }
}
