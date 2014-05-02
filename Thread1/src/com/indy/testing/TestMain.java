package com.indy.testing;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class TestMain extends Activity implements Runnable{
	
	// text view influenced by the Thread
	private TextView threadModifiedText;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        threadModifiedText = (TextView) findViewById(R.id.thread_modified_text);
        
        // initializing and starting a new local Thread object 
        Thread currentThread = new Thread(this);
        currentThread.start();
    }

    //Method you must override to control what the Thread is doing
	@Override
	public void run() {
		try {
			// all the stuff we want our Thread to do goes here
			Thread.sleep(3000);
			// signaling things to the outside world goes like this  
			threadHandler.sendEmptyMessage(0);
		} catch (InterruptedException e) {
			//don't forget to deal with the Exception !!!!!
		}
	}
	
	// Receives Thread's messages, interprets them and acts on the 
	// current Activity as needed
	private Handler threadHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			// whenever the Thread notifies this handler we have 
			// only this behavior
			threadModifiedText.setText("my text changed by the thread");
		}
	};

}