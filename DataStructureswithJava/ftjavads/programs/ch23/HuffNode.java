import ds.util.BitArray;

// node used to construct the Huffman tree. the compress application
// builds a node with these attributes plus those of the super class
// DiskHuffNode. the attributes in this class are not needed in
// the uncompress application
public class HuffNode extends DiskHuffNode implements Comparable<HuffNode>
{
	// NIL represents an empty subtree
	public static final short NIL = -1;
	// maximum size of the bit code for a character
	public static final int MAXBITSIZE = 255;

	public int freq;					// frequency of the byte b
	public short index;				// my index in the tree
	public short parent;				// my parent in the tree
	public int numberOfBits;		// number of bits in Huffman code for b
	public BitArray bits = null;	// bit vector holding the code

	public HuffNode (byte b, short left, short right,
				 		  int freq, short index, short parent,
				 		  int numberOfBits, int maxSizeOfBits)
	{
		super(b, left, right);

		this.freq = freq;
		this.index= index;
		this.parent = parent;
		this.numberOfBits = numberOfBits;
		bits = new BitArray(maxSizeOfBits);
	}

	// necessary to build the priority queue
	public int compareTo(HuffNode node)
	{
		// HuffNode node = (HuffNode)obj;

		if (freq < node.freq)
			return -1;
		else if (freq == node.freq)
			return 0;
		else
			return 1;
	}
}
