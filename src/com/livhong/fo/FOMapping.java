package com.livhong.fo;

import java.util.HashMap;
import java.util.Set;

/**
 * @author Livhong
 * @Date 2016年4月28日
 */
public class FOMapping {

	HashMap<String, String> mapping = new HashMap<String, String>();
	HashMap<String, String> secMapping = new HashMap<String, String>();
	HashMap<String, HashMap<String, String>> secMappings = new HashMap<String, HashMap<String, String>>();

	public static final int TYPE_INT = 0;
	public static final int TYPE_DOUBLE = 1;
	public static final int TYPE_LONG = 2;
	public static final int TYPE_STRING = 3;
	
	HashMap<String, Integer> typeMapping = new HashMap<String, Integer>();
	
	String idName;
	int idType;
	
	public Set<String> getFormParams(){
		Set<String> set = mapping.keySet();
		set.addAll(secMapping.keySet());
		Set<String> tmps = secMappings.keySet();
		for(String tmp: tmps){
			HashMap<String, String> hash = secMappings.get(tmp);
			set.addAll(hash.keySet());
		}
		return set;
	}
	
	public void print(){
		StringBuffer sb = new StringBuffer();
		sb.append("idAttr : "+idName+" "+this.getType(idType)+"\n");
		sb.append("[PlainParam] : \n");
		Set<String> params = mapping.keySet();
		for(String param: params){
//			System.out.println(mapping.get(param));
//			System.out.println(typeMapping.get(param));
			sb.append("[Param]"+param+" [attr]"+mapping.get(param)+" [type]"+this.getType(typeMapping.get(mapping.get(param)))+"\n");
		}
		sb.append("[SecurityParam] : \n");
		params = secMapping.keySet();
		for(String param: params){
			sb.append("[Param]"+param+" [attr]"+secMapping.get(param)+" [type]"+this.getType(typeMapping.get(secMapping.get(param)))+"\n");
		}
		sb.append("[SecurityParams] : \n");
		params = secMappings.keySet();
		for(String param: params){
			sb.append("[OutterParam] : "+param+"\n");
			HashMap<String, String> tmp = secMappings.get(param);
			params = tmp.keySet();
			for(String iparam: params){
				sb.append("[Param]"+iparam+" [attr]"+tmp.get(iparam)+" [type]"+this.getType(typeMapping.get(tmp.get(iparam)))+"\n");
			}
		}
		System.out.println(sb.toString());
	}
	
	private String getType(int type){
		switch(type){
		case TYPE_INT:return "int";
		case TYPE_DOUBLE:return "double";
		case TYPE_LONG:return "long";
		case TYPE_STRING:return "string";
		}
		return null;
	}
	
}
