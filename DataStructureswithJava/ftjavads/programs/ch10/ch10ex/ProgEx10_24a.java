import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import ds.util.LinkedList;

public class ProgEx10_24a extends JFrame
{
   private JList jlist;

   private String[] mapName =
      { "Canada", "Denmark", "France", "India", "Mexico", "Peru", "Tibet"};
   private LinkedList<String> mapList = new LinkedList<String>();

   public static void main( String args[] )
   {
      ProgEx10_24a app = new ProgEx10_24a();
      app.setVisible(true);
   }

  	public ProgEx10_24a()
   {
      setTitle("Map List");
      setBounds(100, 100, 170, 180);

      Container content = getContentPane();

      for (int i = 0; i < mapName.length; i++)
      	mapList.add(mapName[i]);

      // create a list with an Object array from mapList
      jlist = new JList( mapList.toArray() );
      jlist.setVisibleRowCount( mapName.length );
      TitledBorder border = new TitledBorder("Countries");
      jlist.setBorder(border);

      // do not allow multiple selections
      jlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );

      // set up event handler
      jlist.addListSelectionListener(new ListHandler());

      content.add(jlist);
   }


	private class ListHandler  implements ListSelectionListener
	{
		public void valueChanged( ListSelectionEvent e )
		{
			int n = jlist.getSelectedIndex();

			shiftToFront(mapList, n);
			jlist.setListData(mapList.toArray());
		}

		private void shiftToFront(LinkedList<String> mapList, int n)
		{ . . . 	}
	}
}