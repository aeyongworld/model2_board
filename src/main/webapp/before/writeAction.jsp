<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.BoardDAO" %>
<%@ page import="java.io.PrintWriter" %>
<jsp:useBean id="board" class="dto.Board" scope="page"/>
<jsp:setProperty name="board" property="boardId"/>
<jsp:setProperty name="board" property="categoryId"/>
<jsp:setProperty name="board" property="username"/>
<jsp:setProperty name="board" property="password"/>
<jsp:setProperty name="board" property="title"/>
<jsp:setProperty name="board" property="content"/>
<jsp:setProperty name="board" property="createdDate"/>
<jsp:setProperty name="board" property="updatedDate"/>
<jsp:setProperty name="board" property="viewCount"/>

<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html"; charset="UTF-8">
  <title>JSP 게시판</title>
</head>
<body>

<%
  int categoryId = Integer.parseInt(request.getParameter("category"));
  String username = request.getParameter("username");
  String password = request.getParameter("password");
  String title = request.getParameter("title");
  String content = request.getParameter("content");

  BoardDAO boardDAO = new BoardDAO();
  int result = boardDAO.write(categoryId, username, password, title, content);

  if(result == -1) { // write 실패
    PrintWriter script = response.getWriter();
    script.println("<script>");
    script.println("alert('글쓰기에 실패했습니다.')");
    script.println("history.back()");
    script.println("</script>");
  } else {
    PrintWriter script = response.getWriter();
    script.println("<script>");
    script.println("location.href = 'board.jsp'");
    script.println("</script>");
  }
%>
</body>
</html>



