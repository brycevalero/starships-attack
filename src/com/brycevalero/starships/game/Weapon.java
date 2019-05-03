package com.brycevalero.starships.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class Weapon extends Particle {

	private boolean offscreen;

	public Weapon(Point coord) {
		super();
		diameter = 15;
		color = new Color(255, 255, 255);
		x = coord.x;
		y = coord.y;
		speedX = 0;
		speedY = -15;
		offscreen = false;
	}

	public Rectangle getBounds() {
		return new Rectangle(this.x, this.y, diameter, diameter);
	}

	public boolean isOffscreen() {
		return offscreen;
	}

	public void move() {
		this.x += speedX;
		this.y = ((int) (this.y + speedY));

		if (this.y < -100) {
			offscreen = true;
		}
	}

	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval((this.x - (diameter / 2)), (this.y - (diameter / 2)), diameter, diameter);
	}
}
