package com.livhong.fo;

import java.lang.reflect.Method;

/**
 * @author Livhong
 * @Date 2016年4月28日
 */
public class MethodFetcher {

	private static final int TO_UPPER_SPACING = 'A'-'a';
	
//	public static void main(String[] args) throws Exception {
//		User user = new User();
//		System.out.println(user.getClass()==User.class);
//		invokeMethod(user, "setId", new Object[]{1});
//		Class c = user.getClass();
//		Method m = c.getMethod("getId", null);
//		int i = (Integer) m.invoke(user, null);
//		System.out.println(i);
//		
//	}
	
	public static Object invokeMethod(Object owner, String methodName, Object[] args) throws Exception { 
		Class ownerClass = owner.getClass(); //也是从class开始的//得到参数的class数组,相当于得到参数列表的类型数组,来取决我们选择哪个函数。
		Class[] argsClass = new Class[args.length];
		for (int i = 0, j = args.length; i < j; i++) {
			Class tmp = argsClass[i] = args[i].getClass();
			if(tmp==Integer.class){
				argsClass[i] = int.class;
			}else if(tmp==Boolean.class){
				argsClass[i] = boolean.class;
			}else if(tmp==Double.class){
				argsClass[i] = double.class;
			}else if(tmp==Long.class){
				argsClass[i] = long.class;
			}
		}
		//根据函数名和函数类型来选择函数
		Method method = ownerClass.getMethod(methodName, argsClass); 
		return method.invoke(owner, args);//具体实例下,具体参数值下调用此函数
    }
	
	public static void invokeSetMethod(Object owner, String attrName, Object args){
		try {
			char[] s = attrName.toCharArray();
			s[0] = (char) (s[0]+TO_UPPER_SPACING);
			String methodName = "set"+new String(s);
			invokeMethod(owner, methodName, new Object[]{args});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
