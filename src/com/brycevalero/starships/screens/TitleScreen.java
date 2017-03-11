package com.brycevalero.starships.screens;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

import com.brycevalero.starships.framework.Config;
import com.brycevalero.starships.sound.Music;

@SuppressWarnings("serial")
public class TitleScreen extends JPanel {

	private Music themeSong;
	private Image title;

	public static enum State {
		ACTIVE, IDLE
	}

	public static State state;

	public TitleScreen() {
		initTitleScreen();
	}

	public void initTitleScreen() {

		setFocusable(true);
		setBackground(Color.BLUE);
		title = Toolkit.getDefaultToolkit().getImage("images/title.png");

		themeSong = new Music("sound/TechnoWarmup.wav");
		state = State.IDLE;

	}

	public void loop() {
		switch (state) {
		case ACTIVE:
			themeSong.loop(100);
			themeSong.play();
			break;
		case IDLE:
			themeSong.stop();
			break;
		}
	}

	public void draw(Graphics2D g2d) {
		int x = (Config.SCREEN.width - title.getWidth(null)) / 2;
		int y = (Config.SCREEN.height - title.getHeight(null)) / 2;

		g2d.drawImage(title, x, y, this);
	}

}
