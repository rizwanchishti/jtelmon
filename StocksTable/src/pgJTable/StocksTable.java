// modificata baza de date : mdb -> postgres
package pgJTable;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
// import java.io.*;
import java.text.*;
import java.sql.*;

import javax.swing.*;
// import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.table.*;

public class StocksTable extends JFrame 
{
  protected JTable m_table;
  private StockTableData m_data;
  protected JLabel m_title;

  public StocksTable() {
    super("Stocks Table");
    setSize(600, 300);

    m_data = new StockTableData();

    m_title = new JLabel(m_data.getTitle(), 
      new ImageIcon("money.gif"), SwingConstants.LEFT);
    m_title.setFont(new Font("TimesRoman",Font.BOLD,24));
    m_title.setForeground(Color.black);
    getContentPane().add(m_title, BorderLayout.NORTH);

    m_table = new JTable();
    m_table.setAutoCreateColumnsFromModel(false);
    m_table.setModel(m_data);
        
    for (int k = 0; k < StockTableData.m_columns.length; k++) {
      DefaultTableCellRenderer renderer = new 
        ColoredTableCellRenderer();
      renderer.setHorizontalAlignment(
        StockTableData.m_columns[k].m_alignment);
      TableColumn column = new TableColumn(k, 
      StockTableData.m_columns[k].m_width, renderer, null);
      m_table.addColumn(column);   
    }

    JTableHeader header = m_table.getTableHeader();
    header.setUpdateTableInRealTime(true);
    header.addMouseListener(m_data.new ColumnListener(m_table));
    header.setReorderingAllowed(true);

    m_table.getColumnModel().addColumnModelListener(
      m_data.new ColumnMovementListener());

    JScrollPane ps = new JScrollPane();
    ps.getViewport().add(m_table);
    getContentPane().add(ps, BorderLayout.CENTER);

    JMenuBar menuBar = createMenuBar();
      setJMenuBar(menuBar);

    WindowListener wndCloser = new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    };
    addWindowListener(wndCloser);
    setVisible(true);
  }

  protected JMenuBar createMenuBar() {
    JMenuBar menuBar = new JMenuBar();
        
    JMenu mFile = new JMenu("File");
    mFile.setMnemonic('f');

    JMenuItem mData = new JMenuItem("Retrieve Data...");
    mData.setMnemonic('r');
    ActionListener lstData = new ActionListener() { 
      public void actionPerformed(ActionEvent e) { 
        retrieveData(); 
      }
    };
    mData.addActionListener(lstData);
    mFile.add(mData);
    mFile.addSeparator();

    JMenuItem mExit = new JMenuItem("Exit");
    mExit.setMnemonic('x');
    ActionListener lstExit = new ActionListener() { 
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }
    };
    mExit.addActionListener(lstExit);
    mFile.add(mExit);
    menuBar.add(mFile);

    JMenu mView = new JMenu("View");
    mView.setMnemonic('v');
    TableColumnModel model = m_table.getColumnModel();
    for (int k = 0; k < StockTableData.m_columns.length; k++) {
      JCheckBoxMenuItem item = new JCheckBoxMenuItem(
        StockTableData.m_columns[k].m_title);
      item.setSelected(true);
      TableColumn column = model.getColumn(k);
      item.addActionListener(new ColumnKeeper(column, 
        StockTableData.m_columns[k]));
      mView.add(item);
    }
    menuBar.add(mView);

    return menuBar;
  }

  public void retrieveData() {
    SimpleDateFormat frm = new SimpleDateFormat("MM/dd/yyyy");
    String currentDate = frm.format(m_data.m_date);
    String result = (String)JOptionPane.showInputDialog(this, 
      "Please enter date in form mm/dd/yyyy:", "Input", 
      JOptionPane.INFORMATION_MESSAGE, null, null, 
      currentDate);
    if (result==null)
      return;

    java.util.Date date = null;
    try { 
      date = frm.parse(result); 
    }
    catch (java.text.ParseException ex) { 
      date = null; 
    }
        
    if (date == null) {
      JOptionPane.showMessageDialog(this, 
        result+" is not a valid date",
        "Warning", JOptionPane.WARNING_MESSAGE);
      return;
    }

    setCursor( Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR) );
    switch (m_data.retrieveData(date)) {
      case 0:    // Ok with data
        m_title.setText(m_data.getTitle());
        m_table.tableChanged(new TableModelEvent(m_data)); 
        m_table.repaint();
        break;
      case 1: // No data
        JOptionPane.showMessageDialog(this, 
          "No data found for "+result,
          "Warning", JOptionPane.WARNING_MESSAGE);
        break;
      case -1: // Error
        JOptionPane.showMessageDialog(this, 
          "Error retrieving data",
          "Warning", JOptionPane.WARNING_MESSAGE);
        break;
    }
    setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
  }

  class ColumnKeeper implements ActionListener
  {
    protected TableColumn m_column;
    protected ColumnData  m_colData;

    public ColumnKeeper(TableColumn column, ColumnData  colData) {
      m_column = column;
      m_colData = colData;
    }

    public void actionPerformed(ActionEvent e) {
      JCheckBoxMenuItem item = (JCheckBoxMenuItem)e.getSource();
      TableColumnModel model = m_table.getColumnModel();
      if (item.isSelected()) {
        model.addColumn(m_column);
      }
      else {
        model.removeColumn(m_column);
      }
      m_table.tableChanged(new TableModelEvent(m_data)); 
      m_table.repaint();
    }
  }

  public static void main(String argv[]) {
    new StocksTable();
  }
}

