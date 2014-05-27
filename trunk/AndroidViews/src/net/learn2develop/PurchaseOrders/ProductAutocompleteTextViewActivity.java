
/*
   	http://www.mysamplecode.com/2011/10/android-autocompletetextview.html
	http://www.mysamplecode.com/2011/10/android-autocompletetextview_19.html
*/

package net.learn2develop.PurchaseOrders;

import net.learn2develop.R;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
// import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.TextView;
 
public class ProductAutocompleteTextViewActivity extends Activity {
 
    private AutoCompleteTextView autocompleteDenumireView;
    private DataManipulator dbHelper;
    private EditText itemView;
    private TextView descView;

 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.autocomplete_cursor_main);
 
        dbHelper = new DataManipulator(this);
        // dbHelper.open();
 
        itemView = (EditText) findViewById(R.id.item);
        descView = (TextView) findViewById(R.id.itemDesc);
        autocompleteDenumireView = (AutoCompleteTextView) findViewById(R.id.autocomplete_desc);
        // itemDescriptionView.setDropDownBackgroundResource(R.color.autocompletet_background_color);
        autocompleteDenumireView.setDropDownBackgroundResource(R.color.background_color);
        // autocompleteDenumireView.setTextColor(R.color.text_color);
        // autocompleteDenumireView.setTextColor(Color.parseColor("#00FFFF"));
        
        // http://stackoverflow.com/questions/18662938/change-color-of-certain-element-in-autocompletetextview
 
        // Create an ItemAutoTextAdapter for the Item description field,
        // and set it as the OnItemClickListener for that field.
        ProductAutoTextAdapter adapter = this.new ProductAutoTextAdapter(dbHelper);
        autocompleteDenumireView.setAdapter(adapter);
        autocompleteDenumireView.setOnItemClickListener(adapter);
        
        
    }
 
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dbHelper  != null) {
            dbHelper.close();
        }
    }
 
    class ProductAutoTextAdapter extends CursorAdapter
    implements android.widget.AdapterView.OnItemClickListener {
 
        private DataManipulator mDbHelper;
 
        /**
         * Constructor. Note that no cursor is needed when we create the
         * adapter. Instead, cursors are created on demand when completions are
         * needed for the field. (see
         * {@link ProductAutoTextAdapter#runQueryOnBackgroundThread(CharSequence)}.)
         *
         * @param dbHelper
         *            The AutoCompleteDbAdapter in use by the outer class
         *            object.
         */
        public ProductAutoTextAdapter(DataManipulator dbHelper) {
            // Call the CursorAdapter constructor with a null Cursor.
            super(ProductAutocompleteTextViewActivity.this, null);
            mDbHelper = dbHelper;
        }
 
        /**
         * Invoked by the AutoCompleteTextView field to get completions for the
         * current input.
         *
         * NOTE: If this method either throws an exception or returns null, the
         * Filter class that invokes it will log an error with the traceback,
         * but otherwise ignore the problem. No choice list will be displayed.
         * Watch those error logs!
         *
         * @param constraint
         *            The input entered thus far. The resulting query will
         *            search for Items whose description begins with this string.
         * @return A Cursor that is positioned to the first row (if one exists)
         *         and managed by the activity.
         */
        @Override
        public Cursor runQueryOnBackgroundThread(CharSequence constraint) {
            if (getFilterQueryProvider() != null) {
                return getFilterQueryProvider().runQuery(constraint);
            }
 
            Cursor cursor = mDbHelper.fetchItemsByDesc(
                    (constraint != null ? constraint.toString() : "@@@@"));
 
            return cursor;
        }
 
        /**
         * Called by the AutoCompleteTextView field to get the text that will be
         * entered in the field after a choice has been made.
         *
         * @param Cursor
         *            The cursor, positioned to a particular row in the list.
         * @return A String representing the row's text value. (Note that this
         *         specializes the base class return value for this method,
         *         which is {@link CharSequence}.)
         */
        @Override
        public String convertToString(Cursor cursor) {
            final int columnIndex = cursor.getColumnIndexOrThrow("denumire");
            final String str = cursor.getString(columnIndex);
            return str;
        }
 
        /**
         * Called by the ListView for the AutoCompleteTextView field to display
         * the text for a particular choice in the list.
         *
         * @param view
         *            The TextView used by the ListView to display a particular
         *            choice.
         * @param context
         *            The context (Activity) to which this form belongs;
         * @param cursor
         *            The cursor for the list of choices, positioned to a
         *            particular row.
         */
        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            //final String text = convertToString(cursor);
            //((TextView) view).setText(text);
        	
        	// final int itemColumnIndex = cursor.getColumnIndexOrThrow("itemNumber");
        	final int itemColumnIndex = cursor.getColumnIndexOrThrow("stoc_id");
            // final int descColumnIndex = cursor.getColumnIndexOrThrow("description");
            final int descColumnIndex = cursor.getColumnIndexOrThrow("denumire");
            TextView text1 = (TextView) view.findViewById(R.id.text1);
            text1.setText(cursor.getString(itemColumnIndex));
            TextView text2 = (TextView) view.findViewById(R.id.text2);
            text2.setText(cursor.getString(descColumnIndex));
        }
 
        /**
         * Called by the AutoCompleteTextView field to display the text for a
         * particular choice in the list.
         *
         * @param context
         *            The context (Activity) to which this form belongs;
          * @param cursor
         *            The cursor for the list of choices, positioned to a
         *            particular row.
         * @param parent
         *            The ListView that contains the list of choices.
         *
         * @return A new View (really, a TextView) to hold a particular choice.
         */
        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            final LayoutInflater inflater = LayoutInflater.from(context);
            final View view = inflater.inflate(R.layout.autocomplete_item_list,parent, false);
            return view;
        }
 
        /**
         * Called by the AutoCompleteTextView field when a choice has been made
         * by the user.
         *
         * @param listView
         *            The ListView containing the choices that were displayed to
         *            the user.
         * @param view
         *            The field representing the selected choice
         * @param position
         *            The position of the choice within the list (0-based)
         * @param id
         *            The id of the row that was chosen (as provided by the _id
         *            column in the cursor.)
         */
        // @Override
        public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
            // Get the cursor, positioned to the corresponding row in the result set
            Cursor cursor = (Cursor) listView.getItemAtPosition(position);
 
            // Get the Item Number from this row in the database.
            String itemNumber = cursor.getString(cursor.getColumnIndexOrThrow("stoc_id"));
 
            // Update the parent class's TextView
            itemView.setText(itemNumber);
            descView.setText(autocompleteDenumireView.getText());
            Log.w("Quantity:", String.valueOf(descView.getText().length()));
            autocompleteDenumireView.setText("");
        }
    }
 
}
