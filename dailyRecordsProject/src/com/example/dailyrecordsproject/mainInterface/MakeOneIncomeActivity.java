package com.example.dailyrecordsproject.mainInterface;

import java.util.*;

import com.example.dailyrecordsproject.R;
import com.example.dailyrecordsproject.RecordsMainActivity;
import com.example.dailyrecordsproject.utils.Income;
import com.example.dailyrecordsproject.utils.booksOpenHelper;

import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Im;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MakeOneIncomeActivity extends Activity implements OnClickListener {

	private String[] switches= {"Income","Expense"}; 
	private Spinner incomeSwitch, incomeTypes, incomeAcc;
	private List<String> types = new ArrayList<String>();
	private List<String> accounts = new ArrayList<String>();
	private ImageView goBack;
	private TextView addIncomeType, date;
	private Button saveButton, moreButton;
	private EditText etAmount, etRemark;
	private int month, day, currentLedger;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_make_one_income);
		SharedPreferences sp = getSharedPreferences("mysp", MODE_PRIVATE);
		currentLedger = sp.getInt("SPcurrentLedgerID", 1);
		etAmount = (EditText) findViewById(R.id.et_amount);
		etRemark = (EditText) findViewById(R.id.remark_income);
		saveButton = (Button) findViewById(R.id.save_button);
		moreButton = (Button) findViewById(R.id.more_record);
		moreButton.setOnClickListener(this);
		saveButton.setOnClickListener(this);
		incomeSwitch = (Spinner) findViewById(R.id.switch_income);
		incomeTypes = (Spinner) findViewById(R.id.sp_income);
		incomeAcc = (Spinner) findViewById(R.id.sp_account);
		types.add("salary");
		types.add("money award");
		types.add("investment");
		types.add("part-time job");
		types.add("interests");
		accounts.add("cash");
		accounts.add("credit card");
		accounts.add("monetary");
		accounts.add("virtual");
		accounts.add("investment");
		ArrayAdapter<String> aaTypes = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, types);
		ArrayAdapter<String> aaAcounts = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, accounts);
		incomeTypes.setAdapter(aaTypes);
		incomeAcc.setAdapter(aaAcounts);
		goBack = (ImageView) findViewById(R.id.go_back);
		goBack.setOnClickListener(this);
		date = (TextView) findViewById(R.id.date_income);
		
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH) + 1;
		day = cal.get(Calendar.DAY_OF_MONTH);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		
		date.setText(year + "-" + month + "-" + day + " " + hour + ":" + minute);
		
		addIncomeType = (TextView) findViewById(R.id.add_income_type);
		addIncomeType.setOnClickListener(this);
		incomeSwitch.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, switches));
		incomeSwitch.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long arg3) {

				if (position == 1){
					startActivity(new Intent(MakeOneIncomeActivity.this, MakeOneExpenseActivity.class));
				}
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.make_one_income, menu);
		return true;
	}

	

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.go_back:
			startActivity(new Intent(this, RecordsMainActivity.class));
			break;

		case R.id.add_income_type:
			startActivity(new Intent(this, AddIncomeTypeActivity.class));
			break;
			
		
		case R.id.more_record:
			etAmount.setText("");
			incomeAcc.setSelection(0);
			incomeTypes.setSelection(0);
			break;
			
		case R.id.save_button:
			double amount = Double.parseDouble(etAmount.getText().toString());
			String type = incomeTypes.getSelectedItem().toString();
			String account = incomeAcc.getSelectedItem().toString();
			String remark = etRemark.getText().toString();
			Income inc = new Income(amount, type, account);
			inc.setRemark(remark);
			inc.setDate(month + "." + day);
			inc.setLedgerid(currentLedger);
			if (new booksOpenHelper(this).addIncome(inc)){
				Toast.makeText(this, "added successfully! amount=" + amount + ", " + type + ", " + account, 1).show();
			}
			break;
		}
		
	}

}
