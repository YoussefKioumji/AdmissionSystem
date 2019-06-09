package controller.command.faculty;

import controller.command.Command;
import model.entity.Faculty;
import model.service.FacultyService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class FindAllFacultiesCommand implements Command {
    private FacultyService facultyService;

    public FindAllFacultiesCommand (FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        List<Faculty> faculties = facultyService.findAll();
        request.setAttribute("faculties", faculties);
        return "/WEB-INF/view/administrator/afaculty.jsp";
    }
}