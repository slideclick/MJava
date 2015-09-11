// CreditApprovalView
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import ioutil.*;

public class CreditApprovalView extends JFrame{

   //////////////////////////////////////////////////////// Model

   private CreditAccountsModel model;
   
   //////////////////////////////////////////////////////// View

   // Create controls 
   private JLabel      lbCardId         = new JLabel      ("Card ID");
   private JLabel      lbPurchaseAmount = new JLabel      ("Amount");
   private JLabel      lbName           = new JLabel      ("Name");
   private JTextField  tfCardId         = new JTextField  (""); 
   private DoubleField dfPurchaseAmount = new DoubleField (0);
   private JTextField  tfName           = new JTextField  ("");
   private JLabel      lbApproval       = new JLabel      ("");
   private JButton     btTransmit       = new JButton     ("Transmit");
 
   public CreditApprovalView(CreditAccountsModel model){

      // Set title and model. Disable name and approval fields
      setTitle ("Purchase Approval");
      this.model = model;
      tfName.setEditable (false);

      // Create container and layout
      Container contentPane = getContentPane();
      EasyGridLayout layout = new EasyGridLayout();
      contentPane.setLayout (layout);
      
      // Set constraints 
      layout.setConstraints(lbCardId         ,1,1,1,1);
      layout.setConstraints(lbPurchaseAmount ,2,1,1,1);
      layout.setConstraints(lbName           ,3,1,1,1);
      layout.setConstraints(tfCardId         ,1,2,1,1); 
      layout.setConstraints(dfPurchaseAmount ,2,2,1,1);
      layout.setConstraints(tfName           ,3,2,1,1);
      layout.setConstraints(lbApproval       ,4,2,1,1);
      layout.setConstraints(btTransmit       ,5,2,1,1);

      // Add controls to container
      contentPane.add(lbCardId);
      contentPane.add(lbPurchaseAmount);
      contentPane.add(lbName);
      contentPane.add(tfCardId); 
      contentPane.add(dfPurchaseAmount);
      contentPane.add(tfName);
      contentPane.add(btTransmit);
      contentPane.add(lbApproval);

      // Specify listeners
      btTransmit.addActionListener(new MyButtonListener());
      addWindowListener(new MyWindowAdapter());  
   }
   
   //////////////////////////////////////////////////////// Controller

   // Button action listener
   private class MyButtonListener implements ActionListener{
      public void actionPerformed (ActionEvent event){
         String cardId = tfCardId.getText().trim();
         double amount = dfPurchaseAmount.getNumber();
         Account account;
        
         if (cardId.equals ("") || amount <= 0){
            new MessageBox(CreditApprovalView.this, 
                           "Enter the card id and a positive amount",
                           300, 100);
            return;
         }
        
         account = model.getAccount (cardId);
        
         if (account == null){
            new MessageBox(CreditApprovalView.this, "Invalid card id",
                           250, 100);
            return;
         }    
        
         tfName.setText (account.getName());
                
         if (account.withdraw (amount))
            lbApproval.setText (">>>>>> Credit Approved <<<<<<");
         else
            lbApproval.setText (">>>>>> CREDIT DENIED <<<<<<");
      }
   }

   // Window listener
   private class MyWindowAdapter extends WindowAdapter{
      public void windowClosing (WindowEvent e){
         System.exit(0);
      }
   }      
}

