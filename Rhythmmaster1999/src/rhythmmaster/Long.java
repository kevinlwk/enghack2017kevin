package rhythmmaster;

public class Long extends Note{
	
	double speed;
	double time;
	int length;
	boolean startChecked;
	boolean endChecked;
	
	public Long (int length){
		speed = super.speed;
		time = super.time;
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
