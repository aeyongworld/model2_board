package controller.before;

import dao.BoardDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DeleteActionServlet", value = "/DeleteActionServlet")
public class DeleteActionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        superProcess(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        superProcess(request, response);
    }

    protected void superProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int boardId = 0;
        if (request.getParameter("boardId") != null) {
            boardId = Integer.parseInt(request.getParameter("boardId"));
        }
        if (boardId == 0) {
            PrintWriter script = response.getWriter();
            script.println("<script>");
            script.println("alert('유효하지 않은 글입니다.')");
            script.println("location.href = 'board.jsp'");
            script.println("</script>");
        }

        BoardDAO boardDAO = new BoardDAO();
        int result = boardDAO.delete(boardId);

        if (result == -1) {
            redirectWithError(response, "글 삭제에 실패했습니다.", "history.back()");
        } else {
            redirect(response, "BoardServlet");
        }

    }

    private void redirect(HttpServletResponse response, String location) throws IOException {
        PrintWriter script = response.getWriter();
        script.println("<script>");
        script.println("location.href = '" + location + "'");
        script.println("</script>");
    }

    private void redirectWithError(HttpServletResponse response, String message, String location) throws IOException {
        PrintWriter script = response.getWriter();
        script.println("<script>");
        script.println("alert('" + message + "')");
        script.println(location);
        script.println("</script>");
    }

}