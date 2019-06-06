package controller.command;

import model.entity.User;
import model.entity.enums.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Optional;

public class CommandUtility {
    static boolean checkUserIsLogged(HttpServletRequest request, String email, User user) {
        HashSet<String> loggedUsers = (HashSet<String>) request.getSession().getServletContext().getAttribute("loggedUsers");
        if (loggedUsers.stream().anyMatch(email::equals)) {
            return true;
        }
        loggedUsers.add(email);
        request.getSession().getServletContext().setAttribute("loggedUsers", loggedUsers);
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        return false;
    }
}