class ColoredTableCellRenderer extends DefaultTableCellRenderer
{
  public void setValue(Object value) 
  {
    if (value instanceof ColorData) {
      ColorData cvalue = (ColorData)value;
      setForeground(cvalue.m_color);
      setText(cvalue.m_data.toString());
    }
    else if (value instanceof IconData) {
      IconData ivalue = (IconData)value;
      setIcon(ivalue.m_icon);
      setText(ivalue.m_data.toString());
    }
    else
      super.setValue(value);
  }
}

class Fraction
{
  public int m_whole;
  public int m_nom;
  public int m_den;

  public Fraction(double value) {
    int sign = value <0 ? -1 : 1;
    value = Math.abs(value);
    m_whole = (int)value;
    m_den = 32;
    m_nom = (int)((value-m_whole)*m_den);
    while (m_nom!=0 && m_nom%2==0) {
      m_nom /= 2;
      m_den /= 2;
    }
    if (m_whole==0)
      m_nom *= sign;
    else
      m_whole *= sign;
  }

  public double doubleValue() {
    return (double)m_whole + (double)m_nom/m_den;
  }

  public String toString() {
    if (m_nom==0)
      return ""+m_whole;
    else if (m_whole==0)
      return ""+m_nom+"/"+m_den;
    else
      return ""+m_whole+" "+m_nom+"/"+m_den;
  }
}

class SmartLong
{
  protected static NumberFormat FORMAT;
  static {
    FORMAT = NumberFormat.getInstance();
    FORMAT.setGroupingUsed(true);
  }

  public long m_value;

  public SmartLong(long value) { m_value = value; }

  public long longValue() { return m_value; }

  public String toString() { return FORMAT.format(m_value); }
}


class ColorData
{
  public Color  m_color;
  public Object m_data;
  public static Color GREEN = new Color(0, 128, 0);
  public static Color RED = Color.red;

  public ColorData(Fraction data) {
    m_color = data.doubleValue() >= 0 ? GREEN : RED;
    m_data  = data;
  }

  public ColorData(Color color, Object data) {
    m_color = color;
    m_data  = data;
  }
    
  public ColorData(Double data) {
    m_color = data.doubleValue() >= 0 ? GREEN : RED;
    m_data  = data;
  }
    
  public String toString() {
    return m_data.toString();
  }
}

class IconData
{
  public ImageIcon  m_icon;
  public Object m_data;

  public IconData(ImageIcon icon, Object data) {
    m_icon = icon;
    m_data = data;
  }
    
  public String toString() {
    return m_data.toString();
  }
}

