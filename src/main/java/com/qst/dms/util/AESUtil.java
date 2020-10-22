package com.qst.dms.util;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author 陌意随影
 TODO :
 *2019年12月21日  下午12:09:26
 */
public class AESUtil {
 /**
 *@param password 加密的密码 
 * @return  获取key
 */
private static Key getKey(String password){
	KeyGenerator keyGenerator;
	try {
		keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(128, new SecureRandom(password.getBytes()));
		SecretKey secretKey = keyGenerator.generateKey();
		byte[] keyBytes = secretKey.getEncoded();
		Key  key = new SecretKeySpec(keyBytes, "AES");
		return key;
	} catch (NoSuchAlgorithmException e) {
		e.printStackTrace();
	}
	return null;
	   
   }
/**
 * 将指定的字符串加密
 * *@param password 加密的密码 
 * @param code
 */
public static String   Encode(String code ,String password){
	try {
		 Key key = getKey(password);
		Cipher cipher =Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE,key);
			byte[] result = cipher.doFinal(code.getBytes());
			//加密后返回的字节数组是2进制的，需要将其转为16进制后的字符串后才能转化为String返回
			return parseByte2HexStr(result);
		}catch (Exception e) {
		e.printStackTrace();
	}
	
	return null;
	}

/**
 * 将指定的字符串解密
 * *@param password 加密的密码 
 * @param content
 * @return 返回解密后的字符串
 */
public static String Decode(String content ,String password){
	try {
		 Key key = getKey(password);
		Cipher cipher;
		cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE,key);
		//将要加密的字符串先从16进制转换为2进制的字节数组才能进行解压，否则会报错
		byte[] bytes =parseHexStr2Byte(content);
		byte[] result =cipher.doFinal(bytes);
		return new String(result);
	} catch (Exception e) {
 		e.printStackTrace();
	}
	return null;
}
/**将16进制转换为二进制
 * @param hexStr
 * @return  返回转化为二进制数组
 */
private static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
                return null;
        byte[] result = new byte[hexStr.length()/2];
        for (int i = 0;i< hexStr.length()/2; i++) {
                int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
                int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
                result[i] = (byte) (high * 16 + low);
        }
        return result;
}
/**将二进制转换成16进制
 * @param buf
 * @return 返回转换后的十六进制字符串
 */
private static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
                String hex = Integer.toHexString(buf[i] & 0xFF);
                if (hex.length() == 1) {
                        hex = '0' + hex;
                }
                sb.append(hex.toUpperCase());
        }
        return sb.toString();
}

}

