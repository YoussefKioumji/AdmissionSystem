<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <body>
        <fmt:setBundle basename="outputs"/>
        <c:set var="localeCode" value="${pageContext.response.locale}"/>
        <fmt:message key="administrator.applications_title" var="pageTitle"/>
        <jsp:include page="aheader.jsp">
            <jsp:param name="title" value="${pageTitle}"/>
        </jsp:include><br><br>
        <form action="${pageContext.request.contextPath}/app/admin/selectSApplication" method="post">
            <select name="selectedApplication">
                <c:forEach items="${specialities}" var="speciality">
                    <c:choose>
                        <c:when test="${localeCode == 'uk'}">
                            <option value="${speciality.id}" selected><c:out value="${speciality.uaName}"/></option>
                        </c:when>
                        <c:otherwise>
                            <option value="${speciality.id}" selected><c:out value="${speciality.enName}"/></option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
            <br><input type="submit" value="<fmt:message key="submit.button"/>"/>
        </form>
        <c:if test="${not empty applications}">
            <table class="w3-table w3-bordered w3-striped">
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
                        <c:choose>
                            <c:when test="${localeCode == 'uk'}">
                                <th><c:out value="${application.uaName}"/></th>
                            </c:when>
                            <c:otherwise>
                                <th><c:out value="${application.enName}"/></th>
                            </c:otherwise>
                        </c:choose>
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