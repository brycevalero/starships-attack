package com.brycevalero.starships.game;

import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Explosion extends Animate {

	private ArrayList<Image> smallExplosion;

	public Explosion(Point coord) {
		super();

		smallExplosion = new ArrayList<Image>();

		smallExplosion.add(Toolkit.getDefaultToolkit().getImage("images/explosion1/ex1.png"));
		smallExplosion.add(Toolkit.getDefaultToolkit().getImage("images/explosion1/ex2.png"));
		smallExplosion.add(Toolkit.getDefaultToolkit().getImage("images/explosion1/ex3.png"));
		smallExplosion.add(Toolkit.getDefaultToolkit().getImage("images/explosion1/ex4.png"));
		smallExplosion.add(Toolkit.getDefaultToolkit().getImage("images/explosion1/ex5.png"));
		smallExplosion.add(Toolkit.getDefaultToolkit().getImage("images/explosion1/ex6.png"));
		smallExplosion.add(Toolkit.getDefaultToolkit().getImage("images/explosion1/ex7.png"));

		setPosition(coord);
		setImages(smallExplosion);
	}

}
