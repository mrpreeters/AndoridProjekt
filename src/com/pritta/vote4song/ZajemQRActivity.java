package com.pritta.vote4song;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONObject;

import com.pritta.vote4song.R.id;
import com.pritta.vote4song.data.Singel;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.view.View;
import android.os.AsyncTask;



public class ZajemQRActivity extends ActionBarActivity {

	ControlApplication app;
	String id_scan;
	private ListView lv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		app = (ControlApplication) this.getApplication();
		setContentView(R.layout.activity_zajem_qr);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		
		 Bundle extras;
		if (savedInstanceState == null) {
		    extras = getIntent().getExtras();
		    if(extras == null) {
		    	id_scan= null;
		    } else {
		    	id_scan= extras.getString("SCAN_ID");
		    }
		} else {
			id_scan= (String) savedInstanceState.getSerializable("SCAN_ID");
		}
		//Toast.makeText(this, id_scan, Toast.LENGTH_LONG).show();
		pridobiSingle();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.zajem_qr, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_zajem_qr,
					container, false);
			return rootView;
		}
	}
	public void posljiDogodek(View v)
	{

		pridobiSingle();
		
		 
	}
	
	public void pridobiSingle()
	{
		
		int id = Integer.parseInt(id_scan.toString());
		
		try {
			HttpParams httpParams = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(httpParams,2000);
	
	        HttpParams p = new BasicHttpParams();
	        p.setParameter("id_dogodek", "1");
	        HttpClient httpclient = new DefaultHttpClient(p);
	        
	        String url = "http://pritrznik.si/vote4song/service.php?action=get_izvajalci&id_dogodek="+id;
	        
	        HttpPost httppost = new HttpPost(url);
	        
	        try {
	            Log.i(getClass().getSimpleName(), "send  task - start");
	            //
	            
	            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(
	                    2);
	            nameValuePairs.add(new BasicNameValuePair("id_dogodek", "1"));
	            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	            ResponseHandler<String> responseHandler = new BasicResponseHandler();
	            String responseBody = httpclient.execute(httppost,
	                    responseHandler);
	            // Parse
	            
	            JSONObject json = new JSONObject(responseBody);
	            JSONArray jArray = json.getJSONArray("izvajalci");
	            ArrayList<Singel> mylist = 
	                   new ArrayList<Singel>();
	
	            for (int i = 0; i < jArray.length(); i++) {
	                HashMap<String, String> map = new HashMap<String, String>();
	                
	                JSONObject e = jArray.getJSONObject(i);
	              
	               // JSONObject jObject = new JSONObject(s);
	
	                map.put("izvajalec", e.getString("izvajalec"));
	                map.put("naziv",  e.getString("singel"));
	                Singel singel = new Singel(e.getString("id_singel"),e.getString("singel"), 0, e.getString("izvajalec"), e.getString("ocena"));
	                

	                mylist.add(singel);
	            }
	            app.listSingli = mylist;
	            
	            prikaziIzvajalce(mylist);
	            
	          //  Toast.makeText(this, responseBody, Toast.LENGTH_LONG).show();
	
	        } catch (ClientProtocolException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        // Log.i(getClass().getSimpleName(), "send  task - end");
	
	    } catch (Throwable t) {
	        Toast.makeText(this, "Request failed: " + t.toString(),
	                Toast.LENGTH_LONG).show();
	    }	
	}
	public void prikaziIzvajalce(ArrayList list)
	{
		
		lv = (ListView) findViewById(R.id.listIzvajalci);
		
		ArrayAdapter<Singel> arrayAdapter = new ArrayAdapter<Singel>(
                this, 
                android.R.layout.simple_list_item_1,
                list );
		
		
        lv.setAdapter(arrayAdapter); 
        lv.setOnItemClickListener(new OnItemClickListener() {
        	 
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
               int position, long id) {
              
             // ListView Clicked item index
             int itemPosition     = position;
             
             // groza, groza, groza, sem ze rekel groza?
             Object  itemValue    = lv.getItemAtPosition(position);
             Singel s = (Singel) itemValue;  
             Object obj = lv.getAdapter().getItem(position);
             String value = obj.toString();

             oddajGlas(s.getId_singel());
              // Show Alert 
              Toast.makeText(getApplicationContext(),
                "Glasovali ste za :"+s.toString()+s.getId_singel() , Toast.LENGTH_LONG)
                .show();

            }

       }); 
	}
	public void oddajGlas(int id)
	{
		   try {
		        JSONObject json = new JSONObject();
		        json.put("id_singel", id);
		        json.put("test", "testni test");
		        HttpParams httpParams = new BasicHttpParams();
		        HttpConnectionParams.setConnectionTimeout(httpParams,
		                2000);
		        HttpConnectionParams.setSoTimeout(httpParams, 2000);
		        HttpClient client = new DefaultHttpClient(httpParams);
		        //
		        //String url = "http://10.0.2.2:8080/sample1/webservice2.php?" + 
		        //             "json={\"UserName\":1,\"FullName\":2}";
		        String url = "http://pritrznik.si/vote4song/service.php?action=oddaj_glas&id_singel="+id;

		        HttpPost request = new HttpPost(url);
		        request.setEntity(new ByteArrayEntity(json.toString().getBytes(
		                "UTF8")));
		        request.setHeader("json", json.toString());
		        HttpResponse response = client.execute(request);
		        HttpEntity entity = response.getEntity();
		        // If the response does not enclose an entity, there is no need
		        if (entity != null) {
		            InputStream instream = entity.getContent();

		            String result = instream.toString();
		            Log.i("Read from server", result);
		           // Toast.makeText(this, "response"+ result,
		                //    Toast.LENGTH_LONG).show();
		        }
		    } catch (Throwable t) {
		        Toast.makeText(this, "Request failed: " + t.toString(),
		                Toast.LENGTH_LONG).show();
		    }		
	}

}
