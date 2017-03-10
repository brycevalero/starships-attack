package com.brycevalero.starships.game;

import java.awt.Color;
import java.awt.Graphics;

public class Thrust {
	private int thrustDiameter;
	private Color thrustColor;
	private int thrustPositionX;
	private int thrustPositionY;
	private int thrustSpeedX;
	private double thrustSpeedY;

	public Thrust() {
		thrustDiameter = 20;
		thrustColor = new Color(255, 0, 0);
		thrustPositionX = -20;
		thrustPositionY = -20;
		thrustSpeedX = 3;
		thrustSpeedY = 10.0D;
	}

	public int getDiameter() {
		return thrustDiameter;
	}

	public Color getColor() {
		return thrustColor;
	}

	public int getX() {
		return thrustPositionX;
	}

	public int getY() {
		return thrustPositionY;
	}

	public int getSpeedX() {
		return thrustSpeedX;
	}

	public double getSpeedY() {
		return thrustSpeedY;
	}

	public void setDiameter(int d) {
		thrustDiameter = d;
	}

	public void setColor(Color c) {
		thrustColor = c;
	}

	public void setX(int x) {
		thrustPositionX = x;
	}

	public void setY(int y) {
		thrustPositionY = y;
	}

	public void setSpeedX(int dx) {
		thrustSpeedX = dx;
	}

	public void setSpeedY(double dy) {
		thrustSpeedY = dy;
	}

	public void move(int x, int y) {
		thrustPositionX += thrustSpeedX;
		thrustPositionY = ((int) (thrustPositionY + thrustSpeedY));
		thrustDiameter -= 5;

		if (thrustDiameter <= 0) {
			thrustPositionY = y;
			thrustPositionX = x;
			thrustDiameter = 20;
		}
	}

	public void draw(Graphics g) {
		g.setColor(thrustColor);
		g.fillOval(thrustPositionX, thrustPositionY, thrustDiameter, thrustDiameter);
	}
}
