<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
    <fmt:setBundle basename="outputs"/>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${param.title}</title>
    </head>
    <body>
        <a href="${pageContext.request.contextPath}/app/admin/home"><fmt:message key="header.application_name"/></a>
        <a href="${pageContext.request.contextPath}/app/admin/exams"><fmt:message key="header.exams"/></a>
        <a href="${pageContext.request.contextPath}/app/admin/history"><fmt:message key="administrator.history"/></a>
        <a href="${pageContext.request.contextPath}/app/admin/faculties"><fmt:message key="header.faculties"/></a>
        <a href="${pageContext.request.contextPath}/app/logout"><fmt:message key="header.logout"/></a>
        <a href="${pageContext.request.contextPath}/app/changeLanguage?language=en"><fmt:message key="header.english"/></a>
        <a href="${pageContext.request.contextPath}/app/changeLanguage?language=ua"><fmt:message key="header.ukrainian"/></a>
    </body>
</html>