<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 폼</title>
<style>
#container { width: 300px; margin: 0 auto;}
.lang { text-align: center;}
a { text-decoration: none; color: navy;}
h1 { text-align: center;}
table { width: 300px; border: 1px solid black; border-collapse: collapse;}
th, td { border: 1px solid black;}
tr { height: 40px;}
th { background: orange;}
td { padding-left: 5px;}
.btn { text-align: center;}
input[type="submit"] { border: 0; background: black; color: white; width: 100px; height: 30px;
font-size: 1.1em; font-weight: bold; cursor: pointer;}
</style>
</head>
<body>
<div id="container">
<h1><s:message code="message.member.login.title"/></h1>
<div class="lang">
	<a href="login.do?lang=en"><s:message code="message.member.login.language.en"/></a>&nbsp;&nbsp;&nbsp;
	<a href="login.do?lang=ko"><s:message code="message.member.login.language.ko"/></a>
</div>
<hr>
<form action="login.do" method="post">
<table>
	<tr>
		<th width="30%"><s:message code="message.member.login.id"/></th>
		<td width="70%"><input type="text" name="id" size="25" maxlength="16" value="${memberVO.id }"></td>
	</tr>
	<tr>
		<th><s:message code="message.member.login.pwd"/></th>
		<td><input type="password" name="pwd" size="25" maxlength="20" value="${memberVO.pwd }"></td>
	</tr>
	<tr>
		<td colspan="2" class="btn"><input type="submit" value="<s:message code="message.member.login.loginBtn"/>"></td>
	</tr>
</table>
</form>
<hr>
</div>
</body>
</html>