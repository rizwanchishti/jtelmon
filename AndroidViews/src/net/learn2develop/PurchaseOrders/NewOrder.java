package net.learn2develop.PurchaseOrders;
 

// in constructorul comenzii ( NewOrder.java) trebuie sa apara implicit si un ...  
// ...  ID de comanda calculat cu ALGORITM ! 
// ID comanda sa fie o combinatie intre ID agent + iD client + dataAnLunaZi+oraMinutSecunda  

// orice comanda , cand ajunge in server sa primeasca si un server_id alocat de catre server 
// in care sa apara dataOraServerului ... 


import net.learn2develop.R;

import java.util.List;
import java.util.Vector;

import android.app.Activity;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.atomic.AtomicInteger;

public class NewOrder extends ListActivity  {   
	Bundle BundledClient;
	Bundle BundleOrder;
	String strClientName;

	String strTert_id;
	ComenziVextHeader orderHeader = null;
	ComenziCVextLine  orderLine = null;
	String[] setupInfo;

	// private EditText autocompleteClient;
	// private EditText numarbucati;
	// private EditText discount;
	List<String[]> names2 =null ;
	
	List < CommandLine > listOfCommandLines ;
	
	Vector<String> vectorOfStrings = new Vector<String>();
	DataManipulator dm;
	static final int DIALOG_ID = 0;
	
	private AtomicInteger counter= new AtomicInteger();
 
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        dm = new DataManipulator(this);
        orderHeader = new ComenziVextHeader();
        setupInfo = dm.selectFirstRecordFromSetupTable();
        orderHeader.setNrlcId(setupInfo[0]);
        setContentView(R.layout.clickclient);
        BundledClient = getIntent().getExtras();
        if(BundledClient != null) {
            strClientName = BundledClient.getString("client");
            strTert_id    = BundledClient.getString("tert_id");
            orderHeader.setTertId(strTert_id);
            orderHeader.setComId(this.getNextId());
            // headerComanda.setValoare(0);
            
            if(strClientName != null) {
                TextView t = (TextView)findViewById(R.id.textView1);
                if(t != null) {
                    t.setText(strClientName);
                }
            }
        }
    }
 
    public String getNextId() {
        counter.getAndIncrement();
    	return counter.toString();
    }
    
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        	super.onCreateOptionsMenu(menu);
        	 super.onCreateOptionsMenu(menu);
        	MenuInflater blowUp = getMenuInflater();
        	blowUp.inflate(R.menu.cool_menu, menu);
        	return true;
    }
        
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    		// TODO Auto-generated method stub
    		switch(item.getItemId()){
    		case R.id.AdaugaProdus:
    			Intent i = new Intent(this,AddProduct.class);
    			//startActivity(i);
    			startActivityForResult(i,0 );
    			break;
    			
    		case R.id.ValidareComanda:
    			//Intent o = new Intent("eu.itcsolutions.android.tutorial");
    			
    			// ToDo : dm.insereaza_HEADER_comanda(str_id_comanda);
    			// dm.insereazaLiniileComenzii(strClientName);

    			// ToDo : dm.insertLinesInto_ComenziCVext(str_id_comanda, listOfCommandLines);
    			dm.insereazaLiniileComenzii2(listOfCommandLines);
    			
    			//startActivity(i);
    			//startActivityForResult(o,0 );
    			break;
  		
          case R.id.Termenplatadiscount:
        	  	Intent j = new Intent("");
        	  	startActivity(j);
    			break;
    			
    		case R.id.StergeProdus:
    			Intent p = new Intent("eu.itcsolutions.android.tutorial.PREFS");
    			startActivity(p);
    			break;
    		
    		}
    		return false;
    	}
            
    protected void onActivityResult (int requestCode, int resultCode, Intent intent){
	        super.onActivityResult(requestCode, resultCode, intent);
	        Bundle extras = null;
	        try{
	        	extras = intent.getExtras();
	        }
	        catch (Exception e) {
	        	Log.d("NewOrder.onActivityResult", " No Extra Bundle ! " );
        	}  
	        if ( extras != null){
	        	orderLine = new ComenziCVextLine();
	        	String sDenumireProdus =extras.getString("produs");
	        	String sCantitate =extras.getString("bucati");
	        	String sDiscount =extras.getString("discount");
	        	String s4 = new String(" ");

	        	String sidStoc =extras.getString("stoc_id");
	        	String sPretGross =extras.getString("pret_gross");
	        	// orderLine = new ComenziCVextLine();
	        	// orderLine.setCantitate(sCantitate);
	        	// orderLine.setStocId(sidStoc);
	        	// orderLine.setPretGross(sPretGross);
	        	
	        	// dm.adaugaLiniePeComanda(sDenumireProdus, sCantitate, sDiscount, s4);
	        	// dm.adaugaLiniePeComanda2(sDenumireProdus, sCantitate, sDiscount, sidStoc, sPretGross);
	        	
	        	CommandLine newLine = new CommandLine(sDenumireProdus, sCantitate, sDiscount, sidStoc, sPretGross);
	        	listOfCommandLines.add(newLine ) ;
	        	
	        	String s5 = sDenumireProdus+" - "+sCantitate+ " bucati - "+sDiscount+" % discount ";
	        	vectorOfStrings.add(s5);
	        }	
	        showDialog(DIALOG_ID);
	        int orderCount = vectorOfStrings.size();
	        String[] arrayOfStrings = new String[orderCount];
	        vectorOfStrings.copyInto(arrayOfStrings); 
	        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1 , arrayOfStrings));
	        // mEditText1.setText(extras != null ? extras.getString("returnKey"):"nothing returned");
	    }

   
    	
  

protected final Dialog OnCreateDialog(final int id) {
	Dialog dialog = null ;
	switch(id) {
	case DIALOG_ID:
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Produs adaugat cu succes ! Adauga un nou produs ?")
		.setCancelable(false)
		.setPositiveButton("Renunta", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				NewOrder.this.finish();
			}
		})
		.setNegativeButton("Adauga", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
				
			}
		});
		AlertDialog alert = builder.create();
		dialog = alert;
		break;
		default:
	}
	return dialog;
	
}
}
