package com.brycevalero.starships.game;

import java.awt.Color;

public class Thrust extends Particle {

	public Thrust() {
		super();
		diameter = 20;
		color = new Color(255, 0, 0);
		x = -20;
		y = -20;
		speedX = 3;
		speedY = 10.0D;
	}

	public void move(int x, int y) {
		this.x += speedX;
		this.y = ((int) (this.y + speedY));
		diameter -= 5;

		if (diameter <= 0) {
			this.y = y;
			this.x = x;
			diameter = 20;
		}
	}
}
