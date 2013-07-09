package net.learn2develop.PurchaseOrders;

// http://code.google.com/p/jtelmon/source/browse/trunk/AndroidViews/src/net/learn2develop/PurchaseOrders/AdaugaAgent.java
// http://code.google.com/p/jtelmon/source/browse/trunk/AndroidViews/src/net/learn2develop/PurchaseOrders/AgentSetup.java
// http://code.google.com/p/javajdbc/source/browse/trunk/AsecondActivity/src/eu/itcsolutions/android/tutorial/AdaugaAgent.java
// AdaugaAgent ramane clasa care seteaza noul agent in SQLS
// AgentSetup  este clasa care va seta noul agent in Postgres

import net.learn2develop.R;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Vector;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;

import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AgentSetup extends Activity {

	// REINITIALIZEAZA_DATE_AGENT
	
/* http://sfa.pangram.ro:8090/PostgresWebService/rest/sales/search/1/123456
	<userPass>
		<id> 1 </id>
		<password> 123456 </password>
		<userName> ARICIU DANIEL </userName>
		<zone> RESITA </zone>
	</userPass>
*/
	    private final static String SERVICE_URI = "http://sfa.pangram.ro:8090/PostgresWebService/rest";
        // private final static String SERVICE_URI = "http://192.168.61.3/SalesService/SalesService.svc";

	    DataManipulator dm = null;
	    Bundle BundledAgent;

        @Override
            public void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);

                setContentView(R.layout.main_agent_reinitializare);
               
                String isNot =new String("");
                
                BundledAgent = getIntent().getExtras();
                if(BundledAgent != null) {
                   String theAgent = BundledAgent.getString("agent");
                   String thePass = BundledAgent.getString("parola");
                   Log.i("BundledAgent " ,theAgent+" \n");
                   TextView t1 = (TextView)findViewById(R.id.agentid);
                   TextView t2 = (TextView)findViewById(R.id.agpass);
                   if(t1 != null) {
                       t1.setText(theAgent);
                   }
                   if(t2 != null) {
                       t2.setText(thePass);
                   }
                }
                
                // HttpGet request = new HttpGet(SERVICE_URI + "/json/userpasscheck ");       
                HttpGet request = new HttpGet(SERVICE_URI + "/sales/search/1");
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
                String tempString = new String();
                String tempStringID         = new String();
                String tempStringPassword   = new String();
                
                
                StringBuilder builder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                                builder.append(line);
                        }
                stream.close();
                theString = builder.toString();
                
                JSONObject json=new JSONObject(theString);
                tempStringID = json.getString("id");
                tempStringPassword = json.getString("password");
                
                Log.i("userpasscheck","<jsonobject>\n"+json.toString()+"\n</jsonobject>");
                Log.i("userPass","<UtilizatorID"+">"      +json.getString("id")    +"</UtilizatorID"    +">\n");
                Log.i("userPass","<Parola"+">"            +json.getString("password")          +"</Parola"          +">\n");
                
                dm = new DataManipulator(getApplicationContext());
                dm.insertIntoSetup(tempStringID,tempStringPassword);
                dm.close();
                
                //JSONArray nameArray=json.getJSONArray("userPass");
                //for(int i=0;i<nameArray.length();i++)
                //{
                	//tempStringID       = nameArray.getJSONObject(i).getString("id")         ;
                	//tempStringPassword = nameArray.getJSONObject(i).getString("password")   ; 
                	
                	//Log.i("userPass","<UtilizatorID"+i+">"      +nameArray.getJSONObject(i).getString("id")    +"</UtilizatorID"    +i+">\n");
                	//Log.i("userPass","<Parola"+i+">"            +nameArray.getJSONObject(i).getString("password")          +"</Parola"          +i+">\n");
                	// 	Log.i("userpasscheck","<Price"+i+">"   +nameArray.getJSONObject(i).getString("Price")   +"</Price"   +i+">\n");
                	//  Log.i("userpasscheck","<Symbol"+i+">"  +nameArray.getJSONObject(i).getString("Symbol")+"</Symbol"+i+">\n");
                	
                	
                	
                	// tempString=nameArray.getJSONObject(i).getString("Name")+"\n"+
                    //            nameArray.getJSONObject(i).getString("Price")+"\n"+nameArray.getJSONObject(i).getString("Symbol");
                	// vectorOfStrings.add(new String(tempString));
                //}
                
                    

                    
            	// int orderCount = vectorOfStrings.size();
            	// String[] orderTimeStamps = new String[orderCount];
            	// vectorOfStrings.copyInto(orderTimeStamps); 
            	// setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1 , orderTimeStamps));
            } catch (Exception e) {
                        e.printStackTrace();
                        Logger lgr = Logger.getLogger(AgentSetup.class.getName());
                        lgr.log(Level.SEVERE, e.getMessage(), e);

                        if (dm != null) {
                			dm.close();
                		}
                        
            }        
        }
}
