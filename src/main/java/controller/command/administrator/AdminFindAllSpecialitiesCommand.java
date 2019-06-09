package controller.command.administrator;

import controller.command.Command;
import model.entity.Faculty;
import model.entity.Speciality;
import model.entity.Subject;
import model.service.FacultyService;
import model.service.SpecialityService;
import model.service.SubjectService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AdminFindAllSpecialitiesCommand implements Command {
    private SpecialityService specialityService;
    private FacultyService facultyService;
    private SubjectService subjectService;

    public AdminFindAllSpecialitiesCommand(SpecialityService specialityService, FacultyService facultyService, SubjectService subjectService) {
        this.specialityService = specialityService;
        this.facultyService = facultyService;
        this.subjectService = subjectService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        List<Speciality> specialities = specialityService.findAll();
        List<Faculty> faculties = facultyService.findAll();
        List<Subject> subjects = subjectService.findAll();
        request.setAttribute("specialities", specialities);
        request.setAttribute("faculties", faculties);
        request.getSession().setAttribute("subjects", subjects);
        return "/WEB-INF/view/administrator/aspecialities.jsp";
    }
}