/*
 * @(#)CircleShape.java
 */
package ds.graphics;

import java.awt.*;

/**
 * A derived class the extends Shape with circle objects that are specified
 * by a radius and the base point at the center of the figure.
 */
public class CircleShape extends Shape
{
	private double radius;

	/**
	 * Default constructor creates an instance with center (0.0),
	 * radius zero and color LIGHTGRAY.
	 */
	public CircleShape()
	{
		super(0,0,Shape.LIGHTGRAY);
		radius = 0;
	}

	 /**
	 * Creates an instance with center (x,y), radius r and color c.
	 * @param x x-coordinate of the base point.
	 * @param y y-coordinate of the base point.
	 * @param c fill color when drawing the circle.
	 */
	public CircleShape(double x, double y, double r, int c)
	{
		super(x,y,c);
		radius = r;
	}

	 /**
	  * Returns the radius of this circle.
	  * @return radius of this circle.
	  */
	public double getRadius()
	{ return radius; }

	/**
	 * Updates the radius of this circle to have new value r.
	 * @param r  new radius of this circle.
	 */
	public void setRadius (double r)
	{ radius = r; }

	/**
	 * Displays the circle on the drawing window with the base point as the center..
	 */
	public void draw()
	{
		if (DrawTools.openWindowCalled == false)
			DrawTools.openWindow();

		if(!alist.contains(this))
			alist.add(this);

		DrawPanel panel = DrawTools.getDrawingPanel();

		drawFigure(panel.getGraphics(), panel.getFactor());
	}

	/*
	public CircleShape copy()
	{
		CircleShape c = new CircleShape(x, y, radius, colorIndex);

		return c;
	}*/

	protected void drawFigure(Graphics g, double factor)
	{
		g.setColor(colorList[colorIndex]);
		g.fillOval((int)(factor * (x-radius)),(int)(factor * (y-radius)),
					  (int)(factor * 2 * radius), (int)(factor * 2 * radius));
	}
}