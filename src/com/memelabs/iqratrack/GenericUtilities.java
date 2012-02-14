package com.memelabs.iqratrack;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GenericUtilities {
	public static String replace(String str, String pattern, String replace) {
	    Pattern p = Pattern.compile(pattern);
	    Matcher m = p.matcher(str);
	    StringBuffer sb = new StringBuffer();
        boolean result = m.find();
        // Loop through and create a new String 
        // with the replacements
        while(result) {
            m.appendReplacement(sb, replace);
            result = m.find();
        }
        // Add the last segment of input to 
        // the new String
        m.appendTail(sb);
        return sb.toString();
	}
}
