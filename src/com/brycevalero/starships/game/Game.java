package com.brycevalero.starships.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import com.brycevalero.starships.framework.Config;
import com.brycevalero.starships.sound.Music;
import com.brycevalero.starships.sound.SoundFX;

@SuppressWarnings("serial")
public class Game extends JPanel {

	private Hero hero;
	private Star[] star;
	private List<Enemy> enemies;
	private List<Weapon> ammo;
	private List<Explosion> explosions;
	private Dashboard dashboard;
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

		// ImageIcon heroIcon = new ImageIcon("images/hero.png");

		hero = new Hero("images/hero.png", 50, 100, Config.SCREEN);
		enemies = new ArrayList<Enemy>();
		ammo = new ArrayList<Weapon>();
		explosions = new ArrayList<Explosion>();

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

		dashboard = Dashboard.getInstance();

		themeSong = new Music("sound/TechnoWarmup.wav");
		themeSong.loop(100);
		// themeSong.play();

		state = State.PLAY;
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
		// ImageIcon enemyIcon = new ImageIcon("images/enemy.png");
		enemies.add(new Enemy("images/enemy.png", 0, 0, Config.SCREEN));
	}

	public void removeEnemy() {
		if (!enemies.isEmpty()) {
			enemies.remove(enemies.size() - 1);
		}
	}

	public void fireAmmo() {
		ammo.add(new Weapon(hero.getCenter()));
	}

	public void enemyCollision(Point position) {
		explosions.add(new Explosion(position));
		SoundFX.play("sound/explode.wav");
		dashboard.score += 5;
	}

	public void heroCollision(Point position) {
		explosions.add(new Explosion(position));
		SoundFX.play("sound/explode2.wav");
		dashboard.lives--;
	}

	public void checkCollision() {
		for (int i = 0; i < enemies.size(); i++) {
			for (int j = 0; j < ammo.size(); j++) {
				if (ammo.get(j).getBounds().intersects(enemies.get(i).getBounds())) {
					enemyCollision(enemies.get(i).getPosition());
					ammo.remove(j);
					enemies.remove(i);
				}
			}
		}

		// we have to loop through enemies again in case the size of list has
		// changed
		for (int i = 0; i < enemies.size(); i++) {
			if (hero.getBounds().intersects(enemies.get(i).getBounds())) {
				enemyCollision(enemies.get(i).getPosition());
				heroCollision(enemies.get(i).getPosition());
				enemies.remove(i);
				hero.respawn();
			}
		}

		for (int i = 0; i < ammo.size(); i++) {
			if (ammo.get(i).isOffscreen()) {
				ammo.remove(i);
			}
		}

		for (int i = 0; i < explosions.size(); i++) {
			if (explosions.get(i).state == Explosion.State.FINISHED) {
				explosions.remove(i);
			}
		}
	}

	public void move() {

		if (state == State.PAUSE) {
			return;
		}

		for (int i = 0; i < star.length; i++) {
			star[i].move();
		}

		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).move();
		}

		for (int i = 0; i < ammo.size(); i++) {
			ammo.get(i).move();
		}

		for (int i = 0; i < explosions.size(); i++) {
			// explosions dont move yet
		}

		hero.move();

		// dont check collisions until all objects have been moved
		checkCollision();
	}

	/**
	 * Draw the game to the screen. Things in the background should be drawn
	 * first
	 * 
	 * @param g2d
	 *            Graphics2D
	 */
	public void draw(Graphics2D g2d) {

		move();

		for (int i = 0; i < star.length; i++) {
			star[i].draw(g2d);
		}

		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).draw(g2d);
		}

		for (int i = 0; i < ammo.size(); i++) {
			ammo.get(i).draw(g2d);
		}

		for (int i = 0; i < explosions.size(); i++) {
			explosions.get(i).draw(g2d);
		}

		hero.draw(g2d);
		dashboard.draw(g2d);
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT) {
			hero.moveleft(true);
		}
		if (key == KeyEvent.VK_RIGHT) {
			hero.moveright(true);
		}
		if (key == KeyEvent.VK_UP) {
			hero.moveup(true);
		}
		if (key == KeyEvent.VK_DOWN) {
			hero.movedown(true);
		}
		if (key == KeyEvent.VK_SPACE) {
			fireAmmo();
			SoundFX.play("sound/Gun_Silencer.wav");
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT) {
			hero.moveleft(false);
		}
		if (key == KeyEvent.VK_RIGHT) {
			hero.moveright(false);
		}
		if (key == KeyEvent.VK_UP) {
			hero.moveup(false);
		}
		if (key == KeyEvent.VK_DOWN) {
			hero.movedown(false);
		}
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
