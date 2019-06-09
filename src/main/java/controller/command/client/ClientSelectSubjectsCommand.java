package controller.command.client;

import controller.command.Command;
import model.entity.User;
import model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class ClientSelectSubjectsCommand implements Command {
    private UserService userService;

    public ClientSelectSubjectsCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String[] subjects = request.getParameterValues("selectedSubjects");
        int userId = ((User)request.getSession().getAttribute("user")).getId();
        int[] selectedSubjects = new int[3];
        for (int i = 0; i < subjects.length; i++) {
            selectedSubjects[i] = Integer.parseInt(subjects[i]);
        }
        for (int i = 0; i < selectedSubjects.length; i++) {
            userService.createExam(userId, selectedSubjects[i]);
        }
        return "redirect:home";
    }
}