package com.brycevalero.starships.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import javax.swing.JPanel;

/*
 * Dashboard class uses a classic singleton pattern, 
 * this ensures that the same instance is always returned when creating objects.
 */
@SuppressWarnings("serial")
public class Dashboard extends JPanel {

	private static Dashboard instance = null;
	public int score;
	public int level;
	public int lives;
	public int x;
	public int y;

	private Dashboard() {
		score = 0;
		level = 1;
		lives = 3;
	}

	public static Dashboard getInstance() {
		if (instance == null) {
			instance = new Dashboard();
		}
		return instance;
	}

	public void draw(Graphics2D g2d) {
		g2d.setColor(Color.GREEN);
		g2d.setFont(new Font("SansSerif", 0, 18));

		g2d.drawString("Score: " + score, 5, 20);
		g2d.drawString("Level: " + level, 5, 40);
		g2d.drawString("Lives: " + lives, 5, 60);
	}
}

/*
 * bufferGraphics.setColor(Color.GREEN); bufferGraphics.setFont(new
 * Font("SansSerif", 0, 18)); bufferGraphics.drawString("Score: " +
 * Integer.toString(score), 5, 20); bufferGraphics.drawString("Lives: " +
 * Integer.toString(lives), 5, 40); bufferGraphics.drawString("Level: " +
 * Integer.toString(level), dimension.width - 90, 20);
 * 
 * bufferGraphics.setColor(Color.DARK_GRAY); bufferGraphics.setFont(new
 * Font("SansSerif", 0, 12)); bufferGraphics.drawString("X = " +
 * Integer.toString(mouseX), 20, dimension.height - 60);
 * bufferGraphics.drawString("Y = " + Integer.toString(mouseY), 20,
 * dimension.height - 48); bufferGraphics.drawString("Width = " +
 * Integer.toString(dimension.width), 20, dimension.height - 36);
 * bufferGraphics.drawString("Height = " + Integer.toString(dimension.height),
 * 20, dimension.height - 24);
 * bufferGraphics.drawString("Use Arrow Keys to Move and Space Bar to Shoot!!",
 * 20, dimension.height - 12);
 */