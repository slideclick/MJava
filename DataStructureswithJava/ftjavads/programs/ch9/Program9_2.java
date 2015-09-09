import ds.util.ArrayList;

public class Program9_2
{
	public static void main(String[] args)
	{
		// insert coordinates from a file into x and y
		ArrayList<Double> x = new ArrayList<Double>(), 
		                  y = new ArrayList<Double>();
		double xCoor, yCoor;
		double xclose1, yclose1, xclose2, yclose2;

		// arrays for x-points and y-points and closest points
		double[] xPt = {-1, -1.45, -1, -1, 0.75, 1, 0.8, 1.65,
					       -1.2, 3, 2, 2.7, 1.35, 0.5};
		double[] yPt = {1, 1.3, 0, 3, -1.75, -1, 0.7, 1, -1.3, 0,
							 2, 1.3, 1.1, 2.45};
		int[] closestPoints;

		// add coordinate values at the back of x and y
		for (int i = 0; i < xPt.length; i++)
		{
			x.add(xPt[i]);
			y.add(yPt[i]);
		}

		// execute the closest-pair algorithm
		closestPoints = closestPair(x, y);

		// find the coordinates of the the closest points
		xclose1 = x.get(closestPoints[0]);
		yclose1 = y.get(closestPoints[0]);
		xclose2 = x.get(closestPoints[1]);
		yclose2 = y.get(closestPoints[1]);

		// output the pair of points and their minimum distance
		System.out.println("The closest points are ("
			  + xclose1 + "," + yclose1  + ") and (" + xclose2 + 
			  "," + yclose2 + ")");
		System.out.println("Distance = " +
		   Math.sqrt(sqr(xclose1-xclose2) + sqr(yclose1-yclose2)));
	}

	// return the indices of the two closest points in the set of
	// points whose x- and y-coordinates are in ArrayLists x and y
	public static int[]
	closestPair(ArrayList<Double> x, ArrayList<Double> y)
	{
		// capture the number of points in n
		int n = x.size();
		// index1 and index2 will contain the indices of the
		// closest points
		int i,j, index1 = -1, index2 = -1;
		double xi, yi, xj, yj;
		// initialize dmin to the largest possible double value
		double dmin = Double.MAX_VALUE , dsqr;
		// we return this array after determining its values
		int[] closest = new int[2];

		// make n-1 passes through the points
		for (i=0; i < n-1; i++)
			// compute each distance d(Pi, Pj), i+1 <= j < n
			// and record the current minimum distance
			for (j=i+1;j < n;j++)
			{
				// extract the double values from x and y
				xi = x.get(i);
				yi = y.get(i);
				xj = x.get(j);
				yj = y.get(j);
				// compute (xi-xj)^2 + (yi-yj)^2
				dsqr = sqr(xi - xj) + sqr(yi - yj);
				// check for a new minimum distance
				if (dsqr < dmin)
				{
					// new minimum. record it and change indices
					// index1 and index2
					dmin = dsqr;
					index1 = i;
					index2 = j;
				}
			}

		// initialize the elements of closest[] and return it
		closest[0] = index1;
		closest[1] = index2;

		return closest;
	}

	// return x squared
	public static double sqr(double x)
	{
		return x*x;
	}
}

/*
Run:

The closest points are (1.65,1.0) and (1.35,1.1)
Distance = 0.31622776601683783
*/
