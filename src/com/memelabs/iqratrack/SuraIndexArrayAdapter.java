package com.memelabs.iqratrack;

import java.util.ArrayList;

import org.arabic.ArabicUtilities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class SuraIndexArrayAdapter extends ArrayAdapter<SuraEntity>{

	private ArrayList<SuraEntity> suras;
	private Context ctx;
	
	public SuraIndexArrayAdapter(Context context, 
			int textViewResourceId, ArrayList<SuraEntity> objects) {
		super(context, textViewResourceId, objects);
		this.suras = objects;
		this.ctx = context;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		View v = convertView;
		if (v == null){
			LayoutInflater vi = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.sura_list_item, null);
		}
		SuraEntity sura = suras.get(position);
		if (sura!=null){
			TextView tsname = (TextView) v.findViewById(R.id.name);
			TextView tstname = (TextView) v.findViewById(R.id.tname);
			tsname = ArabicUtilities.getArabicEnabledTextView(ctx, tsname);
			//TextView tsename = (TextView) v.findViewById(R.id.ename);
			tsname.setText(ArabicUtilities.reshapeSentence(sura.getName()));
			//tsname.setText(sura.getName());
			tstname.setText(sura.getTname()+" - "+sura.getEname());
			//tsename.setText(sura.getEname());
		}
		return v;
		
		
	}
}
