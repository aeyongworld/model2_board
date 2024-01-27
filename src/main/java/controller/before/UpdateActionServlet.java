package controller.before;

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

@WebServlet(name = "UpdateActionServlet", value = "/UpdateActionServlet")
public class UpdateActionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        superProcess(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        superProcess(request, response);
    }

    protected void superProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 한글 깨짐 방지
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        int boardId = 0;
        if (request.getParameter("boardId") != null) {
            boardId = Integer.parseInt(request.getParameter("boardId"));
        }
        if (boardId == 0) {
            redirectWithError(response, "유효하지 않은 글입니다.", "BoardServlet");
            return;
        }

        BoardDAO boardDAO = new BoardDAO();
        CommentDAO commentDAO = new CommentDAO();

        String boardUsername = request.getParameter("boardUsername");
        String boardTitle = request.getParameter("boardTitle");
        String boardContent = request.getParameter("boardContent");

        if (boardUsername == null || boardTitle == null || boardContent == null ||
                boardUsername.equals("") || boardTitle.equals("") || boardContent.equals("")) {
            redirectWithError(response, "입력이 안 된 사항이 있습니다.", "history.back()");
            return;
        }

        int result = boardDAO.update(boardId, boardUsername, boardTitle, boardContent);

        if (result == -1) {
            redirectWithError(response, "글 수정에 실패했습니다.", "history.back()");
        } else {
            // 수정이 완료된 board 객체를 request에 설정
            Board updatedBoard = boardDAO.getBoard(boardId); // 수정 후의 게시물 정보를 가져옴
            request.setAttribute("board", updatedBoard);

            // JSP로 포워딩
            RequestDispatcher dispatcher = request.getRequestDispatcher("view.jsp?boardId=" + boardId);

            // 댓글 데이터를 가져와서 설정
            List<Comment> comments = commentDAO.getCommentsForBoard(boardId);
            request.setAttribute("comments", comments);

            dispatcher.forward(request, response);
        }

    }

    private void redirectWithError(HttpServletResponse response, String message, String location) throws IOException {
        PrintWriter script = response.getWriter();
        script.println("<script>");
        script.println("alert('" + message + "')");
        script.println(location);
        script.println("</script>");
    }
}
