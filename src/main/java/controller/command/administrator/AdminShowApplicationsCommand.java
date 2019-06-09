package controller.command.administrator;

import controller.command.Command;
import model.entity.Speciality;
import model.service.SpecialityService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AdminShowApplicationsCommand implements Command {
    private SpecialityService specialityService;

    public AdminShowApplicationsCommand(SpecialityService specialityService) {
        this.specialityService = specialityService;
    }
    @Override
    public String execute(HttpServletRequest request) {
        List<Speciality> specialities = specialityService.findAll();
        request.setAttribute("specialities", specialities);
        return "/WEB-INF/view/administrator/aapplications.jsp";
    }
}