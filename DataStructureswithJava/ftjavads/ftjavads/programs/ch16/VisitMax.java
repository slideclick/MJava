public class VisitMax<T extends Comparable<? super T>>
	implements Visitor<T>
{
	T max = null;

	public void visit(T obj)
	{
		if (max == null)
			max = obj;
		else if (obj.compareTo(max) > 0)
			max = obj;
	}

	public T getMax()
	{
		return max;
	}
}
