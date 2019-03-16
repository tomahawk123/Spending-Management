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
import android.database.DataSetObserver;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class StreamActivity extends Activity implements OnClickListener {

	double totalExp = 0;
	double totalInc = 0;
	List<Map<String, Object>> data;
	private ListView lv;
	private TextView goBack, incomeAmount, expenseAmount, balanceAmount;
	List<Expense> listExpense, listExpSuited;
	List<Income> listIncome, listIncSuited;
	int currentID;
	private TextView amountIncome;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stream);
		View v = getLayoutInflater().inflate(R.layout.stream_expense_layout, null);
		amountIncome = (TextView) v.findViewById(R.id.amount);
		balanceAmount = (TextView) findViewById(R.id.balance_amount);
		goBack = (TextView) findViewById(R.id.go_back);
		incomeAmount = (TextView) findViewById(R.id.income_amount);
		expenseAmount = (TextView) findViewById(R.id.expense_amount);
		lv = (ListView) findViewById(R.id.lv_stream);
		listExpense = new booksOpenHelper(this).getAllExpenses();
		listExpSuited = new ArrayList<Expense>();
		listIncome = new booksOpenHelper(this).getAllIncomes();
		listIncSuited = new ArrayList<Income>();
		SharedPreferences sp = getSharedPreferences("mysp", MODE_PRIVATE);
		currentID = sp.getInt("SPcurrentLedgerID", 1);
		Toast.makeText(this, "it should be " + currentID, 1).show();
		for (int i = 0; i < listExpense.size(); i++){
			if (listExpense.get(i).getLedgerid() == currentID)
				listExpSuited.add(listExpense.get(i));
		}
		
		for (int i = 0; i < listIncome.size(); i++){
			if (listIncome.get(i).getLedgerid() == currentID)
				listIncSuited.add(listIncome.get(i));
		}
		initData();
		expenseAmount.setText(totalExp + "");
		incomeAmount.setText(totalInc + "");
		balanceAmount.setText((totalInc - totalExp) + "");
//		Toast.makeText(this, "data: " + data.size() + "," +data.toString(), 1).show();
		SimpleAdapter sa = new SimpleAdapter(this, data, R.layout.stream_expense_layout, new String[]{"date","type","amount"}, new int[]{R.id.date,R.id.name,R.id.amount});
		lv.setAdapter(sa);
		goBack.setText(Calendar.getInstance().get(Calendar.YEAR) + "");
		goBack.setOnClickListener(this);
	}

	private void initData() {
		
		data = new ArrayList<Map<String,Object>>();
		for (int i = 0; i < listExpSuited.size(); i++){
			Expense exp = listExpSuited.get(i);
			Map map = new HashMap<String, Object>();
			map.put("date", exp.getMonthAndDay());
			map.put("type", exp.getType());
			map.put("amount", exp.getAmount());
			totalExp += exp.getAmount();
			data.add(map);
		}
		
		for (int i = 0; i < listIncSuited.size(); i++){
			Income inc = listIncSuited.get(i);
			Map map = new HashMap<String, Object>();
			map.put("date", inc.getDate());
			map.put("type", inc.getType());
			map.put("amount", inc.getAmount());
			totalInc += inc.getAmount();
			data.add(map);
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.everyday, menu);
		return true;
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.go_back:
			startActivity(new Intent(this, RecordsMainActivity.class));
			break;

		case R.id.add_stream:
			startActivity(new Intent(this, AddExpenseTypeActivity.class));
			break;
		}
		
	}
	
	

}
