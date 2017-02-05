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
	public static final int SCREEN_HEIHGT = 600;
	
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		for(int i = 0; i < 7; i++){
			g.setColor(new Color(30 * i,255 - 30 * i, 255 - 5 * i * i));
			g.fillRect(100 * i, 0, 100, 600);
		}
	}
	
	public void drawNotes (Graphics g, ArrayList<Note> notes){
		
	}
}
