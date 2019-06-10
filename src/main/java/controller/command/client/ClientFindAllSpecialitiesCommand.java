package controller.command.client;

import controller.command.Command;
import model.entity.Faculty;
import model.entity.Speciality;
import model.entity.User;
import model.service.FacultyService;
import model.service.SpecialityService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ClientFindAllSpecialitiesCommand implements Command {
    private SpecialityService specialityService;
    private FacultyService facultyService;

    public ClientFindAllSpecialitiesCommand(SpecialityService specialityService, FacultyService facultyService) {
        this.specialityService = specialityService;
        this.facultyService = facultyService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int userId = ((User)request.getSession().getAttribute("user")).getId();
        List<Integer> usersWithSpeciality = specialityService.findUsersWithSpeciality();
        for (Integer userWithSpeciality : usersWithSpeciality) {
            if (userId == userWithSpeciality) {
                request.setAttribute("choseSpeciality", "1");
            }
        }
        List<Speciality> specialities = specialityService.findAll();
        List<Faculty> faculties = facultyService.findAll();
        request.setAttribute("specialities", specialities);
        request.setAttribute("faculties", faculties);
        return "/WEB-INF/view/client/cspecialities.jsp";
    }
}