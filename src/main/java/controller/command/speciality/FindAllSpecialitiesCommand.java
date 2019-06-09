package controller.command.speciality;

import controller.command.Command;
import model.entity.Faculty;
import model.entity.Speciality;
import model.service.FacultyService;
import model.service.SpecialityService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class FindAllSpecialitiesCommand implements Command {
    private SpecialityService specialityService;
    private FacultyService facultyService;

    public FindAllSpecialitiesCommand(SpecialityService specialityService, FacultyService facultyService) {
        this.specialityService = specialityService;
        this.facultyService = facultyService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int recordPerPage = 27;
        int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        int startIndex = (pageNumber * recordPerPage) - recordPerPage;
        int totalNumberOfRecords = specialityService.numberOfRows();
        int numberOfPages = totalNumberOfRecords / recordPerPage;
        if (totalNumberOfRecords > numberOfPages * recordPerPage) {
            numberOfPages = numberOfPages + 1;
        }
        List<Speciality> specialities = specialityService.findAllPagination(startIndex, recordPerPage);
        System.out.println(specialities);
        List<Faculty> faculties = facultyService.findAll();
        request.setAttribute("specialities", specialities);
        request.setAttribute("faculties", faculties);
        request.setAttribute("numberOfPages", numberOfPages);
        return "/WEB-INF/view/specialities.jsp";
    }
}