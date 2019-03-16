package com.example.dailyrecordsproject.drawerInterface;

import com.example.dailyrecordsproject.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class RegisterActivity extends Activity implements OnClickListener {

	private TextView register;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		register = (TextView) findViewById(R.id.go_back);
		register.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
		return true;
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.go_back:
			RegisterActivity.this.finish();
			break;

		default:
			break;
		}
	}
}
