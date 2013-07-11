package net.learn2develop.PurchaseOrders;

// https://code.google.com/p/javajdbc/source/browse/trunk/AsecondActivity/src/eu/itcsolutions/android/tutorial/DateAgent.java


import net.learn2develop.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class DateAgent extends Activity implements OnClickListener {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dateagent);
        View v = findViewById(R.id.Button01reinitializeaza);
        v.setOnClickListener(this);
        View s = findViewById(R.id.Button01home);
        s.setOnClickListener(this);
 
    }

	// @Override
	public void onClick(View v) {
		// reinitializeaza-date-agent
		if(v.getId() == R.id.Button01reinitializeaza){
			TextView txtview_iDagNou, txtview_agPass;
			txtview_iDagNou=(TextView)findViewById(R.id.autoCompleteTextView2iDagentNou);
			txtview_agPass=(TextView)findViewById(R.id.autoCompleteTextView3agpass);
			
			Bundle bundledAgent = new Bundle();
			bundledAgent.putString("agent" , txtview_iDagNou.getText().toString());
			bundledAgent.putString("parola", txtview_agPass.getText().toString());
			Intent intent1 = new Intent(this,AgentSetup.class);
			intent1.putExtras(bundledAgent);
            this.startActivity(intent1);
    }
		
		if(v.getId() == R.id.Button01home){
            Intent intent1 = new Intent(this,MainActivity.class);
            //start the SelectieRutaActivity
            this.startActivity(intent1);
    }
	}
}

