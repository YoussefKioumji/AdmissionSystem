package controller.command.administrator;

import controller.command.Command;
import model.entity.Subject;
import model.service.SubjectService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AdminFindAllSubjectsCommand implements Command {
    private SubjectService subjectService;

    public AdminFindAllSubjectsCommand(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        List<Subject> subjects = subjectService.findAll();
        request.setAttribute("subjects", subjects);
        return "/WEB-INF/view/administrator/asubjects.jsp";
    }
}