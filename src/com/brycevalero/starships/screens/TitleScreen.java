package com.brycevalero.starships.screens;

import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.brycevalero.starships.framework.Config;
import com.brycevalero.starships.game.Hero;
import com.brycevalero.starships.sound.Music;

@SuppressWarnings("serial")
public class TitleScreen extends JPanel {

	private Music themeSong;
	private Hero hero;

	public static enum State {
		PLAY, PAUSE, GAMEOVER, DESTROYED
	}

	public static State state;

	public TitleScreen() {
		initTitleScreen();
	}

	public void initTitleScreen() {

		setFocusable(true);
		setBackground(Color.BLUE);

		ImageIcon heroIcon = new ImageIcon("images/hero.png");
		hero = new Hero(heroIcon, 50, 100, Config.SCREEN);

		themeSong = new Music("sound/TechnoWarmup.wav");
		themeSong.loop(100);
		themeSong.play();
	}

	public void loop() {
		switch (state) {
		case PLAY:
			break;
		case PAUSE:
			// ...
			break;
		case GAMEOVER:
			// ...
			break;
		case DESTROYED:
			// ...
			break;
		}
	}

	public void draw(Graphics2D g2d) {
		hero.move();
		g2d.drawImage(hero.getImage(), hero.getX(), hero.getY(), this);
		// return;
	}

}
