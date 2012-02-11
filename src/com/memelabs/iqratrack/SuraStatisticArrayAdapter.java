package com.memelabs.iqratrack;

import java.util.ArrayList;

import org.arabic.ArabicUtilities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class SuraStatisticArrayAdapter extends ArrayAdapter<SuraEntity>{
	private ArrayList<SuraEntity> suras;
	protected Tracker trk;
	private Context ctx;
	
	public SuraStatisticArrayAdapter(Context context, 
			int textViewResourceId, Tracker trk ,ArrayList<SuraEntity> suras) {
		super(context, textViewResourceId, suras);
		this.ctx = context;
		this.trk = trk;
		this.suras = suras;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		View v = convertView;
		if (v == null){
			LayoutInflater vi = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.sura_statistic_item, null);
		}
		SuraEntity sura = suras.get(position);
		if (sura!=null){
			int ayacnt = sura.getAyas();
			int ayaread = trk.getAyaReadCountBySura(sura);
			TextView tsnum = (TextView) v.findViewById(R.id.numstat);
			TextView tsname = (TextView) v.findViewById(R.id.suranamestat);
			TextView tstname = (TextView) v.findViewById(R.id.suratname);
			TextView tsayacnt = (TextView) v.findViewById(R.id.ayacount);
			//TextView tsayaread = (TextView) v.findViewById(R.id.ayaread);
			tsnum.setText(Integer.toString(position+1));
			tsname = ArabicUtilities.getArabicEnabledTextView(ctx, tsname);
			tsname.setText(ArabicUtilities.reshapeSentence(sura.getName()));
			tstname.setText(sura.getTname());
			Float pctprogress = 100 * (float) (ayaread/ayacnt);
			String pctprogressformatted = String.format("%.2g%n", pctprogress);
			tsayacnt.setText(pctprogressformatted + "%");
			//tsayaread.setText(Integer.toString(ayaread));
			
		}
		return v;
		
		
	}
	
}
