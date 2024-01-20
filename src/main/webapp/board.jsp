<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="board.BoardDAO"%>
<%@ page import="board.Board"%>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width", initial-scale="1" >
    <link rel="stylesheet" href="css/bootstrap.css">
    <title>JSP 게시판</title>
    <style type="text/css">
        a, a:hover {
            color: #000000;
        }
    </style>
</head>
<body>
    <%
        int pageNumber = 1;
        if (request.getParameter("pageNumber") != null) {
            pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        }
    %>
<nav class ="navbar navbar-default">
    <div class="navbar-header"> <!-- 헤더 부분 -->
        <button type="button" class="navbar-toggle collapsed"
                data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
                aria-expand="false">
            <span class ="icon-bar"></span>
            <span class ="icon-bar"></span>
            <span class ="icon-bar"></span>
        </button>
        <a class ="navbar-brand" href="main.jsp">JSP 게시판</a>
    </div>
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav">
            <li><a href="main.jsp">메인</a> </li>
            <li class="active"><a href="board.jsp">게시판</a> </li>
        </ul>
    </div>
</nav>
    <div class="col-lg-4"></div>
</div>
<div class="container">
    <div class="row">
        <table class="table table-striped" style="text-align: center; border: 1px solid #245269">
            <thead>
            <tr>
                <th style="background-color: #2aabd2; text-align: center;">카테고리</th>
                <th style="background-color: #2aabd2; text-align: center;">제목</th>
                <th style="background-color: #2aabd2; text-align: center;">작성자</th>
                <th style="background-color: #2aabd2; text-align: center;">조회수</th>
                <th style="background-color: #2aabd2; text-align: center;">등록 일시</th>
                <th style="background-color: #2aabd2; text-align: center;">수정 일시</th>
            </tr>
            </thead>
            <tbody>

                <%
                    BoardDAO boardDAO = new BoardDAO();
                    ArrayList<Board> list = boardDAO.getList(pageNumber);
                    for(int i=0; i < list.size(); i++) {
//                        System.out.println("DEBUG: board_id for index " + i + ": " + list.get(i).getBoardId());
                %>
                <tr>
                    <td><%= list.get(i).getCategoryName()%></td>
                    <td><a href="view.jsp?boardId=<%= list.get(i).getBoardId() %>"><span style="text-decoration: underline;"><%= list.get(i).getTitle()%></span></a></td>
                    <td><%= list.get(i).getUsername()%></td>
                    <td><%= list.get(i).getViewCount()%></td>
                    <td><%= list.get(i).getCreatedDate().substring(0, 4)+ "." + list.get(i).getCreatedDate().substring(5, 7) + "." + list.get(i).getCreatedDate().substring(8, 10) + " " + list.get(i).getCreatedDate().substring(11,16)%></td>
                    <td><%= list.get(i).getUpdatedDate().substring(0, 4)+ "." + list.get(i).getUpdatedDate().substring(5, 7) + "." + list.get(i).getUpdatedDate().substring(8, 10) + " " + list.get(i).getUpdatedDate().substring(11,16)%></td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
        <%
            if(pageNumber != 1) {
        %>
            <a href="board.jsp?pageNumber=<%=pageNumber - 1%>" class="btn btn-success btn-arrow-left">이전</a>
        <%
            } if(boardDAO.nextPage(pageNumber + 1)) {
        %>
        <a href="board.jsp?pageNumber=<%=pageNumber + 1%>" class="btn btn-success btn-arrow-right">다음</a>
        <%
            }
        %>
        <a href="write.jsp" class="btn btn-primary pull-right">등록</a>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.js"></script>
</body>
</html>