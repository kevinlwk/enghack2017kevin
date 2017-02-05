package ui;
import rhythmmaster.*;
import rhythmmaster.Runtime;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class PianoPanel extends JPanel{
	Runtime runtime;
	public static final int SCREEN_LENGTH = 700;
	public static final int SCREEN_HEIHGT = 750;
	public static final int LINE_HEIGHT = 680;
	
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		for(int i = 0; i < 7; i++){
			g.setColor(new Color(225 - 15 * i,255 - 15 * i, 255 - 15 * i));
			g.fillRect(100 * i, 0, 100, 750);
		}
		g.setColor(Color.yellow);
		g.fillRect(0, LINE_HEIGHT, 700, 10);
	}
	
	public void drawNotes (Graphics g, ArrayList<Note> notes){
		
	}
}
