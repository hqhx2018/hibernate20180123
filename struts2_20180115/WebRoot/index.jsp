<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<base href="<%=basePath%>">

</head>
<body>

<a href="dept/add.action">添加</a>
<a href="dept/del.action">删除</a>
<a href="dept/update.action">修改</a>
<a href="dept/findDeptByDeptno.action">按编号查询</a>
<a href="dept/list.action">查询所有部门</a>
<a href="user/add.action">添加用户</a>
</body>
</html>