class StockData
{
  public static ImageIcon ICON_UP = new ImageIcon("ArrUp.gif");
  public static ImageIcon ICON_DOWN = new ImageIcon("ArrDown.gif");
  public static ImageIcon ICON_BLANK = new ImageIcon("blank.gif");

  public IconData  m_symbol;
  public String  m_name;
  public Fraction  m_last;
  public Fraction  m_open;
  public ColorData  m_change;
  public ColorData  m_changePr;
  public SmartLong  m_volume;

  public StockData(String symbol, String name, double last, 
   double open, double change, double changePr, long volume) {
    m_symbol = new IconData(getIcon(change), symbol);
    m_name = name;
    m_last = new Fraction(last);
    m_open = new Fraction(open);
    m_change = new ColorData(new Fraction(change));
    m_changePr = new ColorData(new Double(changePr));
    m_volume = new SmartLong(volume);
  }

  public static ImageIcon getIcon(double change) {
    return (change>0 ? ICON_UP : (change<0 ? ICON_DOWN : 
      ICON_BLANK));
  }
}

/*
class ColumnData
{
  public String  m_title;
  public int     m_width;
  public int     m_alignment;
  public ColumnData(String title, int width, int alignment) {
    m_title = title;
    m_width = width;
    m_alignment = alignment;
  }
}
*/

class StockTableData extends AbstractTableModel 
{
  static final public ColumnData m_columns[] = {
    new ColumnData( "Symbol", 100, JLabel.LEFT ),
    new ColumnData( "Name", 160, JLabel.LEFT ),
    new ColumnData( "Last", 100, JLabel.RIGHT ),
    new ColumnData( "Open", 100, JLabel.RIGHT ),
    new ColumnData( "Change", 100, JLabel.RIGHT ),
    new ColumnData( "Change %", 100, JLabel.RIGHT ),
    new ColumnData( "Volume", 100, JLabel.RIGHT )
  };

  protected SimpleDateFormat m_frm;
  protected Vector m_vector;
  protected java.util.Date m_date;
  protected int m_columnsCount = m_columns.length;

  protected int m_sortCol = 0;
  protected boolean m_sortAsc = true;

  protected int m_result = 0;

  public StockTableData() {
    m_frm = new SimpleDateFormat("MM/dd/yyyy");
    m_vector = new Vector();
    setDefaultData();
  }

  public void setDefaultData() {
    try { 
      // m_date = m_frm.parse("07/24/1999");
        m_date = m_frm.parse("07/23/1998");
    }
    catch (java.text.ParseException ex) { 
      m_date = null; 
    }

    m_vector.removeAllElements();
    m_vector.addElement(new StockData("ORCL", "Oracle Corp.", 
      23.6875, 25.375, -1.6875, -6.42, 24976600));
    m_vector.addElement(new StockData("EGGS", "Egghead.com", 
      17.25, 17.4375, -0.1875, -1.43, 2146400));
    m_vector.addElement(new StockData("T", "AT&T", 
      65.1875, 66, -0.8125, -0.10, 554000));
    m_vector.addElement(new StockData("LU", "Lucent Technology", 
      64.625, 59.9375, 4.6875, 9.65, 29856300));
    m_vector.addElement(new StockData("FON", "Sprint", 
      104.5625, 106.375, -1.8125, -1.82, 1135100));
    m_vector.addElement(new StockData("ENML", "Enamelon Inc.", 
      4.875, 5, -0.125, 0, 35900)); 
    m_vector.addElement(new StockData("CPQ", "Compaq Computers", 
      30.875, 31.25, -0.375, -2.18, 11853900));
    m_vector.addElement(new StockData("MSFT", "Microsoft Corp.", 
      94.0625, 95.1875, -1.125, -0.92, 19836900));
    m_vector.addElement(new StockData("DELL", "Dell Computers", 
      46.1875, 44.5, 1.6875, 6.24, 47310000));
    m_vector.addElement(new StockData("SUNW", "Sun Microsystems", 
      140.625, 130.9375, 10, 10.625, 17734600));
    m_vector.addElement(new StockData("IBM", "Intl. Bus. Machines", 
      183, 183.125, -0.125, -0.51, 4371400));
    m_vector.addElement(new StockData("HWP", "Hewlett-Packard", 
      70, 71.0625, -1.4375, -2.01, 2410700));
    m_vector.addElement(new StockData("UIS", "Unisys Corp.", 
      28.25, 29, -0.75, -2.59, 2576200));
    m_vector.addElement(new StockData("SNE", "Sony Corp.", 
      96.1875, 95.625, 1.125, 1.18, 330600));
    m_vector.addElement(new StockData("NOVL", "Novell Inc.", 
      24.0625, 24.375, -0.3125, -3.02, 6047900));
    m_vector.addElement(new StockData("HIT", "Hitachi, Ltd.", 
      78.5, 77.625, 0.875, 1.12, 49400));

    Collections.sort(m_vector, new 
      StockComparator(m_sortCol, m_sortAsc));
  }

