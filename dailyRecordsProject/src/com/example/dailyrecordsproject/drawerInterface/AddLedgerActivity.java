package com.example.dailyrecordsproject.drawerInterface;

import java.util.*;

import com.example.dailyrecordsproject.R;
import com.example.dailyrecordsproject.RecordsMainActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class AddLedgerActivity extends Activity implements OnClickListener {

	private View v1w;
	private TextView goBack;
	private ListView lv; 
	List<Map<String, Object>> data;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_ledger);
		lv = (ListView) findViewById(R.id.lv_addLedger);
		goBack = (TextView) findViewById(R.id.go_back);
		goBack.setOnClickListener(this);
		init();
		SimpleAdapter sa = new SimpleAdapter(this, data, R.layout.each_type_layout, 
				new String[]{"imageID", "first", "second"},new int[]{R.id.image_each,R.id.first_text,R.id.second_text});
		lv.setAdapter(sa);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				switch (position) {
				case 0:
					startActivity(new Intent(AddLedgerActivity.this, AddStandardActivity.class));
					break;

				case 1:
					startActivity(new Intent(AddLedgerActivity.this, AddStandardActivity.class));
					break;
					
				case 2:
					startActivity(new Intent(AddLedgerActivity.this, AddStandardActivity.class));
					break;
					
				case 3:
					startActivity(new Intent(AddLedgerActivity.this, AddStandardActivity.class));
					break;
					
				case 4:
					startActivity(new Intent(AddLedgerActivity.this, AddStandardActivity.class));
					break;
					
				case 5:
					startActivity(new Intent(AddLedgerActivity.this, AddStandardActivity.class));
					break;
					
				case 6:
					startActivity(new Intent(AddLedgerActivity.this, AddStandardActivity.class));
					break;
					
				case 7:
					startActivity(new Intent(AddLedgerActivity.this, AddStandardActivity.class));
					break;	
				}
			}
		});	
	}

	private void init() {

		data = new ArrayList<Map<String,Object>>();
		
		Map map1 = new HashMap<String, Object>();
		map1.put("imageID", R.drawable.acc_book_template_bzlc);
		map1.put("first", "Standard");
		map1.put("second", "covers most categories");
		
		Map map2 = new HashMap<String, Object>();
		map2.put("imageID", R.drawable.acc_book_template_syz);
		map2.put("first", "Business");
		map2.put("second", "records business activities");
		
		Map map3 = new HashMap<String, Object>();
		map3.put("imageID", R.drawable.acc_book_template_lyz);
		map3.put("first", "Travel");
		map3.put("second", "manages travel details");
		
		Map map4 = new HashMap<String, Object>();
		map4.put("imageID", R.drawable.acc_book_template_zxz);
		map4.put("first", "Decoration");
		map4.put("second", "helps you with decoration issues");
		
		Map map5 = new HashMap<String, Object>();
		map5.put("imageID", R.drawable.acc_book_template_jhz);
		map5.put("first", "Wedding");
		map5.put("second", "plans your wedding event");
		
		Map map6 = new HashMap<String, Object>();
		map6.put("imageID", R.drawable.acc_book_template_qcz);
		map6.put("first", "Car");
		map6.put("second", "cares for your car");
		
		Map map7 = new HashMap<String, Object>();
		map7.put("imageID", R.drawable.acc_book_template_bbz);
		map7.put("first", "Babies");
		map7.put("second", "money spent on newborns");
		
		Map map8 = new HashMap<String, Object>();
		map8.put("imageID", R.drawable.acc_book_template_clz);
		map8.put("first", "Biz Travel");
		map8.put("second", "must-have for professionals");
		
		data.add(map1);
		data.add(map2);
		data.add(map3);
		data.add(map4);
		data.add(map5);
		data.add(map6);
		data.add(map7);
		data.add(map8);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_ledger, menu);
		return true;
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.go_back:
			startActivity(new Intent(AddLedgerActivity.this, RecordsMainActivity.class));
			break;
		
		
		}
		
		
	}

}
