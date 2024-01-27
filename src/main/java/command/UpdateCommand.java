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
import java.io.PrintWriter;
import java.util.List;

public class UpdateCommand implements Command{

    private int boardId; // boardId 멤버 변수

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        boardId = 0;
        if (request.getParameter("boardId") != null) {
            boardId = Integer.parseInt(request.getParameter("boardId"));
        }

        BoardDAO boardDAO = new BoardDAO();
        CommentDAO commentDAO = new CommentDAO();

        String boardUsername = request.getParameter("boardUsername");
        String boardTitle = request.getParameter("boardTitle");
        String boardContent = request.getParameter("boardContent");

        // 입력 안된 것 있으면 콘솔에 로그 출력
        if (boardUsername == null || boardTitle == null || boardContent == null ||
                boardUsername.equals("") || boardTitle.equals("") || boardContent.equals("")) {
            System.out.println("입력안된거있음");
            return null;
        }

        boardDAO.update(boardId, boardUsername, boardTitle, boardContent);

        // 수정이 완료된 board 객체를 request에 설정
        Board updatedBoard = boardDAO.getBoard(boardId); // 수정 후의 게시물 정보를 가져옴
        request.setAttribute("board", updatedBoard);

        // 댓글 데이터를 가져와서 설정
        List<Comment> comments = commentDAO.getCommentsForBoard(boardId);
        request.setAttribute("comments", comments);


        // JSP로 포워딩
        RequestDispatcher dispatcher = request.getRequestDispatcher("view.jsp?boardId=" + boardId);
        dispatcher.forward(request, response);


        // BoardCommand를 호출하여 게시글 목록을 다시 불러오기
        BoardCommand boardCommand = new BoardCommand();
        boardCommand.execute(request, response);

        return "board.jsp";
    }

    @Override
    public void setParameters(HttpServletRequest request) {
        String boardIdParam = request.getParameter("boardId");
        if (boardIdParam != null) {
            this.boardId = Integer.parseInt(boardIdParam);
        }
    }

}
