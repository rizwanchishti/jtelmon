package net.learn2develop.PurchaseOrders;

import net.learn2develop.R;
import java.util.List;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
 
public class UnsentOrders extends ListActivity {
		TextView selection;
	    DataManipulator dm;
	    List<String[]> allOrders =null ;
	    String[] StringOfOrders;
	    protected void onCreate(Bundle savedInstanceState){
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.check);
	        dm = new DataManipulator(this);
	        allOrders = dm.selectAllOrders();
	        StringOfOrders=new String[allOrders.size()];  
	        int x=0;
	        String stg;
	        for (String[] orderName : allOrders) {
	            stg = orderName[0]+" - "+orderName[1]+ " - "+orderName[2]+ " - "+orderName[3];
	            StringOfOrders[x]=stg;
	            x++;
	        }
	        ArrayAdapter<String> adapter = new ArrayAdapter<String>(    
	                this,android.R.layout.simple_list_item_1,    
	                StringOfOrders);
	        this.setListAdapter(adapter);
	        selection=(TextView)findViewById(R.id.selection);
	    }       
	 
	    public void onListItemClick(ListView parent, View v, int position, long id) {
	        selection.setText(StringOfOrders[position]);
	    }
	
	    public boolean onCreateOptionsMenu(android.view.Menu menu) {
	    	super.onCreateOptionsMenu(menu);
	    	MenuInflater blowUp = getMenuInflater();
	    	blowUp.inflate(R.menu.second_menu, menu);
	    	return true;
	    }

	    public boolean onOptionsItemSelected(MenuItem item) {
	    	// TODO Auto-generated method stub
	    	switch(item.getItemId()){
	    		case R.id.Vizualizareinstrumenteplata:
	    			finish();
	    		break;
			
	    		case R.id.TransferapeServer:
	    			Intent p = new Intent(this,TransferToServer.class);
	    			startActivity(p);
	    		break;
	    	}
	    	return false;
	    }

	    protected void onDestroy() {
	    	super.onDestroy();
	    	if (dm != null) {
	    		dm.close();
	    	}
	    }
}
