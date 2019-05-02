package com.brycevalero.starships.game;

import com.brycevalero.starships.framework.Config;

public class Star extends Particle {

	public Star() {
		super();

		this.randomizeDiameter();
		this.randomizeColor();
		this.randomizeX();
		this.randomizeY();
		speedX = 0;
		this.randomizeSpeedY();
	}

	public void move() {
		x += speedX;
		y = ((int) (y + speedY));

		if (y > Config.SCREEN.height) {
			y = -20;
			this.randomizeDiameter();
			this.randomizeColor();
			this.randomizeX();
			this.randomizeSpeedY();
		}
	}
}
