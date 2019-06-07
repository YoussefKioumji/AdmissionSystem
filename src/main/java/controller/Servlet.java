package controller;

import controller.command.*;
import controller.command.administrator.AdminCheckExamsCommand;
import controller.command.administrator.AdminShowExamsCommand;
import controller.command.administrator.AdminShowHistoryCommand;
import controller.command.administrator.AdminShowHomeCommand;
import controller.command.client.ClientFindAllSpecialitiesCommand;
import controller.command.client.ClientFindAllSubjectsCommand;
import controller.command.client.ClientSelectedSubjectsCommand;
import controller.command.client.ClientShowHomeCommand;
import controller.command.faculty.FindAllFacultiesCommand;
import controller.command.show.*;
import controller.command.speciality.FindAllSpecialitiesCommand;
import controller.command.subject.FindAllSubjectsCommand;
import model.entity.User;
import model.service.SpecialityService;
import model.service.SubjectService;
import model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Servlet extends HttpServlet {
    private HashMap<String, Command> commands = new HashMap<>();

    @Override
    public void init() {
        getServletContext().setAttribute("loggedUsers", new HashSet<String>());
        getServletContext().setAttribute("results", new ArrayList<User>());

        commands.put("home", new ShowHomeCommand());
        commands.put("about", new ShowAboutCommand());
        commands.put("subjects", new FindAllSubjectsCommand(new SubjectService()));
        commands.put("specialities", new FindAllSpecialitiesCommand(new SpecialityService()));
        commands.put("registrationPage", new ShowRegistrationCommand());
        commands.put("registration", new RegisterCommand(new UserService()));
        commands.put("login", new LoginCommand(new UserService()));
        commands.put("logout", new LogoutCommand());
        commands.put("changeLanguage", new ChangeLanguageCommand());

        commands.put("client/home", new ClientShowHomeCommand());
        commands.put("client/subjects", new ClientFindAllSubjectsCommand(new SubjectService()));
        commands.put("client/specialities", new ClientFindAllSpecialitiesCommand(new SpecialityService()));
        commands.put("client/selectSubjects", new ClientSelectedSubjectsCommand(new UserService()));

        commands.put("admin/home", new AdminShowHomeCommand());
        commands.put("admin/exams", new AdminShowExamsCommand(new UserService()));
        commands.put("admin/checkExams", new AdminCheckExamsCommand(new UserService()));
        commands.put("admin/history", new AdminShowHistoryCommand());
        commands.put("admin/faculties", new FindAllFacultiesCommand());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getRequestURI();
        path = path.replaceAll(".*/app/", "");
        Command command = commands.getOrDefault(path, (r) -> "/WEB-INF/view/login.jsp");
        String page = command.execute(request);
        if (page.contains("redirect")) {
            response.sendRedirect(page.replace("redirect:", ""));
        } else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}