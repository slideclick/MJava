import ds.time.Time24;
import java.io.Serializable;

public class SerializableClass implements Serializable
{
	public int n;
	public String str;
	public Time24 t;
	public Integer[] list = new Integer[4];
	transient public Time24 currentTime;

	public SerializableClass(int n, String str, Time24 t, 
	                                Time24 currentTime)
	{
		this.n = n;
		this.str = str;
		this.t = t;
		for (int i = 0; i < list.length; i++)
			list[i] = new Integer(i + 1);
		this.currentTime = currentTime;
	}
}

