package com.javacodegeeks.androidbundleexample;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class AndroidBundleExample extends Activity {
	public EditText name, phone_no, age;
	public CheckBox married;

	public String fullname, phoneNumber, ageBirth;
	public boolean isMarried = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// initialize edit texts
		initializeEditTexts();

		Button send = (Button) findViewById(R.id.send);
		send.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {

				// Getting data from the form
				getDataFromForm();

				if (name.length() == 0 || phone_no.length() == 0 || age.length() == 0) {
					//show toast when not correctly completed
					Toast.makeText(getApplicationContext(), "Complete the form correctly", Toast.LENGTH_SHORT).show();
				} else {
					// Converting phoneNumber to long type
					long phone = Long.parseLong(phoneNumber);
					// Converting ageBirth to double type
					double ageDouble = Long.parseLong(ageBirth);
					
					// Creating Bundle object
					Bundle b = new Bundle();
					
					// Storing data into bundle
					b.putString("fullname", fullname);
					b.putLong("phoneNumber", phone);
					b.putDouble("age", ageDouble);
					b.putBoolean("married", isMarried);

					// Creating Intent object
					Intent in = new Intent(AndroidBundleExample.this, AndroidSecondActivity.class);

					// Storing bundle object into intent
					in.putExtras(b);
					startActivity(in);
				}
			}
		});
	}

	public void initializeEditTexts() {
		name = (EditText) findViewById(R.id.name);
		// name.setTextColor(Color.BLACK);
		phone_no = (EditText) findViewById(R.id.phone_no);
		phone_no.setTextColor(Color.BLACK);
		age = (EditText) findViewById(R.id.age);
		age.setTextColor(Color.BLACK);
		married = (CheckBox) findViewById(R.id.married);
		married.setTextColor(Color.BLACK);
	}

	public void getDataFromForm() {
		fullname = name.getText().toString();
		phoneNumber = phone_no.getText().toString();
		ageBirth = age.getText().toString();
		isMarried = married.isChecked();
	}

}