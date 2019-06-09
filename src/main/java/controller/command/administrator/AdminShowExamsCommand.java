package controller.command.administrator;

import controller.command.Command;
import model.entity.Subject;
import model.entity.User;
import model.service.SubjectService;
import model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AdminShowExamsCommand implements Command {
    private UserService userService;
    private SubjectService subjectService;

    public AdminShowExamsCommand(UserService userService, SubjectService subjectService) {
        this.userService = userService;
        this.subjectService = subjectService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        List<User> users = userService.findAllExams();
        List<Subject> subjects = subjectService.findExamSubject();
        request.setAttribute("users", users);
        request.setAttribute("subjects", subjects);
        return "/WEB-INF/view/administrator/aexams.jsp";
    }
}