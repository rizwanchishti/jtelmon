/*
 * http://code.google.com/p/jtelmon/source/browse/trunk/AndroidViews/src/net/learn2develop/PurchaseOrders/DataManipulator.java
 *  
 * http://www.edumobile.org/android/android-development/use-of-sqlite/
 * http://android-er.blogspot.com/2011/06/delete-row-in-sqlite-database.html
 * 
 */

package net.learn2develop.PurchaseOrders;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.util.Log;

public class DataManipulator {
	
        private static final String DATABASE_NAME = "easysales.db";
        private static final int DATABASE_VERSION = 1;
        static final String  TABLE_ORDERS         = "orders";
        static final String  TABLE_PRODUCTS       = "products";
        static final String  TABLE_CLIENTS        = "clients";
        static final String  TABLE_EURO_CLIENTS   = "clients_eurobit";
        static final String  TABLE_EURO_PRODUCTS  = "products_eurobit";
        static final String  TABLE_SETUP          = "setup";
        static final String  TABLE_HEADER_COMANDA = "comenzi_v_ext_header";
        static final String  TRIGGER_HEADER_COMANDA = "comenzi_v_ext_header_trigger";
        static final String  TABLE_LINII_COMANDA  = "comenzi_cv_ext_linii";
        private static Context context;
        static SQLiteDatabase db;
        private SQLiteStatement insertOrderTemplate;
        private SQLiteStatement insertProductTemplate;
        private SQLiteStatement insertClientTemplate;
        private SQLiteStatement insertClientEurobitTemplate;
        private SQLiteStatement insertProductEurobitTemplate;
        private SQLiteStatement insertSetupTemplate;
        private SQLiteStatement insertOrderHeaderTemplate;
        private SQLiteStatement insertOrderLineTemplate;
        private static final String INSERT_ORDERS           = "insert into " + TABLE_ORDERS         + " (clientName,productName,piecesNumber,discountNumber) values (?,?,?,?)";
    	private static final String INSERT_PRODUCTS         = "insert into " + TABLE_PRODUCTS       + " (ID, Name, Price, Symbol) values (?,?,?,?)";
    	private static final String INSERT_CLIENTS          = "insert into " + TABLE_CLIENTS        + " (Agent, Client, Route, Zone) values (?,?,?,?)";
    	private static final String INSERT_CLIENTS_EUROBIT  = "insert into " + TABLE_EURO_CLIENTS   + " (client, cui, plt, tert_id, categorie, categorie_id, clasa, clasa_id, grupa, grupa_id) values (?,?,?,?,?,?,?,?,?,?)";
    	private static final String INSERT_PRODUCTS_EUROBIT = "insert into " + TABLE_EURO_PRODUCTS  + " (stoc_id, simbol, denumire, categorie_id, grupa_id, clasa_id, clasa, grupa, categorie, pret_gross) values (?,?,?,?,?,?,?,?,?,?)";
    	private static final String INSERT_SETUP            = "insert into " + TABLE_SETUP          + " (UtilizatorID, UserName, Parola) values (?,?,?)";
    	private static final String INSERT_HEADER_COMANDA   = "insert into " + TABLE_HEADER_COMANDA + " (nrdoc,data_c,gestiune_id,tert_id,valoare,nrlc_id,data_l,user_id,nivacc,operare,verstor,tiparit,facturat,zscadenta,pr_disc_expl,val_disc_expl,NrFact,data_f) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    	private static final String INSERT_LINII_COMANDA    = "insert into " + TABLE_LINII_COMANDA  + " (com_id,nrlinie,stoc_id,cont_gest,cantitate,cantitater,livrat,pret_vanzare,pr_disc_incl,disc_contr,disc_com,pret_gross) values (?,?,?,?,?,?,?,?,?,?,?,?)";
    	
   		public DataManipulator(Context context ) {
                DataManipulator.context           = context;
                OpenHelper openHelper             = new OpenHelper(DataManipulator.context);
                DataManipulator.db                = openHelper.getWritableDatabase();
                this.insertOrderHeaderTemplate    = DataManipulator.db.compileStatement(INSERT_HEADER_COMANDA);
                this.insertOrderTemplate          = DataManipulator.db.compileStatement(INSERT_ORDERS);
                this.insertOrderLineTemplate      = DataManipulator.db.compileStatement(INSERT_LINII_COMANDA);
                this.insertProductTemplate        = DataManipulator.db.compileStatement(INSERT_PRODUCTS);
                this.insertClientTemplate         = DataManipulator.db.compileStatement(INSERT_CLIENTS);
                this.insertClientEurobitTemplate  = DataManipulator.db.compileStatement(INSERT_CLIENTS_EUROBIT);
                this.insertProductEurobitTemplate = DataManipulator.db.compileStatement(INSERT_PRODUCTS_EUROBIT);
                this.insertSetupTemplate          = DataManipulator.db.compileStatement(INSERT_SETUP);
    	}

