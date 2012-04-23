
package net.learn2develop.PurchaseOrders;

import net.learn2develop.R;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;


public class MainActivity extends Activity implements OnClickListener {
	ProgressDialog progressDialog;
	private Data dm;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpurchase);
        View v = findViewById(R.id.Button01);
         v.setOnClickListener(this);
         View s = findViewById(R.id.Button02);
         s.setOnClickListener(this);
         View l = findViewById(R.id.Button03);
         l.setOnClickListener(this);
         View e = findViewById(R.id.Button04);
         e.setOnClickListener(this);
         View x = findViewById(R.id.Button05);
         x.setOnClickListener(this);
    }
   
    // @Override
        public void onClick(View arg0) {
        	if(arg0.getId() == R.id.Button02){
        		Intent intent = new Intent(this,SelectieClient.class);
                this.startActivity(intent);
        	}
        	if(arg0.getId() == R.id.Button03){
        		Intent intent = new Intent(this,CautaProdus.class);
        		this.startActivity(intent);
        	}
        	if(arg0.getId() == R.id.Button04){
        		Intent intent = new Intent(this,UnsentOrders.class);
        		this.startActivity(intent);
        	}
        }
        
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
    	super.onCreateOptionsMenu(menu);
    	MenuInflater blowUp = getMenuInflater();
    	blowUp.inflate(R.menu.first_menu, menu);
    	return true;
}
    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()){
		case R.id.Monetar:
				Intent i = new Intent("");
				startActivity(i);
			break;
		
      case R.id.Kilometraj:
    	  		finish();
			break;
			
		case R.id.Observatie:
				Intent p = new Intent("");
				startActivity(p);
			break;
			
		case R.id.Sincronizeazadate:
			
			progressDialog = ProgressDialog.show(this, "", "Sincronizeaza date...");
			 new Thread() {
				 public void run() {
					 try{
						 sleep(5000);
					 } catch (Exception e) {
						 Log.e("log", e.getMessage());
					}
					progressDialog.dismiss();
				 }
			 }.start();
			Intent r = new Intent(this,Sincronizare.class);
			startActivity(r);
			
			break;
			
			
		case R.id.Actualizeazasolduri:
			Intent s = new Intent("");
			startActivity(s);
			
			break;
			
		case R.id.Reinitializeazabazadedate:
			progressDialog = ProgressDialog.show(this, "", "Reinitializare baza de date...");
			new Thread() {
				 public void run() {
					 try{
						 sleep(5000);
					 }catch (Exception e) {
						 Log.e("log", e.getMessage());
					 }
					 progressDialog.dismiss();
				 }
			}.start();
			
			this.dm = new Data(this);
			this.dm.deleteAllProducts ();
			this.dm.deleteAllClients();
			if (this.dm != null) {
    			this.dm.close();
    		}
			
			//Intent t = new Intent("");
			//startActivity(t);
			
			break;
			
		case R.id.Reinitializeazadateagent:
			Intent y = new Intent("");
			startActivity(y);
			
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

