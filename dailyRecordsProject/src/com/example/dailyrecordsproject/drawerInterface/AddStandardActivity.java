package com.example.dailyrecordsproject.drawerInterface;

import java.util.*;

import com.example.dailyrecordsproject.R;
import com.example.dailyrecordsproject.RecordsMainActivity;
import com.example.dailyrecordsproject.utils.*;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class AddStandardActivity extends Activity implements OnClickListener {

	
	private GridView gv;
	private EditText et;
	public int id;
	public int[] ids = {R.drawable.accountbook_shortcut_benefit, R.drawable.accountbook_shortcut_car,R.drawable.accountbook_shortcut_common_1,
			R.drawable.accountbook_shortcut_fitment, R.drawable.accountbook_shortcut_merry, R.drawable.accountbook_shortcut_standard,
			R.drawable.accountbook_shortcut_travel};
	private TextView goBack;
	private ImageView cover, checkSymbol;
	private RelativeLayout rela1;
	List<Map<String, Object>> data;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_standard);
		gv = (GridView) findViewById(R.id.cover_grid);
		et = (EditText) findViewById(R.id.et_name);
		initData();
		SimpleAdapter sa = new SimpleAdapter(this, data, R.layout.every_ledger, new String[]{"imageID"}, new int[]{R.id.imageLedger});
		gv.setAdapter(sa);
		gv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {

				id = position;
				gv.getChildAt(position).setBackgroundColor(Color.CYAN);
		//		((View)(gv.getSelectedItem())).setBackgroundColor(Color.CYAN);
			}
		});
		cover = (ImageView) findViewById(R.id.ledger_cover_image);
		checkSymbol = (ImageView) findViewById(R.id.check);
		goBack = (TextView) findViewById(R.id.go_back);
		checkSymbol.setOnClickListener(this);
		goBack.setOnClickListener(this);
		
		if (id == -1)
			cover.setImageResource(R.drawable.accountbook_shortcut_travel);
		else{
			cover.setImageResource(ids[id]);
			
		}
		rela1 = (RelativeLayout) findViewById(R.id.relative3);
		rela1.setOnClickListener(this);
		
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
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_standard, menu);
		return true;
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		

		case R.id.go_back:
			startActivity(new Intent(AddStandardActivity.this, AddLedgerActivity.class));
			/*Intent intent = new Intent(AddStandardActivity.this, RecordsMainActivity.class);
			intent.putExtra("clickID", id);
			startActivity(intent);*/
			break;
		case R.id.check:
			if (id != -1){
				Ledger le = new Ledger(ids[id]);
				le.setName(et.getText().toString());
				if (new booksOpenHelper(this).addLedger(le)){
					Toast.makeText(this, "added successfully! id=" + id + "text=" + et.getText().toString(), 1).show();
					startActivity(new Intent(AddStandardActivity.this, RecordsMainActivity.class));
				}
				
			}
			break;
		}
	}

}
