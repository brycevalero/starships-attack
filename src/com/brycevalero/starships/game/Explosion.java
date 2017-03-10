package com.brycevalero.starships.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

public class Explosion {
	private int frame = 10;
	private int y = 0;
	private int x = 0;
	private Image explosionG;
	private Image explosionF;
	private Image explosionE;

	public Explosion() {
		URL urlA = getClass().getResource("images/ex1.png");
		URL urlB = getClass().getResource("images/ex2.png");
		URL urlC = getClass().getResource("images/ex3.png");
		URL urlD = getClass().getResource("images/ex4.png");
		URL urlE = getClass().getResource("images/ex5.png");
		URL urlF = getClass().getResource("images/ex6.png");
		URL urlG = getClass().getResource("images/ex7.png");
		explosionA = Toolkit.getDefaultToolkit().getImage(urlA);
		explosionB = Toolkit.getDefaultToolkit().getImage(urlB);
		explosionC = Toolkit.getDefaultToolkit().getImage(urlC);
		explosionD = Toolkit.getDefaultToolkit().getImage(urlD);
		explosionE = Toolkit.getDefaultToolkit().getImage(urlE);
		explosionF = Toolkit.getDefaultToolkit().getImage(urlF);
		explosionG = Toolkit.getDefaultToolkit().getImage(urlG);
	}

	private Image explosionD;
	private Image explosionC;
	private Image explosionB;
	private Image explosionA;

	public void draw(Graphics g) {
		if ((frame >= 0) && (frame <= 2)) {
			g.drawImage(explosionA, x, y, null);
		} else if ((frame >= 3) && (frame <= 5)) {
			g.drawImage(explosionB, x, y, null);
		} else if ((frame >= 6) && (frame <= 8)) {
			g.drawImage(explosionC, x, y, null);
		} else if ((frame >= 9) && (frame <= 11)) {
			g.drawImage(explosionD, x, y, null);
		} else if ((frame >= 12) && (frame <= 14)) {
			g.drawImage(explosionE, x, y, null);
		} else if ((frame >= 15) && (frame <= 17)) {
			g.drawImage(explosionF, x, y, null);
		} else if ((frame >= 18) && (frame <= 20))
			g.drawImage(explosionG, x, y, null);
		frame += 1;
		if (frame >= 3500) {
			frame = 10;
		}
	}

	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
		frame = 0;
	}
}
