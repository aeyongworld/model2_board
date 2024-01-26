package controller;

import dto.Board;
import dao.BoardDAO;
import dto.Comment;
import dao.CommentDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ViewServlet", value = "/ViewServlet")
public class ViewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        superProcess(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        superProcess(request, response);
    }

    protected void superProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int boardId = Integer.parseInt(request.getParameter("boardId"));

        // 게시글 정보 가져오기
        BoardDAO boardDAO = new BoardDAO();
        Board board = boardDAO.getBoard(boardId);

        if (board == null) {
            // 게시글이 없으면 오류 메시지 출력 후 목록 페이지로 이동
            PrintWriter script = response.getWriter();
            script.println("<script>");
            script.println("alert('유효하지 않은 글입니다.');");
            script.println("location.href = 'BoardServlet';");
            script.println("</script>");
            return;
        }

        // 댓글 목록 가져오기
        CommentDAO commentDAO = new CommentDAO();
        List<Comment> comments = commentDAO.getCommentsForBoard(boardId);

        // 데이터를 request에 담기
        request.setAttribute("board", board);
        request.setAttribute("comments", comments);

        // View로 포워드
        RequestDispatcher dispatcher = request.getRequestDispatcher("view.jsp");
        dispatcher.forward(request, response);
    }
}
