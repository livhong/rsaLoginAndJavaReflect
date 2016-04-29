package com.livhong.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.PrivateKey;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.livhong.rsa.RSAKey;

/**
 * @author Livhong
 * @Date 2016年4月14日
 */
public class Login extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		RSAKey rsaKey = new RSAKey();
		String module = rsaKey.getPublicModule();
		String exponent = rsaKey.getPublicExponent();
		PrivateKey priKey = rsaKey.getPrivateKey();
		HttpSession session = request.getSession();
		session.setAttribute("privateKey", priKey);
		Cookie mCookie = new Cookie("LOCMO",module);
		Cookie eCookie = new Cookie("LOCEX",exponent);
		mCookie.setMaxAge(24*60*60);
		eCookie.setMaxAge(24*60*60);
		response.addCookie(mCookie);
		response.addCookie(eCookie);
		request.getRequestDispatcher("login.jsp").forward(request,response);
	}

}
