package com.memelabs.iqratrack;

import android.app.Application;

public class IqraTrackApp extends Application{
	
	private QuranReader qr;
	private Tracker trk;
	private SuraEntity currentSura;
	
	
	public void setQr(QuranReader qr) {
		this.qr = qr;
	}

	public QuranReader getQr(){
		
		if (qr==null){
			qr = new QuranReader(this.getBaseContext());
		}
		
		return qr;
	}
	public Tracker getTracker(){
		
		if(trk==null){
			trk = new Tracker(getBaseContext());
		}
		
		return trk;
		
	}

	public void setCurrentSura(SuraEntity currentSura) {
		if (this.currentSura!=null){
			getTracker().updateTracker(getBaseContext());
		}
		this.currentSura = currentSura;
	}

	public SuraEntity getCurrentSura() {
		if (this.currentSura==null){
			try {
				this.currentSura = getQr().getSuraData(Integer.parseInt(getTracker().getAyasReadCount()[6236]));
			} catch (Exception e) {
				getTracker().resetTracker(getBaseContext());
				this.currentSura = getQr().getSuraData(Integer.parseInt(getTracker().getAyasReadCount()[6236]));
			}
			 
		} 
		return currentSura;
	}
	
	

}
