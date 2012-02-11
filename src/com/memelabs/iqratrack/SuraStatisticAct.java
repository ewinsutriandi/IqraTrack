package com.memelabs.iqratrack;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class SuraStatisticAct extends BaseActivity{
	
	protected QuranReader qr;
	protected Tracker trk;
	protected ListView list;
	protected IqraTrackApp app;	
	protected SuraStatisticArrayAdapter adapter;
	//protected int suraidx;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
        setContentView(R.layout.sura_statistic_main);
        app = (IqraTrackApp) getApplicationContext();
        qr = app.getQr();
        trk = app.getTracker();
        list = (ListView) findViewById(R.id.listsurastat);
        list.setOnItemClickListener(new OnItemClickListener() {
        	
        	public void onItemClick(AdapterView<?> arg0, View view, int position, long index) {
        	
        		onListItemClick(list, view, position, index);
            }
		});
        initReadershipStat();
	}
	private void initReadershipStat() {
		adapter = new SuraStatisticArrayAdapter(this, R.layout.sura_statistic_item,trk,qr.getSuraDatas());
		list.setClickable(true);
		list.setAdapter(adapter);
		
	}
	protected void onListItemClick(ListView list2, View view, int position,
			long index) {
		Intent intent = new Intent(this, SuraContentAct.class);
    	intent.putExtra("suraidx", adapter.getItem(position).getIdx());
    	intent.putExtra("fromlastaya", true);
    	startActivity(intent);
		
	}

}
