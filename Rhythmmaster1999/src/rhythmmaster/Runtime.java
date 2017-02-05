package rhythmmaster;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import javax.swing.JFrame;
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

import static javax.swing.JFrame.EXIT_ON_CLOSE;
import static rhythmmaster.Rhythmmaster1999.kevin;

public class Runtime extends JFrame {

    int tempo;
    int mpb;
    int x;
    int timesig;
    int bpm;
    
    public Runtime(int tempo) {
        this.tempo = tempo;
        mpb = Math.round(60*1000/tempo);
        x = 0;
    }
    
    void run() {
        
        initialize();
        
        ArrayList<Note> test = new ArrayList<Note>();
        test = read("twinkle twinkle little star.txt");
        
        while (isRunning()) {

            long time = System.currentTimeMillis();

            update();
            draw();

            x++;
            time = (1000 / tempo) - (System.currentTimeMillis() - time);

            if (time > 0) {
                try {
                    Thread.sleep(time);
                } catch (Exception e) {
                }
            }
        }
        kevin.setVisible(false);
    }

    /**
     * This method will set up everything need for the game to run
     */
    static void initialize() {

        kevin.setTitle("Rhythmmaster");
        kevin.setSize(C.SCREENWIDTH, C.SCREENHEIGHT);
        kevin.setResizable(false);
        kevin.setDefaultCloseOperation(EXIT_ON_CLOSE);
        kevin.setVisible(true);
    }

    /**
     * This method will check for input move things around and check for win
     * conditions, etc
     */
    void update() {

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
    	for (int i = 0; i < st.countTokens(); i++) {
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
    		
    		measure = Integer.parseInt(st.nextToken());
    		beat = Integer.parseInt(st.nextToken());
    		int time = ((measure - 1) * timesig * 8) + (beat+1);
    				
    		position = Integer.parseInt(st.nextToken());
    		if (st.hasMoreTokens()) {
    			longNote = true;
    			endbeat = Integer.parseInt(st.nextToken());
    			length = ((endbeat - beat));
    		}
    		
    		if (longNote == true) {
    			tempNote = new Long (position, time, length);
    		} else if (longNote == false) {
    			tempNote = new Short (position, time);
    		}
    		
    		output.add(tempNote);    		
    	}
    	
    	return output;
    	
    }
    
    void draw() {

        BufferedImage backBuffer = new BufferedImage(C.SCREENWIDTH, C.SCREENHEIGHT, BufferedImage.TYPE_INT_RGB);

        Graphics g = kevin.getGraphics();
        Graphics bbg = backBuffer.getGraphics();
        
        bbg.setColor(Color.WHITE);
        bbg.fillRect(0, 0, C.SCREENWIDTH, C.SCREENHEIGHT);

        bbg.setColor(Color.BLACK);
        bbg.fillRect(C.SCREENWIDTH/2-100, x-100, C.NOTEWIDTH, C.NOTEHEIGHT);
        
        g.drawImage(backBuffer, 0, 0, this);
    }

    static boolean isRunning() {

        return true;
    }
}
