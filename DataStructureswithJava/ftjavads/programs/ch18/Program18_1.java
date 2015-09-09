import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import ds.util.STree;

public class Program18_1 extends JFrame
{
	private JLabel label = new JLabel("Item");
	private JTextField itemField = new JTextField(5);
	private JButton addButton, removeButton;
	private JTextArea textArea = new JTextArea();
	private String currTreeDisplay, prevTreeDisplay;
	private static Program18_1 appFrame;

	// initial values in the tree
	private int[] arr = {12, 3, 15, 8, 9, 5, 18, 25};
	private STree<Integer> tree = new STree<Integer>();

	public static void main(String[] args)
	{
		appFrame = new Program18_1();
		appFrame.setVisible(true);
	}

   public Program18_1()
   {
  		// build the initial tree
		for (int i=0;i < arr.length;i++)
 			tree.add(arr[i]);

  		setTitle("Binary Search Tree");
      setSize(350, 300);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      Container content = getContentPane();
      content.setLayout(new BorderLayout());

      JPanel panel = new JPanel();
      panel.add(label);
      panel.add(itemField);

      addButton = new JButton("Add");
      addButton.addActionListener(new ExpressionHandler());
      removeButton = new JButton("Remove");
      removeButton.addActionListener(new ExpressionHandler());
		panel.add(addButton);
      panel.add(removeButton);

      textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
      currTreeDisplay = tree.displayTree(2);
      textArea.setText(currTreeDisplay);
      content.add(panel, BorderLayout.SOUTH);
      content.add(new JScrollPane(textArea), BorderLayout.CENTER);
   }

	private class ExpressionHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent ae)
		{
			// ae.getSource() is a reference to the object that caused
			// the ActionEvent
			JButton buttonPressed = (JButton)ae.getSource();
			int n;
		
			// convert the string in input to an int
			n = Integer.parseInt(itemField.getText());
		
			if (n < 0 || n > 99)
			{
				JOptionPane.showMessageDialog(
						appFrame, "Integer must be in range from 0 to 99",
						"Data Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
		
			if (buttonPressed == addButton)
				tree.add(n);
			else
				tree.remove(n);
		
			// display the tree in the text area
			currTreeDisplay += "\n\n" + tree.displayTree(2);
			textArea.setText(currTreeDisplay);
			itemField.requestFocus(true);
		}
	}
}
