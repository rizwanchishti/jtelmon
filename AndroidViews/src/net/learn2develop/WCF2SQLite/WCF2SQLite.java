
/* 
 * http://www.edumobile.org/android/android-development/use-of-sqlite/
 * 
 */

package net.learn2develop.WCF2SQLite;

// import android.R;
import net.learn2develop.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class WCF2SQLite extends Activity implements OnClickListener {
	
	private DataManipulator dm;	
	// DataManipulator dm;
	
	/** Called when the activity is first created. */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wcf2sqlite);
		View button1Click = findViewById(R.id.button1);
		button1Click.setOnClickListener(this);
		View button2Click = findViewById(R.id.button2);
		button2Click.setOnClickListener(this);        
		View button3Click = findViewById(R.id.button3);
		button3Click.setOnClickListener(this);        
		View button4Click = findViewById(R.id.button4);
		button4Click.setOnClickListener(this);
		View button5Click = findViewById(R.id.button5);
		button5Click.setOnClickListener(this);        

	}

	// @Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){

		case R.id.button1:
			Intent i = new Intent(this, GetProducts02.class);  
			startActivity(i);
			break;
		case R.id.button2:
			Intent i1 = new Intent(this, ProductsCheck.class);  
			startActivity(i1);
			break;
		case R.id.button3:
        	this.dm = new DataManipulator(this);
        	this.dm.deleteAllProducts();
        	this.dm.deleteAllClients();
    		if (this.dm != null) {
    			this.dm.close();
    		}
			break;
		case R.id.button4:
			Intent i4 = new Intent(this, GetClients02.class);  
			startActivity(i4);
			break;
		case R.id.button5:
			Intent i5 = new Intent(this, ClientsCheck.class);  
			startActivity(i5);
			break;
		}
	}
	
	protected void onDestroy() {
		super.onDestroy();
		if (dm != null) {
			dm.close();
		}
	}

}

