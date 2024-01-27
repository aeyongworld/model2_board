package command;

import dao.BoardDAO;
import dto.Board;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class BoardCommand implements Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 객체 생성
        BoardDAO boardDAO = new BoardDAO();

        // 게시글 목록 가져와서 request에 저장
        ArrayList<Board> boardList = boardDAO.getList();
        request.setAttribute("boardList", boardList);

        // JSP로 포워딩(넘겨주기)
        RequestDispatcher dispatcher = request.getRequestDispatcher("board.jsp");
        dispatcher.forward(request, response);

        return "board.jsp";
    }

    @Override
    public void setParameters(HttpServletRequest request) {
        // boardId가 필요하지 않아 처리 X
    }

}
