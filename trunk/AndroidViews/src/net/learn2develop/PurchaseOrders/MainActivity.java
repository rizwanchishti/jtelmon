package net.learn2develop.PurchaseOrders;
// https://code.google.com/p/javajdbc/source/browse/trunk/AsecondActivity/src/eu/itcsolutions/android/tutorial/MainActivity.java
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
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity implements OnClickListener {
	ProgressDialog progressDialog;
	private DataManipulator dm;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
         String[] setupInfo =  null;
         // setupInfo[2]= new String("NOBODY");
         dm = new DataManipulator(getApplicationContext());
		 setupInfo = dm.selectFirstRecordFromSetupTable();
		 if (dm != null) {
			 dm.close();
		 }
		 
		setContentView(R.layout.mainpurchase);

		TextView t1;
		t1 = (TextView)findViewById(R.id.textView2agentName);
		if(t1 != null) {
			// http://android-er.blogspot.hk/2013/07/add-shadow-for-textview-using-java-code.html
			t1.setShadowLayer(30, 10, 10, 0xFF303030);
			if(setupInfo != null) {t1.setText(setupInfo[2]);	}
        }
		
		View v = findViewById(R.id.Button01SelectieRuta);
        v.setOnClickListener(this);
        View s = findViewById(R.id.Button02SelectieClient);
        s.setOnClickListener(this);
        View l = findViewById(R.id.Button03CautaProdus);
        l.setOnClickListener(this);
        View e = findViewById(R.id.Button04ComenziNetrimise);
        e.setOnClickListener(this);
        View x = findViewById(R.id.Button05VizualizareClasificari);
        x.setOnClickListener(this);
    }
   
    // @Override
        public void onClick(View arg0) {
        	if(arg0.getId() == R.id.Button02SelectieClient){
        		// Intent intent = new Intent(this,SelectieClient.class);
        		Intent intent = new Intent(this,SelectEuroClient.class);
                this.startActivity(intent);
        	}
        	if(arg0.getId() == R.id.Button03CautaProdus){
        		// Intent intent = new Intent(this,CautaProdus.class);
        		Intent intent = new Intent(this,SelectEuroProduct.class);
        		this.startActivity(intent);
        	}
        	if(arg0.getId() == R.id.Button04ComenziNetrimise){
        		Intent intent = new Intent(this,UnsentOrders.class);
        		this.startActivity(intent);
        	}
        	if(arg0.getId() == R.id.Button05VizualizareClasificari){
        		Intent intent = new Intent(this,SelectEuroGroup.class);
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
			
			 // progressDialog = ProgressDialog.show(this, "", "Sincronizeaza date...");
			 new Thread() {
				 public void run() {
					 try{
						 // sleep(5000);
						 
						 Intent r = new Intent(getApplicationContext(),SyncFromWebService.class);
						 // Intent r = new Intent(getApplicationContext(),Sincronizare.class);
						 startActivity(r);
					 } catch (Exception e) {
						 Log.e("log", e.getMessage());
					}
					// progressDialog.dismiss();
				 }
			 }.start();
			// Intent r = new Intent(this,Sincronizare.class);
			// startActivity(r);
			
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
						 // sleep(5000);
						 DataManipulator dm;
						 dm = new DataManipulator(getApplicationContext());
						 dm.deleteAllProducts();
						 dm.deleteAllClients();
						 if (dm != null) {
							 dm.close();
				    	 }

					 }catch (Exception e) {
						 Log.e("log", e.getMessage());
					 }
					 progressDialog.dismiss();
				 }
			}.start();
			//this.dm = new DataManipulator(this);
			//this.dm.deleteAllProducts ();
			//this.dm.deleteAllClients();
			//if (this.dm != null) {
    		//	this.dm.close();
    		//}
			Toast.makeText(this, " OK ! " + "\n", Toast.LENGTH_LONG).show() ;
			break;
			
		case R.id.Reinitializeazadateagent:
			Intent y = new Intent(this,DateAgent.class);
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

