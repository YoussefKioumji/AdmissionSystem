<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <body>
        <fmt:setBundle basename="outputs"/>
        <fmt:message key="administrator.applications_title" var="pageTitle"/>
        <jsp:include page="aheader.jsp">
            <jsp:param name="title" value="${pageTitle}"/>
        </jsp:include><br>
        <form action="${pageContext.request.contextPath}/app/admin/selectSApplication" method="post">
            <select name="selectedApplication">
                <c:forEach items="${specialities}" var="speciality">
                    <option value="${speciality.id}" selected><c:out value="${speciality.enName}"/></option>
                </c:forEach>
            </select>
            <br><input type="submit" value="Submit">
        </form>
        <c:if test="${not empty applications}">
            <table>
                <thead>
                <tr>
                    <th><fmt:message key="administrator.application_speciality"/></th>
                    <th><fmt:message key="administrator.application_email"/></th>
                    <th><fmt:message key="administrator.application_mark"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${applications}" var="application">
                    <c:forEach items="${application.users}" varStatus="loop">
                    <tr>
                        <th><c:out value="${application.enName}"/></th>
                        <th><c:out value="${application.users[loop.index].email}"/></th>
                        <th><c:out value="${finalMarks[loop.index]}"/></th>
                    </tr>
                    </c:forEach>
                </c:forEach>
                </tbody>
            </table>
        </c:if>
    </body>
</html>