package net.learn2develop.PurchaseOrders;

import java.util.HashMap;

import net.learn2develop.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Setari_ip extends Activity implements OnClickListener   {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setari_ip);
        
        View v = findViewById(R.id.buttonGoBack);
        v.setOnClickListener(this);
        
    }
        public void onClick(View arg0) {
    		// TODO Auto-generated method stub
    		
        	if(arg0.getId() == R.id.buttonGoBack){
        		
        		Intent intent = new Intent(this,MainActivity.class);
                this.startActivity(intent);
        	}
    	}

      
     
        }
        