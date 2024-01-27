package command;

import dao.BoardDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteCommand implements Command{

    private int boardId; // boardId 멤버 변수

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        boardId = 0;

        if (request.getParameter("boardId") != null) {
            boardId = Integer.parseInt(request.getParameter("boardId"));
        }

        BoardDAO boardDAO = new BoardDAO();
        boardDAO.delete(boardId);

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
