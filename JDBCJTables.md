http://www.pioverpi.net/2009/06/23/jtables-jdbc-introduction/
http://www.pioverpi.net/2009/06/24/jtables-jdbc-pt-2-extending-abstracttablemodel/
http://www.pioverpi.net/2009/06/26/jtables-jdbc-pt-3-connecting-to-your-database-through-jdbc/
http://www.pioverpi.net/2009/07/02/jtables-jdbc-pt-4-putting-it-all-together/
http://onjava.com/pub/a/onjava/excerpt/swinghks_hack24/index.html?page=1
http://onjava.com/pub/a/onjava/excerpt/swinghks_hack24/index.html?page=2
http://onjava.com/pub/a/onjava/excerpt/swinghks_hack24/index.html?page=3


Consider this: you have a running Java application with a Swing front-end and a PostgreSQL database managing the data in the backend.  You want to let your users view and modify the data in your database, but they don’t know a smidgen of SQL.  Perhaps there is a way to (easily?) draw a GUI front-end using Swing components on the Java side.

# Introduction #

But before you go off  tearing through the API, make sure you take a look at the JTable tutorial on the sun website.  You don’t have to study it like the Bible, but read it over to get a good handle on how JTables work.  In order to display our database entries, we’ll need to extend the AbstractTableModel class to create our own TableModel object.  This will give us a greater degree of control over how our JTable behaves when we start to fill it with more information.


# Details #

A TableModel represents the underlying data of the JTable. It manages everything from the name of the column headers to the actual contents of the table itself.

The easiest way to create a custom TableMode is to extend the AbstractTableModel class.

When you extend the AbstractTableModel, it will require you to implement the following methods:

public int getColumnCount() {}
public int getRowCount() {}
public Object getValueAt(int arg0, int arg1) {}
On top of those, we will also need to implement:

public boolean isCellEditable{}
public void setValueAt(Object aValue, int row, int col) {}
public String getColumnName(int col) {}
Fundamentally, your TableModel will need to keep track of two things: the titles or names of your columns and the actual content itself.

The titles of each column can be retrieved from the database (that is, when we actually connect to it). Ideally this information will be stored in a String array.

How you store the actual content is up in the air. Take careful note of the method signatures: the getValueAt() method returns an Object, and the setValueAt() method takes an Object.

The easiest way to go would be to store everything as a 2D Object array (Object[.md](.md)[.md](.md)). This makes it easier to address elements in the table(for the programmer that is), but makes it harder to adjust the size of the table later on. Alternatively, you could create a 2D ArrayList of Objects; this makes it harder for the programmer to access various elements, but makes it easier to adjust the size of the table.

There are trade-offs to consider, so take some time to decide on what your system needs before you proceed. As for my own implementation, I chose to store everything in a 2D Object array.

The next step is to connect to your database and populate the column names and content arrays with the relevant data. Once we have populated the column name array, implementing getColumnCount() and getColumnName() will be trivial. Similarly, implementing getRowCount(), getValueAt(), setValueAt() and isCellEditable() will be a lot easier once we’ve populated our content array.


Now that we have set up our empty TableModel, we’ll need to populate it with information from our database. In order to do that, we’ll first have to establish a connection to our database and query it for information.


First, we’ll need to find the appropriate JDBC drivers for your database. Since I am using a PostgreSQL db, I was able to obtain the driver here.  If you’re using something else then I’m sure a quick Google search will give you what you’re looking for.

In order to use the driver, you will need to add the jar to your CLASSPATH. If you are using Eclipse, then its a simple matter of adding the jar as an External Jar under your project’s Referenced Libraries. If you’re not, then you have a lot of other options, but I’ll let Google tell you what they are.

Connecting to the Database
Following the documentation for the PostgreSQL JDBC driver, we can connect to the our database as follows:

public void Connection connect() {
> String url = "jdbc:postgresql://localhost:5432/myDatabase";
> String user = "username";
> String pass = "password";

> try {
> > Class.forName("org.postgresql.Driver");
> > Connection db = DriverManager.getConnection(url, user, pass);


> return db;
> } catch (ClassNotFoundException e) {
> > System.err.println("Could not find PostgreSQL driver");

> } catch (SQLException e) {
> > System.err.println("Failed to establish connection with db");

> }
> return null;
}
If successful, the method will return a Connection object from which we can access and modify our database.

We can also disconnect from the database as follows:

public void disconnect(Connection conn) {
> try {
> > conn.close();

> }
> catch (SQLException sqx) {
> > System.err.println("Could not disconnect from db, trying again");

> }
}
It may seem like overkill, but this way we can reduce the number of try/catch blocks while also generating meaningful error statements at the same time.

Getting the Column Names of a Table

We can obtain the column names of a PostgreSQL table as a String[.md](.md)by looking at a table’s metadata as follows:

public String[.md](.md) getTableColumnNames(Connection conn, String tableName)
> throws SQLException {
> Statement st = conn.createStatement();
> ResultSet rs = st.executeQuery("SELECT **FROM " + tableName + ";");**

> ResultSetMetaData rsMeta = rs.getMetaData();

> String [.md](.md) colNames = new String[rsMeta.getColumnCount()];

> for (int i = 0; i < rsMeta.getColumnCount(); i++) {
> > colNames[i](i.md) = rsMeta.getColumnName(i+1);

> }

> rs.close();
> st.close();
> return colNames;
}
By issuing a general query for all of the Columns in the target table, we can access each column’s name by accessing the ResultSet‘s metadata.

Determining the Class Type of each Column
For reasons that will be clear later, we will need to determine what type of data each column holds in the table. This can be done by using the following code:

