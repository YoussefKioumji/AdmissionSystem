package controller.command.client;

import controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class ClientShowHomeCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/view/client/chome.jsp";
    }
}