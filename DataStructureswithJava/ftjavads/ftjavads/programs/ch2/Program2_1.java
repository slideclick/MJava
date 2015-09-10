import java.util.Scanner;
import java.io.*;
import java.text.DecimalFormat;

public class Program2_1
{
	public static void main(String[] args)
	{
		final double SALESTAX = 0.05;

		// input streams for the keyboard and a file
		Scanner fileIn = null;

		// input variables and pricing information
		String product;
		int quantity;
		double unitPrice, quantityPrice, tax, totalPrice;
		char taxStatus;
		
		// create formatted strings for aligning output 
		DecimalFormat fmtA = new DecimalFormat("#"),
						  fmtB = new DecimalFormat("$#.00");
		
		// open the file; catch exception if file not found
		// use regular expression as delimiter 
		try
		{
			fileIn = new Scanner(new FileReader("food.dat"));
			fileIn.useDelimiter("[\t\n\r]+");
		}
		catch (IOException ioe)
		{
			System.err.println("Cannot open file 'food.dat'");
			System.exit(1);
		}
		
		// header for listing output
		System.out.println("Product" + align("Quantity", 16) +
			align("Price", 10) + align("Total", 12));

		// read to end of file;  break when no more tokens
		while(fileIn.hasNext())
		{
			// input product/purchase input fields
			product = fileIn.next();
			quantity = fileIn.nextInt();
			unitPrice = fileIn.nextDouble();
			taxStatus = fileIn.next().charAt(0);

			// calculations and output
			quantityPrice = unitPrice * quantity;
			tax =
				(taxStatus == 'Y') ? quantityPrice * SALESTAX : 0.0;
			totalPrice = quantityPrice + tax;
			System.out.println(product +
				align("", 15-product.length()) + 
			   align(fmtA.format(quantity), 6) +
				align(fmtB.format(unitPrice), 13) + 
				align(fmtB.format(totalPrice),12) +
				((taxStatus == 'Y') ? " *" : ""));
		}
	}
	
	// aligns a string right justified in a field of n characters
	public static String align(String str, int n)
	{
		String alignStr = "";
		for (int i = 1; i < n - str.length(); i++)
			alignStr += " ";
		alignStr += str;
		return alignStr;
	}
} 

/*
Input file ('food.dat')
Soda	3 2.69 Y
Eggs	2 2.89 N
Bread	3 2.49 N
Grapefruit	8 0.45 N
Batteries	10 1.15 Y
Bakery	1 14.75 N

Run:

Product       Quantity    Price      Total
Fruit Punch       4       $2.69     $11.30 *
Eggs              2       $2.89      $5.78
Rye Bread         3       $2.49      $7.47
Grapefruit        8        $.45      $3.60
AA Batteries     10       $1.15     $12.08 *
Ice Cream         1       $3.75      $3.75
*/
