import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

import ds.util.DNode;
import ds.util.DNodes;
import ds.util.ArrayList;

public class Program11_1G
	extends JFrame implements ActionListener
{
	// graphical components
   JButton wordButton, answerButton;
   JTextField wordField, answerField;
   JLabel status = new JLabel();
   // input the words from a textfile into wordList
   ArrayList<String> wordList = new ArrayList<String>();
   // the current word
	String word = "";
   // header for the doubly linked list of characters that
   // jumble the current word
   DNode<Character> header = new DNode<Character>();

	// index cycles through the list of words the user
	// sees as scrambled
   int index;
   // random number generator used to scamble words
   Random rnd;

   public static void main(String[] args)
   {
      Program11_1G wj = new Program11_1G();
      wj.setVisible(true);
   }

   public Program11_1G()
   {
 		// initialize the layout
 		setTitle("Word Jumble");
      setSize(350,150);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      Container c = getContentPane();
      c.setLayout(new BorderLayout());

      JPanel panelA = new JPanel();
      panelA.setLayout(new FlowLayout());

      panelA.add(new JLabel("Jumbled Word"));
      wordField = new JTextField(9);
      // do not allow the user to type into the
      // text field that contains the jumbled word
      wordField.setEditable(false);
      // change the background of the non-editable
      // text field from gray to white
      wordField.setBackground(Color.white);
      panelA.add(wordField);

      panelA.add(new JLabel("Answer"));
      answerField = new JTextField(6);
      answerField.addActionListener(this);
      panelA.add(answerField);

      JPanel panelB = new JPanel();
      // left-justify the word and panel components of
      // this FlowLayout. keeps the button from moving
      // around as the contents of the label change
      panelB.setLayout(new FlowLayout(FlowLayout.LEFT));

      wordButton = new JButton("Get Word");
      wordButton.addActionListener(this);
      panelB.add(wordButton);
      panelB.add(status);

 		// add the panels to the BorderLayout
 		c.add(panelA, BorderLayout.NORTH);
      c.add(panelB, BorderLayout.CENTER);

		// create the random number generator
      rnd = new Random();
      // read the words
      setupList();
      
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }

   public void setupList()
   {
		// open the textfile
      Scanner fin = null;
      
      try
      {
      	fin = new Scanner(new FileReader("jumblewords.dat"));
      }
      catch (IOException ioe)
      {
			JOptionPane.showMessageDialog(
					this,
					"Cannot open the file \"jumblewords.dat\"",
					"File open error",
					JOptionPane.ERROR_MESSAGE);
      	System.exit(1);
      }
      
      String word;

		// input words from the textfile until EOF, adding
		// each string to the back of the ArrayList wordList
      while(fin.hasNext())
      {
         word = fin.next();
         wordList.add(word);
      }

 		// access words in the order
 		// index = 0, 1, 2, ..., wordList.size()-1, 0, 1, ...
 		index = 0;
   }

	// listener for ActionEvents
	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getSource() == wordButton)
		{
			// header node for list holding jumbled characters
			int i;
			String jumbleword = "";

			// clear solution and status labels from a previous word
			answerField.setText("");
			status.setText("");

			// retrieve a word from the list
			word = (String)wordList.get(index);
			// advance index. if index == wordList.size()-1,
			// set index = 0
			index = (index + 1)%wordList.size();

			// use rnd.nextInt(2) to determine if char is inserted
			// at the front (value = 0) or back (value = 1) of list
			for (i = 0; i < word.length(); i++)
				if (rnd.nextInt(2) == 0)
					// add at the front of the list
					DNodes.addBefore(header.next,
										  new Character(word.charAt(i)));
				else
					// insert at the back of the list
					DNodes.addBefore(header,
										  new Character(word.charAt(i)));

			// create the jumbled word and clear the list
			while (header.next != header)
			{
				jumbleword += header.next.nodeValue;
				DNodes.remove(header.next);
			}

			// display the jumbled word in the textfield wordField
			wordField.setText(jumbleword);
		}
		else if (ae.getSource() == answerField)
		{
			// user pressed <Enter> after typing a solution
			// see if it corrent and display response in
			// the label status
			if (answerField.getText().equals(word))
				status.setText("Congratulations!");
			else
			{
				status.setText(
						"Your answer is not correct. Try again!");
				// clear the wrong answer
				answerField.setText("");
			}
		}
	}
}
