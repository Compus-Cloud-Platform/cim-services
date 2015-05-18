package com.college.util;

import java.security.MessageDigest;

public class Md5Util {
	
	public final static String md5calc(String data) {
		String s = data == null ? "" : data;
		  
		try {
			
			int i; 
			
			byte[] strTemp = s.getBytes();
	
		    MessageDigest mdTemp = MessageDigest.getInstance("MD5");
	
		    mdTemp.update(strTemp);
	
		    byte[] md = mdTemp.digest();

		    StringBuffer buf = new StringBuffer(); 
		    
		    for (int offset = 0; offset < md.length; offset++){ 
		    	i = md[offset]; 
		    	
		    	if(i<0) i+= 256; 
		    	
		    	if(i<16) buf.append("0");
		    	
		    	buf.append(Integer.toHexString(i));
		    }
		    
		    //System.out.println("result: " + buf.toString());//32位的加密 

		    //System.out.println("result: " + buf.toString().substring(8,24));//16位的加密 
		    
		    return buf.toString();
		    
		  } catch (Exception e) {
			  return null;
		  }
	}
	
	public static void main(String[] args) {
		String content = "12312312312312";
	    System.err.println(content + "\n" + md5calc(content));
	}
}
