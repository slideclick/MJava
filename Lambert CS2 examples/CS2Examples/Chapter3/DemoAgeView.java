import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import ioutil.*;

public class DemoAgeView extends JFrame{

   //////////////////////////////////////////////////// Model

   private Dog dog;

   //////////////////////////////////////////////////// The App

   private DemoMultiViewApp app;

   //////////////////////////////////////////////////// View

   // Create controls 
   private JTextArea    taDisplayDog = new JTextArea("");
   private JLabel       lbAge        = new JLabel("Age");
   private IntegerField ifAge        = new IntegerField(0);
   private JButton      btModify     = new JButton("Modify");

   public DemoAgeView(DemoMultiViewApp app, Dog dog){

      this.dog = dog;
      this.app = app;

      // Set title
      setTitle("The Age View");
 
      // Initialize taDisplayDog
      taDisplayDog.setText(dog.toString());
      taDisplayDog.setEditable(false);

      // Create container and layout
      Container contentPane = getContentPane();
      EasyGridLayout layout = new EasyGridLayout();
      contentPane.setLayout (layout);
      
      // Set constraints
      layout.setConstraints(taDisplayDog ,1,1,2,1); 
      layout.setConstraints(lbAge        ,2,1,1,1);
      layout.setConstraints(ifAge        ,2,2,1,1);
      layout.setConstraints(btModify     ,3,1,2,1);

      // Add controls to container
      contentPane.add(taDisplayDog);
      contentPane.add(lbAge);
      contentPane.add(ifAge);
      contentPane.add(btModify);

      // Specify listeners
      btModify.addActionListener(new btModifyListener());
      addWindowListener(new MyWindowAdapter());   
      
      // Display the view
      setSize(200, 150);
      setLocation (200, 300);             
      setVisible(true);
   }

   public void updateView(){
      taDisplayDog.setText(dog.toString());
   }      
   
   //////////////////////////////////////////////////// Controller

   // Modify button listener
   private class btModifyListener implements ActionListener{
      public void actionPerformed (ActionEvent event){
            int age = ifAge.getNumber();
            dog.setAge(age);
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