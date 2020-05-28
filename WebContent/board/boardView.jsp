<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세보기</title>
<link rel="stylesheet" type="text/css" href="css/shopping.css">
<script type="text/javascript" src="script/board.js"></script>
</head>
<body>
	<div id="wrap" align="center">
		<h1>게시글 상세보기</h1>
		<table>
			<tr>
				<th>작성자</th>
				<td>${board.name}</td>
				<th>이메일</th>
				<td>${board.email}</td>
			</tr>
			<tr>
				<th>작성일</th>
				<td><fmt:formatDate value="${board.writedate}" /></td>
				<th>조회수</th>
				<td>${board.readcount }</td>
			</tr>
			<tr>
				<th>제목</th>
				<td colspan="3">${board.title }</td>
			</tr>
			<tr>
				<th>내용</th>
				<td colspan="3"><pre>${board.content }</pre></td>
			</tr>
		</table>
		<div style="margin-top:50px; margin-bottom:50px">
			<h3>댓글</h3>
			<form name="frm2" method="post" action="BoardServlet">
				<input type="hidden" name="command" value="comment_write"> <input type="hidden" name="commentNum" value="${board.num}">
			<div style="width:900px;">
				<div style="width:100px; float:left"><input type="text" placeholder="아이디" name="commentName" style="width:100px"/>
				<input type="password" placeholder="비밀번호" name="commentPass" style="width:100px"/></div>
				<input type="text" style="width:700px; height:50px; margin-bottom:10px;margin-left:10px;float:left" name="commentContent" placeholder="댓글 내용을 입력하세요."/>
				<input type="submit" value="댓글 등록" style="height:50px"/>
			</div>
			</form>
			<table>
				<tr><th style="background:skyblue" width=100>작성자</th><th style="background:skyblue" width=500>내용</th>
				<th style="background:skyblue"  width=25>수정</th><th style="background:skyblue" width=25>삭제</th></tr>
				<c:forEach var="comment" items="${comment_list}">
					<tr class="record">
						<td>${comment.name }</td>
						<td>${comment.content }</td>
						<td><input type="button" value="수정"></td>
						<td><input type="button" value="삭제"></td>
					</tr>
				</c:forEach>
			</table>
		</div>

		<br> <br> <input type="button" value="게시글 수정"
			onclick="open_win('BoardServlet?command=board_check_pass_form&num=${board.num}', 'update')">
		<input type="button" value="게시글 삭제"
			onclick="open_win('BoardServlet?command=board_check_pass_form&num=${board.num}', 'delete')">
		<input type="button" value="게시글 리스트"
			onclick="location.href='BoardServlet?command=board_list&pageNum=1'"> 
		<input type="button" value="게시글 등록"
			onclick="location.href='BoardServlet?command=board_write_form'">
	</div>
</body>
</html>