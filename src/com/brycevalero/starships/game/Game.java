package com.brycevalero.starships.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.brycevalero.starships.framework.Config;
import com.brycevalero.starships.sound.Music;
import com.brycevalero.starships.sound.SoundFX;

@SuppressWarnings("serial")
public class Game extends JPanel {

	private Hero hero;
	private Star[] star;
	List<Enemy> enemies;
	List<Weapon> ammo;
	private Music themeSong;
	private int enemyCount;

	public static enum State {
		PLAY, PAUSE, GAMEOVER, DESTROYED
	}

	public static State state;

	public Game() {
		initGame();
	}

	public void initGame() {

		int numOfStars = Config.SCREEN.width / 10;
		setFocusable(true);
		setBackground(Color.BLACK);

		ImageIcon heroIcon = new ImageIcon("images/hero.png");

		hero = new Hero(heroIcon, 50, 100, Config.SCREEN);
		enemies = new ArrayList<Enemy>();
		ammo = new ArrayList<Weapon>();

		star = new Star[numOfStars];
		for (int i = 0; i < numOfStars; i++) {
			star[i] = new Star(Config.SCREEN.width, Config.SCREEN.height);
		}

		enemyCount = 5;
		if (enemyCount > Config.MAX_ENEMIES) {
			enemyCount = Config.MAX_ENEMIES;
		}
		for (int i = 0; i < enemyCount; i++) {
			addEnemy();
		}

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

	public void addEnemy() {
		ImageIcon enemyIcon = new ImageIcon("images/enemy.png");
		enemies.add(new Enemy(enemyIcon, 0, 0, Config.SCREEN));
	}

	public void removeEnemy() {
		if (!enemies.isEmpty()) {
			enemies.remove(enemies.size() - 1);
		}
	}

	public void fireAmmo() {
		ammo.add(new Weapon(hero.getCenter()));
	}

	/**
	 * Draw the game to the screen. Things in the background should be drawn
	 * first
	 * 
	 * @param g2d
	 *            Graphics2D
	 */
	public void draw(Graphics2D g2d) {
		for (int i = 0; i < star.length; i++) {
			star[i].move();
			star[i].draw(g2d);
		}

		for (int i = 0; i < enemies.size(); i++) {
			Enemy enemy = enemies.get(i);
			enemy.move();

			if (hero.getBounds()[0].intersects(enemy.getBounds()[0])) {
				System.out.println("collision");
				enemies.remove(i);
				SoundFX.play("sound/explode.wav");
			}

			g2d.drawImage(enemy.getImage(), enemy.getX(), enemy.getY(), this);
		}

		for (int i = 0; i < ammo.size(); i++) {
			Weapon ammos = ammo.get(i);
			ammos.move();
			if (ammos.isOffscreen()) {
				ammo.remove(i);
			}
			ammos.draw(g2d);
		}

		hero.move();
		g2d.drawImage(hero.getImage(), hero.getX(), hero.getY(), this);
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		hero.keyPressed(e);

		int key = e.getKeyCode();
		if (key == KeyEvent.VK_SPACE) {
			fireAmmo();
			SoundFX.play("sound/Gun_Silencer.wav");
		}
	}

	public void keyReleased(KeyEvent e) {
		hero.keyReleased(e);
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
