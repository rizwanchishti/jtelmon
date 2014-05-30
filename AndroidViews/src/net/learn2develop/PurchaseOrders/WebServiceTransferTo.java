
package net.learn2develop.PurchaseOrders;

import net.learn2develop.R;

import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.json.JSONStringer;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

public class WebServiceTransferTo extends Activity {
	private final static String SERVICE_URI = "http://sfa.pangram.ro:8090/PostgresWebService/rest";
	// private final static String SERVICE_URI = "http://192.168.61.3/SalesService/SalesService.svc";
	// private final static String SERVICE_URI = "http://ftp.pangram.ro:9090/SalesService/SalesService.svc";

		@Override
        public void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.main);
                final ProgressDialog progressDialog = ProgressDialog.show(this, "", "Trimitere comenzi...");
                new Thread() {
                	public void run() {
                		SyncOrders();
                		progressDialog.dismiss();
                	}
                }.start();
        }
		
        private Handler threadHandler = new Handler() {
            public void handleMessage(android.os.Message msg) {
            	String isNot = new String(" ");
            	 if (msg.what == 0) {
                     // t3.setText(msg.getData().getString("result"));
            		 isNot = msg.getData().getString("result");
            		 Toast.makeText(WebServiceTransferTo.this, isNot + " OK ! " + "\n", Toast.LENGTH_LONG).show() ;
                 }
            	 super.handleMessage(msg);
            }
        };
        
        public void SyncOrders() {
        	Message messageToThread = threadHandler.obtainMessage();
    	 	DataManipulator dm;
    	 	List<String[]> ordersToSend = null ;
            dm = new DataManipulator(this);
            ordersToSend = dm.selectAllOrders();
            String isNot = new String(" ");
            Bundle messageData = new Bundle();
            HttpPost request = new HttpPost(SERVICE_URI + "/sales");
            request.setHeader("Accept", "application/json");
            request.setHeader("Content-type", "application/json");
            for (String[] orderToSend : ordersToSend) {
            	String idLineToSend   = orderToSend[0];
            	String clientToSend   = orderToSend[1];
            	String productToSend  = orderToSend[2];
            	String piecesToSend   = orderToSend[3];
            	String discountToSend = orderToSend[4];
            	try {
            		JSONStringer jsonOrderToSend = new JSONStringer()
            		.object()
                        		.key("client").value(clientToSend)
                        		.key("product").value(productToSend)
                        		.key("pieces").value(piecesToSend)
                        		.key("discount").value(discountToSend)
                        		.key("line").value(idLineToSend)
                    .endObject();
            		StringEntity entity = new StringEntity(jsonOrderToSend.toString());
            		Log.d( " OrderLine  " , jsonOrderToSend.toString() + "\n" ) ;
            		request.setEntity(entity);
            		// 	Send request to REST Web Service -> 
            		DefaultHttpClient httpClient = new DefaultHttpClient();
            		HttpResponse response = httpClient.execute(request);
            		// IF [200=response.getStatusLine().getStatusCode()] => HttpResponse=OK  
            		Log.d("WebInvoke", " OK if 200 = " + response.getStatusLine().getStatusCode());
            	}catch (Exception e) {
            		isNot = "NOT ";
            	} 
            }
            // Toast.makeText(this, isNot + " OK ! " + "\n", Toast.LENGTH_LONG).show() ;
            messageToThread.what = 0;
            messageData.putString("result", isNot);
            messageToThread.setData(messageData);
            threadHandler.sendMessage(messageToThread);
            if (dm != null) {
    			dm.close();
    		}
        }
}
