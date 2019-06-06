package controller.command.speciality;

import controller.command.Command;
import model.entity.Speciality;
import model.service.SpecialityService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class FindAllSpecialitiesCommand implements Command {
    private SpecialityService specialityService;

    public FindAllSpecialitiesCommand(SpecialityService specialityService) {
        this.specialityService = specialityService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        List<Speciality> specialities = specialityService.findAllSpecialities();
        request.setAttribute("specialities", specialities);
        return "/WEB-INF/view/specialities.jsp";
    }
}