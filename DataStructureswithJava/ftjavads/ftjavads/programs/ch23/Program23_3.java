import ds.time.Time24;
import ds.util.Arrays;
import java.io.*;

public class Program23_3
{
	public static void main(String[] args) throws Exception
	{
		// objects used for serialization
		SerializableClass obj, recallObj;

		// object stream connected to file "storeFile" for output
		ObjectOutputStream oos = new ObjectOutputStream(
					new FileOutputStream("storeFile.dat"));

		// initial object with runtime update of 45 minutes
		obj = new SerializableClass(45, "Shooting star",
		         new Time24(9,30), new Time24(7, 10));
		obj.t.addTime(45);

		// output object info before copy to the file
		System.out.println("Serialized object:");
		System.out.println("    Integer: " + obj.n + " String: " +
			obj.str + " Time: " + obj.t + "\n    Current time: " +
			obj.currentTime + " List: " + Arrays.toString(obj.list));

		// send object and close down the output stream
		oos.writeObject(obj);
		oos.flush();
		oos.close();

		// object stream connected to file "storeFile" for output
		ObjectInputStream ois = new ObjectInputStream(
						new FileInputStream("storeFile.dat"));

		// reconstruct object and allocate new currentTime object
		recallObj = (SerializableClass)ois.readObject();
		recallObj.currentTime = new Time24(15, 45);
		// output object after recall from the file
		System.out.println("Deserialized object:");
		System.out.println("    Integer: " + recallObj.n +
		   " String: " + recallObj.str + " Time: " +
		   recallObj.t + '\n' + "    Current time: " +
		   recallObj.currentTime + " List: " +
		   Arrays.toString(obj.list));
	}
}

/*
Run:

Serialized object:
    Integer: 45 String: Shooting star Time: 10:15
    Current time: 7:10 List: [1, 2, 3, 4]
Deserialized object:
    Integer: 45 String: Shooting star Time: 10:15
    Current time: 15:45 List: [1, 2, 3, 4]
*/