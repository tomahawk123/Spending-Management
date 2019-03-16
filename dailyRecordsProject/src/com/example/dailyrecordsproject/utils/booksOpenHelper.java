package com.example.dailyrecordsproject.utils;

import java.util.*;

import com.example.dailyrecordsproject.R;
import com.example.dailyrecordsproject.utils.*;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class booksOpenHelper extends SQLiteOpenHelper {

	
	
//	SQLiteDatabase mydb = this.getWritableDatabase().
	public final String INIT_LEDGERS = "create table ledgers(_id integer PRIMARY KEY, name text NOT NULL, imageid long NOT NULL, userid integer," +
			"FOREIGN KEY (userid) REFERENCES users(uid) ON DELETE CASCADE ON UPDATE CASCADE)";
	
	public final String ADD_DEFAULT = "insert into ledgers values (1,'default book', '"+R.drawable.accountbook_shortcut_travel + "' , 1)";
	
	public final String INIT_INCOME = "create table incomes (_id integer PRIMARY KEY, amountIncome double NOT NULL, typeIncome String NOT NULL, ledgeridIncome integer," +
			"accountIncome String NOT NULL, dateIncome String, remarkIncome String, FOREIGN KEY (ledgeridIncome) REFERENCES ledgers(_id) ON DELETE CASCADE ON UPDATE CASCADE)";
	
	public final String INIT_EXPENSE = "create table expenses (_id integer PRIMARY KEY, amountExpense double NOT NULL, typeExpense String NOT NULL, ledgeridExpense integer," +
			"item String, member String, accountExpense String, dateExpense String, remarkExpense String, FOREIGN KEY (ledgeridExpense) REFERENCES ledgers(_id) ON DELETE CASCADE ON UPDATE CASCADE)";
	
	public final String INIT_USER = "create table users (uid integer PRIMARY KEY, username String NOT NULL, userpwd String NOT NULL)";
	
	public final String INIT_BUDGET = "create table budgets (_id integer PRIMARY KEY, amountBudget double NOT NULL,ledgeridBudget integer," +
			"FOREIGN KEY (ledgeridBudget) REFERENCES ledgers(_id) ON DELETE CASCADE ON UPDATE CASCADE)";
	
	
	public booksOpenHelper(Context context){
		super(context, "books.db",null, 3);
	}
	
	public booksOpenHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}
	
	public List<Ledger> getAllLedgers(){
		List<Ledger> ledgers = new ArrayList<Ledger>();
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from ledgers", null);
		while (cursor.moveToNext()){
			Ledger led = new Ledger();
			led.setName(cursor.getString(1));
			led.setImageID(cursor.getLong(2));
			ledgers.add(led);
		}
		cursor.close();
		db.close();
		return ledgers;
	}
	
	public List<Expense> getAllExpenses(){
		
		List<Expense> expenses = new ArrayList<Expense>();
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from expenses", null);
		while (cursor.moveToNext()){
			Expense exp = new Expense();
			exp.setAmount(cursor.getDouble(1));
			exp.setType(cursor.getString(2));
			exp.setLedgerid(cursor.getInt(3));
			exp.setItem(cursor.getString(4));
			exp.setMember(cursor.getString(5));
			exp.setAccount(cursor.getString(6));
			exp.setMonthAndDay(cursor.getString(7));
			exp.setRemark(cursor.getString(8));
			expenses.add(exp);
		}
		cursor.close();
		db.close();
		return expenses;
	}
	
	public List<Income> getAllIncomes(){
		
		List<Income> incomes = new ArrayList<Income>();
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from incomes", null);
		while (cursor.moveToNext()){
			
			Income inc = new Income();
			inc.setAmount(cursor.getDouble(1));
			inc.setType(cursor.getString(2));
			inc.setLedgerid(cursor.getInt(3));
			inc.setAccount(cursor.getString(4));
			inc.setDate(cursor.getString(5));
			inc.setRemark(cursor.getString(6));
			
			incomes.add(inc);
		}
		cursor.close();
		db.close();
		return incomes;
		
	}
	
	public List<Budget> getAllBudgets(){
		List<Budget> list = new ArrayList<Budget>();
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from budgets", null);
		while (cursor.moveToNext()){
			
			Budget bud = new Budget();
			bud.setAmountBudget(cursor.getDouble(1));
			bud.setLedgerid(cursor.getInt(2));
			list.add(bud);
		}
		cursor.close();
		db.close();
		return list;
	}
	
	public boolean addBudget(Budget bu){
		ContentValues cv = new ContentValues();
		cv.put("ledgeridBudget", bu.getLedgerid());
		cv.put("amountBudget", bu.getAmountBudget());
		long rowID = this.getWritableDatabase().insert("budgets", null, cv);
		return rowID > 0;
	}
	
	public boolean addLedger(Ledger le){
		ContentValues cv = new ContentValues();
		cv.put("name", le.getName());
		cv.put("imageid", le.getImageID());
		long rowID = this.getWritableDatabase().insert("ledgers", null, cv);
		Log.e("rowID", rowID + "");
		return rowID > 0;
	}
	
	public boolean addIncome(Income inc){
		
		ContentValues cv = new ContentValues();
		cv.put("amountIncome", inc.getAmount());
		cv.put("typeIncome", inc.getType());
		cv.put("accountIncome", inc.getAccount());
		cv.put("ledgeridIncome", inc.getLedgerid());
		cv.put("remarkIncome", inc.getRemark());
		cv.put("dateIncome", inc.getDate());
		long rowID = this.getWritableDatabase().insert("incomes", null, cv);
		return rowID > 0;
	}
	
	public boolean addExpense(Expense ex){
		
		ContentValues cv = new ContentValues();
		cv.put("ledgeridExpense", ex.getLedgerid());
		cv.put("remarkExpense", ex.getRemark());
		cv.put("amountExpense", ex.getAmount());
		cv.put("typeExpense", ex.getType());
		cv.put("item", ex.getItem());
		cv.put("member", ex.getMember());
		cv.put("accountExpense", ex.getAccount());
		cv.put("dateExpense", ex.getMonthAndDay());
		long rowID = this.getWritableDatabase().insert("expenses", null, cv);
		return rowID > 0;
	}
	
	
	
	public int deleteLedger(Ledger le){
		String name = le.getName();
		String imageid = le.getImageID() + "";
		int affectedRowNum = this.getWritableDatabase().delete("ledgers", "name=?", new String[]{name});
		return affectedRowNum;
	}
	
	public int updateLedger(Ledger le, ContentValues cv){
		String name = le.getName();
		int affectedRowNum = this.getWritableDatabase().update("ledgers", cv, "name=?", new String[]{name});
		return affectedRowNum;
	}	

	@Override
	public void onCreate(SQLiteDatabase db) {

		Log.e("onCreate", "oncreate!!!");
		db.execSQL(INIT_LEDGERS);
		db.execSQL(ADD_DEFAULT);
		db.execSQL(INIT_EXPENSE);
		db.execSQL(INIT_INCOME);
		db.execSQL(INIT_USER);
		db.execSQL(INIT_BUDGET);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
