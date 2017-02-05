package rhythmmaster;

public class Short extends Note{
	
	double speed;
	double time;
	boolean checked;
	
	public Short (){
		speed = super.speed;
		time = super.time;
		checked = false;
	}
	
	void changeChecked(){
		checked = true;
	}
	
}
