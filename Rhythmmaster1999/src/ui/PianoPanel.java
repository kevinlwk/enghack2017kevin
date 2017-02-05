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
	int framesPassed = 0;
	
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		for(int i = 0; i < 7; i++){
			g.setColor(new Color(225 - 15 * i,255 - 15 * i, 255 - 15 * i));
			g.fillRect(100 * i, 0, 100, 750);
		}
		g.setColor(Color.yellow);
		g.fillRect(0, LINE_HEIGHT, 700, 10);
		framesPassed++;
		g.drawString(Integer.toString(framesPassed), 600, 100);
	}
	
	public void drawNotes (Graphics g, ArrayList<Note> notes){
		g.setColor(Color.black);
		for (Note e : notes){
			g.drawRect(e.keyPos * 100, e.distanceFromLine, 100, 30);
		}
	}
}
