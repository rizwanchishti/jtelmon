
// http://saigeethamn.blogspot.ro/2010/04/threads-and-handlers-android-developer.html

package com.sai.thread;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class HandlerThread extends Activity{
   
	private Button start;
	private ProgressDialog progressDialog;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        start = (Button) findViewById(R.id.Button01);
        start.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				fetchData();
			}
        	
        });
    }


	
	protected void fetchData() {
		// TODO Auto-generated method stub
		progressDialog = ProgressDialog.show(this, "", "Doing...");
		new Thread() {
			public void run() {
				try {

					Thread.sleep(800);

					} catch (InterruptedException e) {

					}
					messageHandler.sendEmptyMessage(0);

			}
		}.start();
	}



	private Handler messageHandler = new Handler() {
		
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			progressDialog.dismiss();
		}
	};
}