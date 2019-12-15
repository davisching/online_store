package pers.dc.ols.utils;

import org.apache.commons.codec.binary.Base64;

import java.security.MessageDigest;

public class MD5Utils {
	public static String doCrypt(String str) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Base64.encodeBase64String(md5.digest(str.getBytes()));
	}
}
