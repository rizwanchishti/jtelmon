
/* 
 * http://www.edumobile.org/android/android-development/use-of-sqlite/
 * http://android-er.blogspot.com/2011/06/delete-row-in-sqlite-database.html
 * 
 */

package net.learn2develop.WCF2SQLite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import java.util.ArrayList;
import java.util.List;
import android.util.Log;


public class DataManipulator {
	private static final  String DATABASE_NAME = "easysales.db";
	private static final int DATABASE_VERSION = 1;
	static final String TABLE_ORDERS   = "orders";
	static final String TABLE_PRODUCTS = "products";
	static final String TABLE_CLIENTS  = "clients";
	private static Context context;
	static SQLiteDatabase db;

	private SQLiteStatement insertOrderStmt;
	private SQLiteStatement insertProductsStmt;
	private SQLiteStatement insertClientsStmt;
	
    private static final String INSERT_ORDERS   = "insert into " + TABLE_ORDERS   + " (clientName,productName,piecesNumber,discountNumber) values (?,?,?,?)";
    private static final String INSERT_PRODUCTS = "insert into " + TABLE_PRODUCTS + " (ID, Name, Price, Symbol)    values (?,?,?,?)";
    private static final String INSERT_CLIENTS  = "insert into " + TABLE_CLIENTS  + " (Agent, Client, Route, Zone) values (?,?,?,?)";
    
	public DataManipulator(Context context) {
		DataManipulator.context = context;
		OpenHelper openHelper = new OpenHelper(DataManipulator.context);
		DataManipulator.db = openHelper.getWritableDatabase();
		this.insertOrderStmt = DataManipulator.db.compileStatement(INSERT_ORDERS);
		this.insertProductsStmt = DataManipulator.db.compileStatement(INSERT_PRODUCTS);
		this.insertClientsStmt = DataManipulator.db.compileStatement(INSERT_CLIENTS);
	}
	
	public long insertIntoOrders(String clientName,String productName,String piecesNumber,String discountNumber) {
		this.insertOrderStmt.bindString(1, clientName);
		this.insertOrderStmt.bindString(2, productName);
		this.insertOrderStmt.bindString(3, piecesNumber);
		this.insertOrderStmt.bindString(4, discountNumber);
		return this.insertOrderStmt.executeInsert();
	}

	public long insertIntoProducts(String ID,String Name,String Price,String Symbol) {
		this.insertProductsStmt.bindString(1, ID);
		this.insertProductsStmt.bindString(2, Name);
		this.insertProductsStmt.bindString(3, Price);
		this.insertProductsStmt.bindString(4, Symbol);
		return this.insertProductsStmt.executeInsert();
	}
	
	public long insertIntoClients(String Agent,String Client,String Route,String Zone) {
		this.insertClientsStmt.bindString(1, Agent);
		this.insertClientsStmt.bindString(2, Client);
		this.insertClientsStmt.bindString(3, Route);
		this.insertClientsStmt.bindString(4, Zone);
		return this.insertClientsStmt.executeInsert();
	}

	public void sincroDB(){
		// ToDo ... Next 
	}

	public void deleteAllProducts() {
		// db.delete(TABLE_PRODUCTS, null, null);
		try{
	    	// db = openOrCreateDatabase(DBNAME, Context.MODE_PRIVATE,null);
	    	db.execSQL("DELETE FROM " + TABLE_PRODUCTS );
			Log.i("_DataManipulator_","<DELETE FROM>" + TABLE_PRODUCTS + ">\n");
	    	// db.close();
	    }catch(Exception e){
			// Toast.makeText(getApplicationContext(), "Error encountered while deleting.", Toast.LENGTH_LONG);
	    	Log.i("_DataManipulator_Exception_ ","<DELETE FROM>" + TABLE_PRODUCTS + ">\n");
		}
	}

	public void deleteAllClients() {
		try{
			// db.delete(TABLE_CLIENTS, null, null);
			db.execSQL("DELETE FROM " + TABLE_CLIENTS );
			Log.i("_DataManipulator_","<DELETE FROM>" + TABLE_CLIENTS + ">\n");
			// db.close();
	    }catch(Exception e){
			// Toast.makeText(getApplicationContext(), "Error encountered while deleting.", Toast.LENGTH_LONG);
	    	Log.i("_DataManipulator_Exception_ ","<DELETE FROM>" + TABLE_CLIENTS + ">\n");
		}
	}
	
	
	public void deleteAll() {
		db.delete(TABLE_ORDERS, null, null);
	}

	public List<String[]> selectAll()
	{
		List<String[]> list = new ArrayList<String[]>();
		Cursor cursor = db.query(TABLE_ORDERS, new String[] { "lineOrderId","clientName","productName","piecesNumber","discountNumber" },	null, null, null, null, "clientName asc"); 
		int x=0;
		if (cursor.moveToFirst()) {
			do {
				String[] b1=new String[]{cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4)};
				list.add(b1);
				x=x+1;
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		} 
		cursor.close();
		return list;
	}

	public List<String[]> selectAllProducts()
	{
		List<String[]> list = new ArrayList<String[]>();
		Cursor cursor = db.query(TABLE_PRODUCTS, new String[] { "ID","Name","Price","Symbol" },	null, null, null, null, "Name asc"); 
		// (ID, Name, Price, Symbol)
		int x=0;
		if (cursor.moveToFirst()) {
			do {
				String[] b1=new String[]{cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3)};
				list.add(b1);
				x=x+1;
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		} 
		cursor.close();
		return list;
	}

	public List<String[]> selectAllClients()
	{
		List<String[]> list = new ArrayList<String[]>();
		// Cursor cursor = db.query(TABLE_CLIENTS, new String[] { "Agent","Client","Route","Zone" },	null, null, null, null, "Route, Client asc"); 
		Cursor cursor = db.query(TABLE_CLIENTS, new String[] { "Agent","Client","Route","Zone" },	null, null, null, null, "Client asc");
		int x=0;
		if (cursor.moveToFirst()) {
			do {
				String[] b1=new String[]{cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3)};
				list.add(b1);
				x=x+1;
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		} 
		cursor.close();
		return list;
	}
	
	
	
	public void delete(int rowId) {
		db.delete(TABLE_ORDERS, null, null);
	}

    // no-delete if missing ? 
	public void close() {
		db.close();
	}

	private static class OpenHelper extends SQLiteOpenHelper {
		OpenHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}
		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("CREATE TABLE " + TABLE_ORDERS +   " (lineOrderId INTEGER PRIMARY KEY, clientName TEXT, productName TEXT, piecesNumber TEXT, discountNumber TEXT)");
			db.execSQL("CREATE TABLE " + TABLE_PRODUCTS + " (_id integer primary key autoincrement, ID TEXT, Name TEXT, Price TEXT, Symbol TEXT)");
			db.execSQL("CREATE TABLE " + TABLE_CLIENTS + "  (_id integer primary key autoincrement, Agent TEXT, Client TEXT, Route TEXT, Zone TEXT)");
		}
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDERS);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLIENTS);
			onCreate(db);
		}
	}
}
