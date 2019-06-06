package controller.command.client;

import controller.command.Command;
import model.entity.Subject;
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
        List<Subject> subjects = subjectService.findAllSubjects();
        request.setAttribute("subjects", subjects);
        return "/WEB-INF/view/client/csubjects.jsp";
    }
}