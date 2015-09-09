import java.util.Random;
import java.util.Scanner;
import ds.util.Node;
import ds.util.Nodes;	// methods toString() and remove()

public class Program10_1
{
	public static void main(String[] args)
	{
		// declare references; by setting front to null,
		// the initial list is empty
		Node<Integer> front = null, newNode, p;

		// variables to create list and setup keyboard input
		Random rnd = new Random();
		Scanner keyIn = new Scanner(System.in);
		int listCount, i;

		// prompt for the size of the list
		System.out.print("Enter the size of the list: ");
		listCount = keyIn.nextInt();

		// create a list with nodes having random integer values
		// from 0 to 99; insert each element at front of the list
		for (i = 0; i < listCount; i++)
		{
			newNode = new Node<Integer>(rnd.nextInt(100));
			newNode.next = front;
			front = newNode;
		}

		System.out.print("Original list:  ");
		System.out.println(Nodes.toString(front));

		System.out.print("Ordered list:   "); 
		// continue finding the maximum node and erasing it
		// until the list is empty
		while (front != null)
		{
			p = getMaxNode(front);
			System.out.print(p.nodeValue + "  ");
			front = Nodes.remove(front, p.nodeValue);
		}
		System.out.println();
	}

	// return a reference to the node with the maximum value
	public static <T extends Comparable<? super T>>
	Node<T> getMaxNode(Node<T> front)
	{
		// maxNode maintains a reference to node containing largest
		// value (maxValue); initially maxNode is front and
		// maxValue is front.nodeValue; scan using reference curr
		// starting with the second node (front.next)
		Node<T> maxNode = front, curr = front.next;
		T maxValue = front.nodeValue;

		while (curr != null)
		{
			// see if maxValue < curr.nodeValue; if so, update
			// maxNode and maxValue. then continue scan at next node
			if (maxValue.compareTo(curr.nodeValue)< 0)
			{
				maxValue = curr.nodeValue;
				maxNode = curr;
			}
			curr = curr.next;
		}
		return maxNode;
	}
}

/*
Run:

Enter the size of the list: 9
Original list:  [77, 83, 14, 38, 70, 35, 55, 11, 6]
Ordered list:   83  77  70  55  38  35  14  11  6  
*/
