package com.livhong.fo;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author Livhong
 * @Date 2016年4月27日
 */
public class XmlHandler extends DefaultHandler{

	private FOMapping fo;
//	private HashMap<String, ?> curMap;
	
	private String className;
	
	boolean isInSecurityMaps = false;

	private HashMap<String, String> innerSecurityMapping;
	private String securityMappingsParam;
	
	@Override
	public void startElement(String uri, String localName, String qName, org.xml.sax.Attributes attributes) throws org.xml.sax.SAXException {
//		System.out.println(qName);
		if(qName.equals("bean")){
			fo = new FOMapping();
			if(attributes!=null){
				for(int i=0; i < attributes.getLength();i++){
					if(attributes.getQName(i).equals("id")){
						fo.idName = attributes.getValue(i);
					}else if(attributes.getQName(i).toLowerCase().equals("idtype")){
						fo.idType = this.parseType(attributes.getValue(i));
					}else if(attributes.getQName(i).toLowerCase().equals("class")){
						className = attributes.getValue(i);
					}
				}
			}
		}else{
			if(qName.equals("security-mappings")){
				innerSecurityMapping = new HashMap<String, String>();
				isInSecurityMaps = true;
				if(attributes!=null){
					for(int i=0; i < attributes.getLength();i++){
						if(attributes.getQName(i).toLowerCase().equals("param")){
							securityMappingsParam = attributes.getValue(i);
						}
					}
				}
				return;
			}
			String param = "";
			String attr = "";
			String type = "";
			
			if(attributes!=null){
				for(int i=0; i < attributes.getLength();i++){
					if(attributes.getQName(i).toLowerCase().equals("param")){
						param = attributes.getValue(i);
//						System.out.println(param);
					}else if(attributes.getQName(i).toLowerCase().equals("attr")){
						attr = attributes.getValue(i);
//						System.out.println(attr);
					}else if(attributes.getQName(i).toLowerCase().equals("type")){
						type = attributes.getValue(i);
//						System.out.println(type);
					}
//					fo.mapping.put(attributes.getQName(i), attributes.getValue(i));
//					System.out.println(attributes.getQName(i)+" "+attributes.getValue(i));
				}
				if(isInSecurityMaps&&qName.equals("mapping")){
					innerSecurityMapping.put(param, attr);
					fo.typeMapping.put(attr, this.parseType(type));
				}else{
					if(qName.equals("mapping")){
//						curMap = fo.mapping;
						fo.mapping.put(param, attr);
						fo.typeMapping.put(attr, this.parseType(type));
					}else if(qName.equals("security-mapping")){
//						curMap = fo.mapping;
						fo.secMapping.put(param, attr);
						fo.typeMapping.put(attr, this.parseType(type));
					}
				}
			}
			
		}
		super.startElement(uri, localName, qName, attributes);
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if(qName.equals("bean")){
			FOEnvironment.setHandlerMapping(className, fo);
		}
		if(qName.equals("security-mappings")){
			isInSecurityMaps = false;
			fo.secMappings.put(securityMappingsParam, innerSecurityMapping);
		}
	}
	
	public int parseType(String type){
		int result = FOMapping.TYPE_STRING;
		if(type.toLowerCase().equals("int")||type.toLowerCase().equals("integer")){
			result = FOMapping.TYPE_INT;
		}else if(type.toLowerCase().equals("double")){
			result = FOMapping.TYPE_DOUBLE;
		}else if(type.toLowerCase().equals("long")){
			result = FOMapping.TYPE_LONG;
		}else if(type.toLowerCase().equals("string")){
			result = FOMapping.TYPE_STRING;
		}
		return result;
	}
	
	public static void main(String[] args) throws Exception {
		 // 1.实例化SAXParserFactory对象 
        SAXParserFactory factory = SAXParserFactory.newInstance(); 
        // 2.创建解析器 
        SAXParser parser = factory.newSAXParser(); 
        // 3.获取需要解析的文档，生成解析器,最后解析文档 
        File f = new File("user.xml"); 
        XmlHandler dh = new XmlHandler(); 
        parser.parse(f, dh); 
	}
	
	public static final String DEFAULT_XML_PATH = "form-object.fo.xml";
	public void load(){
		load(null);
	}
	public void load(String xmlPath){
		if(xmlPath==null){
			xmlPath = DEFAULT_XML_PATH;
		}
		ClassLoader cd = XmlHandler.class.getClassLoader();
		InputStream input = cd.getResourceAsStream(xmlPath);
		 // 1.实例化SAXParserFactory对象 
        SAXParserFactory factory = SAXParserFactory.newInstance(); 
        // 2.创建解析器 
        SAXParser parser;
		try {
			parser = factory.newSAXParser();
			// 3.获取需要解析的文档，生成解析器,最后解析文档 
	        XmlHandler dh = new XmlHandler(); 
	        parser.parse(input, dh); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
}
