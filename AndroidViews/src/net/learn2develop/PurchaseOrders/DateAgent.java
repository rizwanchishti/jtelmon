package net.learn2develop.PurchaseOrders;

// https://code.google.com/p/javajdbc/source/browse/trunk/AsecondActivity/src/eu/itcsolutions/android/tutorial/DateAgent.java


import net.learn2develop.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class DateAgent extends Activity implements OnClickListener {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dateagent);
        View v = findViewById(R.id.Button01add);
        v.setOnClickListener(this);
        View s = findViewById(R.id.Button01home);
        s.setOnClickListener(this);
 
    }

	// @Override
	public void onClick(View v) {
		// reinitializeaza-date-agent
		if(v.getId() == R.id.Button01add){
            //Intent intent1 = new Intent(this,AdaugaAgent.class);
			Intent intent1 = new Intent(this,AgentSetup.class);
            //start the SelectieRutaActivity
            this.startActivity(intent1);
    }
		
		if(v.getId() == R.id.Button01home){
            Intent intent1 = new Intent(this,MainActivity.class);
            //start the SelectieRutaActivity
            this.startActivity(intent1);
    }
	}
}

