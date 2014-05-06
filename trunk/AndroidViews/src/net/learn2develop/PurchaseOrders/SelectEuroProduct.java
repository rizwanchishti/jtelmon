
// http://www.mysamplecode.com/2012/07/android-listview-cursoradapter-sqlite.html

package net.learn2develop.PurchaseOrders;

import net.learn2develop.R;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
 
public class SelectEuroProduct extends Activity {
 
 public static final String KEY_DENUMIRE = "denumire";
 public static final String KEY_CLASA = "clasa";
 public static final String KEY_GRUPA = "grupa";
 public static final String KEY_CATEGORIE = "categorie";

 private DataManipulator dbHelper;
 private SimpleCursorAdapter dataAdapter;
 
 @Override
 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
     setContentView(R.layout.euro_product_main);

  dbHelper = new DataManipulator(this);

  //Generate ListView from SQLite Database
  displayListView();
 
 }
 
 private void displayListView() {
  
  Cursor cursor = dbHelper.selectAllEuroProducts();
  // Log.i("LOG_SelectieClientEurobit.displayListView : ", "Cursor(0)" + cursor.getColumnName(0));
  
  // The desired columns to be bound
  String[] columns = new String[] {
    SelectEuroProduct.KEY_DENUMIRE,
    SelectEuroProduct.KEY_CLASA,
    SelectEuroProduct.KEY_GRUPA,
    SelectEuroProduct.KEY_CATEGORIE
  };
 
  // the XML defined views which the data will be bound to
  int[] to = new int[] { 
    R.id.denumire,
    R.id.clasa,
    R.id.grupa,
    R.id.categorie,
  };
 
  // create the adapter using the cursor pointing to the desired data 
  //as well as the layout information
  dataAdapter = new SimpleCursorAdapter(
    this, R.layout.euro_product_info, 
    cursor, 
    columns, 
    to);
 
  ListView listView = (ListView) findViewById(R.id.listView1);
  // Assign adapter to ListView
  listView.setAdapter(dataAdapter);
 
 
  listView.setOnItemClickListener(new OnItemClickListener() {
   // @Override
   public void onItemClick(AdapterView<?> listView, View view, 
     int position, long id) {
   // Get the cursor, positioned to the corresponding row in the result set
   Cursor cursor = (Cursor) listView.getItemAtPosition(position);
 
   // Get the state's capital from this row in the database.
   String countryCode = 
    cursor.getString(cursor.getColumnIndexOrThrow("denumire"));
   Toast.makeText(getApplicationContext(),
     countryCode, Toast.LENGTH_SHORT).show();
 
   }
  });
 
  EditText myFilter = (EditText) findViewById(R.id.myFilter);
  myFilter.addTextChangedListener(new TextWatcher() {
 
   public void afterTextChanged(Editable s) {
   }
 
   public void beforeTextChanged(CharSequence s, int start, 
     int count, int after) {
   }
 
   public void onTextChanged(CharSequence s, int start, 
     int before, int count) {
	   dataAdapter.getFilter().filter(s.toString());
   }
  });
   
  dataAdapter.setFilterQueryProvider(new FilterQueryProvider() {
         public Cursor runQuery(CharSequence constraint) {
             return dbHelper.fetchEuroProductsByName(constraint.toString());
         }
     });
 
 }
}