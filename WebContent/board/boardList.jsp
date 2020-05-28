<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link rel="stylesheet" type="text/css" href="css/shopping.css">
</head>
<body>
	<div id="wrap" align="center">
		<h1>게시글 리스트</h1>
		<table class="list">
			<tr>
				<td colspan="5" style="border: white; text-align: right"><a
					href="BoardServlet?command=board_write_form">게시글 등록</a></td>
			</tr>
			<tr>
				<th>번호</th>
				<th width="600">제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
			<c:forEach var="board" items="${boardList }">
				<tr class="record">
					<td>${board.num }</td>
					<td><a href="BoardServlet?command=board_view&num=${board.num}" style="font-weight:bold">
							${board.title } </a><span style="margin-left:5px">[${board.comment_num }]</span></td>
					<td>${board.name}</td>
					<td><fmt:formatDate value="${board.writedate }" /></td>
					<td>${board.readcount}</td>
				</tr>
			</c:forEach>
		</table>

		<% int pageTotal= (int)(request.getAttribute("pageTotal"));%>
		<% int pageNum = (int)request.getAttribute("pageNum"); %>
		<% int pageTotalNum = 0;
			for(int i =1;;i++){
				pageTotalNum = i;
				pageTotal = pageTotal-10;
				if(pageTotal<1) break;
			}
		%>
		<%for(int i =1;i<=pageTotalNum;i++){%>
	<a href="BoardServlet?command=board_list&pageNum=<%=i %>">
		<%if(pageNum == i){ %>
			[<%=i %>]
		<%}else{ %>
			<%=i %>
		<%} %>
	</a> 
		<%if(i<pageTotalNum){ %>
		,
	<%} %>
<%} %>
	</div>
</body>
</html>