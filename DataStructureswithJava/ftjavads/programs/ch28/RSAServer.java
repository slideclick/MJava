// RSA Server

import java.net.*;
import java.io.*;
import java.util.*;
import java.math.BigInteger;

public class RSAServer  // the server program
{
	public static void main(String[] args)
	{
		ServerSocket svListenSock;
		Socket svCommSock;
		Thread svChildProcess = null;
		ServerChild svChild;		// Thread svChildProcess;

		try
		{
			System.out.println( "Server: registered at port 8002");
			svListenSock = new ServerSocket(8002);

			while (true)
			{
				svCommSock = svListenSock.accept();
				System.out.println( "Server: Connected to client");
				svChild = new ServerChild(svCommSock);
				svChildProcess = new Thread(svChild);
				svChildProcess.start();						// run() of child process
			}
		}
		catch( Exception e )
		{
			System.err.println( "Exception in main code: " + e);
		}
	}
}

class ServerChild implements Runnable
{
	private Socket sock;
	private ObjectInputStream svIn = null;		// used to get client data
	private ObjectOutputStream svOut = null;	// used to send client data
	private String str;

	// used to compute t and by randomModValue()
	final BigInteger one = new BigInteger("1");

	// encryption and decryption parameters
	private BigInteger n,e,d;
	private Random rnd;

	public ServerChild( Socket s )
	{ 	sock = s;
		rnd = new Random();
	}

	public void run()
	{
		BigInteger msgInt;
		String message;
		try
		{
			svOut = new ObjectOutputStream(sock.getOutputStream());
			svOut.flush();
			svIn = new ObjectInputStream(sock.getInputStream());
			setupKeys(200);
			System.out.println("Server: Sending client n = " + n.toString());
			System.out.println("Server: Sending client e = " + e.toString());
			svOut.writeObject(n);
			svOut.flush();
			svOut.writeObject(e);
			svOut.flush();
			System.out.println("----------------------------------------------" +
						"----------------------------------\n");

			while(true)
			{
				msgInt = (BigInteger)svIn.readObject();
				msgInt = decrypt(msgInt);
				message = getMessage(msgInt);
				System.out.println("Server: BigInteger received: " + msgInt.toString(16));
				System.out.println("Server: Message received: " + message);
			System.out.println("----------------------------------------------" +
						"----------------------------------\n");
			}
		}
		catch ( Exception e )
		{
			System.out.println("Cannot open server socket streams"); }
		finally
		{
			try
			{
				svOut.close();
				svIn.close();
				sock.close();
			}
			catch ( Exception e ) {}
		}
	}

	// make a new key with number of bits
	// for security, nbits > 500 is relatively safe
	private void setupKeys(int nbits)
	{
		BigInteger p,q,t;
  		// create two random randomly generated positive BigInteger
  		// values with the specified bitLength
  		p = new BigInteger(nbits/2,100,rnd);
  		q = new BigInteger((nbits + 1)/2,100,rnd);

  		// define n = p * q
		n = p.multiply(q);

		// define t = (p-1)*(q-1)
		t = p.subtract(one).multiply(q.subtract(one));

		// create a number that is relatively prime to t (e,t)=1
		e = randomModValue(t);

		// let d be the inverse of e mod t  e*d = 1 (mod t)
		d = e.modInverse(t);
	}

	private BigInteger randomModValue(BigInteger f)
	{
		// returns a number relatively prime to f
		BigInteger num;
		// generate a sequence of random numbers; break when
		// the number is relatively prime to f
		do
		{
			num = new BigInteger(128,rnd);
		} 	while(!f.gcd(num).equals(one));
		return num;
	}

 	// reverses encryption which returns original message
 	// return m^d (mod n)
 	private BigInteger decrypt(BigInteger m)
 	{
  		return m.modPow(d,n);
 	}

 	// the inverse of createMessage.
 	private String getMessage(BigInteger b)
 	{
  		// convert the BigInteger to a byte array
  		byte[] bigIntByteArray = b.toByteArray();

  		// length of message stored in first byte
  		int msgLength = bigIntByteArray[0];

  		// create byte array for the message with size msgLength
  		byte[] msgByteArray = new byte[msgLength];

  		// copy the msgLength number of bytes to the message byte array
  		for(int i = 0; i < msgByteArray.length; i++)
   		msgByteArray[i] = bigIntByteArray[i + 1];
  		// return the message byte array as a string
  		return new String(msgByteArray);
 	}
}

