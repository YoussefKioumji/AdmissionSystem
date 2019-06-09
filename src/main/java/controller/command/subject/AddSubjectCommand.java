package controller.command.subject;

import controller.command.Command;
import model.entity.Subject;
import model.service.SubjectService;

import javax.servlet.http.HttpServletRequest;

public class AddSubjectCommand implements Command {
    private SubjectService subjectService;

    public AddSubjectCommand(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String enName = request.getParameter("enNameSubject");
        String uaName = request.getParameter("uaNameSubject");
        int questions = Integer.parseInt(request.getParameter("questionsSubject"));
        Subject subject = new Subject();
        subject.setEnName(enName);
        subject.setUaName(uaName);
        subject.setQuestions(questions);
        subject.setMaximum(100);
        subject.setMinimum(60);
        subjectService.createSubject(subject);
        request.getSession().setAttribute("successMessage", "You have successfully added a subject");
        return "redirect:subjects";
    }
}