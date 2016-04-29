/**
 * 
 */
package com.livhong.rsa;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.Cipher;

/**
 * @author Livhong
 *
 */
public class RSA {

	
	public static String decode(byte[] data, PrivateKey privateKey){
		
		String resultStr = null;
		
		try {
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			byte[] result = cipher.doFinal(data);
			resultStr = new String(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultStr;
	}
	
	public static String decode(String input, PrivateKey privateKey){
		
		String resultStr = null;
		
		try {
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			byte[] result = cipher.doFinal(s16tob(input));
			resultStr = new String(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultStr;
	}
	
	private static byte[] s16tob(String hex){
		byte[] data = new byte[hex.length()/2];
		for(int i = 0; i < hex.length(); i+=2){
			String number = hex.substring(i, i+2);
			data[i/2] = (byte)(int)Integer.valueOf(number, 16);
		}
		return data;
	}
	
//	public static String encode(String data){
//		byte[] dataToEncrypt = data.getBytes();
//		byte[] encryptedData = null;
//		String result = null;
//		try {
//			File file=new File("D:\\---F---\\Code\\myeclipse_workspace\\RSALogin\\public.key");
//			FileInputStream o = new FileInputStream(file);
//			ObjectInputStream os = new ObjectInputStream(o);
//			PublicKey publicKey = (PublicKey) os.readObject();
//			os.close();
//			o.close();
//			
//			Cipher cipher = Cipher.getInstance("RSA");
//			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
//			encryptedData = cipher.doFinal(dataToEncrypt);
//			result = Base64.encodeToString(encryptedData, Base64.DEFAULT);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}	
//		
//		return result;
//	}
	
}
