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
	ArrayList<Note> notes;
	
	public PianoPanel(){
		super();
	}
	
	public PianoPanel(ArrayList<Note> notes){
		new PianoPanel();
		this.notes = notes;
	}
	
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
		drawNotes(g);
		g.drawString(Integer.toString(framesPassed), 600, 100);
	}
	
	public void drawNotes (Graphics g){
		g.setColor(Color.black);
		for (Note e : notes){
			g.drawRect(e.keyPos * 100, LINE_HEIGHT - e.distanceFromLine + 9 * framesPassed, 100, 30);
		}
	}
}
