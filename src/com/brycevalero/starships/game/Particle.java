package com.brycevalero.starships.game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import com.brycevalero.starships.framework.Config;

public class Particle {

	protected Random random = new Random();
	protected int diameter;
	protected Color color;
	protected int x;
	protected int y;
	protected int speedX;
	protected double speedY;

	public Particle() {
		diameter = 0;
		color = new Color(0, 0, 0);
		x = 0;
		y = 0;
		speedX = 0;
		speedY = 0;
	}

	public int getDiameter() {
		return diameter;
	}

	public void setDiameter(int d) {
		diameter = d;
	}

	public void randomizeDiameter() {
		diameter = (random.nextInt(9) + 1);
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color c) {
		color = c;
	}

	public void randomizeColor() {
		int r = random.nextInt(255);
		int g = random.nextInt(255);
		int b = random.nextInt(255);
		color = new Color(r, g, b);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void randomizeX() {
		this.x = random.nextInt(Config.SCREEN.width);
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void randomizeY() {
		this.y = random.nextInt(Config.SCREEN.height);
	}

	public int getSpeedX() {
		return speedX;
	}

	public void setSpeedX(int dx) {
		speedX = dx;
	}

	public void randomizeSpeedX() {
		speedX = (random.nextInt(9) + 1);
	}

	public double getSpeedY() {
		return speedY;
	}

	public void setSpeedY(double dy) {
		speedY = dy;
	}

	public void randomizeSpeedY() {
		speedY = (random.nextInt(9) + 1);
	}

	public void move() {

	}

	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval(x, y, diameter, diameter);
	}
}
