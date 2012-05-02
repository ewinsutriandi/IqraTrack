package com.memelabs.iqratrack;

import java.util.ArrayList;

import org.arabic.ArabicUtilities;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class SuraContentAct extends BaseActivity {

	protected TextView employeeNameText;
	protected TextView titleText;
	protected ArrayList<String> ayas;
	protected String[] ayareadcount;
    protected SuraContentArrayAdapter adapter;
    protected int suraidx;
    protected int employeeId;
    protected int managerId;
    protected QuranReader qr;
	protected Tracker trk;
	protected TranslationReader tr;
    protected ListView list;
    protected IqraTrackApp app;
    protected SuraEntity sura;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sura_content_main);
        app = (IqraTrackApp) getApplicationContext();
        qr = app.getQr();
        trk = app.getTracker();
        tr = app.getTr();
        suraidx = getIntent().getIntExtra("suraidx", 0);
        app.setCurrentSura(qr.getSuraData(suraidx));
        ayas = qr.getSuraContentsInArrayList(suraidx);
        sura = app.getCurrentSura();
        displaySuraInfo();
        if (sura.getIdx()!=1&&sura.getIdx()!=9){
        	displayBismillah();
        }
        adapter = new SuraContentArrayAdapter(this, R.layout.sura_content_item,ayas,sura);
		list = (ListView) findViewById(R.id.listaya);
		list.setAdapter(adapter);
        list.setOnItemClickListener(new OnItemClickListener() {
        	
        	public void onItemClick(AdapterView<?> arg0, View view, int position, long index) {
        	
        		onListItemClick(list, view, position, index);
            }
		});
        /*list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> av, View v, int pos, long id) {
                System.out.println(pos+" "+id);
            	return onLongListItemClick(v,pos,id);
            }
        });*/

        scrollToLastRead();

    }


	protected void displaySuraInfo(){
    	TextView tx = (TextView) findViewById(R.id.surameta);
    	tx.setGravity(Gravity.CENTER);
    	String surainfo;
    	surainfo = sura.getEname()+" ("+sura.getTname()+") - "+sura.getAyas()+" ayat";
        tx.setText(surainfo);
    }

	private void displayBismillah() {
		TextView tx = (TextView) findViewById(R.id.surabismillah);
		tx = ArabicUtilities.getArabicEnabledTextView(getApplicationContext(), tx);
        String bism = ArabicUtilities.reshapeSentence(QuranReader.BISMILLAH); 
		tx.setText(bism);
	}

	protected void onListItemClick(ListView list, View view, int position,
			long index) {
		int ayapos = app.getCurrentSura().getStart()+position;
		//trk.getAyasReadCount()[ayapos]= trk.getAyasReadCount()[ayapos] + 1;
		trk.increaseReadCount(ayapos);
		TextView tAyaSt = (TextView) view.findViewById(R.id.ayareadstatus);
		tAyaSt.setText("\u2713");
		String translation = tr.getSuraTranslations(app.getCurrentSura())[position];
		System.out.println(translation);
		Toast.makeText(this.getApplicationContext(), translation, Toast.LENGTH_LONG).show();
	}
	
	protected boolean onLongListItemClick(View v, int position, long id) {
		//int ayapos = app.getCurrentSura().getStart()+position;
		String translation = tr.getSuraTranslations(app.getCurrentSura())[position];
		System.out.println(translation);
		Toast.makeText(this.getApplicationContext(), translation, Toast.LENGTH_LONG).show();
		   //TextView trnstxt = (TextView) v.findViewById(R.id.transtext);
		   //trnstxt.setText(translation);
		   //PopupWindow m_pw = new PopupWindow( layout,  350,  250,  true);
		   //m_pw.showAtLocation(layout, Gravity.CENTER, 0, 0);
		return true;
	}
	
    protected void scrollToLastRead(){
    	
		for (int i=0;i<=ayas.size();i++) {
			int ayapos = app.getCurrentSura().getStart()+i;
			int ayaReadStatus = Integer.parseInt(app.getTracker().getAyasReadCount()[ayapos]);
			if (ayaReadStatus==0){
				switch (i) {
				case 0:
					list.setSelection(i);
					break;
				default:
					list.setSelection(i-1);
					break;
				}
				break;
			}
		}
    }
}