package com.brycevalero.starships.framework;

import java.awt.Dimension;
import java.awt.Toolkit;

public class Config {

	/*
	 * Sets the width and heigh of the games window
	 */
	// public static Dimension SCREEN = new Dimension(1200, 800);
	public static Dimension SCREEN = Toolkit.getDefaultToolkit().getScreenSize();

	public static int MAX_ENEMIES = 20;
	public static int MAX_ENEMY_SPEED = 40; // 0-99

}
