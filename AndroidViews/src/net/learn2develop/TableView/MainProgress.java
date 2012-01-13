package net.learn2develop.TableView;

import net.learn2develop.R;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/*
 *
 * Class for showing how to work with progress dialog
 *  http://thedevelopersinfo.wordpress.com/2009/10/16/showing-progressdialog-in-android-activity/
 *  http://developer.android.com/guide/topics/ui/dialogs.html
 *  
 */

public class MainProgress extends Activity implements OnClickListener {

	private ProgressDialog progressDialog;

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

	public void onClick(View v) {
	//start the progress dialog
	progressDialog = ProgressDialog.show(MainProgress.this, "", "Loading...");
	new Thread() {
		public void run() {
			try{
				sleep(10000);
			} catch (Exception e) {
				Log.e("tag", e.getMessage());
			}
			// dismiss the progress dialog
			progressDialog.dismiss();
		}
	}.start();
	}
}
