package controller.command.administrator;

import controller.command.Command;
import model.entity.User;
import model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AdminShowHistoryCommand implements Command {
    private UserService userService;

    public AdminShowHistoryCommand(UserService userService) {
        this.userService = userService;
    }
    @Override
    public String execute(HttpServletRequest request) {
        List<User> users = userService.findAllExams();
        request.setAttribute("users", users);
        return "/WEB-INF/view/administrator/ahistory.jsp";
    }
}