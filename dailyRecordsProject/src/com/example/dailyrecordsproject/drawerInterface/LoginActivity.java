package com.example.dailyrecordsproject.drawerInterface;

import com.example.dailyrecordsproject.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity implements OnClickListener {

	
	private TextView goBack; 
	private Button regis_btn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		goBack = (TextView) findViewById(R.id.go_back);
		regis_btn = (Button) findViewById(R.id.regis_btn);
		goBack.setOnClickListener(this);
		regis_btn.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.go_back:
			LoginActivity.this.finish();
			break;

		case R.id.regis_btn:
			startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
			break;
		}
		
	}

}
