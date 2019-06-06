package controller.command.administrator;

import controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class AdminShowHomeCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/view/administrator/ahome.jsp";
    }
}