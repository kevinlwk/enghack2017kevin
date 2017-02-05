package rhythmmaster;

import java.awt.event.KeyEvent;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JFrame;

import ui.PianoPanel;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class Runtime implements Runnable {

    private static final int SCORE_BOUNDARY = -50;

	private static final int MISSED_SCORE = -50;

	private static final int NORMAL_SCORE = 400;
	
	private static final int PERFECT_SCORE = 1000;
    
	int tempo;
    int mpb;
    int timesig;
    int bpm;
    public int score = 0;
    
    boolean[] isKeyPressed;
    
    PianoPanel panel;
    
    ArrayList<Note> test;
    
    public void run() {
    	
        test = new ArrayList<Note>();
        test = read("twinkle twinkle little star.txt");
        isKeyPressed = new boolean[7];
        
        initialize(test);
        
        while (isRunning()) {

            long time = System.currentTimeMillis();
            
            update();
            
            time = (1000 / 60) - (System.currentTimeMillis() - time);

            if (time > 0) {
                try {
                    Thread.sleep(time);
                } catch (Exception e) {
                }
            }
        }
    }
    
    /**
     * This method will set up everything need for the game to run
     */
    void initialize(ArrayList<Note> song) {
    	
		JFrame window = new JFrame("Rhythmmaster1999");
		panel = new PianoPanel(song);
		window.setContentPane(panel);
		window.setSize(PianoPanel.SCREEN_LENGTH, PianoPanel.SCREEN_HEIGHT);
		window.setResizable(false);
		window.setVisible(true);
		window.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
            	formKeyReleased(evt);
            }
        });
    }
    private void formKeyPressed(java.awt.event.KeyEvent evt) {                                
        switch(evt.getKeyCode()){
	        case KeyEvent.VK_S:
	        	isKeyPressed[0] = true;
	        	break;
	        case KeyEvent.VK_D:
	        	isKeyPressed[1] = true;
	        	break;
	        case KeyEvent.VK_F:
	        	isKeyPressed[2] = true;
	        	break;
	        case KeyEvent.VK_SPACE:
	        	isKeyPressed[3] = true;
	        	break;
	        case KeyEvent.VK_J:
	        	isKeyPressed[4] = true;
	        	break;
	        case KeyEvent.VK_K:
	        	isKeyPressed[5] = true;
	        	break;
	        case KeyEvent.VK_L:
	        	isKeyPressed[6] = true;
	        	break;
	        }
    }    
    
    private void formKeyReleased(java.awt.event.KeyEvent evt) {                                
        switch(evt.getKeyCode()){
	        case KeyEvent.VK_S:
	        	isKeyPressed[0] = false;
	        	break;
	        case KeyEvent.VK_D:
	        	isKeyPressed[1] = false;
	        	break;
	        case KeyEvent.VK_F:
	        	isKeyPressed[2] = false;
	        	break;
	        case KeyEvent.VK_SPACE:
	        	isKeyPressed[3] = false;
	        	break;
	        case KeyEvent.VK_J:
	        	isKeyPressed[4] = false;
	        	break;
	        case KeyEvent.VK_K:
	        	isKeyPressed[5] = false;
	        	break;
	        case KeyEvent.VK_L:
	        	isKeyPressed[6] = false;
	        	break;
	        }
    }    
    
    /**
     * This method will check for input move things around and check for win
     * conditions, etc
     */
    void update() {
    	//Flip switches
    	for (Note e: test){
    		int cDistance = - e.distanceFromLine + 9 * panel.framesPassed;
    		if (e.isChecked && !e.isScored){
    			if (cDistance > 10){
					e.isScored = true;
					score += MISSED_SCORE;
    			} else {
	    			if (isKeyPressed[e.keyPos]){
	    				System.out.println(cDistance);
	    				if (cDistance < -30){
	    					e.isScored = true;
	    					score += MISSED_SCORE;
	    				} else {
	    					if (cDistance < 10 - 30 || cDistance > 0){
	    						e.isScored = true;
	    						score += NORMAL_SCORE;
	    					} else {
	    						e.isScored = true;
	    						score += PERFECT_SCORE;
	    					}
	    				}
	    			}
    			}
    		} else {
        		if (cDistance > SCORE_BOUNDARY && !e.isScored){
        			e.isChecked = true;
        		}
    		}
    	}
    	panel.updateScore(score);
    	panel.repaint();
    }

    /**
     * This method will draw everything
     */
    ArrayList<Note> read(String filename){
    	ArrayList<String> temp = new ArrayList<String>();
    	
    	try (FileInputStream fis = new FileInputStream(filename)) {
    		InputStreamReader isr = new InputStreamReader(fis);
    		BufferedReader br = new BufferedReader(isr);
    		
    		while(true) {
    			temp.add(br.readLine().trim());
    		}
    	} catch (Exception e) {}
    	
    	StringTokenizer st = new StringTokenizer(temp.get(0), "#");
    	for (int i = 0; i < 2; i++) {
    		String tempStr = st.nextToken();
    		if (i == 0) {
    			timesig = Integer.parseInt(tempStr.trim());
    		} else if (i == 1) {
    			bpm = Integer.parseInt(tempStr.trim());
    		}
    	}
    	
    	ArrayList<Note> output = new ArrayList<Note>();
    	Note tempNote = new Note();
    	
    	for (int x = 1; x < temp.size(); x++) {
    		boolean longNote = false;
    		st = new StringTokenizer(temp.get(x), "#");
    		int measure = 0;
    		int beat = 0;
    		int position = 0;
    		int endbeat = 0;
    		int length = 0;
    		int startDistance = 0;
    		
    		final int STARTBUFFER = 1920;
    		
    		measure = Integer.parseInt(st.nextToken());
    		beat = Integer.parseInt(st.nextToken());
    		int time = ((measure - 1) * timesig * 8) + (beat+1);
    				
    		position = Integer.parseInt(st.nextToken());
    		if (st.hasMoreTokens()) {
    			longNote = true;
    			endbeat = Integer.parseInt(st.nextToken());
    			length = ((endbeat - beat));
    		}
    		
    		startDistance = time*30 + STARTBUFFER;
    		
    		if (longNote == true) {
    			tempNote = new Long (position, time, startDistance, length);
    		} else if (longNote == false) {
    			tempNote = new Short (position, time, startDistance);
    		}
    		
    		output.add(tempNote);    		
    	}
    	
    	return output;
    	
    }
    
    static boolean isRunning() {

        return true;
    }
}
