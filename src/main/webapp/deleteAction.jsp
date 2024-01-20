<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="board.BoardDAO" %>
<%@ page import="board.Board" %>
<%@ page import="java.io.PrintWriter" %>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html"; charset="UTF-8">
  <title>JSP 게시판</title>
</head>
<body>
<%
  int boardId = 0;
  if (request.getParameter("boardId") != null) {
    boardId = Integer.parseInt(request.getParameter("boardId"));
  }
  if (boardId == 0) {
    PrintWriter script = response.getWriter();
    script.println("<script>");
    script.println("alert('유효하지 않은 글입니다.')");
    script.println("location.href = 'board.jsp'");
    script.println("</script>");
  }

    BoardDAO boardDAO = new BoardDAO();
    int result = boardDAO.delete(boardId);
    if(result == -1) { // delete 실패
      PrintWriter script = response.getWriter();
      script.println("<script>");
      script.println("alert('글 삭제에 실패했습니다.')");
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



