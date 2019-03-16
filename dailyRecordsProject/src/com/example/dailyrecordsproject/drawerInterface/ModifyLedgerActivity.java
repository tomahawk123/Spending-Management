package com.example.dailyrecordsproject.drawerInterface;

import java.util.*;

import com.example.dailyrecordsproject.R;
import com.example.dailyrecordsproject.RecordsMainActivity;
import com.example.dailyrecordsproject.utils.Ledger;
import com.example.dailyrecordsproject.utils.booksOpenHelper;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class ModifyLedgerActivity extends Activity implements OnClickListener{
	
	List<Ledger> ledgers;
	public int[] ids = {R.drawable.accountbook_shortcut_benefit, R.drawable.accountbook_shortcut_car,R.drawable.accountbook_shortcut_common_1,
			R.drawable.accountbook_shortcut_fitment, R.drawable.accountbook_shortcut_merry, R.drawable.accountbook_shortcut_standard,
			R.drawable.accountbook_shortcut_travel};
	private ImageView iv;
	public int checkedID;
	private EditText et;
	private List<Map<String, Object>> data;
	private GridView gv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modify_ledger);
		ledgers = new booksOpenHelper(this).getAllLedgers();
		et = (EditText) findViewById(R.id.et_name);
		gv = (GridView) findViewById(R.id.cover_grid);
		
		iv = (ImageView) findViewById(R.id.check);
		iv.setOnClickListener(this);
		initData();
		SimpleAdapter sa = new SimpleAdapter(this, data, R.layout.every_ledger, new String[]{"imageID"}, new int[]{R.id.imageLedger});
		gv.setAdapter(sa);
		gv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
				checkedID = arg2;
				gv.getChildAt(arg2).setBackgroundColor(Color.CYAN);
			}
		});
	}
	
	private void initData() {
		
		data = new ArrayList<Map<String,Object>>();
		
		Map map1 = new HashMap<String, Object>();
		map1.put("imageID", R.drawable.accountbook_shortcut_benefit);
		data.add(map1);
		
		Map map2 = new HashMap<String, Object>();
		map2.put("imageID", R.drawable.accountbook_shortcut_car);
		data.add(map2);
		
		Map map3 = new HashMap<String, Object>();
		map3.put("imageID", R.drawable.accountbook_shortcut_common_1);
		data.add(map3);
		
		Map map4 = new HashMap<String, Object>();
		map4.put("imageID", R.drawable.accountbook_shortcut_fitment);
		data.add(map4);
		
		Map map5 = new HashMap<String, Object>();
		map5.put("imageID", R.drawable.accountbook_shortcut_merry);
		data.add(map5);
		
		Map map6 = new HashMap<String, Object>();
		map6.put("imageID", R.drawable.accountbook_shortcut_standard);
		data.add(map6);
		
		Map map7 = new HashMap<String, Object>();
		map7.put("imageID", R.drawable.accountbook_shortcut_travel);
		data.add(map7);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.check:
			Intent intent = new Intent(ModifyLedgerActivity.this, RecordsMainActivity.class);
			String name = et.getText().toString();
			int id = ids[checkedID];			
			int index = getIntent().getIntExtra("IDtoModify", -1);
			ContentValues cv = new ContentValues();
			cv.put("name", name);
			cv.put("imageid", id);
			new booksOpenHelper(this).updateLedger(ledgers.get(index),cv);
			Toast.makeText(this, "updated!", 1).show();
			startActivity(new Intent(ModifyLedgerActivity.this, RecordsMainActivity.class));
			break;

		default:
			break;
		}
	}
}
