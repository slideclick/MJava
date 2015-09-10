// File: SimpleClient.java
import java.net.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.math.BigInteger;

class RSAClient extends JFrame implements ActionListener   // the client program
{
	// client gets server's data from this stream
	private ObjectInputStream clIn = null;
	private ObjectOutputStream clOut = null;
	private Socket clCommSock = null;
	private JLabel msgLabel;
	private JTextField jtf;
	private JButton  connectButton;
	private BigInteger n, e;

	public static void main( String argv[] )
	{
		RSAClient app = new RSAClient();
	}

	public RSAClient()
	{
		setTitle("RSA Encryption");
		setSize(350, 100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container content = getContentPane();
		content.setLayout(new BorderLayout());

		JPanel panelA = new JPanel();
		msgLabel = new JLabel("Message");
		jtf = new JTextField(20);
		jtf.addActionListener(this);
		panelA.add(msgLabel);
		panelA.add(jtf);

		connectButton = new JButton("Connect");
		connectButton.addActionListener(this);

		content.add(panelA, BorderLayout.CENTER);
		content.add(connectButton, BorderLayout.SOUTH);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent ae)
	{
		BigInteger msgInt;
		String str;

		if (ae.getSource() == connectButton)
		{
			try
			{
				// connecting to server at port 8002
				clCommSock = new Socket( InetAddress.getLocalHost(), 8002 );

				clIn = new ObjectInputStream(clCommSock.getInputStream());
				clOut = new ObjectOutputStream(clCommSock.getOutputStream());
				clOut.flush();
				n = (BigInteger)clIn.readObject();
				e = (BigInteger)clIn.readObject();
				connectButton.setEnabled(false);
			}
			catch (Exception e)
			{
				System.out.println("Trouble with server connection");
			}
		}
		else
		{
			try
			{
				str = jtf.getText();
				msgInt = convertMessage(str);
				clOut.writeObject(encrypt(msgInt));
				clOut.flush();
				jtf.setText("");
				jtf.requestFocus();
			}
			catch (IOException ioe)
			{ }
		}
	}

 	// encrypt 'message' m with public key; require 1 <= m < n.
 	public BigInteger encrypt(BigInteger m)
 	{ return m.modPow(e,n); }

 	// convert byte array (message) to BigInteger;
 	// first add size as first byte and pad with random bytes
 	public BigInteger convertMessage(String msg)
 	{
		byte[] msgByteArray = msg.getBytes();

  		if(msgByteArray.length * 8 >= n.bitLength())
    		throw new IllegalArgumentException("Message too long");

  		byte[] msgByteArrayWithSize = new byte[n.bitLength()/8];

  		for(int i = 0; i < msgByteArray.length; i++)
   		msgByteArrayWithSize[i + 1] = msgByteArray[i];
  		msgByteArrayWithSize[0] = (byte)msgByteArray.length;

  		int index = msgByteArrayWithSize.length;
  		for(int i = index + 1; i < index; i++)
    		msgByteArrayWithSize[i] = (byte)(Math.random()*256);
  		return new BigInteger(msgByteArrayWithSize);
 	}
}
