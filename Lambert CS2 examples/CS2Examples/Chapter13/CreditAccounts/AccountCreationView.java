import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import ioutil.*;

public class AccountCreationView extends JFrame{

   //////////////////////////////////////////////////////// Model

   private CreditAccountsModel model;
   
   //////////////////////////////////////////////////////// View

   // Create controls 
   private JLabel       lbCardId  = new JLabel      ("Card ID");
   private JLabel       lbName    = new JLabel      ("Name");
   private JLabel       lbAmount  = new JLabel      ("Amount");
   private JTextField   tfCardId  = new JTextField  (""); 
   private JTextField   flName    = new JTextField  ("");
   private DoubleField  dfAmount  = new DoubleField (0);
   private JButton      btGet     = new JButton     ("Get");
   private JButton      btAdd     = new JButton     ("Add");
   private JButton      btDeposit = new JButton     ("Deposit");
   private JButton      btRemove  = new JButton     ("Remove");
   private JButton      btList    = new JButton     ("List Accounts");

   public AccountCreationView(CreditAccountsModel model)
   {
      // Set title and model
      setTitle ("Account Creation");
      this.model = model;

       // Create container and layout
      Container contentPane = getContentPane();
      EasyGridLayout layout = new EasyGridLayout();
      contentPane.setLayout (layout);
      
      // Set constraints 
      layout.setConstraints(lbCardId  ,1,1,1,1);
      layout.setConstraints(lbName    ,2,1,1,1);
      layout.setConstraints(lbAmount  ,3,1,1,1);     
      layout.setConstraints(tfCardId  ,1,2,1,1); 
      layout.setConstraints(flName    ,2,2,1,1);
      layout.setConstraints(dfAmount  ,3,2,1,1);    
      layout.setConstraints(btGet     ,4,1,1,1);
      layout.setConstraints(btAdd     ,5,1,1,1);
      layout.setConstraints(btDeposit ,4,2,1,1);
      layout.setConstraints(btRemove  ,5,2,1,1);
      layout.setConstraints(btList    ,6,2,1,1);

      // Add controls to container
      contentPane.add(lbCardId);
      contentPane.add(lbName);
      contentPane.add(lbAmount);      
      contentPane.add(tfCardId); 
      contentPane.add(flName);
      contentPane.add(dfAmount);    
      contentPane.add(btGet);
      contentPane.add(btAdd);
      contentPane.add(btDeposit);
      contentPane.add(btRemove);
      contentPane.add(btList);

      // Specify listeners
      btGet.addActionListener(new MyButtonListener());
      btAdd.addActionListener(new MyButtonListener());
      btDeposit.addActionListener(new MyButtonListener());
      btRemove.addActionListener(new MyButtonListener());
      btList.addActionListener(new MyButtonListener());
      addWindowListener(new MyWindowAdapter());   
   } 
   
   //////////////////////////////////////////////////////// Controller

   private void messageBox(String str){
      new MessageBox(this, str, 300, 150);
   }

   private void get(){
      Account account = getAccount();
      if (account == null)
         return;
      else
         display (account);
   }
    
   private Account getAccount(){
      String id = tfCardId.getText().trim();
        
      if (id.equals ("")){
         messageBox ("Enter the card id first");
         return null;
      }
        
      Account account = model.getAccount (id);
        
      if (account == null){
         messageBox ("There is no such account");
         return null;
      }
        
      return account;
   }
              
   private void display (Account account){
      if (account == null){
         tfCardId.setText ("");
         flName.setText ("");
         dfAmount.setNumber (0);
      }else{
         tfCardId.setText (account.getCardId());
         flName.setText (account.getName());
         dfAmount.setNumber (account.getBalance());
      }
   }
    
   private void add (){
      Account account = buildAccountFromScreenData();
        
      if (account == null)
         return;

      if (!model.insertAccount (account)){
         messageBox ("The account is already present");
         return;
      }
   }
    
   private Account buildAccountFromScreenData(){
      String id = tfCardId.getText().trim();
      String name = flName.getText().trim();
      double amount = dfAmount.getNumber();
        
      if (id.equals ("") || name.equals ("")){
         messageBox ("Card id and name are both required");
         return null;
      }
        
      if (amount < 0){
         messageBox ("The amount must be nonnegative");
         return null;
      }
        
      return new Account (id, name, amount);
   }
    
   private void deposit(){
      Account account = getAccount();
        
      if (account == null)
         return;
        
      double amount = dfAmount.getNumber();
        
      if (amount <= 0){
         new MessageBox(AccountCreationView.this, 
                       "The deposit amount must be positive",
                       300, 400);
         return;
      }
        
      account.deposit (amount);
      dfAmount.setNumber (account.getBalance());
   }
              
   private void remove(){
      Account account = getAccount();
        
      if (account == null)
         return;
            
      model.removeAccount (account);
      display (null);
   }
    
   private void listAccounts(){
      messageBox ("Here is a list of all the accounts\n" + model);
   }

   // Button action listener
   private class MyButtonListener implements ActionListener{
      public void actionPerformed (ActionEvent event){
         Object buttonObj = event.getSource();
         if      (buttonObj == btGet)         
            get();
         else if (buttonObj == btAdd)
            add();
         else if (buttonObj == btDeposit)
            deposit();
         else if (buttonObj == btRemove)
            remove();
         else
            listAccounts();    
      }
   }

   // Window listener
   private class MyWindowAdapter extends WindowAdapter{
      public void windowClosing (WindowEvent e){
         System.exit(0);
      }
   }   
}

