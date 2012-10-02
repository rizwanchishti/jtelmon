package net.learn2develop.PurchaseOrders;
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
		// TODO Auto-generated method stub
		if(v.getId() == R.id.Button01add){
            Intent intent1 = new Intent(this,AdaugaAgent.class);
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

