package controller.command.speciality;

import controller.command.Command;
import model.entity.Faculty;
import model.entity.Speciality;
import model.service.FacultyService;
import model.service.SpecialityService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class AddSpecialityCommand implements Command {
    private SpecialityService specialityService;
    private FacultyService facultyService;

    public AddSpecialityCommand(SpecialityService specialityService, FacultyService facultyService) {
        this.specialityService = specialityService;
        this.facultyService = facultyService;
    }
    @Override
    public String execute(HttpServletRequest request) {
        String enName = request.getParameter("enNameSpeciality");
        String uaName = request.getParameter("uaNameSpeciality");
        int years = Integer.parseInt(request.getParameter("years"));
        int facultyId = Integer.parseInt(request.getParameter("selectedFaculty"));
        int code = Integer.parseInt(request.getParameter("code"));
        String[] tmpSubjects = request.getParameterValues("selectedSubjects");
        List<Integer> subjectIds = new ArrayList<>();
        for (int i = 0; i < tmpSubjects.length; i++) {
            subjectIds.add(Integer.parseInt(tmpSubjects[i]));
        }
        Faculty faculty = facultyService.findById(facultyId);
        Speciality speciality = new Speciality();
        speciality.setCode(code);
        speciality.setEnName(enName);
        speciality.setUaName(uaName);
        speciality.setFaculty(faculty);
        speciality.setYears(years);
        specialityService.createWithSubjects(speciality, subjectIds);
        return "redirect:specialities";
    }
}