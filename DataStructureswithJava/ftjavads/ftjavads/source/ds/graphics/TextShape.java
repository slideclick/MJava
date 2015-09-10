/*
 * @(#)TextShape.java
 */
package ds.graphics;

import java.awt.*;

/**
 * A derived class the extends Shape with text objects that are specified
 * by a string and a base point positioned at the start of the text.
 */
public class TextShape extends Shape
{
	/**
    * The font is Times Roman with point size 8.
    */
	public static final int TR8 = 0;

	/**
    * The font is Times Roman with point size 10.
    */
	public static final int TR10 = 1;

	/**
    * The font is Times Roman with point size 12.
    */
	public static final int TR12 = 2;

	/**
    * The font is bold Times Roman with point size 12.
    */
	public static final int TR12B = 3;

	/**
    * The font is Monospaced with point size 8.
    */
	public static final int CODE8 = 4;

	/**
    * The font is Monospaced with point size 10.
    */
	public static final int CODE10 = 5;

	/**
    * The font is Monospaced with point size 12.
    */
	public static final int CODE12 = 6;

	private String text = "";
	private int size;
	private Font[] fontList = {
			new Font("TimesRoman", Font.PLAIN, 8),
			new Font("TimesRoman", Font.PLAIN, 10),
			new Font("TimesRoman", Font.PLAIN, 12),
			new Font("Times Roman", Font.BOLD, 12),
			new Font("Monospaced", Font.PLAIN, 8),
			new Font("Monospaced", Font.PLAIN, 10),
			new Font("Monospaced", Font.PLAIN, 12)};
	private Font fontType;
	private FontMetrics fm = null;

	/**
	 * Default constructor creates an instance with base point (0.0),
	 * color BLACK, and font Time Roman 12 point size.
	 */
	public TextShape()
	{
		super(0,0,Shape.BLACK);
		this.text = "";
		size = 12;
		fontType = fontList[TR12];
	}

	/**
	 * Creates an instance with base point (x.y) and specified
	 * text and color; font is Times Roman 12 point size.
	 */
	public TextShape(double x, double y, String text, int c)
	{
		super(x,y,c);
		this.text = text;
		size = 12;
		fontType = fontList[TR12];
	}

	/**
	 * Creates an instance with base point (x.y) and specified
	 * text, color, and font.
	 */
	public TextShape(double x, double y, String text, int c, int font)
	{
		super(x,y,c);
		this.text = text;
		fontType = fontList[font];
	}

	/*public TextShape copy()
	{
		TextShape t = new TextShape(x, y, text, colorIndex);

		return t;
	}*/

	/**
	 * Returns the text of this instance.
	 * @return text of this instance.
	 */
	public String getText()
	{ return text; }

	/**
	 * Updates the text of this instance.
	 * @param text  new text for this instance.
	 */
	public void setText(String text)
	{ this.text = text; }

	/**
	 * Updates the font of this instance.
	 * @param font  new font for this instance.
	 */
	public void setFont(int font)
	{
		fontType = fontList[font];
	}

	/**
	 * Draws text on the drawing window. The text begins at the base point.
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

	/*private void drawLeft()
	{
		if (DrawTools.openWindowCalled == false)
			DrawTools.openWindow();

		if(!alist.contains(this))
			alist.add(this);

		DrawPanel panel = DrawTools.getDrawingPanel();

		drawFigure(panel.getGraphics(), panel.getFactor());
	}*/

	protected void drawFigure(Graphics g, double factor)
	{
		g.setColor(colorList[colorIndex]);
		g.setFont(fontType);
		fm = g.getFontMetrics(fontType);
		g.drawString(text, (int)(factor * x) - fm.stringWidth(text)/2,
			               (int)(factor * y + fm.getHeight()/4));
	}
}