<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="comment.CommentDAO" %>
<%@ page import="comment.Comment" %>
<%@ page import="board.BoardDAO" %>
<%@ page import="java.io.PrintWriter" %>
<jsp:useBean id="newComment" class="comment.Comment" scope="page"/>
<jsp:setProperty name="newComment" property="boardId" param="boardId"/>
<jsp:setProperty name="newComment" property="commentContent" param="commentContent"/>
<jsp:setProperty name="newComment" property="commentDate"/>

<%
    int boardId = Integer.parseInt(request.getParameter("boardId"));
    String commentContent = request.getParameter("commentContent");

    CommentDAO commentDAO = new CommentDAO();
    int result = commentDAO.addComment(boardId, commentContent);

    if(result == 1) {
        // 댓글 추가 성공
        response.sendRedirect("view.jsp?boardId=" + boardId);
    } else {
        // 댓글 추가 실패
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter pageOut = response.getWriter();

        pageOut.println("<script>");
        pageOut.println("alert('댓글 추가에 실패했습니다.')");
        pageOut.println("history.go(-1);");
        pageOut.println("</script>");

        pageOut.flush();
    }
%>
