/*
 * 
 * http://developer.android.com/resources/tutorials/views/hello-autocomplete.html
 * http://www.giantflyingsaucer.com/blog/?p=1342
 *
 */

package net.learn2develop.WCF2SQLite;

import java.util.List;

import net.learn2develop.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

 
public class SQLiteAutocomplete02 extends Activity implements OnClickListener
{
    DataManipulator dataMan;
    List<String[]> listOfRoutes =null ;
	String[] stringOfClients;
	String theClient;
 
  
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.autocomplete_client);
		View buttonOrder = findViewById(R.id.buttonOrder);
		buttonOrder.setOnClickListener(this);

 
        final AutoCompleteTextView acTextView = (AutoCompleteTextView) findViewById(R.id.autocompleteClient);
 
        dataMan = new DataManipulator(this);
        listOfRoutes = dataMan.selectAllClients();
		stringOfClients=new String[listOfRoutes.size()]; 
		
		int x=0;
		String stringTemporar;

		for (String[] name : listOfRoutes) {
			// Route = name[0]+" - "+name[1]+ " - "+name[2]+" - "+name[3];
			stringTemporar = name[1];
			stringOfClients[x]=stringTemporar;
			x++;
		}

        // Print out the values to the log
        for(int i = 0; i < stringOfClients.length; i++)
        {
            Log.i(this.toString(), stringOfClients[i]);
        }
 
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, stringOfClients);
        acTextView.setAdapter(adapter);
        
        acTextView.setOnItemClickListener(new OnItemClickListener() {
            // http://stackoverflow.com/questions/3434978/android-autocompletetextview-click-event
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // Intent intent = new Intent(Main.this, Campus.class);
                Bundle bundle = new Bundle();
                theClient = arg0.getItemAtPosition(arg2).toString();
                bundle.putString("university_name", arg0.getItemAtPosition(arg2).toString());
                bundle.putLong("_id", arg3);
                // intent.putExtras(bundle);
                // startActivity(intent); 
            }
        });
    }
 
    public void onDestroy()
    {
        super.onDestroy();
        // sqlliteCountryAssistant.close();
    }

	public void onClick(View v) {
		switch(v.getId()){
		case R.id.buttonOrder:
			// Intent i = new Intent(this, GetProducts02.class);  
			// startActivity(i);
			Toast.makeText(this, theClient , Toast.LENGTH_LONG).show() ;
			// Passing Values Between Activities
			// http://www.anddev.org/novice-tutorials-f8/passing-values-between-activities-t11351.html
			// http://stackoverflow.com/questions/2965109/passing-data-between-activities-in-android
			break;
		}
	}
	
	public boolean onCreateOptionsMenu(Menu menu){
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}	
}
