<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
    <body>
        <fmt:setBundle basename="outputs"/>
        <fmt:message key="client.specialities_title" var="pageTitle"/>
        <jsp:include page="cheader.jsp">
            <jsp:param name="title" value="${pageTitle}"/>
        </jsp:include><br>
        <c:if test="${empty passed}">
            <c:if test="${not empty errorMessage}">
                <c:out value="${errorMessage}"/>
            </c:if>
            <fmt:message key="specialities.choose_speciality"/>
            <form action="${pageContext.request.contextPath}/app/client/selectSpeciality" method="post">
                <select name="selectedSpeciality">
                    <c:forEach items="${specialities}" var="speciality">
                        <option value="${speciality.id}" selected><c:out value="${speciality.enName}"/></option>
                    </c:forEach>
                </select>
                <br><input type="submit" value="<fmt:message key="submit.button"/>"/>
            </form>
        </c:if>
        <c:if test="${not empty passed}">
            <fmt:message key="message.stop_adminssion"/>
        </c:if><br>
        <fmt:message key="specialities.search_by_faculty"/>
        <form action="${pageContext.request.contextPath}/app/client/searchByFaculty" method="post">
            <select name="searchedFaculty">
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