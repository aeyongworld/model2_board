package controller.before;

import dao.BoardDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "WriteActionServlet", value = "/WriteActionServlet")
public class WriteActionServlet extends HttpServlet {
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

        PrintWriter out = response.getWriter();

        int categoryId = Integer.parseInt(request.getParameter("category"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        BoardDAO boardDAO = new BoardDAO();
        int result = boardDAO.write(categoryId, username, password, title, content);

        if (result == -1) { // write 실패
            out.println("<script>alert('글쓰기에 실패했습니다.');history.back();</script>");
        } else {
            out.println("<script>location.href = 'BoardServlet';</script>");
        }
    }


}
