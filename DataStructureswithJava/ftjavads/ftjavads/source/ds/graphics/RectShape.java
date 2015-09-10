/*
 * @(#)RectShape.java
 */
package ds.graphics;

import java.awt.*;

/**
 * A derived class the extends Shape with rectangle objects that are specified
 * by a length and a width with the base point at the upper left-hand corner
 * of the figure.
 */
public class RectShape extends Shape
{
	private double length;
	private double width;

	/**
	 * Default constructor creates an instance with base point(0.0), zero
	 * length and width, and color LIGHTGRAY.
	 */
	public RectShape()
	{
		super(0, 0, Shape.LIGHTGRAY);
		length = 0;
		width = 0;
	}

	/**
	 * Creates an instance with base point(x,y) in the upper left-hand corner,
	 * and specified length, width, and color.
	 * @param x x-coordinate of the base point.
	 * @param y y-coordinate of the base point.
	 * @param length length of the rectangle.
	 * @param width width of the rectangle.
	 * @param c fill color when drawing the rectangle.
	 */
	public RectShape(double x, double y, double length, double width, int c)
	{
		super(x, y, c);
		this.length = length;
		this.width = width;
	}

	/**
	 * Returns the length of this rectangle.
	 * @return length of this rectangle
	 */
	public double getLength()
	{  return length;  }

	/**
	 * Returns the width of this rectangle.
	 * @return width of this rectangle
	 */
	public double getWidth()
	{  return width; }

	/**
	 * Updates the length and width of this rectangle.
	 * @param length  new length of this circle.
	 * @param width  new width of this circle.
	 */
	public void setSides(double length, double width)
	{
		this.length = length;
		this.width = width;
	}

	/*
	public RectShape copy()
	{
		RectShape r = new RectShape(x, y, length,  width, colorIndex);

		return r;
	}
	*/

	/**
	 * Dislays the rectangle on the drawing window with the base point in the
	 * upper left-hand corner.
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

	protected void drawFigure(Graphics g, double factor)
	{
		g.setColor(colorList[colorIndex]);
		g.fillRect((int)(factor * x),(int)(factor * y),
					  (int)(factor * length), (int)(factor * width));
	}
}