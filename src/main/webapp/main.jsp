<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width", initial-scale="1" >
    <link rel="stylesheet" href="css/bootstrap.css">
    <title>JSP 게시판</title>
</head>
<body>
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
            <li class="active"><a href="main.jsp">메인</a> </li>
            <li><a href="board.jsp">게시판</a> </li>
        </ul>
    </div>
</nav>

<div class="container">
    <div class="row">
        <h1 style="text-align: center;">게시판 메인</h1>
        <img src="./image/망곰_컴퓨터.jpeg" alt="망곰 사진">
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.js"></script>
</body>
</html>