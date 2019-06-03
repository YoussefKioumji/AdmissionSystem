package controller.command.show;

import controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class ShowRegistrationCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/view/registration.jsp";
    }
}