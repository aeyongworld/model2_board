package command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Command {
    String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;


    // 다음 요청을 처리할 때 파라미터로 boardId가 필요한 경우 사용하는 메서드
    void setParameters(HttpServletRequest request);
}
