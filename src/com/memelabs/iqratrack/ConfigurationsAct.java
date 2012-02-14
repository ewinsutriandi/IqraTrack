package com.memelabs.iqratrack;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class ConfigurationsAct extends BaseActivity {
	protected Button reset,md5chk;
	protected Intent intent;
	public void onCreate(Bundle savedInstanceState) {
	    	super.onCreate(savedInstanceState);
	    	setContentView(R.layout.configurations);
	        addButtonListeners();
	    }
	private void addButtonListeners() {
		reset = (Button) findViewById(R.id.resetprogress);
		reset.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				createResetDialog();
			}
		});
		md5chk = (Button) findViewById(R.id.checkmd5qurandata);
		md5chk.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				createMD5CheckDialog();
			}
		});
	}
	
	protected void createMD5CheckDialog() {
		System.out.println("create MD5 Checksum dialog");
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Pilih Ya untuk melakukan pengecekan integritas file data Al Qur'an yang digunakan program ini")
		       .setCancelable(false)
		       .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		        	   IqraTrackApp app = (IqraTrackApp) getApplicationContext();
		        	   QuranReader qr = app.getQr();
		        	   boolean t = qr.checkMD5QuranData(getApplicationContext());
		        	   if (t){
		        		   Toast.makeText(getApplicationContext(), 
		        				   "File data Al Qur'an yang digunakan valid", Toast.LENGTH_SHORT).show();
		        	   } else {
		        		   Toast.makeText(getApplicationContext(), 
		        				   "FILE ANDA CORRUPT, install ulang aplikasi anda dan hubungi kami di meongmelong@gmail.com", Toast.LENGTH_LONG).show();
		        	   }
		           }
		       })
		       .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		                dialog.cancel();
		           }
		       });
		builder.create().show();
		
		
	}
	private void createResetDialog(){
		System.out.println("create reset dialog");
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Anda akan menghapus seluruh progress bacaan anda, yakin?")
		       .setCancelable(false)
		       .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		        	   IqraTrackApp app = (IqraTrackApp) getApplicationContext();
		        	   Tracker trk = app.getTracker();
		        	   trk.resetTracker(getApplicationContext());
		        	   Toast.makeText(getApplicationContext(), "Catatan progress anda telah direset ulang", Toast.LENGTH_SHORT).show();
		           }
		       })
		       .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		                dialog.cancel();
		           }
		       });
		builder.create().show();
			
	}
	

}
