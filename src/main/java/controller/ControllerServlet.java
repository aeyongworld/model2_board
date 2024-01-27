package controller;

import command.Command;
import command.CommandFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ControllerServlet", value = "/ControllerServlet")
public class ControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }


    // get, post 요청으로 들어왔을때 항상 실행되는 process 메서드
    protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String commandType = request.getParameter("commandType");
        Command command = CommandFactory.getCommand(commandType); // CommandFactory에서 해당하는 커맨드 가져오기

        if (command != null) {
            command.setParameters(request); // boardId 값이 필요한 경우 파라미터로 설정하기

            String nextPage = command.execute(request, response);
            RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
            dispatcher.forward(request, response);

        } else {
            // 적절한 커맨드가 없을 경우 처리
            System.out.println("해당 커맨드를 찾을 수 없습니다 : " + commandType);
        }
    }
}