<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Login</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <script language="JavaScript" type="text/javascript" src="js/jsbn.js"></script>
  <script language="JavaScript" type="text/javascript" src="js/random.js"></script>
  <script language="JavaScript" type="text/javascript" src="js/rsa.js"></script>
  <script src="http://cdn.bootcss.com/jquery/2.2.1/jquery.js"></script>
  
  <script>
  function getCookie(name)
  {
	  var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
	  if(arr=document.cookie.match(reg))
	  return unescape(arr[2]);
	  else
	  return null;
  }
  
  function mysubmit(){
	  var n = getCookie('LOCMO');
	  var e = getCookie('LOCEX');
	  var rsa = new RSAKey();
	  rsa.setPublic(n,e);
	  var password = rsa.encrypt($('#password').val());
	  $('#password').val(password);
	  $('#form').submit();
  }
  
  </script>
  
  <body>
    <form id='form' action='loginAction' method='GET'>
		<label for='username'>Username : </label>
		<input id='username' type=text name=username>
		<label for='password'>Password : </label>
		<input id='password' type=password name=password>
		<button type=button onclick='mysubmit()'>submit</button>
	</form>
  </body>
</html>
