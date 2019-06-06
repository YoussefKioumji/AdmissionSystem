<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
    <body>
        <fmt:setBundle basename="outputs"/>
        <c:if test="${empty user}">
        <fmt:message key="home.title" var="pageTitle"/>
        <jsp:include page="header.jsp">
            <jsp:param name="title" value="${pageTitle}"/>
        </jsp:include>
        </c:if>
        <c:if test="${not empty user}">
            <c:if test="${user.role == 'ADMINISTRATOR'}">
            <jsp:include page="administrator/ahome.jsp"/>
            </c:if>
            <c:if test="${user.role == 'CLIENT'}">
                <jsp:include page="client/chome.jsp"/>
            </c:if>
        </c:if>
    </body>
</html>