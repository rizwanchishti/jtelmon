package net.learn2develop.PurchaseOrders;

// http://code.google.com/p/jtelmon/source/browse/trunk/AndroidViews/src/net/learn2develop/PurchaseOrders/AdaugaAgent.java
// http://code.google.com/p/jtelmon/source/browse/trunk/AndroidViews/src/net/learn2develop/PurchaseOrders/AgentSetup.java
// http://code.google.com/p/javajdbc/source/browse/trunk/AsecondActivity/src/eu/itcsolutions/android/tutorial/AdaugaAgent.java
// http://www.vogella.com/tutorials/AndroidBackgroundProcessing/article.html
// http://saigeethamn.blogspot.ro/2010/04/threads-and-handlers-android-developer.html
// http://indyvision.net/2010/02/android-threads-tutorial-part-3/
// http://indyvision.net/2010/02/android-threads-tutorial-part-2/
// http://indyvision.net/2010/01/android-threads-tutorial/
// http://www.biemmeitalia.net/blog/bundle-android/
//AdaugaAgent ramane clasa care seteaza noul agent in SQLS
//AgentSetup  este clasa care va seta noul agent in Postgres


import net.learn2develop.R;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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

public class AgentSetup extends Activity implements Runnable {

	// REINITIALIZEAZA_DATE_AGENT
	
/* http://sfa.pangram.ro:8090/PostgresWebService/rest/sales/search/1/123456
	<userPass>
		<id> 1 </id>
		<password> 123456 </password>
		<userName> ARICIU DANIEL </userName>
		<zone> RESITA </zone>
	</userPass>

/* http://sfa.pangram.ro:8090/PostgresWebService/rest/sales/search/2/222
<userPass><id>2</id><password>222</password><userName>Zaberca Petru</userName><zone>RESITA</zone></userPass>	
*/
	    private final static String SERVICE_URI = "http://sfa.pangram.ro:8090/PostgresWebService/rest";
        // private final static String SERVICE_URI = "http://192.168.61.3/SalesService/SalesService.svc";

	    DataManipulator dm = null;
	    private Thread newThread;

	    Bundle BundledAgent;
	    String idAgent;
	    String thePass;
	    String theResult = new String(" ");
	    TextView t1;
	    TextView t2;
	    TextView t3;
	    boolean tmpboo1 , tmpboo2;

        // Receives Thread's messages, interprets them and acts on the
        // current Activity as needed
        private Handler threadHandler = new Handler() {
            public void handleMessage(android.os.Message msg) {
            	 if (msg.what == 0) {
                     t3.setText(msg.getData().getString("result"));
                 }
            }
        };
	    
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
                // fara acest new Thread() - apare eroarea : android.os.NetworkOnMainThreadException 
                // functiile de retea trebuiesc executate in new THREAD ...     
                // aceasta eroare apare doar in tableta , nu si in emulator !         
                newThread = new Thread(this);
                newThread.start();
        	}
            
        // run() method is invoked once the start() method on the newThread variable is called
        public void run() {
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
            Message messageToThread = threadHandler.obtainMessage();
            Bundle messageData = new Bundle();
          try {
              HttpResponse response = httpClient.execute(request);
              HttpEntity responseEntity = response.getEntity();
              InputStream stream = responseEntity.getContent();
              BufferedReader reader = new BufferedReader(
                                      new InputStreamReader(stream));
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
              Log.i("userpasscheck","<jsonobject>\n" + json.toString()            + "\n</jsonobject>");
              Log.i("userID","<UtilizatorID"+">"     + json.getString("id")       + "</UtilizatorID"    +">\n");
              Log.i("userPass","<Parola"+">"         + json.getString("password") + "</Parola"          +">\n");
              Log.i("userName","<userName"+">"       + json.getString("userName") + "</userName"        +">\n");
          } catch (Exception e) {
  			  theResult = theResult + "KO, exceptie stream ! ";
              e.printStackTrace();
              Logger lgr = Logger.getLogger(AgentSetup.class.getName());
              lgr.log(Level.SEVERE, e.getMessage(), e);
          }    
          try {
              tmpboo1=restStringID.equals(idAgent);
              tmpboo2=restStringPassword.equals(thePass);
              if( tmpboo1 && tmpboo2){
              	dm = new DataManipulator(getApplicationContext());
              	dm.deleteAllSetup();
              	dm.insertIntoSetup(restStringID,restStringUserName,restStringPassword);
              	dm.close();
              	theResult =  theResult + "OK! "+restStringUserName;
              }else{
              	theResult =  theResult + "KO! ID sau parola gresita";
              }
          } catch (Exception e) {
          			theResult = "KO, exceptie SQLite ! ";
                      e.printStackTrace();
                      Logger lgr = Logger.getLogger(AgentSetup.class.getName());
                      lgr.log(Level.SEVERE, e.getMessage(), e);
                      if (dm != null) {
              			dm.close();
              		  }
          }
          messageToThread.what = 0;
          messageData.putString("result", theResult);
          messageToThread.setData(messageData);
          threadHandler.sendMessage(messageToThread);
      }   // end Thread.run()
} // end class AgentSetup        
