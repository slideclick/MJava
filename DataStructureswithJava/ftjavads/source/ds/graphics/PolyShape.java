/*
 * @(#)PolyShape.java
 */
package ds.graphics;

import java.awt.*;
import java.util.Vector;

/**
 * A derived class the extends Shape with regular polygon objects that are specified
 * by a length and the number of sides with the base point at the center of the figure.
 */

public class PolyShape extends Shape
{
	private int numSides; 				// number of sides
	private double length; 				// length of a side
   private double[] point_X, point_Y;

	/**
	 * Default constructor creates an instance with zero sides and length
	 * and color LIGHTGRAY.
	 */
	public PolyShape()
	{
		super(0,0,Shape.LIGHTGRAY);
		numSides = 0;
		length = 0;
		setPoints();
	}

	 /**
	 * Creates an instance with base point (x,y), n sides of length len and color c.
	 * @param x x-coordinate of the base point.
	 * @param y y-coordinate of the base point.
	 * @param n number of sides in the regular polygon.
	 * @param length length of each side.
	 * @param c fill color when drawing the polygon.
	 */
	public PolyShape(double x, double y, int n, double length, int c)
	{
		super(x,y,c);
		numSides = n;
		this.length = length;
		setPoints();
	}

	/*public PolyShape copy()
	{
		PolyShape p = new PolyShape(x, y, numSides,  length, colorIndex);

		return p;
	}
	*/

  	private void setPoints()
  	{
		int i;
		double theta, d;
		double PI = Math.PI;
		double DELTA_THETA = (2.0*PI)/numSides;

		point_X = new double[numSides];
		point_Y = new double[numSides];
		d = length/(2.0*Math.sin(PI/numSides));
		theta = 0;
		for(i = 0; i  < numSides; i++)
		{
			point_X[i] = x + d*(Math.cos(theta));
			point_Y[i] = y - d*(Math.sin(theta));
			theta += DELTA_THETA;
		}
	}

	/**
	 * Updates the number of sides to have new value n.
	 * @param n  new number of sides for this polygon.
	 */
   public void setN(int n)
   {
		numSides = n;
		setPoints();
	}

	 /**
	  * Returns the number of sides in this polygon.
	  * @return number of sides.
	  */
	public int getN()
	{ return numSides; }

	/**
	 * Updates the length of each side to have new length len.
	 * @param length  new length of a side.
	 */
	public void setLength(double length)
	{
		this.length = length;
		setPoints();
	}

	 /**
	  * Returns the length of each side in this polygon.
	  * @return length of a side.
	  */
	public double getLength()
	{ return length; }

	/**
	 * Displays the regular polygon on the drawing window with the base point as the center.
	 */
	public void draw()
	{
		if (DrawTools.openWindowCalled == false)
			DrawTools.openWindow();

		if(!alist.contains(this))
			alist.add(this);

		setPoints();

		DrawPanel panel = DrawTools.getDrawingPanel();

		drawFigure(panel.getGraphics(), panel.getFactor());
	}

	protected void drawFigure(Graphics g, double factor)
	{
		int i;
		int[] xArr = new int[numSides];
		int[] yArr = new int[numSides];

		for(i = 0; i < numSides; i++)
		{
			xArr[i] = (int)(factor * point_X[i]);
			yArr[i] = (int)(factor * point_Y[i]);
		}

		g.setColor(colorList[colorIndex]);
		g.fillPolygon(xArr, yArr, numSides);
	}
}