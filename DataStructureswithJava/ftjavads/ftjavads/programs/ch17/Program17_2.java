import ds.util.TNode;
import ds.util.BinaryTree;
import ds.util.InorderIterator;
import ds.time.Time24;

public class Program17_2
{
	public static void main(String[] args)
	{
		// roots for the tree
		TNode<Time24> root;

		// build a tree of Time24 data
		root = BinaryTree.buildTime24Tree();

		// display the tree
		System.out.println("Original tree");
		System.out.println(BinaryTree.displayTree(root, 5) + "\n");

		// declare an inorder tree iterator
		InorderIterator<Time24> iter =
			new InorderIterator<Time24>(root);

		// go through the tree and add 1 hour to each time
		while (iter.hasNext())
		{
			// obtain the value in a tree node
			Time24 t = iter.next();

			// add 1 hour to the time
			t.addTime(60);
		}

		System.out.println("Modified tree");
		System.out.println(BinaryTree.displayTree(root, 5));

		// delete the nodes in the tree
		BinaryTree.clearTree(root);
	}
}

/*
Run:

Original tree
                    3:15
             18:35                         20:55
 10:45                         12:00             15:30
        5:15              7:30        9:15


Modified tree
                    4:15
             19:35                         21:55
 11:45                         13:00             16:30
        6:15              8:30       10:15
*/
