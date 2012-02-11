package com.memelabs.iqratrack;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;

public class SuraIndexAct extends BaseActivity {

	protected EditText searchText;
	protected SQLiteDatabase db;
	protected Cursor cursor;
	protected SuraIndexArrayAdapter adapter;
	protected QuranReader qr;
	ListView list;

    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        IqraTrackApp app = (IqraTrackApp) getApplicationContext();
    	setContentView(R.layout.sura_list_main);
        searchText = (EditText) findViewById(R.id.searchText);
        qr = app.getQr();
        list = (ListView) findViewById(R.id.listsura);
        list.setOnItemClickListener(new OnItemClickListener() {
        	
        	public void onItemClick(AdapterView<?> arg0, View view, int position, long index) {
        	
        		onListItemClick(list, view, position, index);
            }
		});
        initSuraIndex();

    }


	public void onListItemClick(ListView parent, View view, int position, long id) {
    	Intent intent = new Intent(this, SuraContentAct.class);
    	intent.putExtra("suraidx", adapter.getItem(position).getIdx());
    	startActivity(intent);
    }
    
    public void search(View view) {

		adapter = new SuraIndexArrayAdapter(this, R.layout.sura_list_item,qr.getSuraDatas());
		ListView list = (ListView) findViewById(R.id.listsura);
		list.setClickable(true);
		list.setAdapter(adapter);
    }
    
    public void initSuraIndex() {

		adapter = new SuraIndexArrayAdapter(this, R.layout.sura_list_item,qr.getSuraDatas());
		list.setClickable(true);
		list.setAdapter(adapter);
    }
    
}