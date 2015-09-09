import java.math.BigInteger;

public class Program28_1
{
	public static void main(String[] args)
	{
		BigInteger p, q, n;		// define Z(n)
		BigInteger t, e, d;		// parts of the public and secret keys
		
		String clientMsg, serverMsg;	// sent and received messages
		
		// BigInteger variables for data encryption
		BigInteger strData, encryptedData, decryptedData;			
		
		// create BigInteger objects p = 5, q = 11
		p = new BigInteger("5");
		q = new BigInteger("11");
		
		// compute n = p * q
		n = p.multiply(q);
		
		// use BigInteger operations to compute t = (p-1)*(q-1)
		t = p.subtract(BigInteger.ONE).multiply(
		q.subtract(BigInteger.ONE));
		
		// create BigInteger e = 3 which is relatively prime to
		// t. that is (e,t)=1
		e = new BigInteger("3");
		
		// modInverse() returns d, the inverse of e (mod t); 
		// that is e*d = 1 (mod t)
		d = e.modInverse(t);
		
		// convert the single characater string "1" to a BigInteger
		clientMsg = "1";
		strData = new BigInteger(clientMsg.getBytes());
		System.out.println("Client message: \"" + clientMsg +
		"\"  Data value: " + strData);
		
		// use modPow() to encrypts strData by raising it to power e mod n 
		encryptedData = strData.modPow(e,n);
		System.out.println("Encrypted data: " + encryptedData);
		
		// decrypt the encrypted data by raising it to power d mod n 
		decryptedData = encryptedData.modPow(d,n);
		System.out.println("Decrypted data: " + decryptedData);
		
		// convert BigInteger back to a string
		serverMsg = new String(decryptedData.toByteArray());
		System.out.println("Server message: \"" + serverMsg + "\"");
	}
}
