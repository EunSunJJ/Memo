package com.sunny.memo.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtils {

	// md5 암호화 기능
	// 암호화 할 문자열을 전달 받고 암호화 된 (16진수)문자열을 돌려준다.
	
	// 오로지 메소드만 호출하는거면
	// static
	public static String md5(String message) {
		
		String resultString = "";
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			
			byte[] bytes = message.getBytes();
			md.update(bytes);
			
			byte[] digest = md.digest();
			for (int i = 0; i < digest.length; i++) {
				// 비트연산
				resultString += Integer.toHexString(digest[i] & 0xff);
			}
			
		} catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
			return null; // 혹시나 암호화가 실행이 안된 경우
		}
		
		return resultString;
	}
}
