import ds.util.RBTree;

public class Program27_1
{
	public static void main (String[] args)
	{
		// list of elements for the red-black tree
		int[] intArr = {10, 25, 40, 15, 50, 45, 30, 65, 70, 55}; 
		RBTree<Integer> rbtree = new RBTree<Integer>();
		int i;
		
		// load the tree with values from intArr; display 
		// available after each insert
		for(i = 0; i < intArr.length; i++)
		{
			rbtree.add(intArr[i]);
			rbtree.drawTrees(4);
		}
		
		// display the final tree in the console window
		System.out.println(rbtree.displayTree(2));
		
		// remove red-node 25
		rbtree.remove(25);
		rbtree.drawTrees(4);
		
		// remove black-node root
		rbtree.remove(45);
		rbtree.drawTree(3);
	}
}
