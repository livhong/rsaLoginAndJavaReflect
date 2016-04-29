/**
 * 
 */
package com.livhong.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.livhong.rsa.Base64;
import com.livhong.rsa.RSA;

/**
 * @author Livhong
 *
 */
public class ParseCryptogram extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String cryptogram = request.getParameter("password");
//		System.out.println("From js : "+cryptogram);
//		System.out.println("From java : "+RSA.encode("sdfsdf"));
//		byte[] data = Base64.decode(cryptogram, Base64.DEFAULT);
//		byte[] data = this.decode(cryptogram);
//		System.out.println("TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT"+data[data.length-1]);
//		System.out.println(data.length);
//		String result = RSA.decode(data);
//		System.out.println("js : "+result);
//		System.out.println("java : "+RSA.decode(Base64.decode(RSA.encode("sdfsdf"), Base64.DEFAULT)));
//		out.print(result);
		out.flush();
		out.close();
	}
	
	private byte[] decode(String hex){
		byte[] data = new byte[hex.length()/2];
		for(int i = 0; i < hex.length(); i+=2){
			String number = hex.substring(i, i+2);
			data[i/2] = (byte)(int)Integer.valueOf(number, 16);
		}
		return data;
	}

}
