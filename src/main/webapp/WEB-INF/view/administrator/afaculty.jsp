<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <body>
        <fmt:setBundle basename="outputs"/>
        <c:set var="localeCode" value="${pageContext.response.locale}"/>
        <fmt:message key="administrator.faculties_title" var="pageTitle"/>
        <jsp:include page="aheader.jsp">
            <jsp:param name="title" value="${pageTitle}"/>
        </jsp:include>
        <table>
            <thead>
                <tr>
                    <th><fmt:message key="faculty.id"/></th>
                    <th><fmt:message key="faculty.name"/></th>
                    <th><fmt:message key="faculty.email"/></th>
                    <th><fmt:message key="faculty.phone"/></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${faculties}" var="faculty">
                    <tr>
                        <th><c:out value="${faculty.id}"/></th>
                        <c:choose>
                            <c:when test="${localeCode == 'uk'}">
                                <th><c:out value="${faculty.uaName}"/></th>
                            </c:when>
                            <c:otherwise>
                                <th><c:out value="${faculty.enName}"/></th>
                            </c:otherwise>
                        </c:choose>
                        <th><c:out value="${faculty.email}"/></th>
                        <th><c:out value="${faculty.phone}"/></th>
                    </tr>
            </c:forEach>
            </tbody>
        </table>
    </body>
</html>