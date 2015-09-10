import ds.time.Time24;
import java.io.*;

class NotSerializableExceptionClass implements Serializable
{
	public int y = 200;
	public Rectangle rect = new Rectangle(4,5);	// not Serializable

	public NotSerializableExceptionClass()
	{ }
}

public class NotSerializableExceptionDemo
{
	public static void main(String[] args) throws Exception
	{
		NotSerializableExceptionClass obj;
		ObjectOutputStream oos = new ObjectOutputStream(
					new FileOutputStream("test.dat"));

		obj = new NotSerializableExceptionClass();

		oos.writeObject(obj);
		oos.flush();
		oos.close();
	}
}

/*
RUN

Exception in thread "main" java.io.NotSerializableException: Rectangle
	at java.io.ObjectOutputStream.writeObject0(ObjectOutputStream.java:1054)

   at java.io.ObjectOutputStream.defaultWriteFields(ObjectOutputStream.java:1332)
	at java.io.ObjectOutputStream.writeSerialData(ObjectOutputStream.java:1304)
	at java.io.ObjectOutputStream.writeOrdinaryObject(ObjectOutputStream.java:1247)
	at java.io.ObjectOutputStream.writeObject0(ObjectOutputStream.java:1052)

	at java.io.ObjectOutputStream.writeObject(ObjectOutputStream.java:278)
	at NotSerializableExceptionDemo.main(NotSerializableExceptionDemo.java:23)
*/

