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