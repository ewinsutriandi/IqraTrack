package com.memelabs.iqratrack;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class BaseActivity extends Activity{
	protected Intent intent;
    public boolean onCreateOptionsMenu(Menu menu) {
   	 
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.optionmenu, menu);
        return true;
    }
    
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.mnabout:
        	Toast.makeText(getApplicationContext(), "", 5);
        	//startActivity(new Intent(this, About.class));
        return true;
        case R.id.mnstartfrlast:
        	intent = new Intent(getApplicationContext(), SuraContentAct.class);
			int lastayaidx=1;
			int lastsuraidx=1;
	    	intent.putExtra("suraidx", lastsuraidx);
	    	intent.putExtra("ayaidx", lastayaidx);
	    	startActivity(intent);
        return true;
        case R.id.mnsuraindex:
        	intent = new Intent(getApplicationContext(), SuraIndexAct.class);
			startActivity(intent);
        case R.id.mnstatistic:
        	intent = new Intent(getApplicationContext(), SuraStatisticAct.class);
			startActivity(intent);
        return true;
        default:
        return super.onOptionsItemSelected(item);
        }
    }

}
