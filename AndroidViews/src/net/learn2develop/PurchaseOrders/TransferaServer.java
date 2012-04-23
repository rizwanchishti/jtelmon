

package net.learn2develop.PurchaseOrders;


import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;



import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
// import org.json.JSONObject;
import org.json.JSONStringer;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

public class TransferaServer extends Activity {
	
	 Data dm;
	 List<String[]> comanda =null ;
	 String[] StringOfOrders;

        private final static String SERVICE_URI = "http://192.168.61.3/SalesService/SalesService.svc";

        @Override
            public void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);

                setContentView(R.layout.main);
                dm = new Data(this);
                comanda = dm.selectAllOrders();
                // String plate = new String("test");
                // POST request to <service>
            HttpPost request = new HttpPost(SERVICE_URI + "/json/addorder");
            request.setHeader("Accept", "application/json");
            request.setHeader("Content-type", "application/json");
            
            String not = new String(" ");
           // String ClientDeTrimis = new String("Miruna SA");
            String[] ComandaDeTrimis = comanda.get(0);
            String ClientDeTrimis = ComandaDeTrimis[0];
            String ProdusDeTrimis = ComandaDeTrimis[1];
            String NumarBucati = ComandaDeTrimis[2];
            String Discount = ComandaDeTrimis[3];
            
            
            // EditText plateEdit = null;
            // Editable plate ;
            // plate =  plateEdit.getText();
            
            try {
            // Build JSON string
            JSONStringer vehicle = new JSONStringer()
                .object()
                    .key("od")
                        .object()
                            .key("ClientName").value(ClientDeTrimis)
                            .key("ProductName").value(ProdusDeTrimis)
                             .key("PiecesNumber").value(NumarBucati)
                              .key("DiscountNumber").value(Discount)
                               .key("LineOrderId").value("1000")
                        .endObject()
                    .endObject();
            
            StringEntity entity = new StringEntity(vehicle.toString());
            
            Toast.makeText(this, vehicle.toString() + "\n", Toast.LENGTH_LONG).show() ;
            
            request.setEntity(entity);
            
            // Send request to WCF service
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpResponse response = httpClient.execute(request);
            // Log.d("WebInvoke", "Saving : " + response.getStatusLine().getStatusCode());
            Toast.makeText(this, response.getStatusLine().getStatusCode() + "\n", Toast.LENGTH_LONG).show() ;
            
            }catch (Exception e) {
                not = "NOT ";
            } 
            
            Toast.makeText(this, not + " OK ! " + "\n", Toast.LENGTH_LONG).show() ;
            
            
        
        }    
}