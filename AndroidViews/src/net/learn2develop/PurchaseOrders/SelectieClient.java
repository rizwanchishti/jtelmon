

package net.learn2develop.PurchaseOrders;

import net.learn2develop.R;
import java.util.ArrayList;
import java.util.List;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class SelectieClient extends ListActivity  {     
	TextView selection;
	public int idToModify; 
	DataManipulator dm;

	List<String[]> list = new ArrayList<String[]>();
	List<String[]> names2 =null ;
	String[] StringOfClients;
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.check);
		  dm = new DataManipulator(this);
	      // names2 = dm.selectAll();
		  names2 = dm.selectAllClients();
		  // dm.db.close();
		StringOfClients=new String[names2.size()]; 

		int x=0;
		String stg;

		for (String[] name : names2) {
			// stg = name[0]+" - "+name[1]+ " - "+name[2]+" - "+name[3]; 
			// stg = name[1]+" - "+name[2]+ " - "+name[3];
			stg = name[1];
			StringOfClients[x]=stg;
			x++;
		}


		ArrayAdapter<String> adapter = new ArrayAdapter<String>(   
				this,android.R.layout.simple_list_item_1,   
				StringOfClients);
        this.setListAdapter(adapter);
		selection=(TextView)findViewById(R.id.selection);

	}      
	

	protected void onListItemClick(ListView l, View v, int position, long id) {
		 
		 super.onListItemClick(l, v, position, id);
		 
		// try{
			Bundle bundledClient = new Bundle();
			bundledClient.putString("client", StringOfClients[position]);
		    // Class ourClass =Class.forName("eu.itcsolutions.android.tutorial.ClientMenu" );
			// Intent ourIntent =new Intent(SelectieClient.this, ourClass);
			Intent ourIntent =new Intent(SelectieClient.this, ClientMenu.class);
			 ourIntent.putExtras(bundledClient);
		     startActivity(ourIntent);
				 
	 //}catch (ClassNotFoundException e){
		// e.printStackTrace();
	 // }
	 }
	 
}
	 