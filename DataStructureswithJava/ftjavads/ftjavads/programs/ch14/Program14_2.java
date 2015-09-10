import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import ds.util.ALStack;

public class Program14_2 extends JFrame implements ActionListener
{
	JTextField inputField;
	JTextArea outputArea;

	public static void main(String[] args)
	{
		Program14_2 app = new Program14_2();
	}

	public Program14_2()
	{
		setSize(300,150);
		setTitle("Balancing Symbol-Pairs");

		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		inputField = new JTextField(20);
		inputField.setFont(new Font("Monospaced",Font.PLAIN,12));
		inputField.addActionListener(this);

		outputArea = new JTextArea(20,10);
		outputArea.setFont(new Font("Monospaced",Font.PLAIN,12));
		c.add(inputField, BorderLayout.NORTH);
		c.add(new JScrollPane(outputArea), BorderLayout.CENTER);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setVisible(true);
	}

	public void actionPerformed(ActionEvent ae)
	{
		// read string from the text field called inputField
		String testStr = inputField.getText();

		// display string in the text area called outputArea
		outputArea.append(testStr);

		// call checkForBalance() and display return message
		outputArea.append(checkForBalance(testStr) + "\n");

		// clear the text field for the next input
		inputField.setText("");
	}

	public String checkForBalance(String expStr)
	{
		// holds left-symbols
		ALStack<Character> s = new ALStack<Character>();
		int i = 0;
		char scanCh = ' ', matchCh;
		String msgStr = "";
	
		while (i < expStr.length())
		{
			// access the character at index i
			scanCh = expStr.charAt(i);
	
			// check for left-symbol; if so, push on stack.
			// otherwise, check for right-symbol and check balancing
			if (scanCh == '(' || scanCh == '[' || scanCh == '{')
				s.push(scanCh);
			else if(scanCh == ')' || scanCh == ']' || scanCh == '}')
			{
				// get character on top of stack; if stack is empty,
				// catch the exception and return the error message
				try
				{
					matchCh = s.pop();
	
					// check for corresponding matching pair; if match
					// fails, return an error message
					if (matchCh == '(' && scanCh != ')'  ||
						 matchCh == '[' && scanCh != ']'  ||
						 matchCh == '{' && scanCh != '}')
					{
						msgStr += "^";
						return "\n" + msgStr + "  Missing left symbol";
					}
				}
				catch (RuntimeException e)
				{
					msgStr += "^";
					return "\n" + msgStr +	"  Missing left symbol";
				}
			}
			i++;
			msgStr += " ";
		}
		// at end of scan, check the stack; if empty,
		// return message that string is balanced; otherwise
		// return an error message
		if (s.isEmpty())
			return  "\n" + msgStr + " Expression is balanced";
		else
		{
			msgStr += "^";
			return "\n" + msgStr +	 "  Missing right symbol";
		}
	}
}
