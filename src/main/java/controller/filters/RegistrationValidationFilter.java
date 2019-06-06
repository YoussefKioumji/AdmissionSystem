package controller.filters;

import javax.servlet.*;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class RegistrationValidationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("outputs", Locale.getDefault());
        String enFirstName = servletRequest.getParameter("enFirstName");
        String enLastName = servletRequest.getParameter("enLastName");
        String uaFirstName = servletRequest.getParameter("uaFirstName");
        String uaLastName = servletRequest.getParameter("uaLastName");
        String email = servletRequest.getParameter("email");
        String password = servletRequest.getParameter("password");
        String age = servletRequest.getParameter("age");
        String phone = servletRequest.getParameter("phone");
        if (enFirstName == null || enLastName == null || uaFirstName == null || uaLastName == null || email == null ||
        password == null || age == null || phone == null) {
            servletRequest.setAttribute("errorMessage", resourceBundle.getString("registration.null_error"));
        }

    }

    @Override
    public void destroy() {
    }
}