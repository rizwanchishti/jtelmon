/*
 * 
 * http://developer.android.com/resources/tutorials/views/hello-autocomplete.html
 * http://www.giantflyingsaucer.com/blog/?p=1342
 *
 */

package net.learn2develop.WCF2SQLite;

import java.util.List;

import net.learn2develop.R;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
 
public class SQLiteAutocomplete01 extends Activity
{
    DataManipulator dataMan;
    List<String[]> listOfRoutes =null ;
	String[] shirOfClients;
 
    static final String[] COUNTRIES = new String[] {
    	  "Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra",
    	  "Angola", "Anguilla", "Antarctica", "Antigua and Barbuda", "Argentina",
    	  "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan",
    	  "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium",
    	  "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia",
    	  "Bosnia and Herzegovina", "Botswana", "Bouvet Island", "Brazil", "British Indian Ocean Territory",
    	  "British Virgin Islands", "Brunei", "Bulgaria", "Burkina Faso", "Burundi",
    	  "Cote d'Ivoire", "Cambodia", "Cameroon", "Canada", "Cape Verde",
    	  "Cayman Islands", "Central African Republic", "Chad", "Chile", "China",
    	  "Christmas Island", "Cocos (Keeling) Islands", "Colombia", "Comoros", "Congo",
    	  "Cook Islands", "Costa Rica", "Croatia", "Cuba", "Cyprus", "Czech Republic",
    	  "Democratic Republic of the Congo", "Denmark", "Djibouti", "Dominica", "Dominican Republic",
    	  "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea",
    	  "Estonia", "Ethiopia", "Faeroe Islands", "Falkland Islands", "Fiji", "Finland",
    	  "Former Yugoslav Republic of Macedonia", "France", "French Guiana", "French Polynesia",
    	  "French Southern Territories", "Gabon", "Georgia", "Germany", "Ghana", "Gibraltar",
    	  "Greece", "Greenland", "Grenada", "Guadeloupe", "Guam", "Guatemala", "Guinea", "Guinea-Bissau",
    	  "Guyana", "Haiti", "Heard Island and McDonald Islands", "Honduras", "Hong Kong", "Hungary",
    	  "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland", "Israel", "Italy", "Jamaica",
    	  "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Kuwait", "Kyrgyzstan", "Laos",
    	  "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg",
    	  "Macau", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands",
    	  "Martinique", "Mauritania", "Mauritius", "Mayotte", "Mexico", "Micronesia", "Moldova",
    	  "Monaco", "Mongolia", "Montserrat", "Morocco", "Mozambique", "Myanmar", "Namibia",
    	  "Nauru", "Nepal", "Netherlands", "Netherlands Antilles", "New Caledonia", "New Zealand",
    	  "Nicaragua", "Niger", "Nigeria", "Niue", "Norfolk Island", "North Korea", "Northern Marianas",
    	  "Norway", "Oman", "Pakistan", "Palau", "Panama", "Papua New Guinea", "Paraguay", "Peru",
    	  "Philippines", "Pitcairn Islands", "Poland", "Portugal", "Puerto Rico", "Qatar",
    	  "Reunion", "Romania", "Russia", "Rwanda", "Sqo Tome and Principe", "Saint Helena",
    	  "Saint Kitts and Nevis", "Saint Lucia", "Saint Pierre and Miquelon",
    	  "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Saudi Arabia", "Senegal",
    	  "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands",
    	  "Somalia", "South Africa", "South Georgia and the South Sandwich Islands", "South Korea",
    	  "Spain", "Sri Lanka", "Sudan", "Suriname", "Svalbard and Jan Mayen", "Swaziland", "Sweden",
    	  "Switzerland", "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand", "The Bahamas",
    	  "The Gambia", "Togo", "Tokelau", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey",
    	  "Turkmenistan", "Turks and Caicos Islands", "Tuvalu", "Virgin Islands", "Uganda",
    	  "Ukraine", "United Arab Emirates", "United Kingdom",
    	  "United States", "United States Minor Outlying Islands", "Uruguay", "Uzbekistan",
    	  "Vanuatu", "Vatican City", "Venezuela", "Vietnam", "Wallis and Futuna", "Western Sahara",
    	  "Yemen", "Yugoslavia", "Zambia", "Zimbabwe"
    	};
    
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_autocomplete);
 
        final AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.autocompleteCountry);
 
        dataMan = new DataManipulator(this);
        listOfRoutes = dataMan.selectAllClients();
		shirOfClients=new String[listOfRoutes.size()]; 
		
		int x=0;
		String stringTemporar;

		for (String[] name : listOfRoutes) {
			// Route = name[0]+" - "+name[1]+ " - "+name[2]+" - "+name[3];
			stringTemporar = name[1];
			shirOfClients[x]=stringTemporar;
			x++;
		}

        // Print out the values to the log
        for(int i = 0; i < shirOfClients.length; i++)
        {
            Log.i(this.toString(), shirOfClients[i]);
        }
 
        // ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, COUNTRIES);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, shirOfClients);
        textView.setAdapter(adapter);
    }
 
    public void onDestroy()
    {
        super.onDestroy();
        // sqlliteCountryAssistant.close();
    }
}
