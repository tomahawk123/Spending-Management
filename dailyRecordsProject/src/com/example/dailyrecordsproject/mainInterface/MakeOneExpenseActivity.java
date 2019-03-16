package com.example.dailyrecordsproject.mainInterface;

import java.io.Serializable;
import java.util.*;

import com.example.dailyrecordsproject.R;
import com.example.dailyrecordsproject.RecordsMainActivity;
import com.example.dailyrecordsproject.utils.Expense;
import com.example.dailyrecordsproject.utils.booksOpenHelper;

import android.os.Bundle;
import android.os.Parcelable;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MakeOneExpenseActivity extends Activity implements OnClickListener {

	
	
	public int receivedLedgerid, month, day; 
	private EditText etAmount, etItem, etRemark;
	private ImageView goBack;
	public Spinner expenseSwitch, expenseType, spMembers, accountType;
	List<String> switches = new ArrayList<String>();
	List<String> types = new ArrayList<String>();
	List<String> members = new ArrayList<String>();
	List<String> accounts = new ArrayList<String>();
	boolean firstTime = true;
	private TextView current, saveUp, addType;
	private Button saveDown, moreRecord;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_make_one_expense);
		SharedPreferences sp = getSharedPreferences("mysp", MODE_PRIVATE);
		receivedLedgerid = sp.getInt("SPcurrentLedgerID", 1);
		Toast.makeText(this, "this is " + receivedLedgerid, 1).show();
		
		moreRecord = (Button) findViewById(R.id.more_record_expense);
		etRemark = (EditText) findViewById(R.id.remark_of_item);
		saveDown = (Button) findViewById(R.id.save_down);
		saveUp = (TextView) findViewById(R.id.save_up);
		moreRecord.setOnClickListener(this);
		saveDown.setOnClickListener(this);
		saveUp.setOnClickListener(this);
		etAmount = (EditText) findViewById(R.id.et_exp_amount);
		etItem = (EditText) findViewById(R.id.et_item_of_expense);
		current = (TextView) findViewById(R.id.current_date);
		goBack = (ImageView) findViewById(R.id.go_back);
		addType = (TextView) findViewById(R.id.add_expense_type);
		addType.setOnClickListener(this);
		switches.add("Expense");
		switches.add("Income");
		members.add("Me");
		members.add("Spouse");
		members.add("Child");
		members.add("Colleague");
		members.add("Father");
		members.add("Mother");
		
		accounts.add("cash");
		accounts.add("credit card");
		accounts.add("monetary");
		accounts.add("virtual");
		accounts.add("investment");
		
		if (firstTime){
			types.add("transport");
			types.add("entertainment");
			types.add("food");
			types.add("hotel");
			types.add("education");
			firstTime = false;
		}
		if (getIntent().getStringExtra("newType") != null){
			String newType = getIntent().getStringExtra("newType");
			types.add(newType);
		}
		Log.e("list length", types.size() + "");
		expenseSwitch = (Spinner) findViewById(R.id.switch_expense);
		expenseType = (Spinner) findViewById(R.id.sp_expense_type);
		spMembers = (Spinner)	findViewById(R.id.sp_member);
		accountType = (Spinner) findViewById(R.id.account_expense);
		
		ArrayAdapter<String> aaAccount = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, accounts);
		ArrayAdapter<String> aaSwitch = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, switches);
		ArrayAdapter<String> aaType = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, types);
		ArrayAdapter<String> aaMember = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, members);
		
		expenseSwitch.setAdapter(aaSwitch);
		expenseSwitch.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long arg3) {

				if (position == 1){
					startActivity(new Intent(MakeOneExpenseActivity.this, MakeOneIncomeActivity.class));
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		expenseType.setAdapter(aaType);
		spMembers.setAdapter(aaMember);
		accountType.setAdapter(aaAccount);
		goBack.setOnClickListener(this);
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH) + 1;
		day = cal.get(Calendar.DAY_OF_MONTH);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		String curDate = year + "-" + month + "-" + day + " " + hour + ":" + minute;
		current.setText(curDate);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.make_one_rec, menu);
		return true;
	}

	

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.go_back:
			startActivity(new Intent(this, RecordsMainActivity.class));	
			break;

		case R.id.more_record_expense:
			etAmount.setText("");
			etItem.setText("");
			expenseType.setSelection(0);
			spMembers.setSelection(0);
			break;
			
		case R.id.add_expense_type:
			startActivity(new Intent(this, AddExpenseTypeActivity.class));
			break;
			
		case R.id.save_down:
			int ledgerID = 1;
			double amount = Double.parseDouble(etAmount.getText().toString());
			String finalItem = etItem.getText().toString();
			String finalType = expenseType.getSelectedItem().toString();
			String finalMember = spMembers.getSelectedItem().toString();
			String account = accountType.getSelectedItem().toString();
			String remark = etRemark.getText().toString();
			String monthDay = month + "." + day;
			Expense exp = new Expense(amount, finalType, finalItem, finalMember);
			if (receivedLedgerid != -1){
				ledgerID = receivedLedgerid;
			}
			exp.setLedgerid(ledgerID); 
			exp.setAccount(account);
			exp.setRemark(remark);
			exp.setMonthAndDay(monthDay);
			if (new booksOpenHelper(this).addExpense(exp)){
				Toast.makeText(this, "expense added! " + amount + ", " + 
						finalItem + "," + finalType + "," + finalMember, 1).show();
//				startActivity(new Intent(this, RecordsMainActivity.class));
			}
//save this expense to local database
			break;
		}
	}

	

}
