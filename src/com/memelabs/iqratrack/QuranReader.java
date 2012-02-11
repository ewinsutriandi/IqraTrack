package com.memelabs.iqratrack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.content.Context;
import android.util.Xml;


public class QuranReader {
	
	private static String QURAN_SRC_FILE = "quransimple.png"; 
	private static String QURAN_META_FILE = "qurandata.xml";
	private String[] quranContents = new String[6236];
	private ArrayList<SuraEntity> suraDatas = new ArrayList<SuraEntity>();
	
	public QuranReader(Context ctx) {
		super();
		try {
			initSuraDatas(ctx);
			quranCtnToArray(ctx);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	private void quranCtnToArray(Context ctx) throws IOException{
		InputStream in; 
		in = ctx.getResources().getAssets().open(QURAN_SRC_FILE);
		BufferedReader br = new BufferedReader(new InputStreamReader(in,"UTF-8"));       
		//while ((br.readLine()) != null) {     
			for (int j = 0; j < 6236; j++){
				quranContents[j] = br.readLine();
			}
			//break;
		//}
		in.close();
	}
	public SuraEntity getSuraData(int suraidx){
		return suraDatas.get(suraidx-1);
	}
	
	public String[] getSuraContents(int suraindex){
		SuraEntity sd = suraDatas.get(suraindex);
		String[] suraContents = new String[sd.getAyas()];
		for (int i = sd.getStart()-1; i <= sd.getAyas(); i++) {
			suraContents[i] = quranContents[i];
		}
		return suraContents;
	}
	public ArrayList<String> getSuraContentsInArrayList(int suraindex){
		SuraEntity sd = suraDatas.get(suraindex-1);
		ArrayList<String> suraContents = new ArrayList<String>();
		for (int i = sd.getStart(); i <= sd.getAyas()+sd.getStart()-1; i++) {
			
			suraContents.add(quranContents[i]);
		}
		return suraContents;
	}
	private void initSuraDatas(Context ctx) throws IOException, XmlPullParserException{
		XmlPullParser parser = Xml.newPullParser();
		InputStream in; 
		in = ctx.getResources().getAssets().open(QURAN_META_FILE);
		parser.setInput(in, null);
		int eventType = parser.getEventType();
	    SuraEntity currentSura = null;
	    boolean done = false;
	    while (eventType != XmlPullParser.END_DOCUMENT && !done){
	        String name = null;
	        switch (eventType){
	            case XmlPullParser.START_DOCUMENT:
	                break;
	            case XmlPullParser.START_TAG:
	                name = parser.getName();
	                if (name.equalsIgnoreCase("sura")){
	                    currentSura = new SuraEntity();
	                    currentSura.setIdx(Integer.parseInt(parser.getAttributeValue(0)));
	                    currentSura.setAyas(Integer.parseInt(parser.getAttributeValue(1)));
	                    currentSura.setStart(Integer.parseInt(parser.getAttributeValue(2)));
	                    currentSura.setName(parser.getAttributeValue(3));
	                    currentSura.setTname(parser.getAttributeValue(4));
	                    currentSura.setEname(parser.getAttributeValue(5));
	                    currentSura.setType(parser.getAttributeValue(6));
	                    currentSura.setOrder(Integer.parseInt(parser.getAttributeValue(7)));
	                    currentSura.setRukus(Integer.parseInt(parser.getAttributeValue(8)));	                    
	                }
	                break;
	            case XmlPullParser.END_TAG:
	                name = parser.getName();
	                if (name.equalsIgnoreCase("sura") && 
	                    currentSura != null){
	                    suraDatas.add(currentSura);
	                } else if (name.equalsIgnoreCase("suras")){
	                    done = true;
	                }
	                break;
	            }
	        eventType = parser.next();
	        }		
	}
	public ArrayList<SuraEntity> getSuraDatas() {
		return suraDatas;
	}

}
