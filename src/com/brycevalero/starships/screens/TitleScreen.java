package com.brycevalero.starships.screens;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.brycevalero.starships.framework.Config;
import com.brycevalero.starships.sound.Music;

@SuppressWarnings("serial")
public class TitleScreen extends JPanel {

	private Music themeSong;
	private Image background;
	private Image title;

	private int bx;
	private int by;
	private int tx;
	private int ty;

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

		URL url = TitleScreen.class.getResource("/images/title.png");
		title = new ImageIcon(url).getImage();

		url = TitleScreen.class.getResource("/images/universe-background-1.jpg");
		background = new ImageIcon(url).getImage();

		themeSong = new Music("/sound/TechnoWarmup.wav");
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
		// by++;
		bx = (Config.SCREEN.width - background.getWidth(null)) / 2;
		by = (Config.SCREEN.height - background.getHeight(null)) / 2;
		tx = (Config.SCREEN.width - title.getWidth(null)) / 2;
		ty = (Config.SCREEN.height - title.getHeight(null)) / 2;

		g2d.drawImage(background, bx, by, this);
		g2d.drawImage(title, tx, ty, this);
	}

}
