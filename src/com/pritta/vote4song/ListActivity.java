package com.pritta.vote4song;

import com.pritta.vote4song.data.Singel;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.os.Build;

public class ListActivity extends ActionBarActivity {
	ControlApplication app;
	String id_scan;
	private ListView lv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		app = (ControlApplication) this.getApplication();
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
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
lv = (ListView) findViewById(R.id.list);
		
		ArrayAdapter<Singel> arrayAdapter = new ArrayAdapter<Singel>(
                this, 
                android.R.layout.simple_list_item_1,
                app.getListSingli() );
		
		
        lv.setAdapter(arrayAdapter); 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list, menu);
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
			View rootView = inflater.inflate(R.layout.fragment_list, container,
					false);
			return rootView;
		}
	}

}
