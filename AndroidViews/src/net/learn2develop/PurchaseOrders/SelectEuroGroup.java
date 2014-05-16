
// http://www.mysamplecode.com/2012/07/android-listview-cursoradapter-sqlite.html
// http://go2tricks.blogspot.ro/2012/04/android-spinner-dropdown-example.html
// http://www.androidpatterns.com/uap_category/getting-input

package net.learn2develop.PurchaseOrders;

import java.util.List;
import net.learn2develop.R;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
 
public class SelectEuroGroup extends Activity implements OnItemSelectedListener {
 
 public static final String KEY_DENUMIRE = "denumire";
 public static final String KEY_CLASA = "clasa";
 public static final String KEY_GRUPA = "grupa";
 public static final String KEY_CATEGORIE = "categorie";

 private DataManipulator dbHelper;
 private SimpleCursorAdapter dataAdapter;
 private EditText myFilter;
 
 @Override
 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
     setContentView(R.layout.euro_class_group);

  dbHelper = new DataManipulator(this);

  //Generate ListView from SQLite Database
  displayListView();
 
 }
 
 private void displayListView() {
  
  Cursor cursor = dbHelper.selectAllEuroProducts();
  // Log.i("LOG_SelectieClientEurobit.displayListView : ", "Cursor(0)" + cursor.getColumnName(0));
  
  // The desired columns to be bound
  String[] columns = new String[] {
    SelectEuroGroup.KEY_DENUMIRE,
    SelectEuroGroup.KEY_CLASA,
    SelectEuroGroup.KEY_GRUPA,
    SelectEuroGroup.KEY_CATEGORIE
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
  Spinner spinner = (Spinner) findViewById(R.id.spinnerClass);
  // Assign adapter to ListView -> 
  listView.setAdapter(dataAdapter);
  // Spinner click listener -> 
  spinner.setOnItemSelectedListener(this);
  List<String> categories = dbHelper.selectClassesOfProducts();
  // Creating adapter for spinner -> 
  ArrayAdapter<String> spDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
  // Drop down layout style - list view with radio button
  spDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
  // attaching data adapter to spinner
  spinner.setAdapter(spDataAdapter);

 
  listView.setOnItemClickListener(new OnItemClickListener() {
   // @Override
   public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
   // Get the cursor, positioned to the corresponding row in the result set
   Cursor cursor = (Cursor) listView.getItemAtPosition(position);
 
   // Get the state's capital from this row in the database.
   String countryCode = cursor.getString(cursor.getColumnIndexOrThrow("denumire"));
   Toast.makeText(getApplicationContext(), countryCode, Toast.LENGTH_SHORT).show();
 
   }
  });
 
  // EditText 
  myFilter = (EditText) findViewById(R.id.myFilter);
  myFilter.addTextChangedListener(new TextWatcher() {
 
   public void afterTextChanged(Editable s) {
   }
 
   public void beforeTextChanged(CharSequence s, int start, 
     int count, int after) {
   }
 
   public void onTextChanged(CharSequence s, int start, 
     int before, int count) {
	   dataAdapter.setFilterQueryProvider(new FilterQueryProvider() {
	         public Cursor runQuery(CharSequence constraint) {
	             return dbHelper.fetchEuroProductsByName(constraint.toString());
	         }
	     });
	   dataAdapter.getFilter().filter(s.toString().trim());
   }
  });
   
 
 }

public void onItemSelected(AdapterView<?> parent, View arg1, int position, long arg3) {
	// On selecting a spinner item
	String item = parent.getItemAtPosition(position).toString();
	// Showing selected spinner item
	Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
	
	// http://stackoverflow.com/questions/18951963/sort-listview-items-from-sqlite-android
	dataAdapter.setFilterQueryProvider(new FilterQueryProvider() {
        public Cursor runQuery(CharSequence constraint) {
            return dbHelper.fetchProductsFromClass(constraint.toString());
        }
    });
	dataAdapter.getFilter().filter(item);
	// myFilter.setText("");
}

public void onNothingSelected(AdapterView<?> arg0) {
	// TODO Auto-generated method stub
	
}
}