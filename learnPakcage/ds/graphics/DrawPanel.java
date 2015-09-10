package ds.graphics;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.print.*;

// utility methods that draw figures on the drawing surface
// relative to the 10 x 8 coordinate system. Prints the figure
// as an image
public class DrawPanel extends JPanel implements Printable
{
	private Shape shape;
	private boolean screenSet = false;
	private Rectangle r;
	private double factor = 0.0;

	public double getFactor()
	{
		if(!screenSet)
		{
			factor = r.getWidth() / 10;
			screenSet = true;
		}

		return factor;
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		ArrayList alist;
		alist = Shape.getShapes();
		r = this.getBounds();

		if(!screenSet)
		{
			factor = r.getWidth() / 10;
			screenSet = true;
		}

		if(alist != null && alist.size() > 0)
		{
			for(int i = 0; i < alist.size(); i++)
			{
				shape = (Shape)alist.get(i);
				shape.drawFigure(g, factor);
			}
		}
	}

	public int print(Graphics g, PageFormat pf, int pi) throws PrinterException
	{
		if (pi >= 1)
		{
			return Printable.NO_SUCH_PAGE;
		}

		Graphics2D g2d = (Graphics2D)g;
		g2d.translate(pf.getImageableX(), pf.getImageableY());

		paintComponent(g2d);
		return Printable.PAGE_EXISTS;
	}
}