public String[.md](.md) getTableColumnClasses(Connection conn, String tableName)
> throws SQLException {
> Statement st = conn.createStatement();
> > ResultSet rs = st.executeQuery("SELECT **FROM " + tableName + ";");**


> ResultSetMetaData rsMeta = rs.getMetaData();

> String [.md](.md) colClasses = new String [rsMeta.getColumnCount()];

> for (int i = 0; i < rsMeta.getColumnCount(); i++) {
> > colClasses[i](i.md) = rsMeta.getColumnClassName(i + 1);

> }

> rs.close();
> st.close();
> return colClasses;
}
Getting at the Table’s Content
Getting at the actual content is a bit trickier.

First, because we cannot easily determine the number of rows in the table, we’ll be storing our information in an ArrayList at first, and then convert the ArrayList back into an Object[.md](.md) array.

Second, we will also need to see what type of data (String, int, float, etc.) the cell holds, so we’ll be cross-referencing our cells with the Class array.

If you are thoroughly confused by now, I suggest you read a bit more from this O’Reilly article here. Most of the work done here is based off of that article.

Anyways, the code to grab the data can be seen here:

public static Object[.md](.md)[.md](.md) getTableContent(Connection conn, String tableName)
> throws SQLException {
> String [.md](.md) colNames = DatabaseData.getTableColumnNames(conn, tableName);
> String [.md](.md) colClasses = DatabaseData.getTableColumnClasses(conn,
> > tableName);


> Statement st = conn.createStatement();
> ResultSet rs = st.executeQuery("SELECT **FROM " + tableName + ";");**

> ArrayList< Object[.md](.md) > rowList = new ArrayList< Object[.md](.md) >();
> while (rs.next()) {
> > ArrayList< Object> cellList = new ArrayList< Object >();
> > for (int i = 0; i < colClasses.length; i++) {
> > > Object cellValue = null;


> if (colClasses[i](i.md).equals(String.class.getName())) {
> > cellValue = rs.getString (colNames[i](i.md));

> }
> else if (colClasses[i](i.md).equals(Integer.class.getName())) {
> > cellValue = new Integer
> > > (rs.getInt (colNames[i](i.md)));

> }
> else if (colClasses[i](i.md).equals(Double.class.getName())) {
> > cellValue = new Double
> > > (rs.getDouble (colNames[i](i.md)));

> }
> else if (colClasses[i](i.md).equals(Date.class.getName())) {
> > cellValue = rs.getDate (colNames[i](i.md));

> }
> else if (colClasses[i](i.md).equals(Float.class.getName())) {
> > cellValue = rs.getFloat(colNames[i](i.md));

> }
> else {
> > System.out.println("PSQL Read Warning: "
> > > + "Unknown type encountered, "
> > > + "attempting to read as String");

> > cellValue = rs.getString(colNames[i](i.md));

> }
> cellList.add (cellValue);
> }
> Object[.md](.md) cells = cellList.toArray();
> rowList.add(cells);
> }

> rs.close();
> st.close();

> Object [.md](.md)[.md](.md) content = new Object[rowList.size()] [.md](.md);
> for (int i=0; i< content.length; i++) {
> > content[i](i.md) = rowList.get(i);

> }

> return content;
}
As you can see, the sole purpose of determining the Class type of the columns is so that we know which getX() method on the data.

And that’s it, you can now pull data from your database! In part 4, we’ll be putting everything together to create a working JTable.



Alright, let’s put this JTable together! Using the code from the previous section, we can now populate our custom AbstractTableModel by pulling information from a remote database.  Don’t worry, its a lot easier than it looks.


Let’s start by looking at a very simple TableModel that would do the trick:

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.table.AbstractTableModel;

public class MyTableModel extends AbstractTableModel{

> private Object[.md](.md)[.md](.md) content;
> private String[.md](.md) colNames;

> public MyTableModel () {
> > try {
> > > Connection conn = DatabaseData.connect();


> content = getTableContent(conn, "TABLE\_NAME");
> colNames = getTableColumnNames(conn, "TABLE\_NAME");

> disconnect(conn);
> }
> catch (SQLException sqx) {
> > content = new Object [.md](.md)[.md](.md) {{""}};
> > colNames = new String [.md](.md) {"Error"};
> > System.err.println("Could not pull data from database");

> }
> }

> public int getColumnCount() {
> > return colNames.length;

> }

> public int getRowCount() {
> > return content.length;

> }

> public Object getValueAt(int arg0, int arg1) {
> > return content[arg0](arg0.md)[arg1](arg1.md);

> }

> public boolean isCellEditable(int row, int col) {
> > return true;

> }
> public void setValueAt(Object aValue, int row, int col) {
> > content[row](row.md)[col](col.md) = aValue;

> }

> public String getColumnName(int col) {
> > return colNames[col](col.md);

> }
}
I would first like to draw your attention to the constructor. In it, we initialize both content and colNames by calling the methods created in the previous section. You’ll also notice that I left out these methods in the MyTableModel class. You can either add the required methods to this class or place the code in a separate class. Personally, I chose to put all of the database code into a separate class because I found that I tended to reuse the code a lot.

The rest of the code is relatively straightforward. You can perform a few tweaks on it here and there to suite your needs, but aside from that you’re good to go.

To create your JTable, I offer you the following sample code:

JFrame myFrame = new JFrame("My Table");

MyTableModel mt = new MyTableModel();
JTable jt = new JTable(mt);
JScrollPane jsp = new JScrollPane(jt);

myFrame.add(jsp);
myFrame.setVisible(true);
First, we create a new instance of MytableModel and pass that into the constructor of a new JTable. The JTable is then placed inside a new JScrollPane, which is then placed in a new JFrame for your viewing pleasure.

Although the JScrollPane seems unnecessary, I was unable to see my table’s column headers if I did not do so.

And there you have it! A JTable that can display data from a database table. Stay tuned for more JTable tricks later on this week.