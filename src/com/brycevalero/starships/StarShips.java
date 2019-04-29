package com.brycevalero.starships;

/*
 * HelloWorldSwing.java requires no other files. 
 */
import javax.swing.JFrame;

import com.brycevalero.starships.framework.Config;
import com.brycevalero.starships.framework.MainLoop;

public class StarShips {
	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event-dispatching thread.
	 */
	private static void showDisplay() {
		// Create and set up the window.
		JFrame frame = new JFrame("Star Ships");
		MainLoop mainLoop = new MainLoop();

		frame.add(mainLoop);
		frame.setSize(Config.SCREEN.width, Config.SCREEN.height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// sets full screen
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);

		frame.setLocationRelativeTo(null);// Centers window
		frame.setVisible(true);// Make the window visible
	}

	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				showDisplay();
			}
		});
	}
}