package com.example.dailyrecordsproject.drawerInterface;

import java.util.List;

import com.example.dailyrecordsproject.R;
import com.example.dailyrecordsproject.RecordsMainActivity;
import com.example.dailyrecordsproject.utils.*;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

@SuppressLint("NewApi")
public class DrawerFragment extends Fragment implements OnItemClickListener{
	
	
	private static final int MODE_PRIVATE = 0;
	GridView gv_x ;
	//	public static List<Map<String, Object>> data;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		
		View v = inflater.inflate(R.layout.frag_ledger_, null);
//		deleteLedger = (ImageView)v.findViewById(R.id.delete_ledger);
//		updateLedger = (ImageView)v.findViewById(R.id.update_ledger);
//		deleteLedger.setOnClickListener(this);
//		updateLedger.setOnClickListener(this);
		
		gv_x = (GridView) v.findViewById(R.id.ledger_grid);
		
		RecordsMainActivity.gv = gv_x;
		//		initData();
		List<Ledger> ledgers_x = RecordsMainActivity.ledgers;
		ledgers_x = new booksOpenHelper(this.getActivity()).getAllLedgers();
		
//		View view = new LedgerAdapter(ledgers, R.layout.edit_ledger_layout).getView(position, convertView, parent);
		Log.e("ledgers", ledgers_x.toString());
		gv_x.setAdapter(new LedgerAdapter(ledgers_x, R.layout.image_text_layout, (RecordsMainActivity) this.getActivity()));
		gv_x.setOnItemClickListener(this);
		
//		if (isCheck){
//			
//			Log.e("status", "edit" + isCheck );
//			gv.setAdapter(new SimpleAdapter(this.getActivity(),data,R.layout.edit_ledger_layout,
//					new String[]{"imageID","name"}, new int[]{R.id.image_book, R.id.text_book}));
//		}
//		else{
//			Log.e("status", "normal" + isCheck);
//			gv.setAdapter(new SimpleAdapter(this.getActivity(), data, R.layout.image_text_layout, 
//					new String[]{"imageID","name"}, new int[]{R.id.image_book, R.id.text_book}));
//		}
//		gv.setAdapter(new SimpleAdapter(this.getActivity(),data,R.layout.every_ledger,new String[]{"imageID"},
//				new int[]{R.id.imageLedger}));
		return v;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		int currentLedgerID = arg2 + 1;
		SharedPreferences sp = getActivity().getSharedPreferences("mysp", MODE_PRIVATE);
		Editor et = sp.edit();
	
		et.putInt("SPcurrentLedgerID", currentLedgerID);
		et.commit();
		
		Intent intent = new Intent(this.getActivity(), RecordsMainActivity.class);
//		intent.putExtra("currentLedgerID", currentLedgerID);
		startActivity(intent);
//		Toast.makeText(this.getActivity(), "you checked number" + arg2, 1).show();
		Log.e("currentLedgerid", sp.getInt("currentLedgerID", -1) + "");
		ImageView iv = new ImageView(getActivity());
		iv.setImageResource(R.drawable.suite_selected_indicator1);
		LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//		getActivity().addContentView(iv, params);
		
		
	}


	/*private void initData() {
		data = new ArrayList<Map<String,Object>>();]
		List<Ledger> ledgers = new booksOpenHelper(this.getActivity()).getAllLedgers();
		for (int i = 0; i < ledgers.size(); i++){
			Map map = new HashMap<String, Object>();
			map.put("imageID", ledgers.get(i).getImageID());
			map.put("name", ledgers.get(i).getName());
			data.add(map);
		}
		
	}*/
}