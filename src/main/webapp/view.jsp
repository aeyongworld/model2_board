<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dto.Board" %>
<%@ page import="dto.Comment" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width", initial-scale="1" >
    <link rel="stylesheet" href="css/bootstrap.css">
    <style>
        /* 스타일 추가 */
        td.highlight {
            border: 2px solid #000000; /* 테두리 색상 및 두께 지정 */
            padding: 8px; /* 셀 안쪽 여백 지정 */
        }
    </style>
    <title>JSP 게시판</title>
</head>
<body>
<%
    // 커맨드에서 전달한 데이터 받아오기
    Board board = (Board) request.getAttribute("board");
    List<Comment> comments = (List<Comment>) request.getAttribute("comments");
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
            <li class="active"><a href="ControllerServlet?commandType=board">게시판</a></li>
        </ul>
    </div>
</nav>
<div class="col-lg-4"></div>
<div class="container">
    <div class="row">
        <table border="0" cellpadding="3" cellspacing="0" width="100%">
            <tr>
                <th colspan="3" bgcolor="#2aabd2" height="25" align="left"><span style="font-size: 24px;"><strong>게시글 보기</strong></span></th>
            </tr>
            <tr>
                <td colspan="2">
                    <table border="0" cellpadding="3" cellspacing="0" width="100%">
                        <tr>
                            <td align="left" bgcolor="#FFFFFF" width="56%" style="height: 30px"><%=board.getUsername()%></td>
                            <td align="center" bgcolor="#DDDDDD" width="8%" style="height: 30px">등록일시</td>
                            <td align="center" bgcolor="#FFFFFF" width="14%" style="height: 30px"><%=board.getCreatedDate().substring(0, 4)+ "." + board.getCreatedDate().substring(5, 7) + "." + board.getCreatedDate().substring(8, 10) + " " + board.getCreatedDate().substring(11,16)%></td>
                            <td align="center" bgcolor="#DDDDDD" width="8%" style="height: 30px">수정일시</td>
                            <td align="center" bgcolor="#FFFFFF" width="14%" style="height: 30px"><%=board.getUpdatedDate().substring(0, 4)+ "." + board.getUpdatedDate().substring(5, 7) + "." + board.getUpdatedDate().substring(8, 10) + " " + board.getUpdatedDate().substring(11,16)%></td>
                            <td bgcolor="#FFFFE8"></td>
                        </tr>
                    </table>
                    <table>
                        <tr>
                            <td align="left" bgcolor="#DDDDDD" width="5%" style="height: 30px">[<%= board.getCategoryName() %>]  </td>
                            <td align="left" bgcolor="#FFFFFF" width="75%" style="height: 30px"><strong><%= board.getTitle() %></strong></td>
                            <td align="center" bgcolor="#FFFFFF" width="10%" style="height: 30px">조회수: </td>
                            <td align="center" bgcolor="#FFFFFF" width="5%" style="height: 30px"><%= board.getViewCount() %></td>
                        </tr>
                    </table>
            <br>
            <tr>
                <td colspan="3" class="highlight" style="min-height: 200px; text-align: left;"><%=board.getContent()%></td>
            </tr>

            <tr>
                <td align="left" bgcolor="#FFFFFF" width="10%">첨부파일값</td>
            </tr>
            </td>
            </tr>
        </table>
        <br>
        <hr>
        <h4>댓글</h4>
        <table class="table">
            <tbody>
            <% for (Comment comment : comments) { %>
            <tr bgcolor="#a9a9a9">
                <td><%= comment.getCommentDate().substring(0,4) + "." + comment.getCommentDate().substring(5,7) + "." + comment.getCommentDate().substring(8,10) + " " + comment.getCommentDate().substring(11,16)%>
                    <br>
                    <%= comment.getCommentContent() %>
                </td>
            </tr>
            </tr>
            <% } %>
            </tbody>
        </table>
        <form method="post" action="ControllerServlet?commandType=addComment">
            <input type="hidden" name="boardId" value="<%=board.getBoardId() %>">
            <table border="0" cellpadding="3" cellspacing="0" width="100%">
                <tr>
                    <td><textarea class="form-control" name="commentContent" style="width: 1000px" placeholder="댓글을 입력해 주세요." rows="2" required></textarea></td>
                    <td><input type="submit" class="btn btn-primary" value="등록"></td>
                </tr>
            </table>
        </form>
        <table align="center">
            <a href="BoardServlet" class="btn btn-default">목록</a>
            <a href="update.jsp?boardId=<%=board.getBoardId()%>" class="btn btn-primary">수정</a>
            <a href="ControllerServlet?commandType=delete&boardId=<%=board.getBoardId()%>" class="btn btn-danger">삭제</a>
        </table>
    </div>
</div>
    </script>
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="js/bootstrap.js"></script>
</body>
</html>
