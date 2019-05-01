package com.brycevalero.starships.game;

import java.awt.Dimension;
import java.awt.Rectangle;

import com.brycevalero.starships.framework.Config;

@SuppressWarnings("serial")
public class Hero extends Sprite {

	private boolean invincible;

	public Hero(String img, int startx, int starty, Dimension bounds) {
		super(img, startx, starty, bounds);
	}

	public void moveleft(boolean moving) {
		moveleft = moving;
	}

	public void moveright(boolean moving) {
		moveright = moving;
	}

	public void moveup(boolean moving) {
		moveup = moving;
	}

	public void movedown(boolean moving) {
		movedown = moving;
	}

	public void gravity() {
		if (grounded) {
			gravity = 0.0;
		} else {
			gravity = 0.3;
		}
	}

	@Override
	public void checkBounds() {
		if (x < 0.0)
			x = 0.0;
		if (x == 0.0)
			dx *= -1.0;
		if (x > screenSize.width - width)
			x = (screenSize.width - width);
		if (x == screenSize.width - width) {
			dx *= -1.0;
		}

		if (y < -height)
			y = (-height);
		if (y == -height)
			dy *= -1.0;
		if (y > screenSize.height - height)
			y = (screenSize.height - height);
		if (y == screenSize.height - height) {
			grounded = true;
		} else {
			grounded = false;
		}
		gravity();
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

		if (moveup)
			dy -= speed;
		if (movedown)
			dy += speed;
		if (moveleft)
			dx -= speed;
		if (moveright) {
			dx += speed;
		}
	}

	public Rectangle getBounds() {
		Rectangle[] bounds = new Rectangle[2];

		// return a 20 x 100 rectangle for fuselage
		bounds[0] = new Rectangle((int) (x + 40), (int) y, 20, 100);

		// return a 100 x 20 rectangle for wings
		bounds[1] = new Rectangle((int) x, (int) (y + 70), 100, 20);

		return bounds[0];
	}

	public void respawn() {
		this.x = Config.SCREEN.width / 2 - this.width - 2;
		this.y = (-100);
	}

	public void setInvincible(boolean state) {
		invincible = state;
	}

	public boolean isInvincible() {
		return invincible;
	}

}
