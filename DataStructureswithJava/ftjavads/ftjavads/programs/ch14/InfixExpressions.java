import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class InfixExpressions
	extends JFrame implements ActionListener
{
   JTextField infixField;
   JTextArea outputArea;

   public static void main(String[] args)
   {
      InfixExpressions app = new InfixExpressions();
   }

   public InfixExpressions()
   {
      setTitle("Infix Expressions");
      setSize(350,200);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      Container c = getContentPane();
      c.setLayout(new BorderLayout());

      JPanel panel = new JPanel();
      panel.setLayout(new FlowLayout());
      panel.add(new JLabel("Infix"));
      infixField = new JTextField(25);
      infixField.addActionListener(this);
      panel.add(infixField);

      outputArea = new JTextArea(25,10);

      c.add(panel, BorderLayout.NORTH);
      c.add(new JScrollPane(outputArea), BorderLayout.CENTER);
      setVisible(true);
   }

	public void actionPerformed(ActionEvent ae)
   {
	  	// variables used for strings, objects and result
		int result;
	  	String infixStr = "", postfixStr;
	  	InfixToPostfix infixObj = new InfixToPostfix();
	  	PostfixEval postfixObj = new PostfixEval();

	  	// input the infix expression and assign to infixObj
		infixStr = infixField.getText();
	  	outputArea.append("Infix Expression: " + infixStr + "\n");
		infixObj.setInfixExp(infixStr);

		// convert to postfix expression; catch any error in the
		// conversion to postfix, and output the message
		try
		{
			postfixStr = infixObj.toPostfix();
			outputArea.append("    Postfix: " + postfixStr + "     ");
		}
		// catch block outputs the error
		catch (ArithmeticException exc)
		{
			outputArea.append("     " + exc.getMessage() + "\n");
			infixField.setText("");
			return;
		}

		// no errors so assign postfix expression to postfixObj
		// and carry out the calculation
		postfixObj.setPostfixExp(postfixStr);
		result = postfixObj.evaluate();

		// output the result and clear the text field
		outputArea.append("Value = " + result + "\n");
 	  	infixField.setText("");
 	}
}

/*
Run:

Enter an infix expression: 3 ^ 2 ^ (1+2)
The postfix form is 3 2 1 2 + ^ ^
Value of the expression = 6561

Enter an infix expression: 3 * (4 - 2 ^ 5) + 6
The postfix form is 3 4 2 5 ^ - * 6 +
Value of the expression = -78

Enter an infix expression: (7 + 8*7
Infix2Postfix : Missing ')'

Enter an infix expression: (9 + 7) 4
Infix2Postfix : Operator expected

Enter an infix expression: 2*4*8/
Infix2Postfix : Operand expected

Enter an infix expression:
*/

