<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
    <body>
        <fmt:setBundle basename="outputs"/>
        <fmt:message key="administrator.specialities_title" var="pageTitle"/>
        <jsp:include page="aheader.jsp">
            <jsp:param name="title" value="${pageTitle}"/>
        </jsp:include><br>
        <fmt:message key="specialities.search_by_faculty"/>
        <form action="${pageContext.request.contextPath}/app/admin/searchByFaculty" method="post">
            <select name="searchedFaculty">
                <c:forEach items="${faculties}" var="faculty">
                    <option value="${faculty.id}" selected><c:out value="${faculty.enName}"/></option>
                </c:forEach>
            </select>
            <br><input type="submit" value="<fmt:message key="submit.button"/>"/>
        </form>
        <fmt:message key="administrator.add_speciality"/>
        <form action="${pageContext.request.contextPath}/app/admin/addSpeciality" method="post">
            <label for="enNameSpeciality"><fmt:message key="administrator.add_speciality_english"/></label><br>
            <input type="text" id="enNameSpeciality" name="enNameSpeciality"><br>
            <label for="uaNameSpeciality"><fmt:message key="administrator.add_speciality_ukrainian"/></label><br>
            <input type="text" id="uaNameSpeciality" name="uaNameSpeciality"><br>
            <label for="years"><fmt:message key="administrator.add_speciality_years"/></label><br>
            <input type="number" min="0" id="years" name="years"><br>
            <label for="code"><fmt:message key="administrator.add_speciality_code"/></label><br>
            <input type="number" min="0" id="code" name="code"><br>
            <fmt:message key="administrator.add_speciality_exams"/>
            <c:forEach var="i" begin="0" end="2">
                <select name="selectedSubjects">
                    <c:forEach items="${subjects}" var="subject">
                        <option value="${subject.id}" selected><c:out value="${subject.enName}"/></option>
                    </c:forEach>
                </select>
            </c:forEach><br>
            <fmt:message key="administrator.add_speciality_faculty"/>
            <select name="selectedFaculty">
                <c:forEach items="${faculties}" var="faculty">
                    <option value="${faculty.id}" selected><c:out value="${faculty.enName}"/></option>
                </c:forEach>
            </select>
            <br><input type="submit" value="<fmt:message key="submit.button"/>"/>
        </form>
        <table>
            <thead>
            <tr>
                <th><fmt:message key="specialities.id"/></th>
                <th><fmt:message key="specialities.code"/></th>
                <th><fmt:message key="specialities.name"/></th>
                <th><fmt:message key="specialities.faculty_name"/></th>
                <th><fmt:message key="specialities.years"/></th>
                <c:forEach begin="0" end="2">
                    <th><fmt:message key="specialities.subject_name"/></th>
                </c:forEach>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${specialities}" var="speciality">
                <tr>
                    <th><c:out value="${speciality.id}"/></th>
                    <th><c:out value="${speciality.code}"/></th>
                    <th><c:out value="${speciality.enName}"/></th>
                    <th><c:out value="${speciality.faculty.enName}"/></th>
                    <th><c:out value="${speciality.years}"/></th>
                    <c:forEach items="${speciality.subjects}" var="subject">
                        <th><c:out value="${subject.enName}"/></th>
                    </c:forEach>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </body>
</html>