package com.livhong.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.livhong.fo.FOHandler;
import com.livhong.fo.XmlHandler;
import com.livhong.test.User;

/**
 * @author Livhong
 * @Date 2016年4月30日
 */
public class TestFormObject extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		XmlHandler xmlHandler = new XmlHandler();
		xmlHandler.load();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		User user = (User) fo.parseRequestToObject(request, User.class);
		System.out.println("id : "+user.getId());
		System.out.println("username : "+user.getUsername());
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}
	
	private FOHandler fo = new FOHandler() {
		
		@Override
		public String decodeSingleCipherParam(String cipher) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Map<String, Object> decodeMutipleCipherParam(String cipher) {
			// TODO Auto-generated method stub
			return null;
		}
	};

}
