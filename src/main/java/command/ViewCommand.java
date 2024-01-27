package command;

import dao.BoardDAO;
import dao.CommentDAO;
import dto.Board;
import dto.Comment;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ViewCommand implements Command{

    private int boardId; // boardId 멤버 변수

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int boardId = this.boardId;

        // 게시글 정보 가져오기
        BoardDAO boardDAO = new BoardDAO();
        Board board = boardDAO.getBoard(boardId);

        // 댓글 목록 가져오기
        CommentDAO commentDAO = new CommentDAO();
        List<Comment> comments = commentDAO.getCommentsForBoard(boardId);

        // 데이터를 request에 담기
        request.setAttribute("board", board);
        request.setAttribute("comments", comments);

        // View로 포워드
        RequestDispatcher dispatcher = request.getRequestDispatcher("view.jsp");
        dispatcher.forward(request, response);


        return "view.jsp";

    }

    @Override
    public void setParameters(HttpServletRequest request) {
        String boardIdParam = request.getParameter("boardId");
        if (boardIdParam != null) {
            this.boardId = Integer.parseInt(boardIdParam);
        }
    }

}
