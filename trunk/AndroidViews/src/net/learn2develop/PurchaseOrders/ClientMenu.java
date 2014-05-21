package net.learn2develop.PurchaseOrders;
 
import net.learn2develop.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
 
public class ClientMenu extends Activity implements OnClickListener{
	 Bundle BundledClient;
	
    @Override
  public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectieclient);
       View v = findViewById(R.id.button1);
        v.setOnClickListener(this);
        
        BundledClient = getIntent().getExtras();
        if(BundledClient != null) {
            String theText = BundledClient.getString("client");
            if(theText != null) {
                TextView t = (TextView)findViewById(R.id.textView1);
                if(t != null) {
                    t.setText(theText);
                }
                
            }
        }
    }
                
  
            
  
public void onClick(View arg0) {
      if(arg0.getId() == R.id.button1){
             Intent intentComandaNoua = new Intent(this,NewOrder.class);
            intentComandaNoua.putExtras(BundledClient);
		   //  startActivity(Intent);
             //start the SelectieRutaActivity
              this.startActivity(intentComandaNoua);
}
    
  }
}
            
        