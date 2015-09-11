//import java.awt.*;
import javax.swing.*;
//import java.awt.event.*;

public class CreditAccountsApplication extends JFrame{

   //////////////////////////////////////////////////////// main

   public static void main (String[] args){

      // The model
      CreditAccountsModel model = new CreditAccountsModel();   
      
      // Instantiate and open the views
      JFrame theFrame = new CreditApprovalView(model);
      theFrame.setSize (300, 200);  
      theFrame.setLocation (0, 0);             
      theFrame.setVisible(true);    
      
      theFrame = new CreditApprovalView(model);
      theFrame.setSize (300, 200);  
      theFrame.setLocation (200, 200);             
      theFrame.setVisible(true);    
      
      theFrame = new AccountCreationView(model);
      theFrame.setSize (300, 250);  
      theFrame.setLocation (400, 400);             
      theFrame.setVisible(true);    
   }
}                   

