/*
 * @(#)DrawTools.java
 */
package ds.graphics;

import java.awt.*;
import java.awt.event.*;
import java.awt.print.PrinterJob;
import java.awt.print.*;
import javax.swing.*;

/**
 * This class contains static methods which open, close, and erase the drawing surface as
 * well as methods that control the view of graphical frames.
 * @version  2/1/2005
 */
public class DrawTools
{
	private static JFrame frame;
  	private static DrawPanel panel = null;
  	private static Container c;
  	private static int count;
  	private static boolean keystatus;
  	private static int factor;
  	private static int dotsPerInch;
  	public static EnterKeyListener ekl;
  	public static boolean openWindowCalled = false;

  	public static DrawPanel getDrawingPanel()
  	{
		return panel;
	}

  	/**
  	 * Opens a window as the drawing surface in the center of the screen. The surface
  	 * has a predefined coordinate system with base point (0,0) in the upper left-hand
  	 * corner of the screen, width 10, and height 8. A menu contains a command
  	 * to print the window.
  	 */
  	public static void openWindow()
  	{
    	int windowH;
    	int windowW;
    	int frameH;
    	int frameW;

    	if (!openWindowCalled)
    	{
			JMenuBar menuBar = new JMenuBar();
			JMenu fileMenu = new JMenu("File");

				//create print menuItem
			JMenuItem printItem = new JMenuItem("Print");
			printItem.addActionListener(new ActionListener()
			{
					public void actionPerformed(ActionEvent e)
					{
					PrinterJob printJob = PrinterJob.getPrinterJob();
					PageFormat pageFormat = printJob.defaultPage();

					// Get the page format after showing page setup dialog
					pageFormat = printJob.pageDialog(pageFormat);
						printJob.setPrintable(panel,pageFormat);
					if (printJob.printDialog())
					{
							try
							{ printJob.print(); }
							catch (Exception ex)
							{ ex.printStackTrace(); }
					}
					}
			});

			fileMenu.add(printItem);

			//create close menuItem
			JMenuItem closeItem = new JMenuItem("Close");
			closeItem.setMnemonic(KeyEvent.VK_C);
			closeItem.addActionListener
			(	new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						frame.setVisible(false);
						frame.dispose();
						System.exit(1);
					}
				}
			);
			fileMenu.add(closeItem);
			menuBar.add(fileMenu);

			frame = new JFrame();
			frame.setTitle("Drawing Window");

			frame.setJMenuBar(menuBar);

			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			windowH = frame.getToolkit().getScreenSize().height;
			windowW = frame.getToolkit().getScreenSize().width;

			dotsPerInch = frame.getToolkit().getScreenResolution();

			frameW = windowW /2;
			frameH = frameW * 8/10 + 44;

			frame.setBounds((windowW - frameW)/2, (windowH - frameH)/2,
					frameW, frameH);

			panel = new DrawPanel();
			panel.setBackground(Color.white);

			c = frame.getContentPane();

			c.setLayout(new BorderLayout());
			c.add(panel, BorderLayout.CENTER);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setResizable(false);
			frame.setVisible(true);
			frame.toFront();
			openWindowCalled = true;

			try
			{
				Thread.sleep(500);
			}
			catch (InterruptedException ie)
			{}
		}
  	}

  	private static class EnterKeyListener extends KeyAdapter
  	{
		public void keyPressed(KeyEvent e)
		{
			int key = e.getKeyCode();
			if (key == KeyEvent.VK_ENTER)
			{ keystatus = true; }
		}
	}

  	public static void drawShape()
	{
   	panel.repaint();
	}

  	/**
  	 * Displays the current frame in the window for a specified number of seconds
  	 * and then continues program execution.
  	 */
	public static void delayWindow(double sec)
  	{
     	try
     	{
        	Thread.sleep((int)(sec * 1000));
     	}
     	catch(Exception e){}

  	}

  	/**
  	 * Displays the current frame in the window until the user press the
  	 * ENTER key.
  	 */
  	public static void viewWindow()
  	{

        ekl = new EnterKeyListener();
        frame.addKeyListener (ekl);
        keystatus = false;
        while(!keystatus)
        {
			// System.out.print("");
		}
      frame.removeKeyListener(ekl);
  	}

  	/**
  	 * Clears all of the figures from the drawing surface. .
  	 */
  	public static void eraseWindow()
  	{
        Shape.clearShapes();
  	}

   /**
  	 * Closes the drawing surface and disposes all of the components.
  	 */
 	public static void closeWindow()
  	{
    	if(frame.isShowing())
    	{
      	frame.setVisible(false);
      	frame.dispose();
    	}
  	}
}


