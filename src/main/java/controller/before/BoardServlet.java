package controller.before;

import dto.Board;
import dao.BoardDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "BoardServlet", value = "/BoardServlet")
public class BoardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        superProcess(request, response); // get 요청이 들어오면 superProcess 메서드 호출
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        superProcess(request,response); // post 요청이 들어오면 superProcess 메서드 호출
    }


    protected void superProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 객체 생성
        BoardDAO boardDAO = new BoardDAO();

        // 게시글 목록 가져와서 request에 저장
        ArrayList<Board> boardList = boardDAO.getList();
        request.setAttribute("boardList", boardList);

        // JSP로 포워딩(넘겨주기)
        RequestDispatcher dispatcher = request.getRequestDispatcher("board.jsp");
        dispatcher.forward(request, response);

    }
}