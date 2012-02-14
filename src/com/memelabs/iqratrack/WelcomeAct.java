package com.memelabs.iqratrack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class WelcomeAct extends Activity {
	protected Button startFrLast,suraIndex,stats,setting,about;
	protected Intent intent;
	public void onCreate(Bundle savedInstanceState) {
	    	super.onCreate(savedInstanceState);
	    	setContentView(R.layout.startmenu);
	        addButtonListeners();
	    }

	private void addButtonListeners() {
		startFrLast = (Button) findViewById(R.id.startfrlastmn);
		startFrLast.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				IqraTrackApp app = (IqraTrackApp) getApplicationContext();
				intent = new Intent(getApplicationContext(), SuraContentAct.class);
				int lastsuraidx = app.getCurrentSura().getIdx();
		    	intent.putExtra("suraidx", lastsuraidx);
		    	startActivity(intent);
			}
		});
		suraIndex = (Button) findViewById(R.id.suraindexmn);
		suraIndex.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				intent = new Intent(getApplicationContext(), SuraIndexAct.class);
				startActivity(intent);
			}
		});
		stats = (Button) findViewById(R.id.statsmn);
		stats.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				intent = new Intent(getApplicationContext(), SuraStatisticAct.class);
				startActivity(intent);
			}
		});
		setting = (Button) findViewById(R.id.settingmn);
		setting.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				intent = new Intent(getApplicationContext(), ConfigurationsAct.class);
				startActivity(intent);
			}
		});
		about = (Button) findViewById(R.id.aboutmn);
		about.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				intent = new Intent(getApplicationContext(), AboutAppAct.class);
				startActivity(intent);
			}
		});
		
		
	}
	

}
