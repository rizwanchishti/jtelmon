
/*
 * http://code.google.com/p/jtelmon/source/browse/trunk/AndroidViews/src/net/learn2develop/PurchaseOrders/SyncFromWebService.java
 * 
 *    Transaction support ; Batch updates ; 
 *    http://zetcode.com/db/postgresqljavatutorial/ 
 *    
 *    "DukesAgeResource" Tutorial : RESTful web service ~ GlassFish with NetBeans
 *    http://docs.oracle.com/javaee/6/firstcup/doc/gcrkm.html
 *    http://stackoverflow.com/questions/18230744/json-parser-read-an-entry-by-entry-from-large-json-file
 *    http://javarevisited.blogspot.ro/2013/02/how-to-convert-json-string-to-java-object-jackson-example-tutorial.html
 *    http://www.skybert.net/java/http/parsing-huge-json-documents/
 *    http://stackoverflow.com/questions/2818697/sending-and-parsing-json-in-android
 *    http://www.androidhive.info/2012/01/android-json-parsing-tutorial/
 *    
 *    
 */

package net.learn2develop.PurchaseOrders;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Vector;
// import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashMap; 

import net.learn2develop.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;
import org.apache.http.util.EntityUtils;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
// import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.JsonNode;

public class WebServiceSyncFrom extends ListActivity {
	
