package com.brycevalero.starships.game;

import java.awt.Image;
import java.awt.Point;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import com.brycevalero.starships.screens.TitleScreen;

@SuppressWarnings("serial")
public class Explosion extends Animate {

	private ArrayList<Image> smallExplosion;

	public Explosion(Point coord) {
		super();

		smallExplosion = new ArrayList<Image>();

		this.addFrame("/images/explosion1/ex1.png");
		this.addFrame("/images/explosion1/ex2.png");
		this.addFrame("/images/explosion1/ex3.png");
		this.addFrame("/images/explosion1/ex4.png");
		this.addFrame("/images/explosion1/ex5.png");
		this.addFrame("/images/explosion1/ex6.png");
		this.addFrame("/images/explosion1/ex7.png");

		setPosition(coord);
		setImages(smallExplosion);
	}

	public void addFrame(String path) {
		URL url = TitleScreen.class.getResource(path);
		Image frame = new ImageIcon(url).getImage();
		smallExplosion.add(frame);
	}

}
