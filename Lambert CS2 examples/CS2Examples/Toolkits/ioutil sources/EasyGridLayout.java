package ioutil;

import javax.swing.*;
import java.awt.*;

public class EasyGridLayout extends GridBagLayout {

   public void setConstraints (JLabel c, int row, int col,
                               int width, int height){
      finishSet (c, row, col, width, height,
                 0, 0, 
                 GridBagConstraints.NONE,
                 GridBagConstraints.NORTHWEST);
   }
      
   public void setConstraints (JButton c, int row, int col, 
                               int width, int height){
      finishSet (c, row, col, width, height,
                 0,0,
                 GridBagConstraints.NONE,
                 GridBagConstraints.CENTER);
   }
   
   public void setConstraints (JTextField c, int row, int col, 
                               int width, int height){
      finishSet (c, row, col, width, height,
                 100, 0,
                 GridBagConstraints.HORIZONTAL,
                 GridBagConstraints.NORTHWEST);
   }
   
   public void setConstraints (JScrollPane c, int row, int col, 
                               int width, int height){
      finishSet (c, row, col, width, height,
                 100, 100,
                 GridBagConstraints.BOTH,
                 GridBagConstraints.NORTHWEST);
   }

   public void setConstraints (JTextArea c, int row, int col, 
                               int width, int height){
      finishSet (c, row, col, width, height,
                 100, 100,
                 GridBagConstraints.BOTH,
                 GridBagConstraints.NORTHWEST);
   }

   public void setConstraints (JList c, int row, int col, 
                               int width, int height){
      finishSet (c, row, col, width, height,
                 100, 100,
                 GridBagConstraints.BOTH,
                 GridBagConstraints.NORTHWEST);
   }

   public void setConstraints (JCheckBox c, int row, int col, 
                               int width, int height){
     finishSet (c, row, col, width, height,
                0, 0,
                GridBagConstraints.HORIZONTAL,
                GridBagConstraints.NORTHWEST);
   }

   public void setConstraints (JRadioButton c, int row, int col, 
                               int width, int height){
     finishSet (c, row, col, width, height,
                0, 0,
                GridBagConstraints.HORIZONTAL,
                GridBagConstraints.NORTHWEST);
   }

   public void setConstraints (JPanel c, int row, int col, 
                               int width, int height){
      finishSet (c, row, col, width, height,
                 100, 100,
                 GridBagConstraints.BOTH,
                 GridBagConstraints.NORTHWEST);
   }

   
   private void finishSet (Component c, int y, int x, int w, int h,
                           int weightx, int weighty,
                           int fill, int anchor){
                           
      GridBagConstraints gbc = new GridBagConstraints();
      
      gbc.insets.bottom = 5;
      gbc.insets.left = 5;
      gbc.insets.right = 5;
      gbc.insets.top = 5;
      
      gbc.weightx = weightx;
      gbc.weighty = weighty;
      
      gbc.fill = fill;
      gbc.anchor = anchor;
      
      gbc.gridx = x-1;
      gbc.gridy = y-1;
      
      gbc.gridwidth = w;
      gbc.gridheight = h;
      
      setConstraints(c, gbc);
   }
}