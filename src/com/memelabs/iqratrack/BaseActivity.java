package com.memelabs.iqratrack;

import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class BaseActivity extends Activity{
	protected Intent intent;
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.optionmenu, menu);
        return true;
    }
    
    public boolean onOptionsItemSelected(MenuItem item) {
    	IqraTrackApp app = updateTracker();
    	switch (item.getItemId()) {
        case R.id.mnabout:
        	intent = new Intent(getApplicationContext(), AboutAppAct.class);
			startActivity(intent);
        return true;
        case R.id.mnstartfrlast:
        	intent = new Intent(getApplicationContext(), SuraContentAct.class);
        	int lastsuraidx = app.getCurrentSura().getIdx();
	    	intent.putExtra("suraidx", lastsuraidx);
	    	startActivity(intent);
        return true;
        case R.id.mnsuraindex:
        	intent = new Intent(getApplicationContext(), SuraIndexAct.class);
			startActivity(intent);
		return true;
        case R.id.mnstatistic:
        	intent = new Intent(getApplicationContext(), SuraStatisticAct.class);
			startActivity(intent);
        return true;
        case R.id.mnsetting:
        	intent = new Intent(getApplicationContext(), ConfigurationsAct.class);
    		startActivity(intent);
        return true;
        default:
        return super.onOptionsItemSelected(item);
        }
    	
    }

	/**
	 * @return
	 */
	private IqraTrackApp updateTracker() {
		IqraTrackApp app = (IqraTrackApp) getApplicationContext();
   	 	app.getTracker().updateTracker(getApplicationContext());
		return app;
	}
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            updateTracker();
            
        }
        return super.onKeyDown(keyCode, event);
    }

}
