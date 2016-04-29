<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
    <script language="JavaScript" type="text/javascript" src="js/jsbn.js"></script>
    <script language="JavaScript" type="text/javascript" src="js/random.js"></script>
    <script language="JavaScript" type="text/javascript" src="js/rsa.js"></script>
    <script src="http://cdn.bootcss.com/jquery/2.2.1/jquery.js"></script>
	<script>
	
		var rsaKey = new RSAKey();
		rsaKey.setPublic('9c9edc8946aa87b4e6d7e2d545f089a403b36f32d6cae6aa8570cce99ac25c114633f146255c27ee0e7d3361daaf2ede34e6d0c5b19931a919de1cef85acacc82d08c3b6f83e30ece10e1cffff18c645ead4d638256a66b9162843b9596256ae14537f94769d756a1bbd1d66540ebf485b86939acd20744af6acc3632d672a7b175b1fec99580977b2b9ccd71e9aed5d8f1d47b3f19045248a01a4ec58405e21311366e69aa5e5d266c8446c00d870d509ee6f8c07f8810180e1d2b120af8993bf46bb98fe5995d281976d0e483efa320f6f58c4303c4022ffc28fd32268586abff1e60a86d1f06f0a5ad34568da7f00a9fd228818a556cb988f2fcf61e1bfad',
				'10001');
		var password = rsaKey.encrypt("woshiyigebing");
		
		$.ajax({
			url:"parse",
			data:{
				password:password
			},
			success:function(result){
				alert(result);
			}
		});		
	
	</script>
	
  </head>
  
  <body>
    This is my JSP page. <br>
  </body>
  
</html>
