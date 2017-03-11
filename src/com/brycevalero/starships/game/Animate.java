package com.brycevalero.starships.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Animate extends JPanel {

	private int frame;
	private int nthframe;
	private int imageIndex;
	private Point position;
	private ArrayList<Image> images;

	public enum State {
		ACTIVE, FINISHED
	}

	public State state;

	public Animate() {
		position = new Point(0, 0);
		frame = 0;
		nthframe = 3;
		imageIndex = 0;
		state = State.ACTIVE;
		images = new ArrayList<Image>();
	}

	public void setPosition(Point coord) {
		position = coord;
	}

	public void setImages(ArrayList<Image> imageList) {
		images = imageList;
	}

	public void draw(Graphics g) {

		if (((frame % nthframe) == 0) && frame > 0) {
			imageIndex++;
		}

		if (imageIndex < images.size()) {
			g.drawImage(images.get(imageIndex), position.x, position.y, null);
		} else {
			frame = 0;
			state = State.FINISHED;
		}

		frame++;
	}
}
