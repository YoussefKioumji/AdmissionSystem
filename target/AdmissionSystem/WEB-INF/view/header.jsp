<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
    <fmt:setBundle basename="outputs"/>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <title>${param.title}</title>
    </head>
    <body>
        <div class="w3-top">
            <div class="w3-bar w3-white w3-padding w3-card" style="letter-spacing:1px;">
                <a href="${pageContext.request.contextPath}/app/home"><fmt:message key="header.application_name"/></a>
                <div class="w3-right">
                    <a href="${pageContext.request.contextPath}/app/about"><fmt:message key="header.about"/></a>
                    <a href="${pageContext.request.contextPath}/app/subjects?pageNumber=1"><fmt:message key="header.subjects"/></a>
                    <a href="${pageContext.request.contextPath}/app/specialities?pageNumber=1"><fmt:message key="header.specialities"/></a>
                    <a href="${pageContext.request.contextPath}/app/registrationPage"><fmt:message key="header.registration"/></a>
                    <a href="${pageContext.request.contextPath}/app/loginPage"><fmt:message key="header.login"/></a>
                    <a href="${pageContext.request.contextPath}/app/changeLanguage?language=en"><fmt:message key="header.english"/></a>
                    <a href="${pageContext.request.contextPath}/app/changeLanguage?language=uk"><fmt:message key="header.ukrainian"/></a>
                </div>
            </div>
        </div>
    </body>
</html>