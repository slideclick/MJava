import java.io.*;
import javax.swing.JTextArea;

import ds.util.HeapPQueue;
import ds.util.Less;
import ds.util.BitArray;

public class HCompress
{
	// input and output streams
	private DataInputStream source = null;
	private DataOutputStream dest = null;
	// charFreq used to count character frequencies. charLoc
	// maintains Huffman tree indices for characters present
	// in the file
	private int[] charFreq = null;
   private int[] charLoc = null;
   // the file name
   String fname;

	// numberLeaves is number of leaf nodes (character nodes)
	// in the tree
	private int numberLeaves;

	// number of Huffman tree nodes in the compressed file
	private short treeSize;

	// stores the Huffman tree
	private HuffNode[] tree = null;

	// size of the source file
	private int fileSize;

	// total number of bits used in the compressed image
	// of the source file
	private int totalBits;

	// does the tree have only 1 unique character?
	private boolean oneChar;

	// are source and dest open?
	private boolean filesOpen;

	// write progress messages to textArea
	private JTextArea textArea = null;

	private static String formatString(int w, String s)
	{
		// capture the length of s
		int sLength = s.length();

		// if length of s is at least w, just return s
		if (sLength >= w)
			return s;

		String str = "";

		// append w - sLength blanks to str
		for (int i=0;i < w - sLength;i++)
			str += " ";
		// append s after the leading blanks
		str += s;

		// return the formatted string
		return str;
	}

	private static String formatInt(int w, int n)
	{
		// get the string representation of n
		String strn = String.valueOf(n);
		// capture the length of strn
		int strnLength = strn.length();

		// if length of strn is at least w, just return strn
		if (strnLength >= w)
			return strn;

		String str = "";

		// append w - strnLength blanks to str
		for (int i=0;i < w - strnLength;i++)
			str += " ";
		// append strn after the leading blanks
		str += strn;

		// return the formatted string
		return str;
	}

	// determine the frequencies of the characters in the
	// source file and store them in charFreq. while doing
	// this, determine numberLeaves. also tabulate
	// fileSize so we can see how much savings the
	// compression algorithm gives us
	private void freqAnalysis() throws IOException
	{
		int b;

		numberLeaves = 0;
		fileSize = 0;

		while (true)
		{
			try
			{
				// try to input a byte
				b = source.readUnsignedByte();
			}
			catch (EOFException eofex)
			{
				// we are at end-of-file
				break;
			}

			// found 1 more byte in the source file
			fileSize++;

			// count an occurrence of ch
			charFreq[b]++;

			// if first time we have found b, count it as a leaf
			if (charFreq[b] == 1)
				numberLeaves++;
		}

		textArea.append("   File size: " + fileSize + " characters\n" +
				 "   Number of unique characters: " +
				 numberLeaves + "\n\n");
	}

	// build the Huffman tree
	private void buildTree()
	{
		// sequences through Huffman tree nodes
		int i;
		short nodeIndex;
		// capture nodes coming out of the priority queue
		HuffNode x, y;
		// priority queue used to build the Huffman tree
		HeapPQueue<HuffNode> pq = new HeapPQueue<HuffNode>(new Less<HuffNode>());

		// handle the special case of a file with only one
		// unique character
		if (numberLeaves == 1)
		{
			// set number of leaves to 2 and add a leaf node
			// at either index 0 or 1, since one of those
			// characters is not present in the file
			numberLeaves = 2;
			if (charFreq[0] != 0)
				charFreq[1] = 1;
			else
				charFreq[0] = 1;

			// record that we have added a "filler" node
			oneChar = true;
		}
		else
			oneChar = false;

		// the size of the tree is exactly 2*numberLeaves-1
		treeSize = (short)(2*numberLeaves - 1);
		tree = new HuffNode[treeSize];

		// index of the tree node we are building
		nodeIndex = 0;

		// start by building each leaf node:
		//    value = char(i)
		//    left/right = NIL,
		//    frequency = charFreq[i]
		//    index = nodeIndex
		//		parent, numberOfBits, maxSizeOfBits default
		// record index of leaf node in charLoc. insert node into
		// the heap
		for (i=0; i < MAXCHARS; i++)
			if (charFreq[i] != 0)
			{
				tree[nodeIndex] = new HuffNode((byte)i,
								HuffNode.NIL, HuffNode.NIL,
								charFreq[i], (short)nodeIndex, (short)0, 0, HuffNode.MAXBITSIZE);
				pq.push(tree[nodeIndex]);
				// record index of this leaf node for use in
				// writeCompressedData()
				charLoc[i] = nodeIndex;
				// get ready to build next node
				nodeIndex++;
			}

		// for numberLeaves-1 iterations, remove pairs of nodes,
		// create the parent, and insert the parent into the tree
		for (i=1; i <= numberLeaves-1; i++)
		{
			// remove the two nodes with least frequency. these are
			// copies of nodes in the vector, tree, at indices x.index
			// and y.index
			x = (HuffNode)pq.pop();
			y = (HuffNode)pq.pop();

			// create a parent (interior) node whose children are
			// at indices x.index, y.index in the array tree and
			// whose frequency is the sum of those in x and y. the
			// node's index is nodeIndex, and the parent of this node
			// is (temporarily) 0, and so are the number of bits and
			// the maximum size of the bitvector
			tree[nodeIndex] = new HuffNode((byte)0, x.index, y.index,
													 x.freq + y.freq,
													 (short)nodeIndex, (short)0, 0, 0);

			// use x.index and y.index to assign nodeIndex as the parent
			// of x and y
			tree[x.index].parent = nodeIndex;
			tree[y.index].parent = nodeIndex;

			// insert the new parent into the heap
			pq.push(tree[nodeIndex]);

			nodeIndex++;
		}
		textArea.append("   Number of nodes in Huffman tree: " +
				 treeSize + "\n\n");
	}

