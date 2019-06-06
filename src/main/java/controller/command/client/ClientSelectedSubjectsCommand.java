package controller.command.client;

import controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class ClientSelectedSubjectsCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String[] selectedSubjects = request.getParameterValues("selectedSubjects");
        for(int i = 0; i < selectedSubjects.length; i++) {
            System.out.println(selectedSubjects[i]);
        }
        return "";
    }
}