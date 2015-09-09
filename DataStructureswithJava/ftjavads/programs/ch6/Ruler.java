import ds.graphics.*;

public class Ruler
{
	// base length of a mark
	final static double BASELENGTH = 0.125;

	public static void main(String[] args)
	{
		// use to draw the ruler without the marks
		LineShape line = new LineShape(1.0, 4.0, 9.0, 4.0, Shape.BLACK);
		// use for labeling each drawing
		StringBuffer labelStr = new StringBuffer("h =  ");
		TextShape label = null;
		// will cycle through values 1 .. 7
		int h;

		// open the drawing window
		DrawTools.openWindow();

		// draw 7 rulers
		for (h=1;h <= 7;h++)
		{
			// draw the ruler without marks
			line.draw();
			// mark the ruler
			drawRuler(1.0, 9.0, h);

			// put the value of h as an ASCII character into
			// labelStr, assign the string into label and draw
			// the text
			labelStr.setCharAt(4,(char)(h + '0'));
			label = new TextShape(5.0,5,labelStr.toString(),Shape.BLACK);
			label.draw();

			// delay for 2 second to view the current ruler
			DrawTools.delayWindow(2.0);
			// erase the window
			DrawTools.eraseWindow();
		}

		// done. close the window
		DrawTools.closeWindow();
	}

	private static void drawMark(double mid, int h)
	{
		// use mark to draw the ruler marks.
		LineShape mark = new LineShape(mid, 4.0 + h*BASELENGTH,
												 mid, 4.0 - h*BASELENGTH,
												 Shape.BLACK);
		mark.draw();
	}

	private static void drawRuler(double low, double high, int h)
	{
		double midpt;

		if (h >= 1)
		{
			midpt = (high + low)/2;

			// draw the mark at midpt of [low, high)
			drawMark(midpt, h);

			// draw all marks on the left half-line, starting
			// with h-1
			drawRuler(low, midpt, h - 1);
			// draw all marks on the right half-line, starting
			// with h-1
			drawRuler(midpt, high, h - 1);
		}
	}
}
