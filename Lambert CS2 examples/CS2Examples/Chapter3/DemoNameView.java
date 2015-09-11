import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import ioutil.*;

public class DemoNameView extends JFrame{

   //////////////////////////////////////////////////////// Model

   private Dog dog;

   //////////////////////////////////////////////////////// The App

   private DemoMultiViewApp app;

   //////////////////////////////////////////////////////// View

   // Create controls 
   private JTextArea  taDisplayDog = new JTextArea("");
   private JLabel     lbName       = new JLabel("Name");
   private JTextField tfName       = new JTextField("");
   private JButton    btModify     = new JButton("Modify");

   public DemoNameView(DemoMultiViewApp app, Dog dog){

      this.dog = dog;
      this.app = app;

      // Set title
      setTitle("The Name View");
 
      // Initialize taDisplayDog
      taDisplayDog.setText(dog.toString());
      taDisplayDog.setEditable(false);

      // Create container and layout
      Container contentPane = getContentPane();
      EasyGridLayout layout = new EasyGridLayout();
      contentPane.setLayout (layout);
      
      // Set constraints
      layout.setConstraints(taDisplayDog ,1,1,2,1); 
      layout.setConstraints(lbName       ,2,1,1,1);
      layout.setConstraints(tfName       ,2,2,1,1);
      layout.setConstraints(btModify     ,3,1,2,1);

      // Add controls to container
      contentPane.add(taDisplayDog);
      contentPane.add(lbName);
      contentPane.add(tfName);
      contentPane.add(btModify);

      // Specify listeners
      btModify.addActionListener(new btModifyListener());
      addWindowListener(new MyWindowAdapter());   
      
      // Display the view
      setSize(200, 150);
      setLocation (550, 300);             
      setVisible(true);
   }

   public void updateView(){
      taDisplayDog.setText(dog.toString());
   }      
   
   //////////////////////////////////////////////////////// Controller

   // Modify button listener
   private class btModifyListener implements ActionListener{
      public void actionPerformed (ActionEvent event){
            String name = tfName.getText().trim();
            dog.setName(name);
            app.updateAllViews();
      }
   }

   // Window listener
   private class MyWindowAdapter extends WindowAdapter{
      public void windowClosing (WindowEvent e){
         System.exit(0);
      }
   }
}
