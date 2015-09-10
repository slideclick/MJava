import ds.util.ArrayList;
import ds.graphics.*;

public class HanoiGraphic
{
	// constants used to specify the width of the disks
	private static double[] diskWidth =
			{2.8, 2.3, 1.8, 1.3, 0.8, 0.3};

	// the x-position of the n disks at pegs 0, 1, 2; for instance
	// disk 2 on peg 1 has x-position 4.25, disk 4 on peg 2 has
	// x-position 7.5
	private static double[] xPos =
			{1.0, 1.25, 1.5, 1.75, 2.0, 2.25,
			 3.75, 4.0, 4.25, 4.5, 4.75, 5.0,
			 6.5, 6.75, 7.0, 7.25, 7.5, 7.75};

	// color of the disks
	private static int[] color = {Shape.BLACK, Shape.RED,
											Shape.LIGHTGRAY, Shape.TEAL,
											Shape.DARKGRAY, Shape.GREEN};

	// graphics object for a line and labels below the pegs
	private static LineShape line =
			new LineShape(0.5, 4.5, 9.5, 4.5, Shape.BLACK);
	private static TextShape
			pTextA =new TextShape(2.32,4.75,"Needle 0",Shape.BLACK),
			pTextB =new TextShape(5.2,4.75,"Needle 1", Shape.BLACK),
			pTextC =new TextShape(8.0,4.75,"Needle 2", Shape.BLACK);

	public static void main(String[] args)
	{
	   // number of disks
	   int n = 0, i;

	   // enter the number of disks on the command line.
	   // convert args[0] to n
	   try
	   {
	   	n = Integer.parseInt(args[0]);
	   }
	   catch (ArrayIndexOutOfBoundsException aiobe)
	   {
	   	System.err.println("***\n  Enter the number of disks " +
	   							 "on the command line\n***");
	   	System.exit(1);
	   }

		// declare a three element array for the three needles.
		// each element is an ArrayList of integers that holds
		// the stack of disks for each needle
		ArrayList[] needleList = new ArrayList[3];

		for (i=0;i < needleList.length;i++)
			needleList[i] = new ArrayList();

		// initialize needle = 0 to hold the n disks 0, 1, ..., n-1
	   for (i = 0; i < n; i++)
		   needleList[0].add(i);

		// open the drawing surface and display initial disks
		DrawTools.openWindow();
		drawDisks(needleList);
		// call recursive function
		hanoi(n,0, 1, 2, needleList);

		// view final display and close the drawing surface
		DrawTools.viewWindow();
		DrawTools.closeWindow();
	}

	private static void drawDisks(ArrayList[] pegList)
	{
		int peg, j, k;
		RectShape rect = null;

		// erase the previous drawing and set the line and labels
		DrawTools.eraseWindow();
		line.draw();
		pTextA.draw();
		pTextB.draw();
		pTextC.draw();

		// scan pegList to identify the disks on each peg; use the
		// disk value set rectangle attributs using constants from
		// arraysxPos, diskWidth, and color;  then draw the
		// disk (rectangle)
		for (peg = 0; peg <= 2; peg++)
			for (j = 0; j < pegList[peg].size(); j++)
			{
				k = ((Integer)(pegList[peg]).get(j)).intValue();

				rect = new RectShape(xPos[peg*6 + k], 4-(j*0.5),
											diskWidth[k], 0.5,
											color[k]);
				rect.draw();
			}
			// delay the frame 1 second
			DrawTools.delayWindow(1.0);
	}


	// recursive hanoi() function in which a move is implemented
	// by popping a disk from startNeedle to endneedle
	private static void hanoi(int n, int startNeedle,
									  int middleNeedle, int endNeedle,
				  					  ArrayList[] needleList)
	{
		Integer diskValue;
		ArrayList startNeedleList = needleList[startNeedle];
		int backIndex;

		// stopping condition: pop disk from the top of
		// needleList[startNeedle] and push it on the top of
		// needleList[endNeedle]; then draw the disks
		if (n == 1)
		{
			backIndex = startNeedleList.size() - 1;
			diskValue =
					(Integer)(startNeedleList.remove(backIndex));
			needleList[endNeedle].add(diskValue);
			drawDisks(needleList);
		}
		// move n-1 disks to middleNeedle, move bottom disk to
		// endNeedle, then move n-1 disks from middleNeedle
		// to endNeedle
		else
		{
			hanoi(n-1,startNeedle,endNeedle,
					middleNeedle, needleList);

			// stopping condition: pop disk from the top of
			// needleList[startNeedle] and push it on the top of
			// needleList[endNeedle]; then draw the disks
			backIndex = startNeedleList.size() - 1;
			diskValue =(Integer)(startNeedleList.remove(backIndex));
			needleList[endNeedle].add(diskValue);
			drawDisks(needleList);

		  hanoi(n-1,middleNeedle,startNeedle,endNeedle,needleList);
		}
	}
}
