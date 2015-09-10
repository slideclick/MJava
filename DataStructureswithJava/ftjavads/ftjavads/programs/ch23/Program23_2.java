import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

import ds.util.HeapPQueue;

public class Program23_2 extends JFrame implements ActionListener
{
	JButton compressButton, decompressButton, exitButton;
	JTextArea textArea;

	public static void main(String[] args)
	{
		Program23_2 app = new Program23_2();
	}

 	public Program23_2()
	{
		setTitle("Demonstrating Huffman compression");
		setSize(575, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container c = getContentPane();
		c.setLayout(new BorderLayout());

		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());

		compressButton = new JButton("Compress");
		panel.add(compressButton);
		decompressButton = new JButton("Decompress");
		panel.add(decompressButton);
		exitButton = new JButton("Exit");
		panel.add(exitButton);

		textArea = new JTextArea(50, 15);
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
		c.add(panel, BorderLayout.SOUTH);
		c.add(new JScrollPane(textArea), BorderLayout.CENTER);

		compressButton.addActionListener(this);
		decompressButton.addActionListener(this);
		exitButton.addActionListener(this);

		setVisible(true);
   }

	private void compressFile()
	{
		// clear the text area
		textArea.setText("");

		FileDialog sourceFileDialog =	new FileDialog(
			this, "Choose file to compress", FileDialog.LOAD);

		sourceFileDialog.setVisible(true);
		final String sourceFile = sourceFileDialog.getFile();
		if (sourceFile == null)
			return;

		// do the compression in a separate thread so the system
		// will refresh the background of the JFrame.
		// create the thread by creating an anonymous inner class
		Thread t = new Thread()
		{
			public void run()
			{
				HCompress hc = null;

				try
				{
					hc = new HCompress(sourceFile, textArea);
					hc.compress();
					textArea.append("The compression ratio is " +
										 hc.compressionRatio());
					if (hc.size() <= 11)
						textArea.append("\n\nHuffman tree\n" +
											 hc.displayTree());
				}
				catch (FileNotFoundException fnfe)
				{}
				catch (IOException ioe)
				{}
			}
		};


		// start the thread and return
		t.start();
	}

	private void decompressFile()
	{
		// clear the text area
		textArea.setText("");

		FileDialog compressedFileDialog =
			new FileDialog(
				this, "Choose compressed file", FileDialog.LOAD);
		compressedFileDialog.setVisible(true);

		final String compressedFile =
			compressedFileDialog.getFile();
		if (compressedFile == null)
			return;

		FileDialog decompressedFileDialog =
			new FileDialog(this,
								"Choose name for decompressed file",
								FileDialog.SAVE);
		decompressedFileDialog.setVisible(true);

		final String decompressedFile =
			decompressedFileDialog.getFile();
		if (decompressedFile == null)
			return;

		// do the decompression in a separate thread so the system
		// will refresh the background of the JFrame.
		// create the thread by creating an anonymous inner class
		Thread t = new Thread()
		{
			public void run()
			{
				try
				{
					HDecompress hd = new HDecompress(
						compressedFile, decompressedFile, textArea);

					hd.decompress();
				}
				catch (FileNotFoundException fnfe)
				{}
				catch (IOException ioe)
				{}
			}
		};

		// start the thread and return
		t.start();
	}

	public void actionPerformed(ActionEvent ae)
	{
		Object eventSource = ae.getSource();

		if (eventSource == compressButton)
			compressFile();
		else if (eventSource == decompressButton)
			decompressFile();
		else if (eventSource == exitButton)
			System.exit(0);
 	}
}

/*
Run 1:

File name: demo.dat
   (16 'a'   4 'b'   8 'c'   6 'd'   20 'e'   3 'f')
Verbose (y or n): y

Frequency analysis ...
   File size: 57000 characters
   Number of unique characters: 6

Building the Huffman tree ...
   Number of nodes in Huffman tree: 11

Generating the Huffman codes ...

Tree has 11 entries.  Root index = 10

Index  Sym      Freq    Parent  Left   Right  NBits    Bits
   0     a     16000      9      -1     -1      2      10
   1     b      4000      6      -1     -1      4      0111
   2     c      8000      8      -1     -1      2      00
   3     d      6000      7      -1     -1      3      010
   4     e     20000      9      -1     -1      2      11
   5     f      3000      6      -1     -1      4      0110
   6   Int      7000      7       5      1
   7   Int     13000      8       3      6
   8   Int     21000     10       2      7
   9   Int     36000     10       0      4
  10   Int     57000      0       8      9

Generating the compressed file

The compression ratio = 3.39 (size of demo.huf = 16,822 bytes)

                                                  57000

        21000                                                   36000

 c:8000               13000                              a:16000       e:20000

               d:6000               7000

                             f:3000        b:4000

Run 2:

File name: webster.dict
	(234,946 words, each on a separate line.
	 Total of 2,721,849 characters)
Verbose (y or n): n

The compression ratio = 1.81 (size of webster.huf = 1,502,436 bytes)

Run 3:

File name: mspaint.exe
	(application. size = 344,064 bytes)
Verbose (y or n): n

The compression ratio = 1.36 (size of mspaint.huf = 253,663 bytes)
*/
