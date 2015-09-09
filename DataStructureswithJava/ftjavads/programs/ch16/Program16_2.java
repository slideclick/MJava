import ds.util.TNode;
import ds.util.BinaryTree;

public class Program16_2
{
	public static void main(String[] args)
	{
		// roots for two trees
		TNode<Character> root, copyRoot;

		// build the character Tree 2 with root root2
		root = BinaryTree.buildTree(2);

		// display the original tree on the console
		System.out.println(BinaryTree.displayTree(root, 1));

		// make a copy of root1 so its root is root2
		copyRoot = BinaryTree.copyTree(root);

		// graphically display the tree copy
		BinaryTree.drawTree(copyRoot, 1);
	}
}

/*
Run:

Original tree                Duplicate Tree
       A
     B         C
 D         E     F
   G     H   I
*/
