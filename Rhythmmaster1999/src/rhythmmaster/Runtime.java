package rhythmmaster;

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

    int tempo;
    int mpb;
    int timesig;
    int bpm;
    
    PianoPanel panel;
    
    ArrayList<Note> test;
    
    public void run() {
        
        initialize();
        
        test = new ArrayList<Note>();
        test = read("twinkle twinkle little star.txt");
        
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
    void initialize() {

		JFrame window = new JFrame("Rhythmmaster1999");
		panel = new PianoPanel();
		window.setContentPane(panel);
		window.setSize(PianoPanel.SCREEN_LENGTH, PianoPanel.SCREEN_HEIHGT);
		window.setResizable(false);
		window.setVisible(true);
    }

    /**
     * This method will check for input move things around and check for win
     * conditions, etc
     */
    void update() {
    	
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
    		
    		startDistance = (beat*10) + STARTBUFFER;
    		
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
