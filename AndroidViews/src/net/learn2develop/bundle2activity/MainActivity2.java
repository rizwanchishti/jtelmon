
/*
 * 
 *    http://remwebdevelopment.com/dev/a33/Passing-Bundles-Around-Activities.html
 *    
 */

package net.learn2develop.bundle2activity;

import java.util.Vector;

import net.learn2develop.R;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Button;

public class MainActivity2 extends ListActivity implements View.OnClickListener {
    private final int SECONDARY_ACTIVITY_REQUEST_CODE=0;
    private EditText mEditText1;
    private Button mButton1;
    Vector<String> vectorOfStrings = new Vector<String>();
    
    //DONT FORGET: to add SecondaryActivity to the manifest file!!!!!
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bundlemain2);
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
        vectorOfStrings.add(new String(extras.getString("returnKey")));
        int orderCount = vectorOfStrings.size();
        String[] arrayOfStrings = new String[orderCount];
        vectorOfStrings.copyInto(arrayOfStrings); 
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1 , arrayOfStrings));
        // mEditText1.setText(extras != null ? extras.getString("returnKey"):"nothing returned");
    }
}
