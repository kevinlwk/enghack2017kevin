package rhythmmaster;

public class Note {
    
    public int keyPos;
    public int time;
    public int distanceFromLine;
    public boolean isScored;
    public boolean isChecked;
    public boolean isHit;
    public boolean missed;
    
    public static final int SHORT_NOTE_SIZE = 45;
    
    public Note (){
    	keyPos = 0;
    	time = 0;
    	distanceFromLine = 0;
    }
    
    public Note (int keyPos, int time, int distanceFromLine){
        this.keyPos = keyPos;
        this.time = time;
        this.distanceFromLine = distanceFromLine;
    }
    
}
