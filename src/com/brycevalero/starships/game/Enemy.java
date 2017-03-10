package com.brycevalero.starships.game;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;

import com.brycevalero.starships.framework.Config;

public class Enemy extends Sprite {

	private Random random;

	public Enemy(ImageIcon img, int startx, int starty, Dimension bounds) {
		super(img, startx, starty, bounds);

		random = new Random();

		x = random.nextInt(Config.SCREEN.width);
		y = (-100);
		setGravity();
	}

	public void draw(Graphics2D g2d) {
		// g2d.drawImage(this.getImage(), (int)this.getX(), (int)this.getY(),
		// this);
	}

	public void setGravity() {
		// get an initial value
		gravity = random.nextInt(Config.MAX_ENEMY_SPEED) * .01;

		// we dont want enemy to be too slow, so lets make sure it doesnt stop
		while (gravity < .05) {
			gravity = random.nextInt(Config.MAX_ENEMY_SPEED) * .01;
		}
	}

	@Override
	public void checkBounds() {
		if (y > Config.SCREEN.height) {
			x = random.nextInt(Config.SCREEN.width);
			y = (-100);
			setGravity(); // give a new value just so things don't get stale
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

	public Rectangle[] getBounds() {
		Rectangle[] bounds = new Rectangle[2];

		// return a 30 x 100 rectangle for fuselage
		bounds[0] = new Rectangle((int) (x + 35), (int) y, 30, 100);

		// return a 100 x 15 rectangle for wings
		bounds[1] = new Rectangle((int) x, (int) (y + 30), 100, 15);

		return bounds;
	}

}
