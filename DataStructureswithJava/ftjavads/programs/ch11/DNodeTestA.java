import ds.util.DNode;
import ds.util.DNodes;

public class DNodeTestA
{
	public static void main (String[] args)
	{
		DNode<String> header = new DNode<String>();
		String[] strArr = {"red", "blue", "green"};
		
		for (int i = 0; i < 3; i++)
			DNodes.addBefore(header, strArr[i]);
		System.out.println(DNodes.toString(header));
	}
}
