package controller.command.client;

import controller.command.Command;
import model.entity.User;
import model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ClientShowMarksCommand implements Command {
    private UserService userService;

    public ClientShowMarksCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        List<User> results = userService.findAllExams();
        request.setAttribute("results", results);
        return "/WEB-INF/view/client/chome.jsp";
    }
}