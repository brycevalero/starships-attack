package com.brycevalero.starships.game;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class Sprite extends JPanel {

	protected Dimension screenSize;
	protected double dx;
	protected double dy;
	protected double x;
	protected double y;
	protected Image image;
	protected double width;
	protected double height;

	protected double speed;
	protected double maxspeed;
	protected double friction;
	protected double gravity;

	protected boolean moveup;
	protected boolean moveleft;
	protected boolean movedown;
	protected boolean moveright;

	protected boolean grounded;
	protected boolean collision;

	public Sprite(String img, int startx, int starty, Dimension bounds) {

		screenSize = bounds;
		image = Toolkit.getDefaultToolkit().getImage(img);
		width = 100;
		height = 100;
		x = startx;
		y = starty;

		dx = 0.0;
		dy = 0.0;
		speed = 1.0;
		maxspeed = 8.0;
		friction = 0.98;

		moveup = false;
		movedown = false;
		moveleft = false;
		moveright = false;

		grounded = false;
		collision = false;
	}

	public abstract void move();

	public abstract void checkBounds();

	public int getX() {
		return (int) x;
	}

	public int getY() {
		return (int) y;
	}

	public Image getImage() {
		return image;
	}

	public Point getCenter() {
		int centerx = (int) (x + 50);
		int centery = (int) (y + 50);
		Point center = new Point(centerx, centery);
		return center;
	}

	public void draw(Graphics2D g2d) {
		g2d.drawImage(image, (int) x, (int) y, this);
	}

}