  public int getRowCount() {
    return m_vector==null ? 0 : m_vector.size(); 
  }

  public int getColumnCount() { 
    return m_columnsCount;
  } 

  public String getColumnName(int column) {
    String str = m_columns[column].m_title;
    if (column==m_sortCol)
      str += m_sortAsc ? " A" : " D";
    return str;
  }
 
  public boolean isCellEditable(int nRow, int nCol) {
    return false;
  }

  public Object getValueAt(int nRow, int nCol) {
    if (nRow < 0 || nRow>=getRowCount())
      return "";
    StockData row = (StockData)m_vector.elementAt(nRow);
    switch (nCol) {
      case 0: return row.m_symbol;
      case 1: return row.m_name;
      case 2: return row.m_last;
      case 3: return row.m_open;
      case 4: return row.m_change;
      case 5: return row.m_changePr;
      case 6: return row.m_volume;
    }
    return "";
  }

  public String getTitle() {
    if (m_date==null)
      return "Stock Quotes";
    return "Stock Quotes at "+m_frm.format(m_date);
  }

  public int retrieveData(final java.util.Date date) {
    GregorianCalendar calendar = new GregorianCalendar();
    calendar.setTime(date);
    int month = calendar.get(Calendar.MONTH)+1;
    int day = calendar.get(Calendar.DAY_OF_MONTH);
    int year = calendar.get(Calendar.YEAR);

    final String query = "SELECT data.symbol, symbols.name, " +
      " data.last, data.open, data.change, data.changeproc, " +
      " data.volume FROM data INNER JOIN symbols " +
      " ON DATA.symbol = SYMBOLS.symbol WHERE " +
      " extract(month from data.date1)=" + month +
      " AND extract(day from data.date1)=" +day+
      " AND extract(year from data.date1)=" +year;

    // final String  url_sursa = "jdbc:postgresql://192.168.61.200:5432/javamarket";
    final String  url_sursa = "jdbc:postgresql://192.168.101.222:5432/javamarket";
    final String username_sursa = "postgres";
    final String password_sursa = "telinit";

    Thread runner = new Thread() {
      public void run() {
        try {
          Class.forName("org.postgresql.Driver");
          Connection conn = DriverManager.getConnection(url_sursa,username_sursa,password_sursa);
            

          Statement stmt = conn.createStatement();
          ResultSet results = stmt.executeQuery(query);

          boolean hasData = false;
          while (results.next()) {
            if (!hasData) {
              m_vector.removeAllElements();
              hasData = true;
            }
            String  symbol = results.getString(1);
            String  name = results.getString(2);
            double  last = results.getDouble(3);
            double  open = results.getDouble(4);
            double  change = results.getDouble(5);
            double  changePr = results.getDouble(6);
            long volume = results.getLong(7);
            m_vector.addElement(new StockData(symbol, name, last, 
              open, change, changePr, volume));
          }
          results.close();
          stmt.close();
          conn.close();

          if (!hasData)    // We've got nothing
            m_result = 1;
        }
        catch (Exception e) {
          e.printStackTrace();
          System.err.println("Load data error: "+e.toString());
          m_result = -1;
        }
        m_date = date;
        Collections.sort(m_vector, 
          new StockComparator(m_sortCol, m_sortAsc));
        m_result = 0;
      }
    };
    runner.start();

    return m_result;
  }