	// for each leaf node, go up the tree and determine the Huffman
	// code for the character. compute the total number of bits of
	// compressed data
	private void generateCodes()
	{
		int i, j;
		int bitNumber, current, parent;
		// use to compute each character's bit code
		BitArray bits = new BitArray(HuffNode.MAXBITSIZE);

		totalBits = 0;

		// the nodes tree[0], tree[1], ..., tree[numberLeaves-1] are
		// all leaf nodes. for each leaf, follow the parent index
		// up to the root and build the bit code for the character
		for (i=0; i < numberLeaves; i++)
		{
			// starting bit number of 0
			bitNumber = 0;
			// clear all the bits in the BitVector object bits
			bits.clear();
			// parent of our current leaf node
			parent = tree[i].parent;
			// identify the current node
			current = i;

			// continue generating bits until we hit the root,
			// whose parent is 0
			while (parent != 0)
			{
				// if the current node is the right child
				// of parent, add a 1 to bits at bitNumber.
				// otherwise, the bit is a 0, and bits
				// began with all zeros
				if (tree[parent].right == current)
					bits.set(bitNumber);
				// advance to the next bit number
				bitNumber++;
				// find the next parent
				current = parent;
				parent = tree[current].parent;
			}

			// the concluding value of bitNumber is the number of
			// bits in the Huffman code for tree[i].ch
			tree[i].numberOfBits = bitNumber;

			// copy the Huffman code from bits to tree[i].bits.
			// the order must be reversed
			for (j=bitNumber-1; j >= 0; j--)
				if (bits.bit(j) == 1)
					tree[i].bits.set(bitNumber-1-j);

			// add the bit contribution of the character
			// to the total number of bits. in other words,
			// add the path weight of the leaf node to the
			// external path weight
			totalBits += bitNumber * tree[i].freq;
		}
	}

	// reread the source file and write the Huffman codes specified
	// by the Huffman tree to the stream dest
	private void writeCompressedData() throws IOException
	{
		// vector that will contain the Huffman codes for
		// the compressed file
		BitArray compressedData = new BitArray(totalBits);
		int bitPos, i, j;
		int b;
	
		// close the source file and reopen it.
		source.close();
		source = new DataInputStream(new FileInputStream(fname));
	
		// bitPos is used to put bits into compressedData
		bitPos = 0;
	
		// re-read the source file and generate the Huffman codes in
		// compressedData
		while (true)
		{
			try
			{
				// try to input a byte
				b = source.readUnsignedByte();
			}
			catch (EOFException eofex)
			{
				// we are at end-of-file
				break;
			}
			// index of the tree node containing ch
			i = charLoc[b];
	
			// put the bit code for tree[i].b into the bit vector
			for (j=0;j < tree[i].numberOfBits; j++)
			{
				// only need to call set() if tree[i].bits.bit(j) is 1
				if (tree[i].bits.bit(j) == 1)
					compressedData.set(bitPos);
				// always advance bitPos
				bitPos++;
			}
		}
	
		// write the bit codes to the output file
		compressedData.write(dest);
	}

