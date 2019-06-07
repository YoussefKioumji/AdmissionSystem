<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <body>
        <fmt:setBundle basename="outputs"/>
        <fmt:message key="administrator.history_title" var="pageTitle"/>
        <jsp:include page="aheader.jsp">
            <jsp:param name="title" value="${pageTitle}"/>
        </jsp:include><br>
        <table>
            <thead>
                <tr>
                    <th><fmt:message key="administrator.history_email"/></th>
                    <th><fmt:message key="administrator.history_subject"/></th>
                    <th><fmt:message key="administrator.history_mark"/></th>
                </tr>
            </thead>
            <tbody>
                <c:if test="${not empty results}">
                    <c:forEach items="${results}" var="result">
                        <c:forEach items="${result.exams}" var="exam">
                            <c:if test="${exam.mark != 0}">
                                <tr>
                                    <th><c:out value="${userEmail}"/></th>
                                    <th><c:out value="${exam.enName}"/></th>
                                    <th><c:out value="${exam.mark}"/></th>
                                </tr>
                            </c:if>
                        </c:forEach>
                    </c:forEach>
                </c:if>
            </tbody>
        </table>
    </body>
</html>