   		public long insertIntoComenziVext(String nrdoc,String data_c,String gestiune_id,String nrlc_id,String tert_id, String valoare,String data_l,String facturat,String user_id,String operare,
        		String verstor,String tiparit,String nivacc,String zscadenta,String pr_disc_expl,String val_disc_expl,String NrFact,String data_f
        		) {
           this.insertOrderHeaderTemplate.bindString(1, nrdoc);
           this.insertOrderHeaderTemplate.bindString(2, data_c);
           this.insertOrderHeaderTemplate.bindString(3, gestiune_id);
           this.insertOrderHeaderTemplate.bindString(4, tert_id);
           this.insertOrderHeaderTemplate.bindString(5, valoare);
           this.insertOrderHeaderTemplate.bindString(6, nrlc_id);
           this.insertOrderHeaderTemplate.bindString(7, data_l);
           this.insertOrderHeaderTemplate.bindString(8, user_id);
           this.insertOrderHeaderTemplate.bindString(9, nivacc);
           this.insertOrderHeaderTemplate.bindString(10, operare);
           this.insertOrderHeaderTemplate.bindString(11, verstor);
           this.insertOrderHeaderTemplate.bindString(12, tiparit);
           this.insertOrderHeaderTemplate.bindString(13, facturat);
           this.insertOrderHeaderTemplate.bindString(14, zscadenta);
           this.insertOrderHeaderTemplate.bindString(15, pr_disc_expl);
           this.insertOrderHeaderTemplate.bindString(16, val_disc_expl);
           this.insertOrderHeaderTemplate.bindString(17, NrFact);
           this.insertOrderHeaderTemplate.bindString(18, data_f);
           return this.insertOrderHeaderTemplate.executeInsert();
  }

   		
   		public long insertHeaderIntoComenziVext(ComenziVextHeader orderHeader ) {
   		   String gestiune_id    = " " ;
   		   String data_c         = " " ;
   		   String data_l         = " " ;
   		   String nrdoc          = " " ;
   		   String user_id        = " " ;
   		   String nivacc         = " " ;
   		   String operare        = " " ;
   		   String verstor        = " " ;
   		   String tiparit        = " " ;
   		   String facturat       = " " ;
   		   String zscadenta      = "0" ;
   		   String pr_disc_expl   = " " ;
   		   String val_disc_expl  = " " ;
   		   String NrFact         = " " ;
   		   String data_f         = " " ;
           this.insertOrderHeaderTemplate.bindString(1,  nrdoc);
           this.insertOrderHeaderTemplate.bindString(2,  data_c);
           this.insertOrderHeaderTemplate.bindString(3,  gestiune_id);
           this.insertOrderHeaderTemplate.bindString(4,  orderHeader.getTertId());
           this.insertOrderHeaderTemplate.bindString(5,  orderHeader.getValoare());
           this.insertOrderHeaderTemplate.bindString(6,  orderHeader.getNrlcId());
           this.insertOrderHeaderTemplate.bindString(7,  data_l);
           this.insertOrderHeaderTemplate.bindString(8,  user_id);
           this.insertOrderHeaderTemplate.bindString(9,  nivacc);
           this.insertOrderHeaderTemplate.bindString(10, operare);
           this.insertOrderHeaderTemplate.bindString(11, verstor);
           this.insertOrderHeaderTemplate.bindString(12, tiparit);
           this.insertOrderHeaderTemplate.bindString(13, facturat);
           this.insertOrderHeaderTemplate.bindString(14, zscadenta);
           this.insertOrderHeaderTemplate.bindString(15, pr_disc_expl);
           this.insertOrderHeaderTemplate.bindString(16, val_disc_expl);
           this.insertOrderHeaderTemplate.bindString(17, NrFact);
           this.insertOrderHeaderTemplate.bindString(18, data_f);
           
           return this.insertOrderHeaderTemplate.executeInsert();
  }

   		
   		
