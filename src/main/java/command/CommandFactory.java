package command;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {

    private static final Map<String, Command> commandMap = new HashMap<>();

    static {
        // 커맨드 등록
        registerCommand("board", new BoardCommand());
        registerCommand("view", new ViewCommand());
        registerCommand("write", new WriteCommand());
        registerCommand("update", new UpdateCommand());
        registerCommand("delete", new DeleteCommand());
        registerCommand("addComment", new AddCommentCommand());
    }

    public static Command getCommand(String commandType) { // commandType에 일치하는 커맨드를 생성 후 반환
        if ("board".equals(commandType)) {
            return new BoardCommand();
        } else if ("view".equals(commandType)){
            return new ViewCommand();
        } else if ("write".equals(commandType)) {
            return new WriteCommand();
        } else if ("update".equals(commandType)) {
            return new UpdateCommand();
        } else if ("delete".equals(commandType)) {
            return new DeleteCommand();
        } else if ("addComment".equals(commandType)) {
            return new AddCommentCommand();
        }
        return null;
    }


    // 커맨드 등록
    private static void registerCommand(String commandType, Command command) {
        commandMap.put(commandType, command);
    }
}
