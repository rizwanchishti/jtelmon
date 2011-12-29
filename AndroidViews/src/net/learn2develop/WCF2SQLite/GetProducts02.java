/*
 * http://www.vogella.de/articles/AndroidSQLite/article.html
 * http://www.youtube.com/watch?v=_7gxQq467Nk
 * 
 */

package net.learn2develop.WCF2SQLite;

import android.app.Activity;
import android.os.Bundle;
import android.app.ListActivity; 
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Vector;
import android.app.Activity;
import android.os.Bundle;
import android.util.Xml; 
import android.widget.ArrayAdapter;
import android.widget.Toast;
import org.apache.http.HttpResponse;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpGet;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import java.io.InputStream;
import org.json.JSONArray;
import java.io.BufferedReader;
import android.util.Log;

import net.learn2develop.R;

public class GetProducts02 extends ListActivity {
	
	private final static String SERVICE_URI = "http://192.168.61.3/SalesService/SalesService.svc";
	private DataManipulator dm;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.listview);
        
        HttpGet request = new HttpGet(SERVICE_URI + "/json/getproducts");       
        request.setHeader("Accept", "application/json");
        request.setHeader("Content-type", "application/json");
 
        DefaultHttpClient httpClient = new DefaultHttpClient();

        String theString = new String("");
        
        try {
        	HttpResponse response = httpClient.execute(request);
        	HttpEntity responseEntity = response.getEntity();
        	InputStream stream = responseEntity.getContent();
        	BufferedReader reader = new BufferedReader(
					new InputStreamReader(stream));

        	Vector<String> vectorOfStrings = new Vector<String>();
        	String tempString       = new String();
        	String tempStringID     = new String();
        	String tempStringName   = new String();
        	String tempStringPrice  = new String();
        	String tempStringSymbol = new String();
        	
        	StringBuilder builder = new StringBuilder();
        	String line;
        	while ((line = reader.readLine()) != null) {
				builder.append(line);
			}
        	stream.close();
        	theString = builder.toString();
        	
        	// www.jondev.net/articles/Android_JSON_Parser_Example
        	JSONObject json=new JSONObject(theString);
        	Log.i("_GetPerson_","<jsonobject>\n"+json.toString()+"\n</jsonobject>");
        	
        	this.dm = new DataManipulator(this);
        	// this.dm.deleteAllProducts();
        	// List<String[]> names2 =null ;
        	
        	JSONArray nameArray=json.getJSONArray("getProductsResult");
            for(int i=0;i<nameArray.length();i++)
            {
            	tempStringID     = nameArray.getJSONObject(i).getString("ID");
            	tempStringName   = nameArray.getJSONObject(i).getString("Name");
            	tempStringPrice  = nameArray.getJSONObject(i).getString("Price");
            	tempStringSymbol = nameArray.getJSONObject(i).getString("Symbol");
            		
                // Log.i("_GetProducts_","<ID"+i+">"      + tempStringID      +"</ID"    +i+">\n");
                // Log.i("_GetProducts_","<Name"+i+">"    + tempStringName    +"</Name"    +i+">\n");
                // Log.i("_GetProducts_","<Price"+i+">"   + tempStringPrice   +"</Price"   +i+">\n");
                // Log.i("_GetProducts_","<Symbol"+i+">"  + tempStringSymbol  +"</Symbol"+i+">\n");
    			
    			this.dm.insertIntoProducts(tempStringID,tempStringName,tempStringPrice,tempStringSymbol);
    			
                tempString=nameArray.getJSONObject(i).getString("Name")+"\n"+
                		nameArray.getJSONObject(i).getString("Price")+"\n"+nameArray.getJSONObject(i).getString("Symbol");
                vectorOfStrings.add(new String(tempString));
            }

            // names2 = this.dm.selectAllProducts();
    		// int ns = names2.size();

    		//String stg;
    		// Log.i("_DataManipulator_Products_", "FROM SQLite : " + Integer.toString(ns) +"\n");
    		//for (String[] name : names2) {
    		//	stg = name[0]+" - "+name[1]+ " - "+name[2]+" - "+name[3];
    		//	Log.i("_DM_Products_", stg + ">\n");	
    		//}
    		
    		// this.dm.db.close();
    		
            int orderCount = vectorOfStrings.size();
            String[] orderTimeStamps = new String[orderCount];
            vectorOfStrings.copyInto(orderTimeStamps); 
            setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1 , orderTimeStamps));
            
		} catch (Exception e) {
			e.printStackTrace();
		}        

    }
    
    // @Override
	protected void onDestroy() {
		super.onDestroy();
		if (dm != null) {
			dm.close();
		}
	}
    
}