   		public long insertIntoOrders(String clientName,String productName,String piecesNumber,String discountNumber) {
                this.insertOrderTemplate.bindString(1, clientName);
                this.insertOrderTemplate.bindString(2, productName);
                this.insertOrderTemplate.bindString(3, piecesNumber);
                this.insertOrderTemplate.bindString(4, discountNumber);
                return this.insertOrderTemplate.executeInsert();
        }

   		public long insertLineInto_ComenziCVext(CommandLine commandLine) {
   			String sSpace = " ";
   			// (com_id, nrlinie, stoc_id, cont_gest, cantitate, cantitater, livrat, pret_vanzare, pr_disc_incl, disc_contr, disc_com, pret_gross)
            this.insertOrderLineTemplate.bindString(1,  commandLine.getComId());
            this.insertOrderLineTemplate.bindString(2,  commandLine.getNrLinie());
            this.insertOrderLineTemplate.bindString(3,  commandLine.getStocId());
            this.insertOrderLineTemplate.bindString(4,  sSpace);
            this.insertOrderLineTemplate.bindString(5,  commandLine.getBucati());
            this.insertOrderLineTemplate.bindString(6,  sSpace);
            this.insertOrderLineTemplate.bindString(7,  sSpace);
            this.insertOrderLineTemplate.bindString(8,  commandLine.getPretGross());
            this.insertOrderLineTemplate.bindString(9,  commandLine.getDiscount());
            this.insertOrderLineTemplate.bindString(10, commandLine.getDiscount());
            this.insertOrderLineTemplate.bindString(11, commandLine.getDiscount());
            this.insertOrderLineTemplate.bindString(12, commandLine.getPretGross());
            return this.insertOrderLineTemplate.executeInsert();
        }

        public long insertIntoProducts(String ID,String Name,String Price,String Symbol) {
                this.insertProductTemplate.bindString(1, ID);
                this.insertProductTemplate.bindString(2, Name);
                this.insertProductTemplate.bindString(3, Price);
                this.insertProductTemplate.bindString(4, Symbol);
                return this.insertProductTemplate.executeInsert();
        }
        
        public long insertIntoClients(String Agent,String Client,String Route,String Zone) {
        		this.insertClientTemplate.bindString(1, Agent);
        		this.insertClientTemplate.bindString(2, Client);
        		this.insertClientTemplate.bindString(3, Route);
        		this.insertClientTemplate.bindString(4, Zone);
        		return this.insertClientTemplate.executeInsert();
        }

        public long insertIntoEurobitClients(String Client, String Cui, String Plt, String Tert_id, String Categorie, String Categorie_id, String Clasa, String Clasa_id, String Grupa, String Grupa_id) {
    		this.insertClientEurobitTemplate.bindString(1, Client);
    		this.insertClientEurobitTemplate.bindString(2, Cui);
    		this.insertClientEurobitTemplate.bindString(3, Plt);
    		this.insertClientEurobitTemplate.bindString(4, Tert_id);
    		this.insertClientEurobitTemplate.bindString(5, Categorie);
    		this.insertClientEurobitTemplate.bindString(6, Clasa);
    		this.insertClientEurobitTemplate.bindString(7, Clasa_id);
    		this.insertClientEurobitTemplate.bindString(8, Categorie_id);
    		this.insertClientEurobitTemplate.bindString(9, Grupa);
    		this.insertClientEurobitTemplate.bindString(10, Grupa_id);
    		return this.insertClientEurobitTemplate.executeInsert();
    }

        public long insertIntoEurobitProducts(String Stoc_id, String Simbol, String Denumire, String Categorie_id, String Grupa_id, String Clasa_id, String Clasa, String Grupa, String Categorie, String PretGross) {
    		this.insertProductEurobitTemplate.bindString(1, Stoc_id);
    		this.insertProductEurobitTemplate.bindString(2, Simbol);
    		this.insertProductEurobitTemplate.bindString(3, Denumire);
    		this.insertProductEurobitTemplate.bindString(4, Categorie_id);
    		this.insertProductEurobitTemplate.bindString(5, Grupa_id);
    		this.insertProductEurobitTemplate.bindString(6, Clasa_id);
    		this.insertProductEurobitTemplate.bindString(7, Clasa);
    		this.insertProductEurobitTemplate.bindString(8, Grupa);
    		this.insertProductEurobitTemplate.bindString(9, Categorie);
    		this.insertProductEurobitTemplate.bindString(10, PretGross);
        	return this.insertProductEurobitTemplate.executeInsert();
        }
        
