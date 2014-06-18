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
import android.widget.TextView;

public class Setari extends Activity implements OnClickListener  {

   EditText login_password; 
   Button login_signin;
   Button login_cancel;
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_setari);
        login_password =(EditText) findViewById(R.id.login_password);
        login_signin =(Button) findViewById(R.id.login_signin);
        login_cancel =(Button) findViewById(R.id.login_cancel);
        login_signin.setOnClickListener(this);
        login_cancel.setOnClickListener(this);
        
        
      
   }
    
    public void onClick(View v) {
    	String na=login_password.getText() .toString();
    	
    	switch (v.getId()) {
    	case R.id.login_signin:
    		
    		if(na.equals("123456")) {
    			Intent i=new Intent(this,Setari_ip.class);
    			startActivity(i);
    		}
    	
    break;
    
    	case R.id.login_cancel:
    		break;
    	}
    	
    	
    }

   
   }