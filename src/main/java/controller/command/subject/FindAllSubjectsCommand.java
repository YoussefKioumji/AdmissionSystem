package controller.command.subject;

import controller.command.Command;
import model.entity.Subject;
import model.service.SubjectService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class FindAllSubjectsCommand implements Command {
    private SubjectService subjectService;

    public FindAllSubjectsCommand(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int recordPerPage = 5;
        int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        int startIndex = (pageNumber * recordPerPage) - recordPerPage;
        int totalNumberOfRecords = subjectService.numberOfRows();
        int numberOfPages = totalNumberOfRecords / recordPerPage;
        if (totalNumberOfRecords > numberOfPages * recordPerPage) {
            numberOfPages = numberOfPages + 1;
        }
        List<Subject> subjects = subjectService.findAllPagination(startIndex, recordPerPage);
        request.setAttribute("subjects", subjects);
        request.setAttribute("numberOfPages", numberOfPages);
        return "/WEB-INF/view/subjects.jsp";
    }
}