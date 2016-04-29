package com.livhong.rsa;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;

/**
 * @author Livhong
 * @Date 2016年4月14日
 */
public class RSAKey {

	KeyPairGenerator keyPairGenerator;
	PublicKey publicKey;
	PrivateKey privateKey;
	BigInteger module;
	BigInteger exponent;
	public RSAKey(){
		try {
			keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			keyPairGenerator.initialize(2048); //1024 used for normal securities
			KeyPair keyPair = keyPairGenerator.generateKeyPair();
			publicKey = keyPair.getPublic();
			privateKey = keyPair.getPrivate();
			KeyFactory keyFactory;
			keyFactory = KeyFactory.getInstance("RSA");
			RSAPublicKeySpec rsaPubKeySpec = keyFactory.getKeySpec(publicKey, RSAPublicKeySpec.class);
			module = rsaPubKeySpec.getModulus();
			exponent = rsaPubKeySpec.getPublicExponent();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public PrivateKey getPrivateKey(){
		return this.privateKey;
	}
	
	public String getPublicModule(){
		return module.toString(16);
	}
	
	public String getPublicExponent(){
		return exponent.toString(16);
	}
	
}

