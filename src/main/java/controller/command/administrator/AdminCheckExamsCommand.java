package controller.command.administrator;

import controller.command.Command;
import model.entity.User;
import model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class AdminCheckExamsCommand implements Command {
    private UserService userService;

    public AdminCheckExamsCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String userEmail = request.getParameter("userEmail");
        int mark = Integer.parseInt(request.getParameter("mark"));
        int subjectId = Integer.parseInt(request.getParameter("subjectId"));
        User user = userService.findByEmail(userEmail);
        userService.updateExam(mark, user.getId(), subjectId);
        return "redirect:exams";
    }
}