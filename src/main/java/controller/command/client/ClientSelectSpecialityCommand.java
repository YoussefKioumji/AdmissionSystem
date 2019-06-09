package controller.command.client;

import controller.command.Command;
import model.entity.Subject;
import model.entity.User;
import model.service.SpecialityService;
import model.service.SubjectService;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

public class ClientSelectSpecialityCommand implements Command {
    private SubjectService subjectService;
    private SpecialityService specialityService;

    public ClientSelectSpecialityCommand (SubjectService subjectService, SpecialityService specialityService) {
        this.subjectService = subjectService;
        this.specialityService = specialityService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int specialityId = Integer.parseInt(request.getParameter("selectedSpeciality"));
        int userId = ((User)request.getSession().getAttribute("user")).getId();
        List<Subject> userSubjects = subjectService.getUserSubjects(userId);
        List<Subject> specialitySubjects = subjectService.getSpecialitySubjects(specialityId);
        if (!userSubjects.containsAll(specialitySubjects)) {
            request.getSession().setAttribute("errorMessage", "You cannot choose this speciality because you did not choose the right exams");
            return "redirect:specialities";
        }
        int[] marks = subjectService.findMarks(userId);
        for (int mark : marks) {
            if (mark < 60) {
                request.getSession().setAttribute("errorMessage", "You cannot choose this speciality because you did not pass the exams");
                return "redirect:specialities";
            }
        }
        int finalMark = Arrays.stream(marks).sum();
        specialityService.createAdmission(specialityId, userId, finalMark);
        return "redirect:specialities";
    }
}
