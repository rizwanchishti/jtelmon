
/*
 *    Transaction support ; Batch updates ; 
 *    http://zetcode.com/db/postgresqljavatutorial/ 
 *    
 *    "DukesAgeResource" Tutorial : RESTful web service ~ GlassFish with NetBeans
 *    http://docs.oracle.com/javaee/6/firstcup/doc/gcrkm.html
 */

package net.learn2develop.PurchaseOrders;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Vector;
import net.learn2develop.R;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

public class SyncFromWebService extends ListActivity {
	
	private final static String SERVICE_URI = "http://sfa.pangram.ro:8090/PostgresWebService/rest";
	// private final static String SERVICE_URI = "http://ftp.pangram.ro:9090/SalesService/SalesService.svc";
	// private final static String SERVICE_URI = "http://192.168.61.3/SalesService/SalesService.svc";
	// private final static String SERVICE_URI = "http://192.168.101.222/SalesService/SalesService.svc";		
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.listview);

        final ProgressDialog progressDialog = ProgressDialog.show(this, "", "Sincronizeaza date...");
        new Thread() {
        public void run() {
        	DataManipulator dm = null;    
        HttpGet request = new HttpGet(SERVICE_URI + "/sales/allproducts"  );       
        request.setHeader("Accept", "application/json");
        request.setHeader("Content-type", "application/json");
 
        final DefaultHttpClient httpClient = new DefaultHttpClient();

        String theString = new String("");
        
        HttpGet request1 = new HttpGet(SERVICE_URI +   "/sales/3165/routes");       
        request.setHeader("Accept", "application/json");
        request.setHeader("Content-type", "application/json");
 
        DefaultHttpClient httpClient1 = new DefaultHttpClient();

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
        	
        	JSONObject json=new JSONObject(theString);
        	Log.i("_GetPerson_","<jsonobject>\n"+json.toString()+"\n</jsonobject>");
        	
        	dm = new DataManipulator(getApplicationContext());
        	
        	JSONArray nameArray;
        	// nameArray=json.getJSONArray("getProductsResult");
        	nameArray=json.getJSONArray("product");
        	 for(int i=0;i<nameArray.length();i++)
             {
             	tempStringID     = nameArray.getJSONObject(i).getString("classID");
             	tempStringName   = nameArray.getJSONObject(i).getString("productName");
             	tempStringPrice  = nameArray.getJSONObject(i).getString("price");
             	tempStringSymbol = nameArray.getJSONObject(i).getString("symbolName");

             	dm.insertIntoProducts(tempStringID,tempStringName,tempStringPrice,tempStringSymbol);

             	tempString=nameArray.getJSONObject(i).getString("productName")+"\n"+
                 		nameArray.getJSONObject(i).getString("price")+"\n"+nameArray.getJSONObject(i).getString("symbolName");
                 vectorOfStrings.add(new String(tempString));
             }
        	 int orderCount = vectorOfStrings.size();
             String[] orderTimeStamps = new String[orderCount];
             vectorOfStrings.copyInto(orderTimeStamps); 
        } catch (Exception e) {
			e.printStackTrace();
		}  
       
        	try {
        		HttpResponse response1 = httpClient1.execute(request1);
            	HttpEntity response1Entity = response1.getEntity();
            	InputStream stream1 = response1Entity.getContent();
            	BufferedReader reader1 = new BufferedReader(
    					new InputStreamReader(stream1));
            	
            	Vector<String> vectorOfStrings = new Vector<String>();
            	String tempString1       = new String();
            	String tempStringAgent     = new String();
            	String tempStringClient   = new String();
            	String tempStringRoute  = new String();
            	String tempStringZone = new String();
            	
            	StringBuilder builder1 = new StringBuilder();
            	String line1;
            	while ((line1 = reader1.readLine()) != null) {
    				builder1.append(line1);
    			}
            	
            	stream1.close();
            	theString = builder1.toString();
            	
            	JSONObject json1=new JSONObject(theString);
            	Log.i("_GetPerson_","<jsonobject>\n"+json1.toString()+"\n</jsonobject>");
            	
            	dm = new DataManipulator(getApplicationContext());
            	
            	JSONArray nameArray1;
            	nameArray1=json1.getJSONArray("clientRoute");
            	// nameArray1=json1.getJSONArray("GetRoutesByAgentResult");
            	
            	 for(int i=0;i<nameArray1.length();i++)
                 {
                 	tempStringAgent     = nameArray1.getJSONObject(i).getString("agent");
                 	tempStringClient   = nameArray1.getJSONObject(i).getString("client");
                 	tempStringRoute  = nameArray1.getJSONObject(i).getString("route");
                 	tempStringZone = nameArray1.getJSONObject(i).getString("zone");
                 		
         			
         			dm.insertIntoClients(tempStringAgent,tempStringClient,tempStringRoute,tempStringZone);
         			
                    tempString1=nameArray1.getJSONObject(i).getString("client")+"\n"+
                     		nameArray1.getJSONObject(i).getString("route")+"\n"+nameArray1.getJSONObject(i).getString("zone");
                    vectorOfStrings.add(new String(tempString1));
                 }
            int orderCount1 = vectorOfStrings.size();
            String[] orderTimeStamps1 = new String[orderCount1];
            vectorOfStrings.copyInto(orderTimeStamps1); 
        } catch (Exception a) {
        	a.printStackTrace();
        }
    		if (dm != null) {
    			dm.close();
    		}
    		progressDialog.dismiss();
    }
    }.start();
    }
    
    // @Override
	protected void onDestroy() {
		super.onDestroy();
		//if (dm != null) {
		//	dm.close();
		//}
	}
    
	
	
}

