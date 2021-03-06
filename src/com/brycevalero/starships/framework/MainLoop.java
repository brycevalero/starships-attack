package com.brycevalero.starships.framework;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.brycevalero.starships.game.Game;
import com.brycevalero.starships.screens.TitleScreen;

/**
 * Framework that controls the game (Game.java) that created it, update it and
 * draw it on the screen.
 * 
 * @author www.gametutorial.net
 */

@SuppressWarnings("serial")
public class MainLoop extends Canvas implements KeyListener {

	public static int frameWidth;
	public static int frameHeight;

	/**
	 * Time of one second in nanoseconds. 1 second = 1 000 000 000 nanoseconds
	 */
	public static final long secInNanosec = 1000000000L;

	/**
	 * Time of one millisecond in nanoseconds. 1 millisecond = 1 000 000
	 * nanoseconds
	 */
	public static final long milisecInNanosec = 1000000L;
	private final int GAME_FPS = 60;

	// Pause between updates. It is in nanoseconds.
	private final long GAME_UPDATE_PERIOD = secInNanosec / GAME_FPS;

	// Possible states of the game
	public static enum MainState {
		VISUALIZING, INITIALIZING, TITLE_SCREEN, GAME_PLAY
	}

	// Current state of the game
	public static MainState mainState;
	// Elapsed game time in nanoseconds.
	private long gameTime;
	// It is used for calculating elapsed time.
	private long lastTime;
	// main title screen
	private TitleScreen titleScreen;
	// The actual game
	private Game game;

	public MainLoop() {
		super();

		// Adds the keyboard listener to JPanel.
		this.addKeyListener(this);

		mainState = MainState.VISUALIZING;

		// We start game in new thread.
		Thread gameThread = new Thread() {
			@Override
			public void run() {
				mainLoop();
			}
		};
		gameThread.start();
	}

	/**
	 * Set variables and objects. This method is intended to set the variables
	 * and objects for this class, variables and objects for the actual game can
	 * be set in Game.java.
	 */
	private void Initialize() {
		newTitleScreen();
		newGame();
	}

	/**
	 * Load files - images, sounds, ... This method is intended to load files
	 * for this class, files for the actual game can be loaded in Game.java.
	 */
	private void LoadContent() {

	}

	/**
	 * In specific intervals of time (GAME_UPDATE_PERIOD) the game/logic is
	 * updated and then the game is drawn on the screen.
	 */
	private void mainLoop() {
		// This two variables are used in VISUALIZING state of the game. We used
		// them to wait some time so that we get correct frame/window
		// resolution.
		long visualizingTime = 0, lastVisualizingTime = System.nanoTime();

		// This variables are used for calculating the time that defines for how
		// long we should put threat to sleep to meet the GAME_FPS.
		long beginTime, timeTaken, timeLeft;

		while (true) {
			beginTime = System.nanoTime();

			switch (mainState) {
			case GAME_PLAY:
				gameTime += System.nanoTime() - lastTime;
				game.loop();
				lastTime = System.nanoTime();
				break;
			case TITLE_SCREEN:
				titleScreen.loop();
				break;
			case INITIALIZING:
				// Sets variables and objects.
				Initialize();
				// Load files - images, sounds, ...
				LoadContent();

				mainState = MainState.TITLE_SCREEN;
				break;
			case VISUALIZING:
				// On Ubuntu OS (when I tested on my old computer)
				// this.getWidth() method doesn't return the correct value
				// immediately (eg. for frame that should be 800px width,
				// returns 0 than 790 and at last 798px).
				// So we wait one second for the window/frame to be set to its
				// correct size. Just in case we
				// also insert 'this.getWidth() > 1' condition in case when the
				// window/frame size wasn't set in time,
				// so that we although get approximately size.
				if (this.getWidth() > 1 && visualizingTime > secInNanosec) {
					frameWidth = this.getWidth();
					frameHeight = this.getHeight();
					mainState = MainState.INITIALIZING;
				} else {
					visualizingTime += System.nanoTime() - lastVisualizingTime;
					lastVisualizingTime = System.nanoTime();
				}
				break;
			}

			// Repaint the screen.
			repaint();

			// Here we calculate the time that defines for how long we should
			// put threat to sleep to meet the GAME_FPS.
			timeTaken = System.nanoTime() - beginTime;
			timeLeft = (GAME_UPDATE_PERIOD - timeTaken) / milisecInNanosec; // In
																			// milliseconds
			// If the time is less than 10 milliseconds, then we will put thread
			// to sleep for 10 millisecond so that some other thread can do some
			// work.
			if (timeLeft < 10)
				timeLeft = 10; // set a minimum
			try {
				// Provides the necessary delay and also yields control so that
				// other thread can do work.
				Thread.sleep(timeLeft);
			} catch (InterruptedException ex) {
			}
		}
	}

	/**
	 * Draw the game to the screen. It is called through repaint() method in
	 * GameLoop() method.
	 */
	@Override
	public void draw(Graphics2D g2d) {
		switch (mainState) {
		case GAME_PLAY:
			game.draw(g2d);
			// game.Draw(g2d, mousePosition());
			break;
		case TITLE_SCREEN:
			titleScreen.draw(g2d);
			// ...
			break;
		}
	}

	private void newTitleScreen() {
		titleScreen = new TitleScreen();
	}

	/**
	 * Starts new game.
	 */
	private void newGame() {
		// We set gameTime to zero and lastTime to current time for later
		// calculations.
		gameTime = 0;
		lastTime = System.nanoTime();

		game = new Game();
	}

	/**
	 * Restart game - reset game time and call RestartGame() method of game
	 * object so that reset some variables.
	 */
	private void restartGame() {
		// We set gameTime to zero and lastTime to current time for later
		// calculations.
		gameTime = 0;
		lastTime = System.nanoTime();

		// game.RestartGame();

		// We change game status so that the game can start.
		mainState = MainState.GAME_PLAY;
	}

	/**
	 * Returns the position of the mouse pointer in game frame/window. If mouse
	 * position is null than this method return 0,0 coordinate.
	 * 
	 * @return Point of mouse coordinates.
	 */
	private Point mousePosition() {
		try {
			Point mp = this.getMousePosition();

			if (mp != null)
				return this.getMousePosition();
			else
				return new Point(0, 0);
		} catch (Exception e) {
			return new Point(0, 0);
		}
	}

	// Methods of the keyboard listener.
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		switch (mainState) {
		case GAME_PLAY:
			game.keyPressed(e);
			if (key == KeyEvent.VK_P) {
				if (Game.state != Game.State.PAUSE) {
					Game.state = Game.State.PAUSE;
				} else {
					Game.state = Game.State.PLAY;
				}
			}
			if (key == KeyEvent.VK_Q) {
				TitleScreen.state = TitleScreen.State.ACTIVE;
				mainState = MainState.TITLE_SCREEN;
			}
			break;
		case TITLE_SCREEN:
			if (key == KeyEvent.VK_ENTER) {
				TitleScreen.state = TitleScreen.State.IDLE;
				mainState = MainState.GAME_PLAY;
			}
			break;
		}

		if (key == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		game.keyReleased(e);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
