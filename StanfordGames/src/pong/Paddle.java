package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Paddle {
	
	public int paddleNumber;
	
	public int x, y, width = 30, height = 200, score;
	
	public Paddle(Pong pong, int paddleNumber) {
		this.paddleNumber = paddleNumber;
		
		if(paddleNumber == 1) {
			this.x = 0;
		} 
		if(paddleNumber == 2) {
			this.x = pong.width - width;
		}
		this.y = pong.height / 2 - this.height / 2;
	}
	public void render(Graphics2D g) {
		// TODO Auto-generated method stub
		g.setColor(Color.WHITE);
		g.fillRect(x, y, width, height);
	}
	public void move(boolean up) {
		// TODO Auto-generated method stub
		int gap = 20, speed = 10;
		if(up) {
			if(y > 0) {
				y -= speed;
			} else {
				y = 0;
			}
		} else {
			if(y+height+gap < Pong.pong.height) {
				y += speed;
			} else {
				y = Pong.pong.height - height - gap;
			}
		}
	}
}
