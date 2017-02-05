package rhythmmaster;

public class Short extends Note{
	
	boolean checked;
	
	public Short (int keyPos, int time){
		super(keyPos, time);
		checked = false;
	}
	
	void changeChecked(){
		checked = true;
	}
	
}
