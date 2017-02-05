package ui;
import rhythmmaster.*;
import rhythmmaster.Runtime;

import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.sound.midi.*;

public class PianoPanel extends JPanel{
	Runtime runtime;
	public static final int SCREEN_LENGTH = 700;
	public static final int SCREEN_HEIGHT = 750;
	public static final int LINE_HEIGHT = 600;
	public static final int LINE_SIZE = 10;
	public int framesPassed = 0;
	private int displayScore = 0;
	ArrayList<Note> notes;
	Runtime context;
	
	public PianoPanel(){
		super();
	}
	
	public PianoPanel(ArrayList<Note> notes, Runtime context){
		new PianoPanel();
		this.notes = notes;
		this.context=context;
    		/*try {
    			Sequencer sequencer = MidiSystem.getSequencer();
    			sequencer.open();
    			InputStream is = new BufferedInputStream(new FileInputStream(new File("happybirthday.mid")));
    			sequencer.setSequence(is);
    			sequencer.start();
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}*/
	}
	
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		for(int i = 0; i < 7; i++){
			int j = 0;
			if (context.isKeyPressed[i]){
				j = 50;
			}
			g.setColor(new Color(175 + j - 15 * i,205 + j - 15 * i, 205 + j - 15 * i));
			g.fillRect(100 * i, 0, 100, 750);
		}
		g.setColor(Color.yellow);
		g.fillRect(0, LINE_HEIGHT, 700, LINE_SIZE);
		g.setColor(Color.black);
		framesPassed++;
		drawNotes(g);
		g.setColor(Color.black);
		g.drawString(Integer.toString(displayScore), 600, 100);
		g.setColor(Color.WHITE);
		g.fillOval(context.ball.x, context.ball.y, context.ball.size, context.ball.size);
		g.setColor(Color.BLUE);
		g.fillRect(context.paddle.x, LINE_HEIGHT, Paddle.PADDLE_SIZE, LINE_SIZE);
	}
	
	public void updateScore (int newScore){
		displayScore = newScore;
	}
	
	public void drawNotes (Graphics g){
		for (Note e : notes){
			/*if (- e.distanceFromLine + 9 * framesPassed > 0 && - e.distanceFromLine + 9 * framesPassed < SCREEN_HEIGHT - LINE_HEIGHT){
				g.setColor(new Color(1.0f,1.0f,1.0f, s.0f - (float)((- e.distanceFromLine + 9 * framesPassed)/(float)(SCREEN_HEIGHT - LINE_HEIGHT))));
			} else {*/
				if (e.isScored){
					if (e.missed){
						g.setColor(Color.red);
					} else {
						g.setColor(Color.green);
					}
				} else {
					g.setColor(Color.black);
				}
			//}
			if (e.isHit){
				g.drawRect(e.keyPos * 100, LINE_HEIGHT - e.distanceFromLine + 9 * framesPassed, 100, Note.SHORT_NOTE_SIZE);
			} else {
				g.fillRect(e.keyPos * 100, LINE_HEIGHT - e.distanceFromLine + 9 * framesPassed, 100, Note.SHORT_NOTE_SIZE);
			}
		}
	}
}
