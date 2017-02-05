package rhythmmaster;

public class Long extends Note{

	int length;
	boolean startChecked;
	boolean endChecked;
	
	public Long (int keyPos, int StartTime, int distanceFromLine, int length){
		super(keyPos, StartTime, distanceFromLine);
		this.length = length;
		startChecked = false;
		endChecked = false;
	}
	
	void changeStartChecked(){
		startChecked = true;
	}
	
	void changeEndChecked(){
		endChecked = false;
	}
}
