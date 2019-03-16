package com.example.dailyrecordsproject.drawerInterface;

import com.example.dailyrecordsproject.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class SyncActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sync);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sync, menu);
		return true;
	}

}
