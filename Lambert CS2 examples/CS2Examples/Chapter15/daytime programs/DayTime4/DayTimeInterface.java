import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import ioutil.*;

public class DayTimeInterface extends JFrame{

   //////////////////////////////////////////// main

   public static void main(String[] args){
      JFrame frm = new DayTimeInterface();
      frm.setSize (400, 300);
      frm.setTitle("Day Time Server");
      frm.setVisible(true);
   }

   //////////////////////////////////////////// View

   // Create control
   private JTextArea output = new JTextArea("");
	
	public DayTimeInterface(){
      // Set title
      setTitle("Main Application");

      // Create container and layout
      Container contentPane = getContentPane();
      EasyGridLayout layout = new EasyGridLayout();
      contentPane.setLayout (layout);

      // Set constraints and add control to container
      JScrollPane spOutput = new JScrollPane(output);
      layout.setConstraints(spOutput  , 1,1,1,1);
      contentPane.add(spOutput);
		
      // Specify listener
		addWindowListener(new MyWindowAdapter());
		
		// Start the server daemon
		new DayTimeServerDaemon(this);
	}
      
   //////////////////////////////////////////// Controller

   public void println(String text){
      output.append(text + "\n");
   }
	
	   // Window listener
   private class MyWindowAdapter extends WindowAdapter{
      public void windowClosing (WindowEvent e){
         System.exit(0);
      }
   }

}