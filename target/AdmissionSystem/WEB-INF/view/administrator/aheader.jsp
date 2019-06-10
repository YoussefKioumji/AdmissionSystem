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
        <a href="${pageContext.request.contextPath}/app/admin/history"><fmt:message key="header.history"/></a>
        <a href="${pageContext.request.contextPath}/app/admin/faculties"><fmt:message key="header.faculties"/></a>
        <a href="${pageContext.request.contextPath}/app/admin/subjects"><fmt:message key="header.subjects"/></a>
        <a href="${pageContext.request.contextPath}/app/admin/specialities"><fmt:message key="header.specialities"/></a>
        <a href="${pageContext.request.contextPath}/app/admin/applications"><fmt:message key="header.applications"/></a>
        <a href="${pageContext.request.contextPath}/app/logout"><fmt:message key="header.logout"/></a>
        <a href="${pageContext.request.contextPath}/app/changeLanguage?language=en"><fmt:message key="header.english"/></a>
        <a href="${pageContext.request.contextPath}/app/changeLanguage?language=uk"><fmt:message key="header.ukrainian"/></a>
    </body>
</html>