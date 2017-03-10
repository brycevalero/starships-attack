package com.brycevalero.starships.game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Star
{
  Random randomGenerator = new Random();
  
  private int starDiameter;  
  private Color starColor;
  
  private int starPositionX;
  private int starPositionY;
  private int starSpeedX;
  private double starSpeedY;
  private int screenWidth;
  private int screenHeight;
  
  public Star(int w, int h)
  {
    screenWidth = w;
    screenHeight = h;
    starDiameter = (randomGenerator.nextInt(9) + 1);
    starColor = new Color(randomGenerator.nextInt(255), randomGenerator.nextInt(255), randomGenerator.nextInt(255));
    
    starPositionX = randomGenerator.nextInt(screenWidth);
    starPositionY = randomGenerator.nextInt(screenHeight);
    starSpeedX = 0;
    starSpeedY = (randomGenerator.nextInt(9) + 1);
  }

  public int getDiameter()
  {
    return starDiameter;
  }
  
  public Color getColor()
  {
    return starColor;
  }

  public int getX()
  {
    return starPositionX;
  }

  public int getY()
  {
    return starPositionY;
  }
  
  public int getSpeedX()
  {
    return starSpeedX;
  }
  
  public double getSpeedY()
  {
    return starSpeedY;
  }
  
  public void setDiameter(int d)
  {
    starDiameter = d;
  }

  public void setColor(Color c)
  {
    starColor = c;
  }

  public void setX(int x)
  {
    starPositionX = x;
  }
  
  public void setY(int y)
  {
    starPositionY = y;
  }

  public void setSpeedX(int dx)
  {
    starSpeedX = dx;
  }
  
  public void setSpeedY(double dy)
  {
    starSpeedY = dy;
  }

  public void move()
  {
    starPositionX += starSpeedX;
    starPositionY = ((int)(starPositionY + starSpeedY));
    
    if (starPositionY > screenHeight)
    {
      starPositionY = -20;
      starPositionX = randomGenerator.nextInt(screenWidth);
      starDiameter = (randomGenerator.nextInt(9) + 1);
      starColor = new Color(randomGenerator.nextInt(255), randomGenerator.nextInt(255), randomGenerator.nextInt(255));
      
      starSpeedY = (randomGenerator.nextInt(9) + 1);
    }
  }
  
  public void draw(Graphics g)
  {
    g.setColor(starColor);
    g.fillOval(starPositionX, starPositionY, starDiameter, starDiameter);
  }
}
