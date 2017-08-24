<%@page import="java.util.List"%>
<%@page import="com.bigdata2017.emaillist.dao.EmaillistDao"%>
<%@page import="com.bigdata2017.emaillist.vo.EmaillistVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% request.setCharacterEncoding("UTF-8"); 
    	String firstName = request.getParameter("fn");
    	String lastName = request.getParameter("ln");
    	String email = request.getParameter("email");
    	
    	EmaillistVo vo = new EmaillistVo();
    	vo.setFirst_name(firstName);
    	vo.setLast_name(lastName);
    	vo.setEmail(email);
    	
    	new EmaillistDao().insert(vo);
    	
    	response.sendRedirect(request.getContextPath());
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>성공적으로 등록되었습니다.</h1>
	<a href="index.jsp">리스트 돌아가기</a>
	
</body>
</html>