	// output Huffman tree data
	private void treeData()
	{
		int i, j;

		textArea.append("Tree has " + treeSize + " entries.  Root index = "
			  + (treeSize-1) + "\n\n");

		// table header
		textArea.append("Index" + formatString(5,"Sym") +
				formatString(10,"Freq") +
				formatString(10,"Parent") +
				formatString(6,"Left") +
				formatString(8,"Right") +
				formatString(7,"NBits") +
				formatString(8,"Bits") + "\n");

		// generate the table lines
		for (i=0;i < treeSize;i++)
		{
			textArea.append(formatInt(4,i));
			if (' ' <= tree[i].b && tree[i].b <= '~')
				textArea.append(formatString(6,String.valueOf((char)tree[i].b)));
			else if (i < numberLeaves)
			{
				textArea.append(formatString(4,"0x"));
				if (tree[i].b <= 15)
					textArea.append("0" + Integer.toString((int)tree[i].b,16));
				else
					textArea.append(Integer.toString((int)tree[i].b,16));
			}
			else
				textArea.append(formatString(6,"Int"));

			textArea.append(formatInt(10,tree[i].freq) +
					formatInt(7,tree[i].parent) +
					formatInt(8,tree[i].left) +
					formatInt(7,tree[i].right));
			if (tree[i].numberOfBits != 0)
				  textArea.append(formatInt(7,tree[i].numberOfBits));
			for (j=0;j < tree[i].numberOfBits;j++)
				if (tree[i].bits.bit(j) != 0)
					// only use setw(7) once
					if (j == 0)
						textArea.append(formatInt(7,1));
					else
						textArea.append("1");
				else
					// only use setw(7) once
					if (j == 0)
						textArea.append(formatInt(7,0));
					else
						textArea.append("0");
			textArea.append("\n");
		}
		textArea.append("\n");
	}

	// number of ASCII characters
	public static final int MAXCHARS = 256;

	// constructor. call setFile() to open the
	// source file fname, and create a binary output file
	// by adding extension ".huf". any previous extension is
	// replaced. verbose determines if progress messages are
	// output. constructor must initialize charFreq and charLoc
	// to have MAXCHARS characters
	public HCompress(String fname, JTextArea textArea)
		throws FileNotFoundException, IOException
	{
		charFreq = new int[MAXCHARS];
		charLoc = new int[MAXCHARS];
		this.textArea = textArea;
		this.filesOpen = false;

		this.fname = fname;
		setFile(fname);
	}

	// open the source file fname and create a binary output
	// file with extension ".huf". throws
	// FileNotFoundException if the input or output
	// file cannot be opened
	public void setFile(String fname) throws FileNotFoundException, IOException
	{
		StringBuffer ofname = new StringBuffer(fname);
		int i;

		if (filesOpen)
		{
			source.close();
			dest.close();
		}

		source = new DataInputStream(new FileInputStream(fname));

		// if the original file name ends with an extension (".xxx"),
		// remove the extension. in any case, add the extension ".huf".

		// find the last occurrence of '.'
		if ((i = ofname.toString().lastIndexOf('.')) != -1)
			// there is an extension. remove everything
			// from '.' to the end of the string
			ofname.delete(i,ofname.length());
		// add ".huf" as the extension
		ofname.append(".huf");

		// open the compressed image file in binary mode
		dest = new DataOutputStream(
				new FileOutputStream(ofname.toString()));

		filesOpen = true;
	}


	// compress the file
	public void compress() throws IOException
	{
		int i;
		DiskHuffNode tmp;

		textArea.append("Frequency analysis ...\n");
		// do the frequency analysis
		freqAnalysis();

		textArea.append("Building the Huffman tree ...\n");
		// build the Huffman tree
		buildTree();

		textArea.append("Generating the Huffman codes ...\n\n");
		// generate the Huffman code for each character and compute
		// the total number of bits in the compressed characters
		generateCodes();
		// output tree data now that the codes are complete
		treeData();

		textArea.append("Generating the compressed file\n\n");
		// output tree size
		dest.writeShort(treeSize);
		// output the tree. note that we output only the base class
		// portion of a HuffNode object. all decompress() needs is
		// the character and the child references
		for (i=0; i < treeSize; i++)
		{
			tmp = (DiskHuffNode)tree[i];
			tmp.write(dest);
		}
		// for a source file containing a single unique character,
		// delete the extra bit that was added to the total cost
		// because of the additional leaf node
		if (oneChar)
			totalBits--;
		// output the number of bits of Huffman code
		dest.writeInt(totalBits);
		// read through the source file and write the Huffman
		// codes for the characters found in tree to dest
		writeCompressedData();

		// close both files
		source.close();
		dest.close();

		filesOpen = false;
	}

	// return the compression ratio
	public double compressionRatio()
	{
		double compRatio;

		// determine the ratio of the size of the compressed file
		// to the size of the orginal
		compRatio = (double)fileSize/
				(2 + treeSize*DiskHuffNode.sizeof() +
				 8 + (totalBits+7)/8 );

		return compRatio;
	}

	// return the number of nodes in the
	// Huffman tree
	public int size()
	{
		return treeSize;
	}

	// display the Huffman tree. recommended
	// only if tree size is <= 11
	public String displayTree()
	{
		return DisplayHuffmanTree.display(tree) + "\n\n\n";
	}
}
