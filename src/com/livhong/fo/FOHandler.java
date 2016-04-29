package com.livhong.fo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * @author Livhong
 * @Date 2016年4月28日
 */
public abstract class FOHandler {

	private FOMapping fo;
	private Set<String> mappingSet;
	private Set<String> secMappingSet;
	private Set<String> secMappingsSet;
	
	public Object parseRequestToObject(HttpServletRequest request, Class className){
		//notice if the request is multipart.
		Object own = null;
		fo = FOEnvironment.getHandlerMapping(className.getName());
		if(fo==null){
			System.out.println("Maybe this class is not configed into xml file.");
		}
		System.out.println(fo.mapping);
		System.out.println(fo.secMapping);
		System.out.println(fo.secMappings);
		mappingSet = fo.mapping.keySet();
		secMappingSet = fo.secMapping.keySet();
		secMappingsSet = fo.secMappings.keySet();
		try {
			own = className.newInstance();
			if(ServletFileUpload.isMultipartContent(request)){
		        DiskFileItemFactory factory = new DiskFileItemFactory();  
		        factory.setSizeThreshold(1024*1024) ;  
		        ServletFileUpload upload = new ServletFileUpload(factory);  
		        List<FileItem> list = (List<FileItem>)upload.parseRequest(request);
		        for(FileItem item : list){
		        	String param = item.getFieldName();
		        	if(item.isFormField())  
	                {
	                    String value = item.getString();
	                    this.setAttr(own, param, value);
	                }
		        }
			}else{
				for(String param: mappingSet){
					String value = request.getParameter(param);
					if(value==null||value.equals("")){
						continue;
					}
					String attr = fo.mapping.get(param);
					MethodFetcher.invokeSetMethod(own, attr, this.parseObject(attr, value));
				}
				for(String param: secMappingSet){
					String value = request.getParameter(param);
					if(value==null||value.equals("")){
						continue;
					}
					String attr = fo.secMapping.get(param);
					String realValue = this.decodeSingleCipherParam(value);
					MethodFetcher.invokeSetMethod(own, attr, this.parseObject(attr, realValue));
				}
				for(String param: secMappingsSet){
					String value = request.getParameter(param);
					if(value==null||value.equals("")){
						continue;
					}
					HashMap<String, String> attrs = fo.secMappings.get(param);
					Set<String> params = attrs.keySet();
					Map<String, Object> realValues = this.decodeMutipleCipherParam(value);
					for(String par:params){
						String attr = attrs.get(par);
						Object realValue = realValues.get(attr);
						MethodFetcher.invokeSetMethod(own, attr, this.parseObject(attr, (String)realValue));
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return own;
	}
	
	private void setAttr(Object own, String param, String value){
		if(mappingSet.contains(param)){
			String attr = fo.mapping.get(param);
			MethodFetcher.invokeSetMethod(own, attr, this.parseObject(attr, value));
		}else if(secMappingSet.contains(param)){
			String attr = fo.secMapping.get(param);
			String realValue = this.decodeSingleCipherParam(value);
			MethodFetcher.invokeSetMethod(own, attr, this.parseObject(attr, realValue));
		}else if(secMappingsSet.contains(param)){
			HashMap<String, String> attrs = fo.secMappings.get(param);
			Set<String> params = attrs.keySet();
			Map<String, Object> realValues = this.decodeMutipleCipherParam(value);
			for(String par:params){
				String attr = attrs.get(par);
				Object realValue = realValues.get(attr);
				MethodFetcher.invokeSetMethod(own, attr, this.parseObject(attr, (String)realValue));
			}
		}
	}
	
	private Object parseObject(String attr, String realValue){
		Object result = null;
		switch(fo.typeMapping.get(attr)){
		case FOMapping.TYPE_DOUBLE:
			result = Double.parseDouble(realValue);
			break;
		case FOMapping.TYPE_INT:
			result = Integer.parseInt(realValue);
			break;
		case FOMapping.TYPE_LONG:
			result = Long.parseLong(realValue);
			break;
		case FOMapping.TYPE_STRING:
			result = realValue;
		}
		return result;
	}
	
	protected abstract String decodeSingleCipherParam(String cipher);
	
	protected abstract Map<String, Object> decodeMutipleCipherParam(String cipher);
	
}
