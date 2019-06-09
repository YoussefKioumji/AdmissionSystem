package controller.command.administrator;

import controller.command.Command;
import model.entity.Speciality;
import model.service.SpecialityService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AdminSelectApplicationCommand implements Command {
    private SpecialityService specialityService;

    public AdminSelectApplicationCommand (SpecialityService specialityService) {
        this.specialityService = specialityService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int specialityId = Integer.parseInt(request.getParameter("selectedApplication"));
        List<Speciality> applications = specialityService.findApplications(specialityId);
        List<Integer> finalMarks = specialityService.getFinalMarks(specialityId);
        request.getSession().setAttribute("applications", applications);
        request.getSession().setAttribute("finalMarks", finalMarks);
        return "redirect:applications";
    }
}