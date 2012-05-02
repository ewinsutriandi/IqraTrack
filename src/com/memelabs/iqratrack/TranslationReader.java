package com.memelabs.iqratrack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.content.Context;


public class TranslationReader {
	
	private String translation_src_file = "id-indonesian-depag.png"; 
	public static String lang;
	public static String BISMILLAH = "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ";
	private String[] translationContents = new String[6236];
	private ArrayList<SuraEntity> suraDatas = new ArrayList<SuraEntity>();
	
	public String getTranslation_src_file() {
		return translation_src_file;
	}
	public void setTranslation_src_file(String translation_src_file) {
		this.translation_src_file = translation_src_file;
	}

	public TranslationReader(Context ctx) {
		super();
		try {
			translationToArray(ctx);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	private void translationToArray(Context ctx) throws IOException{
		InputStream in; 
		in = ctx.getResources().getAssets().open(translation_src_file);
		BufferedReader br = new BufferedReader(new InputStreamReader(in,"UTF-8"));       
		//while ((br.readLine()) != null) {     
			for (int j = 0; j < 6236; j++){
				translationContents[j] = br.readLine();
			}
			//break;
		//}
		in.close();
	}
	public SuraEntity getSuraData(int suraidx){
		return suraDatas.get(suraidx-1);
	}
	
	public String[] getSuraTranslations(SuraEntity sd){
		String[] translations = new String[sd.getAyas()];
		for (int i = 0; i < sd.getAyas(); i++) {
			translations[i] = translationContents[i+sd.getStart()];
		}
		return translations;
	}
	public ArrayList<String> getSuraTranslationsInArrayList(SuraEntity sd){
		ArrayList<String> translations = new ArrayList<String>();
		for (int i = 0; i < sd.getAyas(); i++) {	
			translations.add(translationContents[i+sd.getStart()]);
		}
		return translations;
	}
		

}
