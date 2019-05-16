package com.example.dailyrecordsproject;

import java.io.*;
import java.util.*;

import com.example.dailyrecordsproject.*;
import com.example.dailyrecordsproject.drawerInterface.*;
import com.example.dailyrecordsproject.mainInterface.*;
import com.example.dailyrecordsproject.utils.*;

import android.os.Bundle;
import android.os.Environment;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;



@SuppressLint({ "NewApi", "ValidFragment" })
public class RecordsMainActivity extends Activity implements OnClickListener{

	public boolean firstTime = true;
	public int currentLedger;
	public static boolean isCheck = false;
	public double budget = 0;
	public TextView month, year, fullDate, expenseToday, expenseWeek, expenseMonth, 
	dateWeek, dateMonth, incomeToday, incomeWeek, incomeMonth, incomeMonthly, expenseMonthly, budgetLeft;
//	SQLiteDatabase mydb;
	
	public static GridView gv;
	public static List<Ledger> ledgers;
	public booksOpenHelper booksoh;
	public int[] ids = {R.drawable.accountbook_shortcut_benefit, R.drawable.accountbook_shortcut_car,R.drawable.accountbook_shortcut_common_1,
			R.drawable.accountbook_shortcut_fitment, R.drawable.accountbook_shortcut_merry, R.drawable.accountbook_shortcut_standard,
			R.drawable.accountbook_shortcut_travel};
	FragmentManager fm;
	private DrawerFragment frag;
	private LinearLayout sync, add;
	private ImageView editLedger, loginPlace;
	private Button makeRecord;
	private ImageView deleteLedger;
	private ImageView updateLedger;
	private DrawerLayout drawerLayout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_records_main);
		booksoh = new booksOpenHelper(this);
		SharedPreferences sp = getSharedPreferences("mysp", MODE_PRIVATE);
		currentLedger = sp.getInt("SPcurrentLedgerID", 1);
		ledgers = new booksOpenHelper(this).getAllLedgers();
		ImageView iv = (ImageView) findViewById(R.id.ledger_up_right_icon);
		TextView tv = (TextView) findViewById(R.id.current_name);
		Toast.makeText(this, "current ledger is " + currentLedger,1).show();
		long currentImageid = ledgers.get(currentLedger - 1).getImageID();
		String currentName = ledgers.get(currentLedger - 1).getName();
		iv.setImageResource((int) currentImageid);
		tv.setText(currentName);
		Log.e("filedir", this.getDatabasePath("books.db").toString());
//		mydb = this.openOrCreateDatabase("books.db", MODE_PRIVATE, null);
		fm = getFragmentManager();
		int clickID = this.getIntent().getIntExtra("clickID", -1);
