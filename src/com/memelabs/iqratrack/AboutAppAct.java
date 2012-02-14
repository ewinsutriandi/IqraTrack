package com.memelabs.iqratrack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.widget.TextView;

public class AboutAppAct extends BaseActivity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aboutapplication);
		PackageInfo pinfo;
		try {
			pinfo = getPackageManager().getPackageInfo(getPackageName(), 0);
			String versionName = pinfo.versionName;
			TextView appver = (TextView)findViewById(R.id.abouttitle);
			appver.setText("Iqra Track v:"+versionName);
		} catch (NameNotFoundException e) {
			
			e.printStackTrace();
		}
		
		
		TextView helloTxt = (TextView)findViewById(R.id.abouttext);
		helloTxt.setText(readTxt());
	}

	private String readTxt(){

		InputStream is = getResources().openRawResource(R.raw.about);
	    BufferedReader r = new BufferedReader(new InputStreamReader(is));
	    StringBuilder total = new StringBuilder();
	    String line;
	    try {
			while ((line = r.readLine()) != null) {
			    total.append(line +"\n");
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	    return total.toString();
	}

}
