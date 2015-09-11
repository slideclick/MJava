import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import ioutil.*;

public class DemoDialogTester extends JFrame{

   //////////////////////////////////////////////////// main

   public static void main (String[] args){
      JFrame theFrame = new DemoDialogTester();
      theFrame.setSize (125, 125);
      theFrame.setVisible (true);
   }

   //////////////////////////////////////////////////// Model

   private Dog dog;

   //////////////////////////////////////////////////// View

   // Create controls 
   private JTextArea taDisplayDog = new JTextArea ("");
   private JButton btModify       = new JButton ("Modify");

   public DemoDialogTester(){

      // Set title
      setTitle("Doggie Dialog Tester");
 
      // Initialize the dog object
      dog = new Dog();
      dog.setName("Suzie"); dog.setAge(3);

      // Initialize the taDisplayDog
      taDisplayDog.setText(dog.toString());
      taDisplayDog.setEditable(false);

      // Create container and layout
      Container contentPane = getContentPane();
      EasyGridLayout layout = new EasyGridLayout();
      contentPane.setLayout (layout);
      
      // Set constraints
      layout.setConstraints(taDisplayDog ,1,1,1,1); 
      layout.setConstraints(btModify     ,2,1,1,1);

      // Add controls to container
      contentPane.add (taDisplayDog);
      contentPane.add (btModify);

      // Specify listeners
      btModify.addActionListener(new btModifyListener());
      addWindowListener(new MyWindowAdapter());   
   }
   
   //////////////////////////////////////////////////// Controller

   // Modify button listener
   private class btModifyListener implements ActionListener{
      public void actionPerformed (ActionEvent event){
         DemoDialog dlg = 
            new DemoDialog(DemoDialogTester.this, dog);
         dlg.show();
         if (dlg.getDlgCloseIndicator().equals("OK")){
            taDisplayDog.setText(dog.toString());
         }
      }
   }

   // Window listener
   private class MyWindowAdapter extends WindowAdapter{
      public void windowClosing (WindowEvent e){
         System.exit(0);
      }
   }
}