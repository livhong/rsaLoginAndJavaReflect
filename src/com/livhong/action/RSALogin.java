package com.livhong.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.PrivateKey;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.livhong.rsa.RSA;

/**
 * @author Livhong
 * @Date 2016年4月14日
 */
public class RSALogin extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		HttpSession session = request.getSession();
		PrivateKey priKey = (PrivateKey) session.getAttribute("privateKey");
		String result = RSA.decode(password, priKey);
		out.print("username : "+username);
		out.print("<br>");
		out.print("password : "+result);
		out.flush();
		out.close();
	}

}
