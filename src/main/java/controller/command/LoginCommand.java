package controller.command;

import model.entity.User;
import model.entity.enums.Role;
import model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class LoginCommand implements Command {
    private Map<Role, String> pages = new HashMap<>();
    private UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
        pages.put(Role.ADMINISTRATOR, "redirect:admin/home");
        pages.put(Role.CLIENT, "redirect:client/home");
    }

    @Override
    public String execute(HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Optional<User> user = userService.login(email);
        if (user.isPresent() && password.equals(user.get().getPassword())) {
            if (CommandUtility.checkUserIsLogged(request, user.get().getEmail(), user.get())) {
                return "/WEB-INF/view/error.jsp";
            }
            return pages.getOrDefault(user.get().getRole(), "/WEB-INF/view/login.jsp");
        }
        return "/WEB-INF/view/login.jsp";
    }
}