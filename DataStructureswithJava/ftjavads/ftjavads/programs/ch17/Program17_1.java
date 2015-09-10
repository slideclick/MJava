import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import ds.util.BinaryTree;
import ds.util.TNode;

public class Program17_1 extends JFrame
{
   JTextField expInput;
   JTextArea textArea;

   public static void main(String[] args)
   {
      Program17_1 app = new Program17_1();
      app.setVisible(true);
   }

   public Program17_1()
   {
      setTitle("Expression Trees");
      setSize(500, 300);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      Container content = getContentPane();
      content.setLayout(new BorderLayout());

      expInput = new JTextField(20);
      expInput.addActionListener(new ExpressionHandler());
      textArea = new JTextArea();
      textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
      content.add(expInput, BorderLayout.NORTH);
      content.add(new JScrollPane(textArea),
      				BorderLayout.CENTER);
   }
   
   private class ExpressionHandler implements ActionListener
   {
		public void actionPerformed(ActionEvent ae)
		{
			// obtain the expression the user typed
			String expression = expInput.getText();
			// build the expression tree
			TNode<Character> root =
				BinaryTree.buildExpTree(expression);
	
			// output the expression and its tree
			textArea.append("Expression tree for " +
								 expression + "\n\n");
			textArea.append(BinaryTree.displayTree(root, 1) + "\n");
			// output the scans
			textArea.append("Preorder scan: " +
				BinaryTree.preorderDisplay(root) + "\n\n");
			textArea.append("Inorder scan: " +
				BinaryTree.inorderDisplay(root) + "\n\n");
			textArea.append("Postorder scan: " +
				BinaryTree.postorderDisplay(root) + "\n\n");
			// clear the text field
			expInput.setText("");
		}
   }
}
