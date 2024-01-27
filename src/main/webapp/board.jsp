<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dto.Board" %>
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
    // 커맨드에서 전달한 데이터 받아오기
    ArrayList<Board> list = (ArrayList<Board>) request.getAttribute("boardList");
%>
<nav class="navbar navbar-default">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed"
                data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
                aria-expand="false">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="main.jsp">JSP 게시판</a>
    </div>
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav">
            <li><a href="main.jsp">메인</a> </li>
            <li class="active"><a href="ControllerServlet?commandType=board">게시판</a></li>
        </ul>
    </div>
</nav>
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
                if(list != null) {
                for(int i=0; i < list.size(); i++) {
            %>
            <tr>
                <td><%= list.get(i).getCategoryName() %></td>
                <td><a href="ControllerServlet?commandType=view&boardId=<%= list.get(i).getBoardId() %>"><span style="text-decoration: underline;"><%= list.get(i).getTitle() %></span></a></td>
                <td><%= list.get(i).getUsername() %></td>
                <td><%= list.get(i).getViewCount() %></td>
                <td><%= list.get(i).getCreatedDate().substring(0, 16) %></td>
                <td><%= list.get(i).getUpdatedDate().substring(0, 16) %></td>
            </tr>
            <%
                    }
                }
            %>
            </tbody>
        </table>
        <a href="write.jsp" class="btn btn-primary pull-right">등록</a>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.js"></script>
</body>
</html>


