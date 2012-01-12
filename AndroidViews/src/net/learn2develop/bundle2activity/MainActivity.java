
/*
 * 
 *    http://remwebdevelopment.com/dev/a33/Passing-Bundles-Around-Activities.html
 *    
 */

package net.learn2develop.bundle2activity;

import net.learn2develop.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {
    private final int SECONDARY_ACTIVITY_REQUEST_CODE=0;
    private EditText mEditText1;
    private Button mButton1;
    //DONT FORGET: to add SecondaryActivity to the manifest file!!!!!
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bundlemain);
        mEditText1 = (EditText)findViewById(R.id.editText1);
        mButton1 = (Button)findViewById(R.id.button1);
        mButton1.setOnClickListener(this);
    }
    public void onClick(View view){
        if(view == mButton1){
            //create a new intent and specify that it's target is SecondaryActivity...
            Intent intent = new Intent(getApplicationContext(),SecondaryActivity.class);
            //load the intent with a key "myKey" and assign it's value
            //to be whatever has been entered into the text field...
            intent.putExtra("myKey",mEditText1.getText().toString());
            //launch the secondary activity and send the intent along with it
            //note that a request code is passed in as well so that when the 
            //secondary activity returns control to this activity, 
            //we can identify the source of the request...
            startActivityForResult(intent, SECONDARY_ACTIVITY_REQUEST_CODE);
        }
    }
    //we need a handler for when the secondary activity finishes it's work
    //and returns control to this activity...
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode, resultCode, intent);
        Bundle extras = intent.getExtras();
        mEditText1.setText(extras != null ? extras.getString("returnKey"):"nothing returned");
    }
}
