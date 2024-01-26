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
            <li><a href="main.jsp">메인</a> </li>
            <li class="active"><a href="BoardServlet">게시판</a> </li>
        </ul>
    </div>
</nav>
<div class="col-lg-4"></div>
</div>
<div class="container">
    <div class="row">
        <form method="post" action="WriteActionServlet" enctype="application/x-www-form-urlencoded">
        <table class="table" style="text-align: left; border: 1px solid #245269">
                <thead>
                <tr>
                    <th colspan="2" style="background-color: #2aabd2; text-align: center;">게시판 글쓰기</th>
                </tr>
                </thead>
                <table class="table" style="text-align: left; border: 1px solid #999999">
                    <tr>
                        <td><strong>카테고리</strong></td>
                        <td><select id="category" name="category" required>
                            <option value=""  disabled selected>카테고리 선택</option>
                            <option value="1">JAVA</option>
                            <option value="2">JSP</option>
                            <option value="3">Servlet</option>
                            <option value="4">eBrain</option>
                        </select>
                        </td>
                    </tr>
                    <tr>
                        <td><strong>작성자</strong></td>
                        <td><input type="text" class="form-control" name="username" maxlength="5" required></td>
                    </tr>
                    <tr>
                        <td><strong>비밀번호</strong></td>
                        <td><input type="password" class="form-control" placeholder="비밀번호" name="password" maxlength=16 required>
                            <input type="password" class="form-control" placeholder="비밀번호 확인" name="confirm_password" maxlength=16 required>
                        </td>
                    </tr>
                    <tr>
                        <td><strong>제목</strong></td>
                        <td><input type="text" class="form-control" name="title" maxlength=100 required></td>
                    </tr>
                    <tr>
                        <td><strong>내용</strong></td>
                        <td><textarea class="form-control" name="content" maxlength="2000" style="height: 350px;" required></textarea></td>
                    </tr>
                    <tr>
                        <td><strong>파일 첨부</strong></td>
                        <td>
                            <input type="file" class="form-control" name="file1">
                            <input type="file" class="form-control" name="file2">
                            <input type="file" class="form-control" name="file3">
                        </td>
                    </tr>
                </table>
            </table>
            <input type="button" class="btn btn-default pull-left" onclick="cancelWrite()" value="취소">
            <input type="submit" class="btn btn-primary pull-right" value="저장">
        </form>
    </div>
</div>
<script>
    function cancelWrite() { // 취소 버튼 눌렀을 때(작성 취소)
        if (confirm('작성을 취소하시겠습니까?')) {
            window.location.href = 'BoardServlet'; // board.js로 이동(목록 페이지)
        }
    }
</script>

<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.js"></script>
</body>
</html>