        public long insertIntoSetup(String strID, String strUserName, String strPassword) {
    		this.insertSetupTemplate.bindString(1, strID);
    		this.insertSetupTemplate.bindString(2, strUserName);
    		this.insertSetupTemplate.bindString(3, strPassword);
    		// this.insertClientTemplate.bindString(3, Route);
    		// this.insertClientTemplate.bindString(4, Zone);
    		return this.insertSetupTemplate.executeInsert();
    }

        public void insereazaLiniileComenzii(long com_id, List<CommandLine> listOfCommandLines){
        	Iterator i  = listOfCommandLines.iterator();
        	int id = select_MaxID_from_ComenziVheader();
        	while (i.hasNext())
        	{
        		CommandLine  commandLine= (CommandLine) i.next();
        		commandLine.setComId(Long.toString(id));
        		this.insertLineInto_ComenziCVext(commandLine);
          		// System.out.println(value.getprodus()+ "-" +value.getbucati()+ "-" +value.getcost());
        	}
        }

        public int select_MaxID_from_ComenziVheader_old()
        {
                int id = 0;
                // final String MY_QUERY = "SELECT MAX(_id) FROM " + TABLE_HEADER_COMANDA;
                final String MY_QUERY = "SELECT * FROM " + TABLE_HEADER_COMANDA;
                Cursor cursor = DataManipulator.db.rawQuery(MY_QUERY, null);  
                try {
                    if (cursor.getCount() > 0) {
                      cursor.moveToFirst();
                      id = cursor.getInt(cursor.getColumnIndex("_id"));
                      cursor.close();
                    }
                  } catch (Exception e) {
                    System.out.println(e.getMessage());
                  }
                return id;
        }

        public int select_MaxID_from_ComenziVheader()
        {
                int id = 0;

                Cursor cursor = db.rawQuery("SELECT seq FROM sqlite_sequence WHERE name=?", 
                        new String[] { TABLE_HEADER_COMANDA });

                try {
                    id = (cursor.moveToFirst() ? cursor.getInt(0) : 0);
                    cursor.close();
                  } catch (Exception e) {
                    System.out.println(e.getMessage());
                  }
                return id;
        }

        
        public void sincroDB(){
                // ToDo ... Next 
        }

        
        public void deleteAllProducts() {
            // db.delete(TABLE_PRODUCTS, null, null);
            try{
                // db = openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE,null);
                db.execSQL("DELETE FROM " + TABLE_PRODUCTS );
                        Log.i("_DataManipulator_","<DELETE FROM>" + TABLE_PRODUCTS + ">\n");
                // db.close();
            }catch(Exception e){
                // Toast.makeText(getApplicationContext(), "Error encountered while deleting.", Toast.LENGTH_LONG);
            }
        }
        
        public void deleteAllRecordsFromTable(String theTable) {
            try{
            	 db.delete(theTable, null, null);
            	 // db = openOrCreateDatabase(DBNAME, Context.MODE_PRIVATE,null);
            	 //db.execSQL("DELETE FROM " + TABLE_CLIENTS );
                 //Log.i("_DataManipulator_","<DELETE FROM>" + TABLE_CLIENTS + ">\n");
            	 // db.close();
            }catch(Exception e){
                	// Toast.makeText(this, "Error encountered while deleting.", Toast.LENGTH_LONG);
            		System.out.println(e.getMessage());
            }
        }
        
        public void deleteAllClients() {
            try{
            	 db.delete(TABLE_CLIENTS, null, null);
            	 // db = openOrCreateDatabase(DBNAME, Context.MODE_PRIVATE,null);
            	 //db.execSQL("DELETE FROM " + TABLE_CLIENTS );
                 //Log.i("_DataManipulator_","<DELETE FROM>" + TABLE_CLIENTS + ">\n");
            	 // db.close();
            }catch(Exception e){
                	// Toast.makeText(this, "Error encountered while deleting.", Toast.LENGTH_LONG);
            		// System.out.println(e.getMessage());
            }
        }

