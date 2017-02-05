package ui;

import java.util.ArrayList;

import rhythmmaster.Note;

public class BouncyBall {
	public int x;
	public int y;
	public int dx;
	public int dy;
	public int size;
	
	public BouncyBall(int x, int y, int dx, int dy, int size){
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
		this.size = size;
	}
	
	public void ChangePosition(){
		//Change for x
		x = x + dx;
		//Left bounce
		if (x < 0){
			dx = -dx;
			x = 0 - x;
		}
		if (x + size > PianoPanel.SCREEN_LENGTH){
			dx = -dx;
			x = 2 * PianoPanel.SCREEN_LENGTH - (x + size) - size;
		}
		//Change for y
		y = y + dy;
		//Top bounce
		if (y < 0){
			dy = -dy;
			y = 0 - y;
		}
	}
	
	public void checkCollision(ArrayList<Note> notes, int frames, Paddle paddle){
		for (Note n : notes){
			if (!n.isHit && !n.isScored){
				int nx = n.keyPos * 100;
				int ny = PianoPanel.LINE_HEIGHT - n.distanceFromLine + 9 * frames;
				int nWidth = 100;
				int nHeight = 45;
				boolean xIntersect = (nx - x) < size && (x < nx + nWidth);
				boolean yIntersect = (ny - y) < size && (y < ny + nHeight);
				boolean xInside = xIntersect && x > nx && x + size < nx + nWidth;
				boolean yInside = yIntersect && y > ny && y + size < ny + nHeight;
				if (xIntersect && yIntersect){
					n.isHit = true;
					if (xInside){
						if (yInside){
							dx = -dx;
							dy = -dy;
							x += dx;
							y += dy;
						} else {
							dy = -dy;
							y += dy;
						}
					}
					else {
						if (yInside){
							dx = -dx;
							x += dx;
						} else {
							dx = -dx;
							dy = -dy;
							x += dx;
							y += dy;
						}
					}
				}
			}
			//Otherwise no collision
			//Check collision for paddle
			int px = paddle.x;
			int py = PianoPanel.LINE_HEIGHT;
			int pWidth = Paddle.PADDLE_SIZE;
			int pHeight = PianoPanel.LINE_SIZE;
			boolean xIntersect = (px - x) < size && (x < px + pWidth);
			boolean yIntersect = (py - y) < size && (y < py + pHeight);
			if (xIntersect && yIntersect){
				dy = -Math.abs(dy);
			}
		}
	}
}
