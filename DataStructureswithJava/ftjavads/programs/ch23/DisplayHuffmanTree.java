import ds.util.LinkedQueue;

public class DisplayHuffmanTree
{
	private static String hex(byte b)
	{
		String digits = "0123456789ABCDEF";

		return String.valueOf(digits.charAt(b>>>4)) +
				 String.valueOf(digits.charAt(b & 0xf));
	}


	public static String formatInt(int w, int n)
	{
		// get the string representation of n
		String strn = String.valueOf(n);
		// capture the length of strn
		int strnLength = strn.length();

		// if length of strn is at least w, just return strn
		if (strnLength >= w)
			return strn;

		String str = "";

		str += strn;
		// append w - strnLength blanks to str
		for (int i=0;i < w - strnLength;i++)
			str += " ";

		// return the formatted string
		return str;
	}

	private static InfoNode createInfoNode(HuffNode[] tree,
													 	int i, int level,
													 	int dataWidth)
	{
		InfoNode newNode = null;
		int dx = dataWidth;

		if (i != HuffNode.NIL)
		{
			newNode = new InfoNode();
			// allocate node for left child at next level in tree; attach node
			InfoNode newLeft = createInfoNode(tree, tree[i].left, level+1, dataWidth);
			newNode.left = newLeft;

			// initialize data in new node
			newNode.value = tree[i];
			newNode.column = InfoNode.columnValue;
			newNode.level = level;
			// update column to position dx characters to right of current position
			InfoNode.columnValue += dx;

			// allocate node for right child at next level in tree; attach node
			InfoNode newRight = createInfoNode(tree, tree[i].right, level+1, dataWidth);
			newNode.right = newRight;
		}

		return newNode;
	}

	// output tree vertically
	public static String display(HuffNode[] tree)
	{
		final int DATAWIDTH = 7;
		int y = 0;
		InfoNode root = null;
		InfoNode currNode, prevNode;
		HuffNode h;
		String displayTree = "";

		// set column as 1
		InfoNode.columnValue = 1;
		// launch the inorder scan
		root = createInfoNode(tree, tree.length-1, y, DATAWIDTH);

		// store siblings of each InfoNode object in a queue so that
		// they are visited in order at the next level of the tree
		LinkedQueue<InfoNode> q = new LinkedQueue<InfoNode>();

		// objects to maintain current print position on a line
		int i, currPosition = 1;

		// insert the root in the queue and initialize prevNode as root
		q.push(root);
		prevNode = root;

		// continue the iterative process until the queue is empty
		while(!q.isEmpty())
		{
			// delete front node from queue and set as current node
			currNode = (InfoNode)q.pop();

			// if current node on the next level, output newlines.
			// reset currPosition (on line) back to 0
			if (prevNode.level < currNode.level)
			{
				displayTree += "\n";
				currPosition = 0;
			}

			// use spaces to move from current position to x position of node
			for (i = currPosition; i < currNode.column; i++)
				displayTree += " ";
			// output data value and update currPosition dataWidth chars
			h = currNode.value;
			if (h.left == HuffNode.NIL)
				if (h.b >= ' ' && h.b <= '~')
					displayTree += (char)h.b + ":" + formatInt(5, h.freq);
				else
					displayTree += "x" + hex(h.b)
						  + ":" + formatInt(3, h.freq);
			else
					displayTree += formatInt(7, h.freq);
			currPosition = currNode.column + DATAWIDTH;
			// update prevNode for the next iteration
			prevNode = currNode;

			// if a left child exists, insert it in the queue
			if(currNode.left != null)
				q.push(currNode.left);
			// if a right child exists, insert next to its sibling
			if(currNode.right != null)
				q.push(currNode.right);
		}

		return displayTree;
	}
}

class InfoNode
{
	public static int columnValue;	// x position on the line
	public HuffNode value;			// node value
	public int column, level;		// horizontal position and level (line) position
	public InfoNode left, right;

	public InfoNode ()
	{}
}

