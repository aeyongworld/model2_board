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


  if(request.getParameter("boardUsername") == null || request.getParameter("boardTitle") == null || request.getParameter("boardContent") == null ||
          request.getParameter("boardUsername").equals("") || request.getParameter("boardTitle").equals("") || request.getParameter("boardContent").equals("")) {
    PrintWriter script = response.getWriter();
    script.println("<script>");
    script.println("alert('입력이 안 된 사항이 있습니다.')");
    script.println("history.back()");
    script.println("</script>");
  } else {
    BoardDAO boardDAO = new BoardDAO();
    int result = boardDAO.update(boardId, request.getParameter("boardUsername"),request.getParameter("boardTitle"), request.getParameter("boardContent"));
    if(result == -1) { // update 실패
      PrintWriter script = response.getWriter();
      script.println("<script>");
      script.println("alert('글 수정에 실패했습니다.')");
      script.println("history.back()");
      script.println("</script>");
    } else {
      PrintWriter script = response.getWriter();
      script.println("<script>");
      script.println("location.href = 'board.jsp'");
      script.println("</script>");
    }
  }

%>
</body>
</html>



