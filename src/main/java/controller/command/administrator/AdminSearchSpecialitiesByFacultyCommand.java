package controller.command.administrator;

import controller.command.Command;
import model.entity.Faculty;
import model.entity.Speciality;
import model.service.FacultyService;
import model.service.SpecialityService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AdminSearchSpecialitiesByFacultyCommand implements Command {
    private SpecialityService specialityService;
    private FacultyService facultyService;

    public AdminSearchSpecialitiesByFacultyCommand(SpecialityService specialityService, FacultyService facultyService) {
        this.specialityService = specialityService;
        this.facultyService = facultyService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int facultyId = Integer.parseInt(request.getParameter("searchedFaculty"));
        List<Speciality> specialities = specialityService.findByFaculty(facultyId);
        List<Faculty> faculties = facultyService.findAll();
        request.setAttribute("specialities", specialities);
        request.setAttribute("faculties", faculties);
        return "/WEB-INF/view/administrator/aspecialities.jsp";
    }
}