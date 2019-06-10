package controller.command.client;

import controller.command.Command;
import model.entity.Subject;
import model.entity.User;
import model.service.SubjectService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ClientFindAllSubjectsCommand implements Command {
    private SubjectService subjectService;

    public ClientFindAllSubjectsCommand(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int userId = ((User)request.getSession().getAttribute("user")).getId();
        List<Integer> usersWithExams = subjectService.findUsersWithExams();
        for (Integer userWithExams : usersWithExams) {
            if (userId == userWithExams) {
                request.setAttribute("choseExams", "1");
            }
        }
        List<Subject> subjects = subjectService.findAll();
        request.setAttribute("subjects", subjects);
        return "/WEB-INF/view/client/csubjects.jsp";
    }
}