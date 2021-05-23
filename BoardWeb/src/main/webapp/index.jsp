<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 프로그램</title>
<style>
#container { width: 300px; margin: 0 auto; text-align: center;}
a { text-decoration: none;}
</style>
</head>
<body>
<div id="container">
	<h1>게시판 프로젝트</h1>
	<hr>
	<a href="login.do">로그인</a><br><br>
	<a href="getBoardList.do">글목록 바로가기</a><br><br>
	<a href="dataTransformJson.do">글목록 변환처리(JSON)</a><br><br>
	<a href="dataTransformXml.do">글목록 변환처리(XML)</a><br><br>
	<hr>
</div>
</body>
</html>