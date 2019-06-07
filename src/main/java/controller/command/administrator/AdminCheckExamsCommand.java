package controller.command.administrator;

import controller.command.Command;
import model.entity.Subject;
import model.entity.User;
import model.service.SubjectService;
import model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AdminCheckExamsCommand implements Command {
    private UserService userService;

    public AdminCheckExamsCommand(UserService userService) {
        this.userService = userService;
    }
    @Override
    public String execute(HttpServletRequest request) {
        ArrayList<User> results = (ArrayList<User>)request.getSession().getServletContext().getAttribute("results");
        if (results.isEmpty()) {
            results = (ArrayList<User>)userService.findAllExams();
        }
        String userEmail = request.getParameter("userEmail");
        int mark = Integer.parseInt(request.getParameter("mark"));
        int subjectId = Integer.parseInt(request.getParameter("subjectId"));
        System.out.println(userEmail);
        System.out.println(mark);
        System.out.println(subjectId);
        for (User user : results) {
            if (user.getEmail().equals(userEmail)) {
                for (Subject exam : user.getExams()) {
                    if (exam.getId() == subjectId) {
                        exam.setMark(mark);
                    }
                }
            }
        }
        System.out.println(results);
        request.getSession().getServletContext().setAttribute("userEmail", userEmail);
        request.getSession().getServletContext().setAttribute("results", results);
//        Optional<User> user = userService.findByEmail(userEmail);
//        user.ifPresent(user1 -> userService.deleteExam(user1.getId(), subjectId));
        return "redirect:exams";
    }
}