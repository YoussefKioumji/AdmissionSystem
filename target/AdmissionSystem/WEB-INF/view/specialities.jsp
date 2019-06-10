<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
    <body>
        <fmt:setBundle basename="outputs"/>
        <c:set var="localeCode" value="${pageContext.response.locale}"/>
        <fmt:message key="specialities.title" var="pageTitle"/>
        <jsp:include page="header.jsp">
            <jsp:param name="title" value="${pageTitle}"/>
        </jsp:include><br>
        <fmt:message key="specialities.search_by_faculty"/>
        <form action="${pageContext.request.contextPath}/app/searchByFaculty" method="post">
            <select name="searchedFaculty">
                <c:forEach items="${faculties}" var="faculty">
                    <c:choose>
                        <c:when test="${localeCode == 'uk'}">
                            <option value="${faculty.id}" selected><c:out value="${faculty.uaName}"/></option>
                        </c:when>
                        <c:otherwise>
                            <option value="${faculty.id}" selected><c:out value="${faculty.enName}"/></option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
            <br><input type="submit" value="<fmt:message key="submit.button"/>"/>
        </form>
        <table>
            <thead>
                <tr>
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
                        <c:choose>
                            <c:when test="${localeCode == 'uk'}">
                                <th><c:out value="${speciality.code}"/></th>
                                <th><c:out value="${speciality.uaName}"/></th>
                                <th><c:out value="${speciality.faculty.uaName}"/></th>
                                <th><c:out value="${speciality.years}"/></th>
                                <c:forEach items="${speciality.subjects}" var="subject">
                                    <th><c:out value="${subject.uaName}"/></th>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <th><c:out value="${speciality.code}"/></th>
                                <th><c:out value="${speciality.enName}"/></th>
                                <th><c:out value="${speciality.faculty.enName}"/></th>
                                <th><c:out value="${speciality.years}"/></th>
                                <c:forEach items="${speciality.subjects}" var="subject">
                                    <th><c:out value="${subject.enName}"/></th>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <c:forEach begin="1" end="${numberOfPages}" varStatus="loop">
            <a href="${pageContext.request.contextPath}/app/specialities?pageNumber=${loop.index}">${loop.index}</a>
        </c:forEach>
    </body>
</html>