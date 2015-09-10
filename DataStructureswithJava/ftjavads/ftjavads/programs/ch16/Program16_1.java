import ds.util.TNode;
import ds.util.BinaryTree;

public class Program16_1
{
	public static void main(String[] args)
	{
		// root of the tree
		TNode<Integer> root;

		// create the Visitor objects
		VisitOutput<Integer> output = new VisitOutput<Integer>();
		VisitMax<Integer> max = new VisitMax<Integer>();

		// create the tree using buildTree16_1
		root = buildTree16_1();

		// output the recursive scans and the level order scan
		System.out.println("Scans of the tree");
		System.out.println("   Preorder scan:    " +
					BinaryTree.preorderDisplay(root));
		System.out.println("   Inorder scan:     " +
					BinaryTree.inorderDisplay(root));
		System.out.println("   Postorder scan:   " +
					BinaryTree.postorderDisplay(root));
		System.out.println("   Level order scan: " +
					BinaryTree.levelorderDisplay(root) + "\n");

		// use Visitor object and scanInorder() to traverse the
		// tree and determine the maximum value
		System.out.println(
				"Call scanInorder() with VisitOutput: ");
		scanInorder(root, output);
		System.out.println();

		scanInorder(root, max);
		System.out.println(
				"Call scanInorder() with VisitMax: Max value is " +
				max.getMax());
	}

	public static <T> void scanInorder(TNode<T> t, Visitor<T> v)
	{
		if (t != null)
		{
			scanInorder(t.left, v);
			v.visit(t.nodeValue);
			scanInorder(t.right, v);
		}
	}

	public static TNode<Integer> buildTree16_1()
   {
      // TNode references; point to the 8 items in the tree
      TNode<Integer> root20 = null, t45, t15, t30,
      								  t5, t10, t25, t35;

		t35 = new TNode<Integer>(35);
		t25 = new TNode<Integer>(25);
		t10 = new TNode<Integer>(10, null, t35);
		t5 = new TNode<Integer>(5);
		t30 = new TNode<Integer>(30, t25, null);
		t15 = new TNode<Integer>(15, t5, t10);
		t45 = new TNode<Integer>(45, null, t30);
		root20 = new TNode<Integer>(20, t45, t15);

      return root20;
   }
}

/*
Run:

Scans of the tree
   Preorder scan:    20  45  30  25  15  5  10  35
   Inorder scan:     45  25  30  20  5  15  10  35
   Postorder scan:   25  30  45  5  35  10  15  20
   Level order scan: 20  45  15  30  5  10  25  35

Call scanInorder() with VisitOutput:
45  25  30  20  5  15  10  35
Call scanInorder() with VisitMax: Max value is 45
*/
