package com.brycevalero.starships;


import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Display extends JPanel{
	
	public Display(){
		//setBackground(Color.BLACK);
		//Game game = new Game();
	}

    //paint a ball
    public void paintComponent(Graphics g){

        super.paintComponent(g);
        g.setColor(Color.WHITE);

        g.fillOval(250, 250, 20, 20);
    }
}
