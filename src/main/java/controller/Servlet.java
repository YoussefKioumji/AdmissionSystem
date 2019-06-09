package controller;

import controller.command.*;
import controller.command.administrator.*;
import controller.command.client.*;
import controller.command.faculty.FindAllFacultiesCommand;
import controller.command.show.*;
import controller.command.speciality.AddSpecialityCommand;
import controller.command.speciality.FindAllSpecialitiesCommand;
import controller.command.speciality.SearchSpecialityByFacultyCommand;
import controller.command.subject.AddSubjectCommand;
import controller.command.subject.FindAllSubjectsCommand;
import model.service.FacultyService;
import model.service.SpecialityService;
import model.service.SubjectService;
import model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

public class Servlet extends HttpServlet {
    private HashMap<String, Command> commands = new HashMap<>();

    @Override
    public void init() {
        getServletContext().setAttribute("loggedUsers", new HashSet<String>());

        commands.put("home", new ShowHomeCommand());
        commands.put("about", new ShowAboutCommand());
        commands.put("subjects", new FindAllSubjectsCommand(new SubjectService()));
        commands.put("specialities", new FindAllSpecialitiesCommand(new SpecialityService(), new FacultyService()));
        commands.put("searchByFaculty", new SearchSpecialityByFacultyCommand(new SpecialityService(), new FacultyService()));
        commands.put("registrationPage", new ShowRegistrationCommand());
        commands.put("registration", new RegisterCommand(new UserService()));
        commands.put("login", new LoginCommand(new UserService()));
        commands.put("logout", new LogoutCommand());
        commands.put("changeLanguage", new ChangeLanguageCommand());

        commands.put("client/home", new ClientShowHomeCommand());
        commands.put("client/subjects", new ClientFindAllSubjectsCommand(new SubjectService()));
        commands.put("client/specialities", new ClientFindAllSpecialitiesCommand(new SpecialityService(), new FacultyService()));
        commands.put("client/selectSubjects", new ClientSelectSubjectsCommand(new UserService()));
        commands.put("client/showMarks", new ClientShowMarksCommand(new UserService()));
        commands.put("client/selectSpeciality", new ClientSelectSpecialityCommand(new SubjectService(), new SpecialityService()));
        commands.put("client/searchByFaculty", new ClientSearchSpecialitiesByFacultyCommand(new SpecialityService(), new FacultyService()));

        commands.put("admin/home", new AdminShowHomeCommand());
        commands.put("admin/exams", new AdminShowExamsCommand(new UserService(), new SubjectService()));
        commands.put("admin/searchBySubject", new AdminSearchExamsBySubjectCommand(new UserService(), new SubjectService()));
        commands.put("admin/checkExams", new AdminCheckExamsCommand(new UserService()));
        commands.put("admin/history", new AdminShowHistoryCommand(new UserService()));
        commands.put("admin/faculties", new FindAllFacultiesCommand(new FacultyService()));
        commands.put("admin/applications", new AdminShowApplicationsCommand(new SpecialityService()));
        commands.put("admin/selectSApplication", new AdminSelectApplicationCommand(new SpecialityService()));
        commands.put("admin/stopAdmission", new AdminStopAdmissionCommand(new SpecialityService()));
        commands.put("admin/subjects", new AdminFindAllSubjectsCommand(new SubjectService()));
        commands.put("admin/addSubject", new AddSubjectCommand(new SubjectService()));
        commands.put("admin/specialities", new AdminFindAllSpecialitiesCommand(new SpecialityService(), new FacultyService(), new SubjectService()));
        commands.put("admin/searchByFaculty", new AdminSearchSpecialitiesByFacultyCommand(new SpecialityService(), new FacultyService()));
        commands.put("admin/addSpeciality", new AddSpecialityCommand(new SpecialityService(), new FacultyService()));
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