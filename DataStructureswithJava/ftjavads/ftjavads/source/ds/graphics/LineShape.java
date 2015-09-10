/*
 * @(#)LineShape.java
 */
package ds.graphics;

import java.awt.*;

/**
 * A derived class the extends Shape with line objects that are specified
 * by a base point and a second point <tt>(x,y)</tt> which is referred to as the end point.
 */
public class LineShape extends Shape
{
	private double endX;
	private double endY;

	/**
	 * Default constructor creates an instance based at point (0.0),
	 * with zero length and color BLACK.
	 */
	public LineShape()
	{
    	super(0,0,Shape.BLACK);
    	endX = 0;
    	endY = 0;
  	}

	/**
	 * Creates an instance connecting points (x1, y1) and (x2, y2) with color c.
	 * @param x1 x-coordinate of the base point.
	 * @param y1 y-coordinate of the base point.
	 * @param x2 x-coordinate of the end point.
	 * @param y2 y-coordinate of the end point.
	 * @param c fill color when drawing the line.
	 */
	public LineShape(double x1, double y1, double x2, double y2, int c)
	{
    	super(x1,y1,c);
    	endX = x2;
    	endY = y2;
  	}

	/**
	 * Returns the length of this line.
	 * @return length of the line
	 */
	public double getLength()		// length of line
	{
		double dx = endX - x, dy = endY - y;
		return Math.sqrt(dx*dx + dy*dy);
	}

	/**
	 * Updates the end point of the line to the new value (x,y).
	 * @param x   x-coordinate of the new end point
	 * @param y   y-coordinate of the new end point
	 */
	public void setEndPoint(double x, double y)  // change 2nd point
	{
		endX = x;
		endY = y;
	}

	/*
	public LineShape copy()
	{
		LineShape l = new LineShape(x, y, endX,  endY, colorIndex);

		return l;
	}
	*/

	/**
	 * Displays the line on the drawing window connecting the base point to the end point..
	 */
	public void draw()
	{
		if (DrawTools.openWindowCalled == false)
			DrawTools.openWindow();

		// if(!alist.contains(this))
			alist.add(this);

		DrawPanel panel = DrawTools.getDrawingPanel();

		drawFigure(panel.getGraphics(), panel.getFactor());
	}

	protected void drawFigure(Graphics g, double factor)
	{
		g.setColor(colorList[colorIndex]);
		g.drawLine((int)(factor * x),(int)(factor * y),
                 (int)(factor * endX), (int)(factor * endY));
	}
}