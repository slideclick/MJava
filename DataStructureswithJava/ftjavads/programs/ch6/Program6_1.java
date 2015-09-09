import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Program6_1 extends JFrame
{
   JTextField decimalField, baseField;
   JButton binButton, octButton, hexButton;
   JLabel baseLabel;

   public static void main(String[] args)
   {
      Program6_1 app = new Program6_1();
      app.setVisible(true);
   }

   public Program6_1()
   {
      setTitle("Multibase");
      setSize(275,100);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      Container c = getContentPane();
      c.setLayout(new BorderLayout());

      JPanel displayPanel = new JPanel();
      // left-justify each component in the flow layout
      displayPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
      decimalField = new JTextField(4);
      baseField = new JTextField(10);
      baseLabel = new JLabel("       ");
      displayPanel.add(new JLabel("Decimal"));
      displayPanel.add(decimalField);
      displayPanel.add(baseLabel);
      displayPanel.add(baseField);

      JPanel commandPanel = new JPanel();
      commandPanel.setLayout(new FlowLayout());

      binButton = new JButton("Binary");
      binButton.addActionListener(new ConvertNumber());
      commandPanel.add(binButton);

      octButton = new JButton("Octal");
      octButton.addActionListener(new ConvertNumber());
      commandPanel.add(octButton);

      hexButton = new JButton("Hex");
      hexButton.addActionListener(new ConvertNumber());
      commandPanel.add(hexButton);

      c.add(displayPanel, BorderLayout.NORTH);
      c.add(commandPanel, BorderLayout.CENTER);
   }

	private class ConvertNumber implements ActionListener
	{
		public void actionPerformed(ActionEvent ae)
		{
			JButton buttonPressed = (JButton)ae.getSource();
			int n = Integer.parseInt(decimalField.getText());
			String str;
		
			if (buttonPressed == binButton)
			{
				baseLabel.setText(" Binary");
				str = baseString(n, 2);
			} else if (buttonPressed == octButton)
			{
				baseLabel.setText(" Octal");
				str = baseString(n, 8);
			}
			else
			{
				baseLabel.setText(" Hex");
				str = baseString(n, 16);
			}
			baseField.setText(str);
		}
	}

	// returns string representation of n as a base b number
	public static String baseString(int n, int b)
	{
	   String str = "", digitChar = "0123456789abcdef";
	
	   // if n is 0, return empty string
	   if (n == 0)
	      return "";
	   else
	   {
	   	// get string for digits in n/b
			str = baseString(n/b, b);			// recursive step
	
			// return str with next digit appended
			return str + digitChar.charAt(n % b);
	   }
	}
}