	private final static String SERVICE_URI         = "http://sfa.pangram.ro:8090/PostgresWebService/rest";
	// private final static String SERVICE_URI_TEMP = "http://192.168.61.207:8080/EmployeeDirectoryJAXRS20140420/rest";
	// private final static String SERVICE_URI_TEMP = "http://sfa.pangram.ro:8090/EmployeeDirectoryJAXRS20140420/rest";
	// private final static String SERVICE_URI_TEMP = "http://sfa.pangram.ro:8090/EmployeeDirectoryJAXRS20140422/rest";
	private final static String SERVICE_URI_TEMP    = "http://sfa.pangram.ro:8090/EmployeeDirectoryJAXRS20140513/rest";

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
        		SyncClientsEurobit();
        		SyncProduseEurobit();
        		// SyncRoutes();
        		// SyncProducts();
        		progressDialog.dismiss();
        	}
        }.start();
    }

    public void SyncProduseEurobit() {
    	// http://192.168.61.207:8080/PostgresWebService/rest/sales/eurobit/allproducts
    	// http://192.168.61.207:8080/EmployeeDirectoryJAXRS20140420/rest/sales/eurobit/allproducts
    	// http://ftp.pangram.ro:8090/EmployeeDirectoryJAXRS20140420/rest/sales/eurobit/allproducts
    		
    	DataManipulator dm = null;    
        dm = new DataManipulator(getApplicationContext());
    	HttpGet request = new HttpGet(SERVICE_URI_TEMP + "/sales/eurobit/allproducts");
    	request.setHeader("Accept", "application/json");
    	request.setHeader("Content-type", "application/json");
    	DefaultHttpClient httpClient = new DefaultHttpClient();
       	try {
    		HttpResponse response = httpClient.execute(request);
        	HttpEntity responseEntity = response.getEntity();
        	InputStream inputStream = responseEntity.getContent();
        	BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        	// Parsing Huge JSON Documents -> 
        	// http://www.skybert.net/java/http/parsing-huge-json-documents/
        	ObjectMapper objMapper = new ObjectMapper();
        	JsonFactory jsonFactory = objMapper.getJsonFactory();
        	JsonParser parser = jsonFactory.createJsonParser(reader);
        	// Map where to store your field-value pairs per object
        	HashMap<String, String> fields = new HashMap<String, String>();
        	// http://stackoverflow.com/questions/18230744/json-parser-read-an-entry-by-entry-from-large-json-file
        	// http://javarevisited.blogspot.ro/2013/02/how-to-convert-json-string-to-java-object-jackson-example-tutorial.html
        	JsonToken token;
        	while ((token = parser.nextToken()) != JsonToken.END_ARRAY) {
        	    switch (token) {
        	        // Starts a new object, clear the map
        	        case START_OBJECT:
        	            fields.clear();
        	            break;
        	        case FIELD_NAME:
        	            String field = parser.getCurrentName();
        	            token = parser.nextToken();
        	            String value = parser.getValueAsString();
        	            fields.put(field, value);
        	            System.out.println(field+" - "+value);
        	            break;
        	        case END_OBJECT:
        	        	System.out.println("END_OBJECT: " + fields.toString());
        	        	dm.insertIntoEurobitProducts(fields.get("stocId"), fields.get("simbol"), fields.get("denumire"), fields.get("categorieId"), fields.get("grupaId"), fields.get("clasaId"), fields.get("clasa"), fields.get("grupa"), fields.get("categorie"),fields.get("pretGross"));
        	            break;
        	        }
        	    }
        	    parser.close();
       	} catch (Exception a) {
       		a.printStackTrace();
       	}
		if (dm != null) {
			dm.close();
		}
    }    
    
    public void SyncClientsEurobit() {
        // http://sfa.pangram.ro:8090/PostgresWebService/rest/sales/allclients
    	DataManipulator dm = null;    
        dm = new DataManipulator(getApplicationContext());
    	HttpGet request = new HttpGet(SERVICE_URI + "/sales/allclients");
        // HttpGet request = new HttpGet(SERVICE_URI + "/sales/allproducts"  );
    	request.setHeader("Accept", "application/json");
    	request.setHeader("Content-type", "application/json");
    	DefaultHttpClient httpClient = new DefaultHttpClient();
    	String theString = new String("");
    	// ArrayList<HashMap<String, String>> clientsList;
    	// clientsList = new ArrayList<HashMap<String, String>>();
    	
       	try {
    		HttpResponse response = httpClient.execute(request);
        	HttpEntity responseEntity = response.getEntity();
        	InputStream inputStream = responseEntity.getContent();
        	BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        	
        	// Parsing Huge JSON Documents
        	// http://www.skybert.net/java/http/parsing-huge-json-documents/
        	ObjectMapper objMapper = new ObjectMapper();
        	// objMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        	JsonFactory jsonFactory = objMapper.getJsonFactory();
        	JsonParser parser = jsonFactory.createJsonParser(reader);
        	
        	// Map where to store your field-value pairs per object
        	HashMap<String, String> fields = new HashMap<String, String>();
        	// http://stackoverflow.com/questions/18230744/json-parser-read-an-entry-by-entry-from-large-json-file
        	// http://javarevisited.blogspot.ro/2013/02/how-to-convert-json-string-to-java-object-jackson-example-tutorial.html
        	JsonToken token;
        	while ((token = parser.nextToken()) != JsonToken.END_ARRAY) {
        	    switch (token) {

        	        // Starts a new object, clear the map
        	        case START_OBJECT:
        	            fields.clear();
        	            break;

        	        // For each field-value pair, store it in the map 'fields'
        	        case FIELD_NAME:
        	            String field = parser.getCurrentName();
        	            token = parser.nextToken();
        	            String value = parser.getValueAsString();
        	            fields.put(field, value);
        	            System.out.println(field+" - "+value);
        	            
        	            break;

        	        // Do something with the field-value pairs
        	        case END_OBJECT:
        	            // doSomethingWithTheObject(fields)
        	        	System.out.println(fields.toString());
        	        	// theString="client";
        	        	// System.out.println("END_OBJECT: client" + fields.get(theString));
        	        	// System.out.println("END_OBJECT: grupa" + fields.get("grupa"));
        	        	// clientsList.add(fields);
        	        	dm.insertIntoEurobitClients(fields.get("client"), fields.get("cui"), fields.get("plt"), fields.get("tertId"), fields.get("categorie"), fields.get("categorieId"), fields.get("clasa"), fields.get("clasaId"), fields.get("grupa"), fields.get("grupaId"));
        	            break;
        	        }
        	    }
        	    parser.close();
       	} catch (Exception a) {
       		a.printStackTrace();
       	}
		if (dm != null) {
			dm.close();
		}
    }
    
    
    public void SyncClientsKO() {
        // http://sfa.pangram.ro:8090/PostgresWebService/rest/sales/allclients
    	DataManipulator dm = null;    
        dm = new DataManipulator(getApplicationContext());
    	// HttpGet request = new HttpGet(SERVICE_URI + "/sales/allclients");
        HttpGet request = new HttpGet(SERVICE_URI + "/sales/allproducts"  );
    	request.setHeader("Accept", "application/json");
    	request.setHeader("Content-type", "application/json");
    	DefaultHttpClient httpClient = new DefaultHttpClient();
    	String theString = new String("");

       	try {
    		HttpResponse response = httpClient.execute(request);
        	HttpEntity responseEntity = response.getEntity();
        	InputStream inputStream = responseEntity.getContent();
        	BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        	
        	// Parsing Huge JSON Documents
        	// http://www.skybert.net/java/http/parsing-huge-json-documents/
        	ObjectMapper objMapper = new ObjectMapper();
        	// objMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        	JsonFactory factory = objMapper.getJsonFactory();
        	JsonParser jsonParser = factory.createJsonParser(reader);
        	JsonNode actualObj = objMapper.readTree(jsonParser);
        	if (actualObj.isArray()) {
        		// Iterator<JsonNode> elements = actualObj.getElements();
        		Iterator<JsonNode> elements = actualObj.elements();
        		while (elements.hasNext()) {
        			JsonNode arrayElement = elements.next();
        			// JSONObject jsonObject = JSONObject.fromObject(arrayElement.toString());
        			
        			// do something useful with jsonObject
        			System.out.println(arrayElement.toString());
        			
        		}
        	}
			// System.out.println(actualObj.asText());
			theString=actualObj.asText();
			
        	Vector<String> vectorOfStrings = new Vector<String>();
        	String tempStringCategorie  	= new String();
        	String tempStringCategorieID  	= new String();
        	String tempStringClasa  		= new String();
        	String tempStringClasaID  		= new String();
        	String tempStringClient 		= new String();
        	String tempStringCUI 			= new String();
        	String tempStringGrupa  		= new String();
        	String tempStringPLT 			= new String();
        	String tempStringTertID 		= new String(); 
        	StringBuilder builder1 			= new StringBuilder();
        	String line1;
        	
        	
        	if(responseEntity!=null) {
        		theString = EntityUtils.toString(responseEntity);
        	}
        	
        	// BufferedReader.readLine() : Out of memory 
        	// line1 = reader1.readLine();
        	
        	      	
        	//while ((line1 = reader1.readLine()) != null) {
			//	builder1.append(line1);
			//}
        	
        	// stream1.close();
        	// theString = builder1.toString();
        	JSONObject json1=new JSONObject(theString);
        	// JSONObject : Out of memory
        	
        	Log.i("_GetClient_","<jsonobject>\n"+json1.toString()+"\n</jsonobject>");
        	dm = new DataManipulator(getApplicationContext());
        	JSONArray nameArray1;
        	nameArray1=json1.getJSONArray("client");
        	// nameArray1=json1.getJSONArray("GetRoutesByAgentResult");
        	 for(int i=0;i<nameArray1.length();i++)
             {
             	tempStringClasa   = nameArray1.getJSONObject(i).getString("clasa");
             	tempStringClient  = nameArray1.getJSONObject(i).getString("client");
             	tempStringGrupa   = nameArray1.getJSONObject(i).getString("grupa");
             	tempStringPLT 	  = nameArray1.getJSONObject(i).getString("plt");
     			// dm.insertIntoClients(tempStringClasa,tempStringClient,tempStringGrupa,tempStringPLT);
                tempStringCategorie=nameArray1.getJSONObject(i).getString("client")+"\n"+
                 		nameArray1.getJSONObject(i).getString("clasa")+"\n"+nameArray1.getJSONObject(i).getString("grupa");
                vectorOfStrings.add(new String(tempStringCategorie));
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
    }
    
    public void SyncRoutes() {
    	DataManipulator dm = null;    
        String[] setupInfo =  new String[]{};
        dm = new DataManipulator(getApplicationContext());
		    setupInfo = dm.selectFirstRecordFromSetupTable();

    //final DefaultHttpClient httpClient = new DefaultHttpClient();

    String theString = new String("");
    
    // HttpGet request1 = new HttpGet(SERVICE_URI +   "/sales/3165/routes");       
    HttpGet request1 = new HttpGet(SERVICE_URI +   "/sales/"+setupInfo[0]+"/routes");
    DefaultHttpClient httpClient1 = new DefaultHttpClient();
   	try {
    		HttpResponse response1 = httpClient1.execute(request1);
        	HttpEntity response1Entity = response1.getEntity();
        	InputStream stream1 = response1Entity.getContent();
        	BufferedReader reader1 = new BufferedReader(new InputStreamReader(stream1));
        	Vector<String> vectorOfStrings = new Vector<String>();
        	String tempString1      = new String();
        	String tempStringAgent  = new String();
        	String tempStringClient = new String();
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

    }

    
    public void SyncProducts() {
    	DataManipulator dm = null;    
        // String[] setupInfo =  new String[]{};
        dm = new DataManipulator(getApplicationContext());
		// setupInfo = dm.selectFirstRecordFromSetupTable();
		HttpGet request = new HttpGet(SERVICE_URI + "/sales/allproducts"  );       
		request.setHeader("Accept", "application/json");
		request.setHeader("Content-type", "application/json");
		final DefaultHttpClient httpClient = new DefaultHttpClient();
		String theString = new String("");
		// 	HttpGet request1 = new HttpGet(SERVICE_URI +   "/sales/3165/routes");       
		//HttpGet request1 = new HttpGet(SERVICE_URI +   "/sales/"+setupInfo[0]+"/routes");
		request.setHeader("Accept", "application/json");
		request.setHeader("Content-type", "application/json");
		//DefaultHttpClient httpClient1 = new DefaultHttpClient();
		try {
			HttpResponse response = httpClient.execute(request);
			HttpEntity responseEntity = response.getEntity();
			InputStream stream = responseEntity.getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
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
			// dm = new DataManipulator(getApplicationContext());
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
		if (dm != null) {
			dm.close();
		}
    }
    
    // @Override
	protected void onDestroy() {
		super.onDestroy();
		//if (dm != null) {
		//	dm.close();
		//}
	}
}
