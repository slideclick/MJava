import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

// node object for the Huffman tree that goes in the
// compressed file
public class DiskHuffNode
{
	// byte stored
	public byte b;

	// indices of children
	public short left;
	public short right;

	// default constructor. use when want to assign
	// data "by hand" or use read()
	public DiskHuffNode()
	{}

	public DiskHuffNode(byte b, short left, short right)
	{
		this.b = b;
		this.left = left;
		this.right = right;
	}

	public void write(DataOutputStream out) throws IOException
	{
		out.writeByte(b);
		out.writeShort(left);
		out.writeShort(right);
	}

	public void read(DataInputStream in) throws IOException
	{
		b = in.readByte();
		left = in.readShort();
		right = in.readShort();
	}

	public static int sizeof()
	{
		return 5;
	}
}