        public void deleteAllSetup() {
            try{
            	 db.delete(TABLE_SETUP, null, null);
            }catch(Exception e){
                	// Toast.makeText(this, "Error encountered while deleting.", Toast.LENGTH_LONG);
            		System.out.println(e.getMessage());
            }
        }

        
        public void deleteAll() {
                db.delete(TABLE_ORDERS, null, null);
        }

        public List<String[]> selectAllOrdersOLD()
        {
                List<String[]> list = new ArrayList<String[]>();
                Cursor cursor = db.query(TABLE_ORDERS, new String[] { "lineOrderId","clientName","productName","piecesNumber","discountNumber" }, null, null, null, null, "clientName asc"); 
                int x=0;
                if (cursor.moveToFirst()) {
                        do {
                                String[] b1=new String[]{cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4)};
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

        public List<String[]> selectAllOrders()
        {
                List<String[]> list = new ArrayList<String[]>();
                String selectQuery = "SELECT thc.com_id, tec.client, tep.denumire, tlc.cantitate, tlc.pret_gross FROM " 
                        + TABLE_HEADER_COMANDA + " thc, "
                        + TABLE_LINII_COMANDA + " tlc, "
                        + TABLE_EURO_CLIENTS  + " tec, " 
                        + TABLE_EURO_PRODUCTS + " tep "
                        + " WHERE thc.com_id  = tlc.com_id "
                        + " AND thc.tert_id = tec.tert_id "
                        + " AND tlc.stoc_id=tep.stoc_id ";
                Cursor cursor = db.rawQuery(selectQuery, null);
                int x=0;
                if (cursor.moveToFirst()) {
                        do {
                                String[] b1=new String[]{cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4)};
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
                Cursor cursor = db.query(TABLE_PRODUCTS, new String[] { "ID","Name","Price","Symbol" }, null, null, null, null, "Name asc"); 
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
        

        public List<String> selectClassesOfProducts()
        {
                List<String> list = new ArrayList<String>();
                // http://my.safaribooksonline.com/book/programming/android/9781849518123/3dot-sqlite-queries/ch03s04_html
                // http://www.mysamplecode.com/2011/10/android-sqlite-query-example-selection.html
                Cursor cursor = db.query(true, TABLE_EURO_PRODUCTS, new String[] { "clasa" }, null, null, null, null, null, null);
                int x=0;
                if (cursor.moveToFirst()) {
                        do {
                                String b1=new String(cursor.getString(0));
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

        public List<String> selectGroupsOfProducts()
        {
                List<String> list = new ArrayList<String>();
                Cursor cursor = db.query(true, TABLE_EURO_PRODUCTS, new String[] { "grupa" }, null, null, null, null, null, null);
                int x=0;
                if (cursor.moveToFirst()) {
                        do {
                                String b1=new String(cursor.getString(0));
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

        public List<String> selectCategoriesOfProducts()
        {
                List<String> list = new ArrayList<String>();
                Cursor cursor = db.query(true, TABLE_EURO_PRODUCTS, new String[] { "categorie" }, null, null, null, null, null, null);
                int x=0;
                if (cursor.moveToFirst()) {
                        do {
                                String b1=new String(cursor.getString(0));
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
                Cursor cursor = db.query(TABLE_CLIENTS, new String[] { "Agent","Client","Route","Zone" }, null, null, null, null, "Client asc"); 
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

        public Cursor selectAllEuroProducts()
        {
                Cursor cursor = db.query(TABLE_EURO_PRODUCTS, new String[] { "_id","stoc_id","simbol","denumire","categorie_id","grupa_id","clasa_id","clasa","grupa","categorie"}, null, null, null, null, "denumire asc"); 
                if (cursor != null) {
                	   cursor.moveToFirst();
                }
                return cursor;
        }

        public Cursor fetchProductsFromClass(String inputText) throws SQLException {
        	Cursor mCursor = null;
        	  if (inputText == null  ||  inputText.length () == 0)  {
          		  mCursor = selectAllEuroProducts();
          	  }
          	  else {
          		   mCursor = db.query(TABLE_EURO_PRODUCTS, new String[] { "_id","stoc_id","simbol","denumire","categorie_id","grupa_id","clasa_id","clasa","grupa","categorie"}, "clasa" + " like '%"+ inputText + "%'" , null, null, null, "clasa asc");
          	  }
          	  if (mCursor != null) {
          		  mCursor.moveToFirst();
          	  }
        	return mCursor;
        }

        public Cursor fetchProductsFromGroup(String inputText) throws SQLException {
        	Cursor mCursor = null;
        	  if (inputText == null  ||  inputText.length () == 0)  {
          		  mCursor = selectAllEuroProducts();
          	  }
          	  else {
          		   mCursor = db.query(TABLE_EURO_PRODUCTS, new String[] { "_id","stoc_id","simbol","denumire","categorie_id","grupa_id","clasa_id","clasa","grupa","categorie"}, "grupa" + " like '%"+ inputText + "%'" , null, null, null, "grupa asc");
          	  }
          	  if (mCursor != null) {
          		  mCursor.moveToFirst();
          	  }
        	return mCursor;
        }

        public Cursor fetchProductsFromCategory(String inputText) throws SQLException {
        	Cursor mCursor = null;
        	  if (inputText == null  ||  inputText.length () == 0)  {
          		  mCursor = selectAllEuroProducts();
          	  }
          	  else {
          		   mCursor = db.query(TABLE_EURO_PRODUCTS, new String[] { "_id","stoc_id","simbol","denumire","categorie_id","grupa_id","clasa_id","clasa","grupa","categorie"}, "categorie" + " like '%"+ inputText + "%'" , null, null, null, "categorie asc");
          	  }
          	  if (mCursor != null) {
          		  mCursor.moveToFirst();
          	  }
        	return mCursor;
        }
        
        public Cursor fetchEuroProductsByName(String inputText) throws SQLException {
      	  Cursor mCursor = null;
      	  if (inputText == null  ||  inputText.length () == 0)  {
      		  mCursor = selectAllEuroProducts();
      	  }
      	  else {
      		   mCursor = db.query(TABLE_EURO_PRODUCTS, new String[] { "_id","stoc_id","simbol","denumire","categorie_id","grupa_id","clasa_id","clasa","grupa","categorie"}, "denumire" + " like '%"+ inputText + "%'" , null, null, null, "denumire asc");
      	  }
      	  if (mCursor != null) {
      		  mCursor.moveToFirst();
      	  }
      	  return mCursor;
      	 }
        
        public Cursor selectAllEuroClients()
        {
        		// client , cui , plt , tert_id , categorie , categorie_id , clasa , clasa_id , grupa , grupa_id
                Cursor cursor = db.query(TABLE_EURO_CLIENTS, new String[] { "_id","client","cui","plt","tert_id","categorie","categorie_id","clasa","clasa_id","grupa","grupa_id"}, null, null, null, null, "client asc"); 
        	    // Cursor cursor = db.query(TABLE_CLIENTS_EUROBIT, new String[] { "client","cui","plt","tert_id"}, null, null, null, null, "client asc");
                if (cursor != null) {
                	   cursor.moveToFirst();
                }
                return cursor;
        }

        public Cursor fetchEurobitClientsByName(String inputText) throws SQLException {
        	  // Log.w(TAG, inputText);
        	  Cursor mCursor = null;
        	  if (inputText == null  ||  inputText.length () == 0)  {
        	   // mCursor = db.query(SQLITE_TABLE, new String[] {KEY_ROWID, KEY_CODE, KEY_NAME, KEY_CONTINENT, KEY_REGION}, null, null, null, null, null);
        		  mCursor = selectAllEuroClients();
        	  }
        	  else {
        		  // mCursor = mDb.query(true, TABLE_CLIENTS_EUROBIT, new String[] {KEY_ROWID, KEY_CODE, KEY_NAME, KEY_CONTINENT, KEY_REGION}, KEY_NAME + " like '%" + inputText + "%'", null, null, null, null, null);
        		   mCursor = db.query(TABLE_EURO_CLIENTS, new String[] { "_id","client","cui","plt","tert_id","categorie","categorie_id","clasa","clasa_id","grupa","grupa_id"}, "client" + " like '%"+ inputText + "%'" , null, null, null, "client asc");
        		  // mCursor = selectAllEurobitClients();
        	  }
        	  if (mCursor != null) {
        		  mCursor.moveToFirst();
        	  }
        	  return mCursor;
        	 }


        public String[] selectFirstRecordFromSetupTable()
        {
                String[] setupInfo = null ;
                
                Cursor cursor = db.query(TABLE_SETUP, new String[] { "UtilizatorID","Parola","UserName","SefID", "ZonaID" }, null, null, null, null, "UserName asc");
                if (cursor.moveToFirst()) {
                	setupInfo = new String[]{cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4)};
                }
                if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                } 
                cursor.close();
                return setupInfo;
        }

      
        public Cursor fetchProductsByLetters(String inputText) throws SQLException {
            Cursor mCursor = db.query(true, TABLE_EURO_PRODUCTS, new String[] {"_id",
            		"stoc_id", "denumire", "pret_gross"}, "denumire" + " like '%" + inputText + "%'", null,
                    null, null, null, null);
            if (mCursor != null) {
                mCursor.moveToFirst();
            }
            return mCursor;
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
                	db.execSQL("CREATE TABLE " + TABLE_HEADER_COMANDA + " (_id integer primary key autoincrement, com_id TEXT , nrdoc TEXT, data_c TEXT, gestiune_id TEXT, tert_id TEXT, valoare TEXT,nrlc_id TEXT, data_l TEXT, user_id TEXT, nivacc TEXT, operare TEXT, verstor TEXT, tiparit TEXT, facturat TEXT, zscadenta TEXT, pr_disc_expl TEXT, val_disc_expl TEXT, NrFact TEXT, data_f TEXT )");
                	db.execSQL("CREATE TABLE " + TABLE_LINII_COMANDA + " (_id integer primary key autoincrement, com_id TEXT , nrlinie TEXT, stoc_id TEXT, cont_gest TEXT, cantitate TEXT, cantitater TEXT,livrat TEXT, pret_vanzare TEXT, pr_disc_incl TEXT, disc_contr TEXT, disc_com TEXT, pret_gross TEXT )");
                	db.execSQL("CREATE TABLE " + TABLE_ORDERS +   " (lineOrderId INTEGER PRIMARY KEY, clientName TEXT, productName TEXT, piecesNumber TEXT, discountNumber TEXT)");
                    db.execSQL("CREATE TABLE " + TABLE_PRODUCTS + " (_id integer primary key autoincrement, ID TEXT, Name TEXT, Price TEXT, Symbol TEXT)");
                    db.execSQL("CREATE TABLE " + TABLE_CLIENTS + " (_id integer primary key autoincrement, Agent TEXT, Client TEXT, Route TEXT, Zone TEXT)");
                    db.execSQL("CREATE TABLE " + TABLE_SETUP + " (_id integer primary key autoincrement, UtilizatorID TEXT, Parola TEXT, UserName TEXT, SefID TEXT, ZonaID TEXT, counterComanda TEXT)");
                    db.execSQL("CREATE TABLE " + TABLE_EURO_CLIENTS + " (_id integer primary key autoincrement,client TEXT, cui TEXT, plt TEXT, tert_id TEXT, categorie TEXT, categorie_id TEXT, clasa TEXT, clasa_id TEXT, grupa TEXT, grupa_id TEXT)");
                    db.execSQL("CREATE TABLE " + TABLE_EURO_PRODUCTS + " (_id integer primary key autoincrement,stoc_id TEXT, simbol TEXT, denumire TEXT, categorie_id TEXT, grupa_id TEXT, clasa_id TEXT, clasa TEXT, grupa TEXT, categorie TEXT, pret_gross TEXT)");
                    db.execSQL("CREATE TRIGGER " + TRIGGER_HEADER_COMANDA + " AFTER INSERT ON " + TABLE_HEADER_COMANDA+ " BEGIN UPDATE " + TABLE_HEADER_COMANDA+ " SET com_id=cast(_id as text) WHERE rowid = new.rowid; END");
                    // http://stackoverflow.com/questions/12419693/convert-integer-to-text-in-sqlites-select-query
                    // http://sqlite.awardspace.info/syntax/sqlitepg11.htm
                }   
                    
                // http://www.vogella.com/tutorials/AndroidSQLite/article.html
                // Method is called during an upgrade of the database,
                // e.g. if you increase the database version
                @Override
                public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                	db.execSQL("DROP TABLE IF EXISTS " + TABLE_HEADER_COMANDA);
                	db.execSQL("DROP TABLE IF EXISTS " + TABLE_LINII_COMANDA);
                    db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDERS);
                    db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
                    db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLIENTS);
                    db.execSQL("DROP TABLE IF EXISTS " + TABLE_EURO_CLIENTS);
                    db.execSQL("DROP TABLE IF EXISTS " + TABLE_EURO_PRODUCTS);
                    db.execSQL("DROP TABLE IF EXISTS " + TABLE_SETUP);
                    onCreate(db);
                }
        }
}
