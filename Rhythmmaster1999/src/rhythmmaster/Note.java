package rhythmmaster;

public class Note {
    
    int keyPos;
    int time;
    int distanceFromTop;
    
    public Note (){
    	keyPos = 0;
    	time = 0;
    	distanceFromTop = 0;
    }
    
    public Note (int keyPos, int time){
        this.keyPos = keyPos;
        this.time = time;
        distanceFromTop = 0;
    }
    
}
