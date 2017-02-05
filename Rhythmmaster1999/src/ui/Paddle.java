package ui;

public class Paddle {
	public static int PADDLE_SIZE = 100;
	public static int SPEED = 30;
	
	int x;
	
	public Paddle (int x){
		this.x = x;
	}
	
	public void move(boolean movingLeft){
		if(movingLeft){
			if (x - SPEED < 0){
				x = 0;
			} else {
				x = x - SPEED;
			}
		} else {
			if (x + SPEED + PADDLE_SIZE > PianoPanel.SCREEN_LENGTH){
				x = PianoPanel.SCREEN_LENGTH - PADDLE_SIZE;
			} else {
				x = x + SPEED;
			}
		}
	}
}
