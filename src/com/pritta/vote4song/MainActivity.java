package com.pritta.vote4song;

import java.io.IOException;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


public class MainActivity extends ActionBarActivity {

	ControlApplication app;
	String result;
	ProgressDialog progressDialog;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (ControlApplication) this.getApplication();
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }
   
	public void odpriSingliList(View v) {
		 Intent intent = new Intent();
	      //  intent.setClass(MainActivity.this, SingliList.class);
	        startActivity(intent);		
		
	}

	public void QRScan(View v)
	{
		IntentIntegrator integrator = new IntentIntegrator(this);
		integrator.initiateScan();
	}
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		  IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
		  if (scanResult != null && scanResult.getContents() != null) {
		   
		   String tip;
		   
		   result = scanResult.getContents();
		   tip = scanResult.getFormatName();
		   
		   
		   progressDialog = new ProgressDialog(this);
			progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			progressDialog.setMessage("Prenos podatkov...");
			progressDialog.setCancelable(false);
			progressDialog.show();
			new getPodatkiWeb().execute(1);
			
		   
		  }
		  else
		  {
			  AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			           public void onClick(DialogInterface dialog, int id) {
			               // User clicked OK button
			           }
			       });
			
				builder.setMessage("QR koda ni bila zajeta.")
			       .setTitle("Opozorilo");
				AlertDialog dialog = builder.create();
				dialog.show(); 
		  }
		  // else continue with any other code you need in the method
		  
		}
	private class getPodatkiWeb extends AsyncTask<Integer, Integer, Long> {
		protected Long doInBackground(Integer... a) {
			// NEEEEEEEEEEEE!!!!!!!! klicat elementov glavne (UI) niti npr. button1.setText("Basdasad");
			long totalSize = 0;
			for (int i = 0; i < a[0]; i++) {
				// app.loadData();
				// sleep je samo simulacija da nekaj traja, ponavadi je tu
				// kakšen klic na splet
				try {
					//pridobiSingle();
					 Intent zajem = new Intent();
					   zajem.setClass(MainActivity.this, ZajemQRActivity.class);
					   zajem.putExtra("SCAN_ID", result);
						startActivity(zajem);	
						progressDialog.hide();
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println("Malo info " + i);
				publishProgress(i);
			}
			return totalSize;
		}
}
	protected void onProgressUpdate(Integer... progress) {
		setProgressPercent(progress[0]);
	}
	public void setProgressPercent(Integer integer) {
		progressDialog.setProgress(integer);

	}
	
	public void prikaziStatistiko(View v)
	{
		if(app.getListSingli() != null)
		{
			Intent zajem = new Intent();
			zajem.setClass(MainActivity.this, StatistikaActivity.class);
			//zajem.putExtra("SCAN_ID", result);
			startActivity(zajem);	
		}
		else
		{
	
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		               // User clicked OK button
		           }
		       });
		
			builder.setMessage("Napaka - ni zajetih podatkov.")
		       .setTitle("Opala");
			AlertDialog dialog = builder.create();
			dialog.show();
		}
	}
	public void prikaziList(View v)
	{
		if(app.getListSingli() != null)
		{
			Intent zajem = new Intent();
			zajem.setClass(MainActivity.this, ListActivity.class);
			zajem.putExtra("SCAN_ID", result);
			startActivity(zajem);	
		}
		else
		{
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		               // User clicked OK button
		           }
		       });
		
			builder.setMessage("Napaka - ni zajetih podatkov.")
		       .setTitle("Opala");
			AlertDialog dialog = builder.create();
			dialog.show();
		}
	}
}
