package com.uestc.myss.util;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5util {
   public static String md5(String string) {
	   return DigestUtils.md5Hex(string);
   }
   private static final String salt="1a2b3c4d";
   public static String inputPassFormPass(String inputpass) {
	   
	   String string=""+salt.charAt(0)+salt.charAt(2)+inputpass+salt.charAt(5)+salt.charAt(4);
	   return md5(string);   
   }   
 public static String formPassDBPass(String formpass,String salt) {
	 
	   String string=""+salt.charAt(0)+salt.charAt(2)+formpass+salt.charAt(5)+salt.charAt(4);
	   return md5(string);   
   }   
 public static String inputPassDBPass(String inputpass,String saltDB) {
	 
	   String formpass=inputPassFormPass(inputpass);
	   return formPassDBPass(formpass, saltDB);   
 }   
  
}
