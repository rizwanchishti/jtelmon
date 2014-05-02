// http://examples.javacodegeeks.com/android/core/ui/datepicker/android-date-picker-example
package com.javacodegeeks.android.datepickerexample;

import java.util.Calendar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
 
public class MainActivity extends Activity {
 
	private TextView text_date;
	private DatePicker date_picker;
	private Button button;
 
	private int year;
	private int month;
	private int day;
 
	static final int DATE_DIALOG_ID = 100;
 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
 
		setCurrentDate();
		addButtonListener();
 
	}
 
	// display current date both on the text view and the Date Picker
	public void setCurrentDate() {
 
		text_date = (TextView) findViewById(R.id.text_date);
		date_picker = (DatePicker) findViewById(R.id.date_picker);
 
		final Calendar calendar = Calendar.getInstance();
		
		year = calendar.get(Calendar.YEAR);
		month = calendar.get(Calendar.MONTH);
		day = calendar.get(Calendar.DAY_OF_MONTH);
 
		// set current date into textview
		text_date.setText(new StringBuilder()
			// Month is 0 based, so you have to add 1
			.append(month + 1).append("-")
			.append(day).append("-")
			.append(year).append(" "));
 
		// set current date into Date Picker
		date_picker.init(year, month, day, null);
 
	}
 
	public void addButtonListener() {
 
		button = (Button) findViewById(R.id.button);
		
		button.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View v) {
 
				showDialog(DATE_DIALOG_ID);
 
			}
 
		});
 
	}
	@Override
	protected Dialog onCreateDialog(int id) {
		
		switch (id) {
		case DATE_DIALOG_ID:
		   // set date picker as current date
		   return new DatePickerDialog(this, datePickerListener, year, month,day);
		}
		return null;
	}
 
	
	private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
		 
		// when dialog box is closed, below method will be called.
		public void onDateSet(DatePicker view, int selectedYear,int selectedMonth, int selectedDay) {
			year = selectedYear;
			month = selectedMonth;
			day = selectedDay;
 
			// set selected date into Text View
			text_date.setText(new StringBuilder().append(month + 1)
			   .append("-").append(day).append("-").append(year).append(" "));
 
			// set selected date into Date Picker
			date_picker.init(year, month, day, null);
 
		}
	};
 
}