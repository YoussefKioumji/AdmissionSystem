package controller.command.administrator;

import controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class AdminShowHistoryCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/view/administrator/ahistory.jsp";
    }
}