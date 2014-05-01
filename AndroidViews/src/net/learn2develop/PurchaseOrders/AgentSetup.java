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
	    String idAgent;
	    String thePass;
	    String theResult = new String("OK ");
	    TextView t1;
	    TextView t2;
	    TextView t3;

        @Override
            public void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.main_agent_reinitializare);
                BundledAgent = getIntent().getExtras();
                if(BundledAgent != null) {
                   idAgent = BundledAgent.getString("agent").trim();
                   thePass = BundledAgent.getString("parola").trim();
                   Log.i("BundledAgent " ,idAgent+" \n");
                   t1 = (TextView)findViewById(R.id.agentid);
                   t2 = (TextView)findViewById(R.id.agpass);
                   t3 = (TextView)findViewById(R.id.result);
                   if(t1 != null) {
                       t1.setText(idAgent);
                   }
                   if(t2 != null) {
                       t2.setText(thePass);
                   }
                }
                // HttpGet request = new HttpGet(SERVICE_URI + "/json/userpasscheck ");       
                // HttpGet request = new HttpGet(SERVICE_URI + "/sales/search/1");
                HttpGet request = new HttpGet(SERVICE_URI + "/sales/search/"+idAgent);
                request.setHeader("Accept", "application/json");
                request.setHeader("Content-type", "application/json"); 
                DefaultHttpClient httpClient = new DefaultHttpClient();
                String theString = new String("");

                String restStringID         = new String();
                String restStringPassword   = new String();
                String restStringUserName   = new String();
                boolean tmpb1 , tmpb2;
                
            try {
                HttpResponse response = httpClient.execute(request);
                HttpEntity responseEntity = response.getEntity();
                InputStream stream = responseEntity.getContent();
                BufferedReader reader = new BufferedReader(
                                        new InputStreamReader(stream));
                // Vector<String> vectorOfStrings = new Vector<String>();
                // String tempString = new String();
                StringBuilder builder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                                builder.append(line);
                }
                stream.close();
                theString = builder.toString();
                JSONObject json=new JSONObject(theString);
                restStringID = json.getString("id").trim();
                restStringPassword = json.getString("password").trim();
                restStringUserName = json.getString("userName").trim();
    			t1.setText(restStringID);
    			t2.setText(restStringPassword);
                Log.i("userpasscheck","<jsonobject>\n" + json.toString()            + "\n</jsonobject>");
                Log.i("userID","<UtilizatorID"+">"     + json.getString("id")       + "</UtilizatorID"    +">\n");
                Log.i("userPass","<Parola"+">"         + json.getString("password") + "</Parola"          +">\n");
                Log.i("userName","<userName"+">"       + json.getString("userName") + "</userName"        +">\n");
            } catch (Exception e) {
    			theResult = "KO, exceptie stream ! ";
    			t3.setText(theResult + e.toString());
                e.printStackTrace();
                Logger lgr = Logger.getLogger(AgentSetup.class.getName());
                lgr.log(Level.SEVERE, e.getMessage(), e);
            }    
            try {
                tmpb1=restStringID.equals(idAgent);
                tmpb2=restStringPassword.equals(thePass);
                if( tmpb1 && tmpb2){
                	dm = new DataManipulator(getApplicationContext());
                	dm.deleteAllSetup();
                	dm.insertIntoSetup(restStringID,restStringUserName,restStringPassword);
                	dm.close();
                	theResult = "OK! "+restStringUserName;
                }else{
                	theResult = "KO! ID sau parola gresita";
                }
                t3.setText(theResult);
            } catch (Exception e) {
            			theResult = "KO, exceptie SQLite ! ";
            			t3.setText(theResult + e.toString());
                        e.printStackTrace();
                        Logger lgr = Logger.getLogger(AgentSetup.class.getName());
                        lgr.log(Level.SEVERE, e.getMessage(), e);
                        if (dm != null) {
                			dm.close();
                		}
            }        
        }
}
