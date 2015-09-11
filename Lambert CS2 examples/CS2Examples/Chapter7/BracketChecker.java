// Checks expressions for matching brackets

// Inputs:

// 1. A string of bracket pairs, of the form "<b1a><b1b> . . . <b2a><b2b>"
//    Example: "[]()"

// 2. An expression (any string)

// Outputs:

// 1. An echo of the input expression
// 2. OK, if the brackets match, or Not OK otherwise

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import ioutil.*;

public class BracketChecker extends JFrame{

   //////////////////////////////////////////////////////// main

   public static void main (String[] args){
      BracketChecker theGUI = new BracketChecker();
      theGUI.setSize (300, 300);
      theGUI.setVisible (true);
   }


   //////////////////////////////////////////////////////// View

   // Create controls 
   private JLabel     bracketLabel   = new JLabel("Bracket symbols"); 
   private JTextField bracketField   = new JTextField("");               
   private JLabel     inputLabel     = new JLabel("Input string");    
   private JTextField inputField     = new JTextField("");                 
   private JButton    checkButton    = new JButton("Check Brackets");
   private JTextArea  outputArea     = new JTextArea("");                

   public BracketChecker(){
      // Set title and initialize
      setTitle("Bracket Checker");
		
		// Create container and layout
      Container contentPane = getContentPane();
      EasyGridLayout layout = new EasyGridLayout();
      contentPane.setLayout (layout);
      
      // Set constraints
      JScrollPane spOutput = new JScrollPane(outputArea);
      layout.setConstraints(bracketLabel, 1,1,1,1); 
      layout.setConstraints(bracketField, 1,2,1,1); 
      layout.setConstraints(inputLabel  , 2,1,1,1); 
      layout.setConstraints(inputField  , 2,2,1,1);
		layout.setConstraints(checkButton , 3,1,2,1); 
      layout.setConstraints(spOutput    , 4,1,3,1);
		
	   // Add controls to container
      contentPane.add (bracketLabel);
      contentPane.add (bracketField);
      contentPane.add (inputLabel);
      contentPane.add (inputField);
      contentPane.add (checkButton);
      contentPane.add (spOutput);
 
      // Specify listeners
      checkButton.addActionListener(new CheckListener(this));
      addWindowListener(new MyWindowAdapter());   
   }

   //////////////////////////////////////////////////////// Controller

   // Button action listener
   private class CheckListener implements ActionListener{
	
	   private BracketChecker view;
		
	   private CheckListener(BracketChecker v){
		   view = v;
		}
		
      public void actionPerformed (ActionEvent event){
         String bracketString = bracketField.getText();
         if (bracketString.length() < 2 || bracketString.length() % 2 != 0){
            new MessageBox(view, "Must enter even number of bracket symbols", 200, 50);
            return;
         }
         String inputString = inputField.getText();
         outputArea.append (inputString + "\n");
         if (bracketsBalance (inputString, bracketString))
            outputArea.append ("OK\n");
         else
            outputArea.append ("Not OK\n");
      }

      // An opening bracket will be at an even-numbered index in the brackets string
      // The matching bracket will be at index + 1 in the brackets string
   
      private boolean bracketsBalance (String exp,   //exp represents the expression
                                       String brackets){  // brackets contains bracket symbols  
         StackPT stk = new LinkedStackPT();                     //Create a new stack
         for (int i = 0; i < exp.length(); i++){        //Scan across the expression
            char ch = exp.charAt(i);
            int inputPos = brackets.indexOf(ch);
            if (inputPos % 2 == 0){   //Push an opening bracket onto the stack
               stk.push (new Character(ch));
            }else if (inputPos != -1){            //Process a closing bracket
               if (stk.isEmpty())                 //If the stack is empty, then error
                  return false;                  
               char charFromStack = ((Character)stk.pop()).charValue();
               int stackPos = brackets.indexOf(charFromStack);
               if ((stackPos - inputPos + 1) != 0)
                  return false;                         //If the opening and closing
                                    //brackets are of different types, then error
            }
         } 
         return stk.isEmpty();      //If the stack is empty then no error else error
      }
	}
	
   // Window listener
   private class MyWindowAdapter extends WindowAdapter{
      public void windowClosing (WindowEvent e){
         System.exit(0);
      }
   }
            
}
