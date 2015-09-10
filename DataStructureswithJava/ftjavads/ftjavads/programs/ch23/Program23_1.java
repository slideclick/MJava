import java.io.*;

public class Program23_1
{
	public static void main(String[] args) throws IOException
	{
		int intVal = 100;
		short shortVal = 1500;
		long longVal = 4294967295L;
		byte[] buf = {3, 5, 2, 7, 15, 100, 127, 55};

		// create a DataOutputStream that writes to
		// the file "data.dat" in the local directory
		DataOutputStream  fout = null;
		// use to input data from "data.dat"
		DataInputStream fin = null;

		try
		{
			fout = new DataOutputStream(
				new FileOutputStream("data.dat"));
		}
		catch (FileNotFoundException fnfe)
		{
			System.err.println("Cannot create \"data.dat\"");
			System.exit(1);
		}

		// write each variable and the array to f
		fout.writeInt(intVal);
		fout.writeShort(shortVal);
		fout.writeLong(longVal);
		fout.write(buf, 0, buf.length);

		// close the stream and open it as a DataInputStream
		fout.close();
		try
		{
			fin =
				new DataInputStream(new FileInputStream("data.dat"));
		}
		catch (FileNotFoundException fnfe)
		{
			System.err.println("Failure to open \"data.dat\"");
			System.exit(1);
		}

		// input the int, short, and long from the file
		System.out.println("int:    " + fin.readInt());
		System.out.println("short:  " + fin.readShort());
		System.out.println("long:   " + fin.readLong());

		// input the byte array that was written to the file.
		// the number of bytes in the array is the number of
		// bytes remaining unread in the file
		byte[] b = new byte[fin.available()];

		System.out.print("byte array: ");
		// input the array
		fin.read(b);
		// output the bytes
		for (int i=0;i < b.length;i++)
			System.out.print(b[i] + "  ");
		System.out.println();

		// close the stream
		fin.close();
	}
}

/*
Run:

int:    100
short:  1500
long:   4294967295
byte array: 3  5  2  7  15  100  127  55
*/
