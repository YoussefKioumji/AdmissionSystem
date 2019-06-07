<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <body>
        <fmt:setBundle basename="outputs"/>
        <fmt:message key="client.home_title" var="pageTitle"/>
        <jsp:include page="cheader.jsp">
            <jsp:param name="title" value="${pageTitle}"/>
        </jsp:include><br>
        <fmt:message key="client.marks"/><br>
        <c:forEach items="${results}" var="result">
            <c:if test="${user.email == result.email}">
                <c:forEach items="${result.exams}" var="exam">
                    <c:if test="${exam.mark != 0}">
                        <c:out value="${exam.enName}"/>
                        <c:out value="${exam.mark}"/><br>
                    </c:if>
                </c:forEach>
            </c:if>
        </c:forEach>
    </body>
</html>