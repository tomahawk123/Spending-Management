package com.example.dailyrecordsproject.mainInterface;

import java.util.List;

import com.example.dailyrecordsproject.R;
import com.example.dailyrecordsproject.RecordsMainActivity;
import com.example.dailyrecordsproject.utils.Budget;
import com.example.dailyrecordsproject.utils.Expense;
import com.example.dailyrecordsproject.utils.booksOpenHelper;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BudgetActivity extends Activity implements OnClickListener {

	private double budget = 0.00;
	private double amount;
	private int currentID;
	private TextView goBack, budgetAmount, usedAmount, availableAmount;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_budget);
		SharedPreferences sp = getSharedPreferences("mysp", MODE_PRIVATE);
		currentID = sp.getInt("SPcurrentLedgerID", 1);
		List<Budget> budgets = new booksOpenHelper(this).getAllBudgets();
		
		for (int i = 0; i < budgets.size(); i++){
			if (budgets.get(i).getLedgerid() == currentID){
				amount = budgets.get(i).getAmountBudget();
			}
		}
		Toast.makeText(this, currentID + ":" + amount, 1).show();
		budgetAmount = (TextView) findViewById(R.id.budget_amount);
		budget = amount;
		budgetAmount.setText("$" + budget);
		usedAmount = (TextView) findViewById(R.id.used_amount);
		availableAmount = (TextView) findViewById(R.id.available_amount);
		budgetAmount.setOnClickListener(this);
		goBack = (TextView) findViewById(R.id.go_back);
		goBack.setOnClickListener(this);
		List<Expense> expenses = new booksOpenHelper(this).getAllExpenses();
		double used = 0;
		double left = 0;
		for (int i = 0; i < expenses.size(); i++){
			used += (expenses.get(i).getAmount());
		}
		left = budget - used;
		usedAmount.setText("$" + used);
		availableAmount.setText("$" + left); 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.budget, menu);
		return true;
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.go_back:
			startActivity(new Intent(this, RecordsMainActivity.class));
			break;

		case R.id.budget_amount:
			final EditText et = new EditText(this);
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setView(et);
			builder.setTitle("enter budget");
			builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Budget bu = new Budget(Double.parseDouble(et.getText().toString()), currentID);
					new booksOpenHelper(getApplicationContext()).addBudget(bu);
					budget = Double.parseDouble(et.getText().toString());
					budgetAmount.setText("$" + budget);
				}
			});
			builder.show();
			break;
		}
		
	}

}
