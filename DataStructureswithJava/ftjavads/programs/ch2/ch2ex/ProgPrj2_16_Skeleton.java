import java.io.*;
import java.util.zip.*;
import java.util.*;

public class ProgPrj2_16
{
	public static void main(String[] args) throws Exception
	{
		Scanner keyIn = new Scanner(System.in);
		String filename;
		ZipFile z = null;
		ZipInputStream zipInputStream = null;
		Enumeration e;
		BufferedInputStream zipIn = null;
		BufferedOutputStream unzipOut = null;
		ZipEntry ze = null;
		byte[] buf = null;
		int fileSize, bytesRead;

		// Prompt for the archive file name; create a ZipFile object
		// and a ZipInputStream
		. . .

     	// use an Enumeration to display files in the archive
		System.out.println("Files in the archive " + filename);
     	. . .

		System.out.println("Unzipping files in the archive");
		while(true)
		{
			// uncompress files in the archive; each iteration accesses
			// the file as a ZipEntry; use the ZipFile and ZipEntry objects
			// to create an InputStream.  Extract the compressed
			// elements and uses an OutputStream to write the uncompressed
			// data to a file.
			. . .
		}
	}
}

/*
Run:

Enter name of archive: graphics.zip
Files in the archive graphics.zip
    DrawTools.java
    LineShape.java
    . . .
Unzipping files in the archive
    File DrawTools.java  (size 3781)
    File LineShape.java  (size 1220)
    . . .
*/
