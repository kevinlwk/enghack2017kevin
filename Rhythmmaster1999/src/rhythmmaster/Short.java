package rhythmmaster;

public class Short extends Note{
	
	boolean checked;
	
	public Short (int keyPos, int time, int distanceFromLine){
		super(keyPos, time, distanceFromLine);
		checked = false;
	}
	
	void changeChecked(){
		checked = true;
	}
	
}
