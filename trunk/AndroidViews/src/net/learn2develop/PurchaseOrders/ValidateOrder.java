package net.learn2develop.PurchaseOrders;
 
import java.util.Calendar;

import net.learn2develop.R;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
 
public class ValidateOrder extends Activity {
    
    private EditText mDateDisplay;
    private Button mPickDate;
 
    private EditText mDateDisplay2;
    private Button mPickDate2;
 
    private int mYear;
    private int mMonth;
    private int mDay;
 
    private EditText mTimeDisplay;
    private Button mPickTime;
 
    private int mHour;
    private int mMinute;
 
    static final int DATE_DIALOG_ID = 0;
    static final int CALENDAR_VIEW_ID = 1;
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.save_order);
        
        mDateDisplay = (EditText) findViewById(R.id.dateDisplay);
        mPickDate = (Button) findViewById(R.id.pickDate);
        mPickDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });
        
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
 
        // display the current date
        displayDate();
      //  displayCalendarViewDate();
 
        // display the current time
      //  displayTime();
    }
 
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        switch(requestCode) {
        case CALENDAR_VIEW_ID:
            if (resultCode == RESULT_OK) {
 
                Bundle bundle = data.getExtras();
 
              //  mDateDisplay2 = (EditText) findViewById(R.id.dateDisplay2);
                mDateDisplay2.setText(bundle.getString("dateSelected"));
                break;
            }
        }
 
    } 
 
    // updates the date in the EditText
    private void displayDate() {
        mDateDisplay.setText(
                new StringBuilder()
                // Month is 0 based so add 1
                .append(mMonth + 1).append("/")
                .append(mDay).append("/")
                .append(mYear).append(" "));
    }
 
    // updates the date in the EditText
    private void displayCalendarViewDate() {
        mDateDisplay.setText(
                new StringBuilder()
                // Month is 0 based so add 1
                .append(mMonth + 1).append("/")
                .append(mDay).append("/")
                .append(mYear).append(" "));
    }
 
    // updates the time we display in the EditText
   
 
    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }
 
    // the callback received when the user "sets" the date in the dialog
    private DatePickerDialog.OnDateSetListener mDateSetListener =
        new DatePickerDialog.OnDateSetListener() {
 
        public void onDateSet(DatePicker view, int year,
                int monthOfYear, int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            displayDate();
        }
    };
 
    // the callback received when the user "sets" the time in the dialog
    
 
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
        case DATE_DIALOG_ID:
            return new DatePickerDialog(this,
                    mDateSetListener,
                    mYear, mMonth, mDay);
      //  case TIME_DIALOG_ID:
        //    return new TimePickerDialog(this,
      //              mTimeSetListener, mHour, mMinute, false);
 
        }
        return null;
    }  
 

 
    }

