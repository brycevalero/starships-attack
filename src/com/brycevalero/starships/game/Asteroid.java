package com.brycevalero.starships.game;

import java.awt.Dimension;
import java.util.Random;

import com.brycevalero.starships.framework.Config;

public class Asteroid extends Sprite {

	private Random random;

	public Asteroid(String img, int startx, int starty, Dimension bounds) {
		super(img, 0, 0, Config.SCREEN);

		random = new Random();

		x = random.nextInt(Config.SCREEN.width);
		y = (-100);
		setGravity();
	}

	public void setGravity() {
		// get an initial value
		gravity = random.nextInt(Config.MAX_ENEMY_SPEED) * .01;

		// we dont want enemy to be too slow, so lets make sure it doesnt stop
		if (gravity < .05) {
			gravity = .05;
		}
	}

	@Override
	public void move() {
		x += dx;
		y += dy;

		dx *= friction;
		dy *= friction;
		dy += gravity;

		if (dx > maxspeed) {
			dx = maxspeed;
		} else if (dx < -maxspeed)
			dx = (-maxspeed);
		if (dy > maxspeed) {
			dy = maxspeed;
		} else if (dy < -maxspeed) {
			dy = (-maxspeed);
		}

		checkBounds();

	}

	public void randomImage() {

	}

	@Override
	public void checkBounds() {
		if (y > Config.SCREEN.height) {
			x = random.nextInt(Config.SCREEN.width);
			y = (-100);
			setGravity();
		}
	}

}
