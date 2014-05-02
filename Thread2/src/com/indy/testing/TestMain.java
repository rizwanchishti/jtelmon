package com.indy.testing;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.widget.TextView;

public class TestMain extends Activity {
	
	// text view influenced by the Thread
	private TextView threadModifiedText;
	private int threadModifiedInt = 4;
	private Point threadModifiedPoint = new Point(20,10);
	
	//boolean that controls if the Thread will run or not
	private boolean activeThread = true;	
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        threadModifiedText = (TextView) findViewById(R.id.thread_modified_text);
        
        // the local Thread used for count-down
        Thread myTread = new Thread() {        	
	        @Override
	        public void run() {
	            try {
	            	// test modifying objects
	            	threadModifiedInt = 20;
	            	threadModifiedPoint.set(30, 40);
	            	
	            	int timeCounter = 100;
	                //main loop. the thread just checks at each 100ms passed if it should 
	        		//still be running and then waits. the time Counter decrements by 1 each loop
	        		while(activeThread && (timeCounter > 0)) {
	                	sleep(100);
	                	// once per second notifies the Handler to update the displayed count-down     
	                	if(timeCounter % 10 == 0){
		        			threadHandler.sendEmptyMessage((int)timeCounter/10);
	                	}
	        			timeCounter--;
	                }
	            } catch(InterruptedException e) {
	                // don't forget to deal with exceptions....
	            } finally {
	            	//this forces the activity to end (also the application in our case)
	                finish();
	            }
	        }
	    };
	    
	    //starting thread
	    myTread.start();
    }

    
    // manages user touching the screen
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			// we set the activeThread boolean to false, forcing the Thread to stop
	        activeThread = false;
	    }
		return super.onTouchEvent(event);
	}

    
	// Receives Thread's messages, interprets them and acts on the 
	// current Activity as needed
	private Handler threadHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			// whenever the Thread notifies this handler we have 
			// only this behavior			
			threadModifiedText.setText("test int is " + threadModifiedInt + 
					"\ntest Point is " + threadModifiedPoint.toString() +
					"\ncounter is " + Integer.toString(msg.what));
		}
	};

}
