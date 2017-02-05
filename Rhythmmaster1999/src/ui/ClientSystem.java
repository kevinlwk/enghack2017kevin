package ui;

import javax.swing.JFrame;

public class ClientSystem {
	public static void main (String[] args){
		JFrame window = new JFrame("Rhythmmaster1999");
		PianoPanel panel = new PianoPanel();
		window.setContentPane(panel);
		window.setSize(PianoPanel.SCREEN_LENGTH, PianoPanel.SCREEN_HEIHGT);
		window.setResizable(false);
		window.setVisible(true);
	}
}
