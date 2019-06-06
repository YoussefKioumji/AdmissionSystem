package controller.filters;

import javax.servlet.*;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class LoginValidationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("outputs", Locale.getDefault());
        String email = servletRequest.getParameter("email");
        String password = servletRequest.getParameter("password");
        if (email == null || email.equals("") || password == null || password.equals("")) {
            servletRequest.setAttribute("errorMessage", resourceBundle.getString("login.null_error"));
            if (password == null || password.equals("")) {
                servletRequest.setAttribute("emailLogin", email);
            }
        } else {
            boolean check = Pattern.compile(".{1,50}@[a-zA-z]{1,15}.[a-zA-Z]{1,5}").matcher(email).matches();
            if (!check) {
                servletRequest.setAttribute("errorMessage", resourceBundle.getString("login.validation_error"));
                servletRequest.setAttribute("emailLogin", email);
            }
        }
        RequestDispatcher requestDispatcher = servletRequest.getRequestDispatcher("/WEB-INF/view/login.jsp");
        requestDispatcher.forward(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}