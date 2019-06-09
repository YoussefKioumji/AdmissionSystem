package controller.command.administrator;

import controller.command.Command;
import model.service.SpecialityService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class AdminStopAdmissionCommand implements Command {
    private SpecialityService specialityService;

    public AdminStopAdmissionCommand(SpecialityService specialityService) {
        this.specialityService = specialityService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        List<Integer> specialityIds = specialityService.getDistinctSpecialityId();
        List<String> passed = new ArrayList<>();
        List<String> notPassed = new ArrayList<>();
        for (Integer specialityId : specialityIds) {
            List<String> tmpPassed = specialityService.findPassed(specialityId);
            passed.addAll(tmpPassed);
        }
        for (Integer specialityId : specialityIds) {
            List<String> tmpNotPassed = specialityService.findNotPassed(specialityId);
            notPassed.addAll(tmpNotPassed);
        }
        request.getServletContext().setAttribute("passed", passed);
        request.getServletContext().setAttribute("notPassed", notPassed);
        return "redirect:home";
    }
}