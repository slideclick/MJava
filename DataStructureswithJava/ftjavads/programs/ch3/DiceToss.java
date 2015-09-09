import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class DiceToss extends JFrame
{
   private JPanel panelA, panelB;
   private JLabel dieLabel1, dieLabel2, totalLabel;
   private JTextField totalField;
   private JTextArea  totalArea;
   private JButton tossButton;

   private Dice d = Dice.getDice();
   private ImageIcon[] diePict = {null,
			new ImageIcon("./die1.gif"), new ImageIcon("./die2.gif"),
			new ImageIcon("./die3.gif"), new ImageIcon("./die4.gif"),
			new ImageIcon("./die5.gif"), new ImageIcon("./die6.gif")};

   public static void main( String args[] )
   {
		DiceToss app = new DiceToss();
		
   	app.setVisible(true);
   }

	public DiceToss()
   {
      setTitle( "Dice Toss" );
		setBounds(100, 100, 200,200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      Container content = getContentPane();
      content.setLayout(new BorderLayout());
      panelA = new JPanel();
      dieLabel1 = new JLabel();
      dieLabel2 = new JLabel();
      panelA.add(dieLabel1);
      panelA.add(dieLabel2);

      totalArea = new JTextArea(10, 15);

      panelB = new JPanel();
      tossButton = new JButton("Toss");
      tossButton.addActionListener(new TossEvent());
      totalLabel = new JLabel("Total");
      totalField = new JTextField(4);
      panelB.add(tossButton);
      panelB.add(totalLabel);
      panelB.add(totalField);

      content.add(panelA, BorderLayout.NORTH);
      content.add(new JScrollPane(totalArea), BorderLayout.CENTER);
      content.add(panelB, BorderLayout.SOUTH);
   }

   private class TossEvent implements ActionListener
   {
		public void actionPerformed(ActionEvent ae)
		{
			d.toss();
			dieLabel1.setIcon(diePict[d.getOne()]);
			dieLabel2.setIcon(diePict[d.getTwo()]);
			totalArea.append("Total is " + d.getTotal() + "\n");
			totalField.setText("" + d.getTotal());
		}
	}
}
