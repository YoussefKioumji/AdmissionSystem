package controller.command;

import model.entity.User;
import model.entity.enums.Role;
import model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class RegisterCommand implements Command {
    private UserService userService;

    public RegisterCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String enFirstName = request.getParameter("enFirstName");
        String enLastName = request.getParameter("enLastName");
        String uaFirstName = request.getParameter("uaFirstName");
        String uaLastName = request.getParameter("uaLastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        int age = Integer.parseInt(request.getParameter("age"));
        String phone = request.getParameter("phone");

        User user = new User();
        user.setRole(Role.CLIENT);
        user.setEnFirstName(enFirstName);
        user.setEnLastName(enLastName);
        user.setUaFirstName(uaFirstName);
        user.setUaLastName(uaLastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setAge(age);
        user.setPhone(phone);
        System.out.println(user.getUaFirstName());
        userService.create(user);
        return "redirect:loginPage";
    }
}