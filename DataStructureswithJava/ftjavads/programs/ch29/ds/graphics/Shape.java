/*
 * @(#)Shape.java
 */
package ds.graphics;

// package figures;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * A base class for the graphics hierarchy that specifies a base point (x,y)
 * and the color of each figure.
 */
public abstract class Shape
{
	/**
    * The color white.  RGB units are (255,255,255).
    */
 	public static final int WHITE = 0;

	/**
    * The color blue.  RGB units are (0,0,240).
    */
  	public static final int BLUE = 1;

	/**
    * The color teal.  RGB units are (0,128,128).
    */
 	public static final int TEAL = 2;

	/**
    * The color green.  RGB units are (0,192,0).
    */
  	public static final int GREEN = 3;

	/**
    * The color magenta.  RGB units are (255,0,255).
    */
  	public static final int MAGENTA = 4;

	/**
    * The color dark gray.  RGB units are (64,64,64).
    */
  	public static final int DARKGRAY = 5;

	/**
    * The color cyan.  RGB units are (0,255,255).
    */
  	public static final int CYAN = 6;

	/**
    * The color purple.  RGB units are (164,0,215).
    */
 	public static final int PURPLE = 7;

	/**
    * The color light blue.  RGB units are (181,218,255).
    */
 	public static final int LIGHTBLUE = 8;

	/**
    * The color light gray.  RGB units are (192,192,192).
    */
  	public static final int LIGHTGRAY = 9;

	/**
    * The color gray.  RGB units are (128,128,128).
    */
 	public static final int GRAY = 10;

	/**
    * The color red.  RGB units are (255,0,0).
    */
  	public static final int RED = 11;

	/**
    * The color orange.  RGB units are (255,200,0).
    */
 	public static final int ORANGE = 12;

	/**
    * The color pink.  RGB units are (255,175,175).
    */
  	public static final int PINK = 13;

	/**
    * The color yellow.  RGB units are (255,255,0).
    */
  	public static final int YELLOW = 14;

	/**
    * The color black.  RGB units are (0,0,0).
    */
  	public static final int BLACK = 15;

	protected static Color[] colorList =
		{ new Color(255,255,255), new Color(0,0,240),
		  new Color(0,128,128),   new Color(0,192,0),
		  new Color(255,0,255),   new Color(64,64,64),
		  new Color(0,255,255),   new Color(164,0,215),
		  new Color(181,218,255), new Color(192,192,192),
		  new Color(128,128,128), new Color(255,0,0),
		  new Color(255,200,0),   new Color(255,175,175),
		  new Color(255,255,0),   new Color(0,0,0)};

  	protected double x;
  	protected double y;
	protected int colorIndex = DARKGRAY;

	protected static ArrayList<Shape> alist;

 	/**
	 * Initializes the shape fields base point (x,y) and color.
	 * @param x     x-coordinate for the base point.
	 * @param y     y-coordinate for the base point.
	 * @param c     fill color when drawing the figure.
	 */
 	public Shape(double x, double y, int c)
  	{
		this.x = x;
		this.y = y;
		colorIndex = c;

    	if(alist == null)
      	alist = new ArrayList<Shape>();
  	}

	// returns the list of shapes currently drawn on the window
  	public static ArrayList getShapes()
  	{ return alist; }

	 // clears the list of shapes currently drawn on the window
  	public static void clearShapes()
  	{
		alist.clear();
		DrawTools.getDrawingPanel().repaint();
	}

  	/**
	 * Update the base point for the figure.  The next draw uses base point (x,y)
	 * @param  x  x-coordinate of the base point.
	 * @param  y  y-coordinate of the base point.
	 */
 	public void move(double x, double y)
  	{
    	this.x = x;
    	this.y = y;
	}

  	/**
	 * Erase the figure from the list of figures
	 */
 	public void erase()
  	{
    	alist.remove(this);
		DrawTools.getDrawingPanel().repaint();
  	}

  	/**
	 * Returns the x coordinate of the base point.
	 * @return  x coordinate of the base point.
	 */
 	public double getX()
  	{ return x; }

  	/**
	 * Returns the y coordinate of the base point.
	 * @return  y coordinate of the base point.
	 */
 	public double getY()
  	{ return y; }


 	/**
	 * Returns the color used when drawing the figure.
	 * @return  color of the figure.
	 */
	public int getColor()
	{ return colorIndex; }

 	/**
	 * Update the color for the next draw of the figure.
	 * @param  c color for the next draw of the figure.
	 */
	public void setColor(int c)
	{ colorIndex = c; }

 	/**
	 * Adances the color to the next color in the palette.
	 */
	public void nextColor()
	{ colorIndex = (colorIndex + 1) % colorList.length; }

  	/**
  	 * Abstract method for displaying the figure on the drawing window.
  	 */
 	public abstract void draw();

  	// draws the figure relative to the 10x8 drawing surface
 	protected abstract void drawFigure(Graphics g, double factor);
}