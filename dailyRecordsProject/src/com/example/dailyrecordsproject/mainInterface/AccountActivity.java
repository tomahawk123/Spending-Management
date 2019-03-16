package com.example.dailyrecordsproject.mainInterface;

import java.util.*;

import com.example.dailyrecordsproject.R;
import com.example.dailyrecordsproject.RecordsMainActivity;
import com.example.dailyrecordsproject.utils.Expense;
import com.example.dailyrecordsproject.utils.Income;
import com.example.dailyrecordsproject.utils.booksOpenHelper;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class AccountActivity extends Activity implements OnClickListener {

	private List<Map<String, Object>> data;
	private TextView goBack;
	private ListView lv;
	int currentLedger;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account);
		SharedPreferences sp = getSharedPreferences("mysp", MODE_PRIVATE);
		currentLedger = sp.getInt("SPcurrentLedgerID", 1);
		Toast.makeText(this, "current is " + currentLedger, 1).show();
		lv = (ListView) findViewById(R.id.lv_account_collection);
		initData();
		SimpleAdapter sa = new SimpleAdapter(this, data, R.layout.acount_records, new String[]{"head", "content"}, new int[]{R.id.record_head, R.id.record_amount});
		lv.setAdapter(sa);
		goBack = (TextView) findViewById(R.id.go_back);
		goBack.setOnClickListener(this);
	}

	private void initData() {
		int cash = 0,cred = 0,virt = 0,money = 0,invest = 0;
		String valueCash = "$";
		String valueCred = "$";
		String valueVirt = "$";
		String valueMoney= "$";
		String valueInvest = "$";
		data = new ArrayList<Map<String,Object>>();
		
		List<Expense> expenses = new booksOpenHelper(this).getAllExpenses();
		List<Income> incomes = new booksOpenHelper(this).getAllIncomes();
		for (int i = 0; i < expenses.size(); i++){
			Expense exp = expenses.get(i);
			
			if (exp.getLedgerid() == currentLedger){
				
				if (exp.getAccount().equals("cash"))
					cash += (exp.getAmount() * (-1));
				else if (exp.getAccount().equals("credit card"))
					cred += (exp.getAmount() *(-1));
				else if (exp.getAccount().equals("monetary"))
					money += (exp.getAmount() *(-1));
				else if (exp.getAccount().equals("virtual"))
					virt += (exp.getAmount() *(-1));
				else if (exp.getAccount().equals("investment"))
					invest += (exp.getAmount() *(-1));
			}
			
		}
		for (int i = 0; i < incomes.size(); i++){
			Income inc = incomes.get(i);
			
			if (inc.getLedgerid() == currentLedger){
			
				if (inc.getAccount().equals("cash"))
					cash += (inc.getAmount());
				else if (inc.getAccount().equals("credit card"))
					cred += (inc.getAmount());
				else if (inc.getAccount().equals("monetary"))
					money += (inc.getAmount());
				else if (inc.getAccount().equals("virtual"))
					virt += (inc.getAmount());
				else if (inc.getAccount().equals("investment"))
					invest += (inc.getAmount());
			}
		}
		
		Map map1 = new HashMap<String,Object>();
		map1.put("head", "Cash Account");
		map1.put("content", valueCash + cash);
		
		Map map2 = new HashMap<String,Object>();
		map2.put("head", "Credit Card");
		map2.put("content", valueCred + cred);
		
		Map map3 = new HashMap<String,Object>();
		map3.put("head", "Monetary Account");
		map3.put("content", valueMoney + money);
		
		Map map4 = new HashMap<String,Object>();
		map4.put("head", "Virtual Account");
		map4.put("content", valueVirt + virt);
		
		Map map5 = new HashMap<String,Object>();
		map5.put("head", "Inverstment Account");
		map5.put("content", valueInvest + invest);
		
		data.add(map1);
		data.add(map2);
		data.add(map3);
		data.add(map4);
		data.add(map5);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.account, menu);
		return true;
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.go_back:
			startActivity(new Intent(this, RecordsMainActivity.class));
			break;

		default:
			break;
		}
		
	}
	
	

}
