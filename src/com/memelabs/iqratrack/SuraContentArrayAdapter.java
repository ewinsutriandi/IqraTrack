package com.memelabs.iqratrack;

import java.util.ArrayList;

import org.arabic.ArabicUtilities;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class SuraContentArrayAdapter extends ArrayAdapter<String>{

	private ArrayList<String> ayas;
	IqraTrackApp app;
	private Context ctx;
	SuraEntity sura;
	
	public SuraContentArrayAdapter(Context context, 
			int textViewResourceId, ArrayList<String> objects,SuraEntity sura) {
		super(context, textViewResourceId, objects);
		this.ayas = objects;
		this.ctx = context;
		this.sura = sura;
		app = (IqraTrackApp) ctx.getApplicationContext();
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		View v = convertView;
		if (v == null){
			LayoutInflater vi = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.sura_content_item, null);
		}
		String aya = ayas.get(position);
		if (aya!=null){
			
			TextView tAya = (TextView) v.findViewById(R.id.aya);
			TextView tAyaNum = (TextView) v.findViewById(R.id.ayanumber);
			TextView tAyaSt = (TextView) v.findViewById(R.id.ayareadstatus);
			tAya = ArabicUtilities.getArabicEnabledTextView(ctx, tAya);
			tAyaSt = ArabicUtilities.getArabicEnabledTextView(ctx, tAyaSt);
			String[] ayarray = aya.split ("\\|");
			//split bismillah in 1st aya, except for al fatihah and at-taubah
			if (position==0&&(sura.getIdx()!=1&&sura.getIdx()!=9)){
				ayarray[2].replace("بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ", "");
				//preg_replace('/^(([^]+ ){4})/u','')
			}
			String ayactn = ArabicUtilities.reshapeSentence((ayarray[2]));
			//String ayanum = ""; 
			String ayanum = ayarray[1];
			tAya.setText(ayactn+'\u200F');
			tAyaNum.setText(ayanum);
			tAya.setGravity(Gravity.RIGHT);
			int ayapos = app.getCurrentSura().getStart() + position;
			int ayaReadStatus = Integer.parseInt(app.getTracker().getAyasReadCount()[ayapos]);
			if (ayaReadStatus>0){
				tAyaSt.setText("\u2713");
				tAyaSt.setGravity(Gravity.BOTTOM);
			}
			else{
				tAyaSt.setText("");
			}
		}
		return v;
				
	}
}
