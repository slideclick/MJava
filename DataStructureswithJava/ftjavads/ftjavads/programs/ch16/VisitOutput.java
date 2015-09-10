public class VisitOutput<T> implements Visitor<T>
{
	public void visit(T obj)
	{
		System.out.print(obj + "  ");
	}
}