//		data =new ArrayList<Map<String,Object>>();
//		deleteLedger = (ImageView)findViewById(R.id.delete_ledger);
//		updateLedger = (ImageView)findViewById(R.id.update_ledger);
		drawerLayout = (DrawerLayout) findViewById(R.id.wholeLayout);
		editLedger = (ImageView) findViewById(R.id.edit_ledger);
		makeRecord = (Button) findViewById(R.id.make_record);
		loginPlace = (ImageView) findViewById(R.id.login_place);
		sync = (LinearLayout) findViewById(R.id.sync_button);
		add = (LinearLayout) findViewById(R.id.add_button);
		month = (TextView) findViewById(R.id.month);
		year = (TextView) findViewById(R.id.year);
		fullDate = (TextView) findViewById(R.id.date_full);
		
		incomeMonthly = (TextView) findViewById(R.id.income_monthly);
		expenseMonthly = (TextView) findViewById(R.id.spent_monthly);
		budgetLeft = (TextView) findViewById(R.id.budget_left);
		incomeToday = (TextView) findViewById(R.id.income_today);
		incomeWeek = (TextView) findViewById(R.id.income_week);
		incomeMonth = (TextView) findViewById(R.id.income_month);
		expenseToday = (TextView) findViewById(R.id.expense_today);
		expenseWeek = (TextView) findViewById(R.id.expense_week);
		expenseMonth = (TextView) findViewById(R.id.expense_month);
		dateMonth = (TextView) findViewById(R.id.date_month);
		dateWeek = (TextView) findViewById(R.id.date_week);
		setMonthAndYear();
		String currentMonthDay = (Calendar.getInstance().get(Calendar.MONTH)+1) + "." + Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		List<Expense> list = new booksOpenHelper(this).getAllExpenses();
		List<Income> listInc = new booksOpenHelper(this).getAllIncomes();
		List<Budget> listBud = new booksOpenHelper(this).getAllBudgets();
		int monthlyIncomeSum = 0;
		int monthlyExpenseSum = 0;
		
		int todayExpenseSum = 0;
		int weekExpenseSum = 0;
		int monthExpenseSum = 0;
		int todayIncomeSum = 0;
		int weekIncomeSum = 0;
		int monthIncomeSum = 0;
		int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
		for (Expense exp: list){
			if ((exp.getMonthAndDay().equals(currentMonthDay)) && (exp.getLedgerid() == currentLedger)){
				todayExpenseSum += (exp.getAmount());
//				Toast.makeText(this, "yes!", 1).show();
			}
			if ((month != 10) && (month != 11) && (month != 12)){
				if ((Integer.parseInt(exp.getMonthAndDay().substring(0, 1)) == month) && (exp.getLedgerid() == currentLedger))
					monthlyExpenseSum += exp.getAmount();
			}
			else{
				if ((Integer.parseInt(exp.getMonthAndDay().substring(0,2)) == month) && (exp.getLedgerid() == currentLedger))
					monthlyExpenseSum += exp.getAmount();
			}
		}
		
		for (Income inc : listInc){
			if ((inc.getDate().equals(currentMonthDay)) && (inc.getLedgerid() == currentLedger)){
				todayIncomeSum += (inc.getAmount());
				
			}
			if ((month != 10) && (month != 11) && (month != 12)){
				if ((Integer.parseInt(inc.getDate().substring(0, 1)) == month) && (inc.getLedgerid() == currentLedger))
					monthlyIncomeSum += inc.getAmount();
			}
			else{
				if ((Integer.parseInt(inc.getDate().substring(0,2)) == month) && (inc.getLedgerid() == currentLedger))
					monthlyIncomeSum += inc.getAmount();
			}
		}
		
		for (Budget bud: listBud){
			if (bud.getLedgerid() == currentLedger)
				budget = bud.getAmountBudget();
		}
		
		budgetLeft.setText("$ " + (budget - monthlyExpenseSum) + "0");
		
		incomeMonthly.setText("$ " + monthlyIncomeSum + ".00");
		expenseMonthly.setText("$ " + monthlyExpenseSum + ".00");
		expenseToday.setText("$ " + todayExpenseSum+ ".00");
		weekExpenseSum += (todayExpenseSum);
		expenseWeek.setText("$ " + weekExpenseSum+ ".00");
		monthExpenseSum += (todayExpenseSum);
		expenseMonth.setText("$ " + monthExpenseSum+ ".00");
		
		
		incomeToday.setText("$" + todayIncomeSum + ".00");
		weekIncomeSum += (todayIncomeSum);
		incomeWeek.setText("$" + todayIncomeSum + ".00");
		monthIncomeSum += (todayIncomeSum);
		incomeMonth.setText("$" + todayIncomeSum + ".00");
		frag = (DrawerFragment) fm.findFragmentById(R.id.ledgers_part);
		
		if (clickID != -1){
			Ledger led = new Ledger(ids[clickID]);
			ledgers.add(led);
		}
		makeRecord.setOnClickListener(this);
		editLedger.setOnClickListener(this);
		loginPlace.setOnClickListener(this);
		sync.setOnClickListener(this);
		add.setOnClickListener(this);
		
		
		if (firstTime){
			currentLedger = 1;
			firstTime = false;
		}
	}

	
	private void setMonthAndYear() {

		Calendar cal = Calendar.getInstance();
		
		int numDays = 0;
		int Year = cal.get(Calendar.YEAR);
		int Month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int dayofWeek = cal.get(Calendar.DAY_OF_WEEK);
		
		String begin = "";
		String end = "";
		
		switch (Month) {
		case 2:
			if ((Year % 4 ==0 && Year % 100 != 0)||(Year % 400 ==0)){
				numDays = 29;
				if (day - (dayofWeek - 1) <= 0){
					begin = "1." + (31-(dayofWeek - day -1));
					end = "2." + (7 - (dayofWeek - day));
				}
				else{
					begin = "2." + (day - (dayofWeek - 1));
					if ( ((day - dayofWeek) >= 23) && ((day - dayofWeek) <= 28) )
						end = "3." + (7 -dayofWeek-(29-day));
					else
						end = "2." + (day - (dayofWeek - 7));
				}
			}
			else {
				numDays = 28;
				if (day - (dayofWeek - 1) <= 0){
					begin = "1." + (31-(dayofWeek - day -1));
					end = "2." + (7 - (dayofWeek - day));
				}
				else{
					begin = "2." + (day - (dayofWeek - 1));
					if ( ((day - dayofWeek) >= 22) && ((day - dayofWeek) <= 27) )
						end = "3." + (7 -dayofWeek - (28-day));
					else
						end = "2." + (day - (dayofWeek - 7));
				}
			}
			break;

		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			numDays = 31;
			if (day - (dayofWeek - 1) <= 0){
				begin = ((Month == 1)?12:(Month-1))+"." + (31-(dayofWeek - day -1));
				end = Month + "." + (7 - (dayofWeek - day));
			}
			else{
				begin = Month + "." + (day - (dayofWeek - 1));
				if ( ((day - dayofWeek) >= 25) && ((day - dayofWeek) <= 30) )
					end = (Month+1) + "." + (7 -dayofWeek - (31-day));
				else
					end = Month + "." + (day - (dayofWeek - 7));
			}
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			numDays = 30;
			if (day - (dayofWeek - 1) <= 0){
				begin = (Month-1)+"." + (31-(dayofWeek - day -1));
				end = Month + "." + (7 - (dayofWeek - day));
			}
			else{
				begin = Month + "." + (day - (dayofWeek - 1));
				if ( ((day - dayofWeek) >= 24) && ((day - dayofWeek) <= 29) )
					end = (Month+1) + "." + (7 -dayofWeek - (30-day));
				else
					end = Month + "." + (day - (dayofWeek - 7));
			}
			break;
		
		}
		dateWeek.setText(begin + "-" + end);
		dateMonth.setText(Month + ".1-" + Month + "."+ numDays);
//		Toast.makeText(this, "today is " + dayofWeek + "-" + Calendar.SUNDAY + "-" + Calendar.MONDAY + "-" + cal.get(Calendar.WEEK_OF_MONTH), 1).show();
		String text = Year + "." + Month + "." + day;
		fullDate.setText(text);
		month.setText(Month + "");
		year.setText("/" + Year +  "");
	}

	public void stream(View v){
		Intent intentStream = new Intent(this, StreamActivity.class);
		intentStream.putExtra("currentID", currentLedger);
		startActivity(intentStream);
	}
	public void account(View v){
		startActivity(new Intent(this, AccountActivity.class));
	}
	
	public void budget(View v){
		startActivity(new Intent(this, BudgetActivity.class));
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.records_main, menu);
		return true;
	}


	@Override
	public void onClick(final View v) {

		
		switch (v.getId()) {
		case R.id.edit_ledger:
			if (isCheck == false){
				editLedger.setImageResource(R.drawable.nav_edit_btn_after);
				isCheck = true;
				
				gv.setAdapter(new LedgerAdapter(ledgers, R.layout.edit_ledger_layout, this));
			}
			else{
				editLedger.setImageResource(R.drawable.nav_edit_btn_before);
				isCheck = false;
				gv.setAdapter(new LedgerAdapter(ledgers, R.layout.image_text_layout, this));
			}
			break;

		case R.id.delete_ledger:
			Log.e("itemID", gv.getSelectedItemPosition() + "," + gv.getCheckedItemPosition());
			if (gv.getCheckedItemPosition() == 0){
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setTitle("Sorry");
				builder.setMessage("default ledger cannot be deleted!");
				builder.show();
			}
//			Log.e(v.getTag() + "","tag");
			else{
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setTitle("delete");
				builder.setMessage("Are you sure you want to delete this ledger?");
				
				builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
	
						int index = Integer.parseInt((String) v.getTag());
						new booksOpenHelper(RecordsMainActivity.this).deleteLedger(ledgers.get(index));
						String name = ledgers.get(index).getName();
						Toast.makeText(RecordsMainActivity.this, "ledger " + name + " has been deleted!", 1).show();
						
					// Can we let it remain at the drawer interface instead of jumping to the main page?	
						startActivity(new Intent(RecordsMainActivity.this, RecordsMainActivity.class));
					}
				});
				
				builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
					}
				});
				builder.show();
			}
			break;
			
		case R.id.update_ledger:
//			Log.e(v.getTag() + "","tag");
			
			int index = Integer.parseInt((String) v.getTag());
			Intent intent = new Intent(RecordsMainActivity.this, ModifyLedgerActivity.class);
			intent.putExtra("IDtoModify", index);
			startActivity(intent);
			
			
			
			
			
		
		// Can we let it remain at the drawer interface instead of jumping to the main page?	
//			startActivity(new Intent(RecordsMainActivity.this, RecordsMainActivity.class));
			break;
			
		
		
		case R.id.make_record:
			Intent intentExpense = new Intent(this, MakeOneExpenseActivity.class);
			intentExpense.putExtra("currentLedger", currentLedger);
			startActivity(intentExpense);
			break;
			
		case R.id.login_place:
			startActivity(new Intent(this, LoginActivity.class));
			break;
			
		case R.id.add_button:
			startActivity(new Intent(this, AddLedgerActivity.class));
			break;
			
		case R.id.sync_button:
			startActivity(new Intent(this, SyncActivity.class));
			break;
		}
		
		
	}

}


//default cannot be deleted
