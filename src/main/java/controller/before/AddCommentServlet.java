package controller.before;

import dao.CommentDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AddCommentServlet", value = "/AddCommentServlet")
public class AddCommentServlet extends HttpServlet {
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

        String commentContent = request.getParameter("commentContent");

        CommentDAO commentDAO = new CommentDAO();
        int result = commentDAO.addComment(boardId, commentContent);

        if (result == 1) {
            // 댓글 추가 성공
            response.sendRedirect("ViewServlet?boardId=" + boardId);
        } else {
            // 댓글 추가 실패
            redirectWithError(response, "댓글 추가에 실패했습니다.", "history.go(-1);");
        }
    }

    private void redirectWithError(HttpServletResponse response, String message, String location) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter script = response.getWriter();

        script.println("<script>");
        script.println("alert('" + message + "')");
        script.println(location);
        script.println("</script>");

        script.flush();
    }
}


