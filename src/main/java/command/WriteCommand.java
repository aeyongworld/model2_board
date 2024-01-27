package command;

import dao.BoardDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class WriteCommand implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int categoryId = Integer.parseInt(request.getParameter("category"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        BoardDAO boardDAO = new BoardDAO();
        boardDAO.write(categoryId, username, password, title, content);


        // BoardCommand를 호출하여 게시글 목록을 다시 불러오기
        BoardCommand boardCommand = new BoardCommand();
        boardCommand.execute(request, response);

        return "board.jsp";
    }

    @Override
    public void setParameters(HttpServletRequest request) {
        // boardId가 필요하지 않아 처리 X
    }

}
