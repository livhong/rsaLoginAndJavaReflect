package com.livhong.fo;

import java.util.HashMap;

/**
 * @author Livhong
 * @Date 2016年4月28日
 */
public class FOEnvironment {

	private static HashMap<String, FOMapping> HANDLER_MAP = new HashMap<String, FOMapping>();
	
	public static void setHandlerMapping(String className, FOMapping fo){
		HANDLER_MAP.put(className, fo);
	}
	
	public static FOMapping getHandlerMapping(String className){
		java.util.Set<String> set = HANDLER_MAP.keySet();
		for(String tmp: set){
			System.out.println(tmp);
		}
		System.out.println(className);
		return HANDLER_MAP.get(className);
	}
	

	public static void main(String[] args) {
		
		XmlHandler xmlHandler = new XmlHandler();
		xmlHandler.load();
		FOMapping fo = FOEnvironment.getHandlerMapping(com.livhong.test.User.class.getName());
		fo.print();
	}
	
}
