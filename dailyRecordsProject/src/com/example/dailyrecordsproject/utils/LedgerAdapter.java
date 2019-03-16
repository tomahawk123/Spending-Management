package com.example.dailyrecordsproject.utils;

import java.util.List;

import com.example.dailyrecordsproject.R;
import com.example.dailyrecordsproject.RecordsMainActivity;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.sax.StartElementListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LedgerAdapter extends BaseAdapter{
	public static int selectedID;
	List<Ledger> data;
	int layoutID;
	RecordsMainActivity rma;
	public LedgerAdapter(List<Ledger> data,  int layoutID,RecordsMainActivity rma){
		this.data = data;
		this.layoutID = layoutID;
		this.rma = rma;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Ledger led = data.get(position);
		View view = null;
		if (convertView != null){
			view = convertView;
		}
		else{
			LayoutInflater li = LayoutInflater.from(parent.getContext());
			view = li.inflate(layoutID, null);
		}
		if (layoutID == R.layout.image_text_layout){
			ImageView iv = (ImageView) view.findViewById(R.id.image_book);
			TextView tv = (TextView) view.findViewById(R.id.text_book);
//			Log.e("imageID", led.getImageID() + "");
//			Log.e("imageName", led.getName());
//			Log.e("name", led.getName());
			iv.setImageResource((int) led.getImageID());
			tv.setText(led.getName());
		}
		else if (layoutID == R.layout.edit_ledger_layout){
			ImageView iv = (ImageView) view.findViewById(R.id.image_book);
			TextView tv = (TextView) view.findViewById(R.id.text_book);
			iv.setImageResource((int) led.getImageID());
			tv.setText(led.getName());
			
			ImageView delete = (ImageView) view.findViewById(R.id.delete_ledger);
			ImageView update = (ImageView) view.findViewById(R.id.update_ledger);
			delete.setTag(position + "");
			update.setTag(position + "");
			delete.setOnClickListener(rma);
			update.setOnClickListener(rma);
			selectedID = position;
			}
		return view;
	}

	
	
}