  class ColumnListener extends MouseAdapter
  {
    protected JTable m_table;

    public ColumnListener(JTable table) {
      m_table = table;
    }

    public void mouseClicked(MouseEvent e) {
      TableColumnModel colModel = m_table.getColumnModel();
      int columnModelIndex = colModel.getColumnIndexAtX(e.getX());
      int modelIndex = colModel.getColumn(columnModelIndex).getModelIndex();

      if (modelIndex < 0)
        return;
      if (m_sortCol==modelIndex)
        m_sortAsc = !m_sortAsc;
      else
        m_sortCol = modelIndex;

      for (int i=0; i < m_columnsCount; i++) { //NEW
        TableColumn column = colModel.getColumn(i);
        column.setHeaderValue(getColumnName(column.getModelIndex()));    
      }
      m_table.getTableHeader().repaint();  

      Collections.sort(m_vector, new 
        StockComparator(modelIndex, m_sortAsc));
      m_table.tableChanged(
        new TableModelEvent(StockTableData.this)); 
      m_table.repaint();  
    }
  }

  class ColumnMovementListener implements TableColumnModelListener
  {
    public void columnAdded(TableColumnModelEvent e) {
      m_columnsCount++;
    }

    public void columnRemoved(TableColumnModelEvent e) {
      m_columnsCount--;
      if (m_sortCol >= e.getFromIndex())
        m_sortCol = 0;
    }

    public void columnMarginChanged(ChangeEvent e) {}
    public void columnMoved(TableColumnModelEvent e) {}
    public void columnSelectionChanged(ListSelectionEvent e) {}
  }
}

class StockComparator implements Comparator
{
  protected int     m_sortCol;
  protected boolean m_sortAsc;

  public StockComparator(int sortCol, boolean sortAsc) {
    m_sortCol = sortCol;
    m_sortAsc = sortAsc;
  }

  public int compare(Object o1, Object o2) {
    if(!(o1 instanceof StockData) || !(o2 instanceof StockData))
      return 0;
    StockData s1 = (StockData)o1;
    StockData s2 = (StockData)o2;
    int result = 0;
    double d1, d2;
    switch (m_sortCol) {
      case 0:    // symbol
        String str1 = (String)s1.m_symbol.m_data;
        String str2 = (String)s2.m_symbol.m_data;
        result = str1.compareTo(str2);
        break;
      case 1:    // name
        result = s1.m_name.compareTo(s2.m_name);
        break;
      case 2:    // last
        d1 = s1.m_last.doubleValue();
        d2 = s2.m_last.doubleValue();
        result = d1<d2 ? -1 : (d1>d2 ? 1 : 0);
        break;
      case 3:    // open
        d1 = s1.m_open.doubleValue();
        d2 = s2.m_open.doubleValue();
        result = d1<d2 ? -1 : (d1>d2 ? 1 : 0);
        break;
      case 4:    // change
        d1 = ((Fraction)s1.m_change.m_data).doubleValue();
        d2 = ((Fraction)s2.m_change.m_data).doubleValue();
        result = d1<d2 ? -1 : (d1>d2 ? 1 : 0);
        break;
      case 5:    // change %
        d1 = ((Double)s1.m_changePr.m_data).doubleValue();
        d2 = ((Double)s2.m_changePr.m_data).doubleValue();
        result = d1<d2 ? -1 : (d1>d2 ? 1 : 0);
        break;
      case 6:    // volume
        long l1 = s1.m_volume.longValue();
        long l2 = s2.m_volume.longValue();
        result = l1<l2 ? -1 : (l1>l2 ? 1 : 0);
        break;
    }

    if (!m_sortAsc)
      result = -result;
    return result;
  }

  public boolean equals(Object obj) {
    if (obj instanceof StockComparator) {
      StockComparator compObj = (StockComparator)obj;
      return (compObj.m_sortCol==m_sortCol) && 
        (compObj.m_sortAsc==m_sortAsc);
    }
    return false;
  }
}

