import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class QueensGUI extends JFrame
{
	JPanel boardPanel, rowPanel;
	JPanel[][] panelMat;
	JTextField rowField;
	ImageIcon queenIcon = new ImageIcon("queen.gif");
	JLabel[][] queenLabel = new JLabel[8][8];
	int i, j;

	public static void main (String[] args)
	{
		QueensGUI app = new QueensGUI();
		app.setVisible(true);
	}

	public QueensGUI()
	{
		setTitle("8-Queens Chess");
		setSize(250,275);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container c = getContentPane();
		c.setLayout(new BorderLayout());

		boardPanel = new JPanel();
		boardPanel.setLayout(new GridLayout(8,8));

		rowPanel = new JPanel();

		panelMat = new JPanel[8][8];

		for (i = 0; i < 8; i++)
			for (j = 0; j < 8; j++)
			{
				panelMat[i][j] = new JPanel();
				queenLabel[i][j] = new JLabel(queenIcon);
				queenLabel[i][j].setVisible(false);;
				panelMat[i][j].add(queenLabel[i][j]);
				if ((i + j) % 2 == 0)
					panelMat[i][j].setBackground(Color.gray);
				else
					panelMat[i][j].setBackground(Color.white);
				boardPanel.add(panelMat[i][j]);
			}

		rowField = new JTextField(6);
		rowField.addActionListener(new RowHandler());
		rowPanel.add(new JLabel("Starting row"));
		rowPanel.add(rowField);

		c.add(rowPanel, BorderLayout.NORTH);
		c.add(boardPanel, BorderLayout.CENTER);
	}

	private class RowHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent ae)
		{
			int row = Integer.parseInt(rowField.getText());

			int[] queenList = new int[8];

			ChessBoardGUI board = new ChessBoardGUI();

			// get a solution
			Queens.queens(queenList, row);
			board.setQueens(queenList);

			// display the solution
			board.displayBoard(queenLabel);
			boardPanel.setVisible(true);
		}
	}
}

/*
*/