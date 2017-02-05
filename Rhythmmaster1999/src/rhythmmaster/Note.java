package rhythmmaster;

public class Note {
    
    double speed;
    double time;
    
    public Note (){
    	speed = 0;
    	time = 0;
    }
    
    public Note (double speed, double time, boolean checked){
        this.speed = speed;
        this.time = time;
    }
    
}
