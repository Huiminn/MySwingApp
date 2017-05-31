package com.huimin.swingApp.encrypteUtils;

public class MD5Algorithm {
	
	public String md5Encryption(byte[] source){
		String s = null;
		char hexDigits[] = { 
		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
				'e', 'f' };
		try {
			java.security.MessageDigest md = java.security.MessageDigest
					.getInstance("MD5");
			md.update(source);
			byte tmp[] = md.digest(); 
			
			char str[] = new char[tmp.length * 2]; 
			
			int k = 0; 
			for (int i = 0; i < tmp.length; i++) { 
				
				byte byte0 = tmp[i]; 
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				
				str[k++] = hexDigits[byte0 & 0xf]; 
			}
			s = new String(str); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}
	
}
