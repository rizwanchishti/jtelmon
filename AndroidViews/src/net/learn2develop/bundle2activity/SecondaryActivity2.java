package net.learn2develop.bundle2activity;

import net.learn2develop.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SecondaryActivity2 extends Activity{
    private EditText mEditText2;
    private Button mButton2;
    private String mIntentString;
    
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.bundlesecondary);
        
        mEditText2 = (EditText)findViewById(R.id.txtSecondary);
        mButton2 = (Button)findViewById(R.id.btnSecondary);
        //add the event handler for the button...
        mButton2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                mIntentString = mEditText2.getText().toString();
                //create a new intent...
                Intent intent = new Intent();
                //add "returnKey" as a key and assign it the value
                //in the textbox...
                intent.putExtra("returnKey",mEditText2.getText().toString());
                //get ready to send the result back to the caller (MainActivity)
                //and put our intent into it (RESULT_OK will tell the caller that 
                //we have successfully accomplished our task..
                setResult(RESULT_OK,intent);
                //close this Activity...
                finish();
            }
        });
        
        //if the activity is being resumed...
        mIntentString = savedInstanceState != null ? savedInstanceState.getString("myKey"):null;
        
        //check to see if a Bundle is .
        if(mIntentString == null){
            //get the Bundle out of the Intent...
            Bundle extras = getIntent().getExtras();
            //check to see if "myKey" is in the bundle, if so then assign it's value
            // to mIntentString  if not, assign "nothing passed in" to mIntentString...
            mIntentString = extras != null ? extras.getString("myKey") : "nothing passed in";
        }
        //set the textbox to display mIntentString...
        mEditText2.setText(mIntentString);
    }

}
