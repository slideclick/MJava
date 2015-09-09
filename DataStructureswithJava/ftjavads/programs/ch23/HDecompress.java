import java.io.*;
import javax.swing.JTextArea;

import ds.util.BitArray;

public class HDecompress
{

	// input and output streams
	private DataInputStream source = null;
	private DataOutputStream dest = null;

	private String decompressedFileName = null;

	// text area for output of decompression
	// information
	private JTextArea textArea = null;

	// are source and dest open?
	private boolean filesOpen;

	// constructor. calls setFiles() to open Huffman
	// compressed file cname and assign the output
	// file name uname.
	public HDecompress(String cname, String uname,
							 JTextArea textArea)
		throws FileNotFoundException, IOException
	{
		filesOpen = false;
		this.textArea = textArea;

		setFiles(cname, uname);
	}

	// open Huffman compressed file cname
	// and assign the output file name uname.
	// throws the fileOpenError exception if a file
	// cannot be opened
	public void setFiles(String cname, String uname)
		throws FileNotFoundException, IOException
	{
		if (filesOpen)
		{
			source.close();
			dest.close();
		}

		// open the streams
		source = new DataInputStream(new FileInputStream(cname));
		textArea.append("Compressed file " + cname +
							 " (" + source.available() +
							 ") characters\n");

		dest = new DataOutputStream(new FileOutputStream(uname));

		filesOpen = true;

		this.decompressedFileName = uname;
	}

	// decompress the file
	public void decompress() throws IOException
	{
		int i, bitPos;
	
		// treeSize and totalBits are read from the compressed file
		short treeSize;
		int totalBits;
		int decompressedFileSize = 0;
	
		textArea.append("Decompressing ... \n");
	
		// input the Huffman tree size
		treeSize = source.readShort();
	
		// treeSize DiskHuffNode nodes are read from the compressed file
		// into the tree
		DiskHuffNode[] tree = new DiskHuffNode[treeSize];
	
		// input the tree
		for (i=0;i < treeSize;i++)
		{
			tree[i] = new DiskHuffNode();
			tree[i].read(source);
		}
	
		// input the number of bits of Huffman code
		totalBits = source.readInt();
	
		// allocate a 1-bit bit array,whose contents we
		// immediately replace by the bits in the compressed
		// file
		BitArray bits = new BitArray(1);
		// read totalBits number of binary bits from the compressed
		// file into bits
		bits.read(source, totalBits);
	
		// restore the original file by using the Huffman codes to
		// traverse the tree and write out the corresponding
		// characters
		bitPos = 0;
		while (bitPos < totalBits)
		{
			// root of the tree is at index treeSize-1
			i = treeSize-1;
			// follow the bits until we arrive at a leaf node
			while (tree[i].left != HuffNode.NIL)
			{
				// if bit is 0, go left; otherwise, go right
				if (bits.bit(bitPos) == 0)
					i = tree[i].left;
				else
					i = tree[i].right;
				// we have used the current bit. move to the
				// next one
				bitPos++;
			}
			// we are at a leaf node. output the character
			// to the file
			dest.writeByte(tree[i].b);
			decompressedFileSize++;
		}
	
		textArea.append("Decompressed file " + decompressedFileName +
							 " (" + decompressedFileSize +
							 ") characters\n");
	
		// close the two streams
		source.close();
		dest.close();
	
		filesOpen = false;
	}
}

