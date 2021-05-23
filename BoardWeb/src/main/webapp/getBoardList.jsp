<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록 보기</title>
<style>
#container { width: 700px; margin: 0 auto; font-size: 0.8em;}
h1 { text-align: center;}
h3 { text-align: right;}
table { width: 700px; border: 1px solid black; border-collapse: collapse;}
th, td { border: 1px solid black;}
th { background: orange;}
.title { padding-left: 10px;}
.table1 th, .table1 td { border: none;}
input[type="submit"], input[type="button"] { border: 0; background: black; color: white;
width: 60px; height: 25px; border-radius: 5px; cursor: pointer;}
input[type="submit"]:hover, input[type="button"]:hover { background: orange; opacity: 0.5;
color: black; font-weight: bold;}
.btn { text-align: center;}
a { text-decoration: none; color: black;}
</style>
</head>
<body>
<div id="container">
<h1><s:message code="message.board.list.mainTitle"/></h1>
<h3>${memberName } <s:message code="message.board.list.welcomeMsg"/> <input type="button" value="<s:message code="message.board.list.button.logout"/>" onclick="location='logout.do'"></h3>
<form action="getBoardList.do" method="post">
<table class="table1">
	<tr>
		<td width="60%"></td>
		<td width="15%" align="center">
			<select name="searchCondition">
					<option value="title"><s:message code="message.board.list.search.condition.title"/></option>
					<option value="content"><s:message code="message.board.list.search.condition.content"/></option>
					<option value="writer"><s:message code="message.board.list.search.condition.writer"/></option>
			<%-- <c:forEach var="option" items="${conditionMap }">
					<option value=${option.value }>${option.key }</option>
				</c:forEach> --%>
			</select>	
		</td>
		<td width="15%" align="center"><input type="text" name="searchKeyword" size="10"></td>
		<td width="10%" align="center"><input type="submit" value="<s:message code="message.board.list.button.search"/>" onclick=""></td>
	</tr>
</table>
</form>

<table>
	<tr>
		<th width="10%"><s:message code="message.board.list.table.head.seq"/></th>
		<th width="50%"><s:message code="message.board.list.table.head.title"/></th>
		<th width="15%"><s:message code="message.board.list.table.head.writer"/></th>
		<th width="15%"><s:message code="message.board.list.table.head.regDate"/></th>
		<th width="10%"><s:message code="message.board.list.table.head.cnt"/></th>
	</tr>
	<c:forEach var="board" items="${boardList }">
	<tr>
		<td align="center">${board.seq }</td>
		<td class="title"><a href="getBoard.do?seq=${board.seq }">${board.title }</a></td>
		<td align="center">${board.writer }</td>
		<td align="center">${board.regdate }</td>
		<td align="center">${board.cnt }</td>
	</tr>
	</c:forEach>
</table>
<br>
<div class="btn"><input type="button" value="<s:message code="message.board.list.button.insert"/>" onclick="location='insertBoard.jsp'"></div>
</div>
</body>
</html>