package com.brycevalero.starships.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class Weapon {

	private int diameter;
	private Color color;
	private Point position;
	private int speed;
	private boolean offscreen;
	private boolean fired;

	public Weapon(Point coord) {
		diameter = 15;
		color = new Color(255, 255, 255);
		position = coord;
		speed = 15;
		offscreen = false;
		fired = false;
	}

	public void checkBoundries() {
		if (position.y < -100) {
			offscreen = true;
		}
	}

	public Rectangle getBounds() {
		return new Rectangle(position.x, position.y, diameter, diameter);
	}

	public Point getPosition() {
		return position;
	}

	public boolean isOffscreen() {
		return offscreen;
	}

	public void move() {
		position.y = (position.y - speed);

		checkBoundries();
	}

	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval((position.x - (diameter / 2)), (position.y - (diameter / 2)), diameter, diameter);
	}
}
