<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
    <body>
        <fmt:setBundle basename="outputs"/>
        <fmt:message key="specialities.title" var="pageTitle"/>
        <jsp:include page="header.jsp">
            <jsp:param name="title" value="${pageTitle}"/>
        </jsp:include>
        <table>
            <thead>
                <tr>
                    <th><fmt:message key="specialities.id"/></th>
                    <th><fmt:message key="specialities.code"/></th>
                    <th><fmt:message key="specialities.name"/></th>
                    <th><fmt:message key="specialities.faculty_name"/></th>
                    <th><fmt:message key="specialities.years"/></th>
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
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>