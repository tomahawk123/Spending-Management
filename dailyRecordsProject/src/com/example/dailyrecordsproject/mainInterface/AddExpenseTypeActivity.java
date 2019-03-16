package com.example.dailyrecordsproject.mainInterface;

import com.example.dailyrecordsproject.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class AddExpenseTypeActivity extends Activity implements OnClickListener{
	
	private TextView goBack;
	private ImageView iv;
	private EditText et;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_expense_type);
		et = (EditText)	findViewById(R.id.et_new_type);
		iv = (ImageView) findViewById(R.id.check);
		goBack = (TextView) findViewById(R.id.go_back);
		goBack.setOnClickListener(this);
		iv.setOnClickListener(this);
		
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}


	@Override
	public void onClick(View arg0) {

		switch (arg0.getId()) {
		case R.id.check:
			Intent intent = new Intent(this, MakeOneExpenseActivity.class);
			intent.putExtra("newType", et.getText().toString());
			startActivity(intent);
			break;

		case R.id.go_back:
			startActivity(new Intent(this, MakeOneExpenseActivity.class));
			break;
		}
		
	}
}
