package com.indy.testing;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * The Class TestMain.
 */
public class TestMain extends Activity {

	private MyThread myThread;
	// text view influenced by the Thread
	private TextView threadModifiedText;

	/**
	 * Called when the activity is first created.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		threadModifiedText = (TextView) findViewById(R.id.text);

		myThread = new MyThread(mainHandler);
		myThread.start();
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onTouchEvent(android.view.MotionEvent)
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			// prepare a message with touch location data
			Message messageToThread = new Message();
			Bundle messageData = new Bundle();
			messageToThread.what = 0;
			messageData.putFloat("location_x", event.getX());
			messageData.putFloat("location_y", event.getY());

			messageToThread.setData(messageData);

			// sending message to MyThread
			myThread.getHandler().sendMessage(messageToThread);
		}

		return super.onTouchEvent(event);
	}

	/** The main handler. */
	public Handler mainHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 0) {
				threadModifiedText.setText(msg.getData().getString("text"));
			}
		};
	};
}
