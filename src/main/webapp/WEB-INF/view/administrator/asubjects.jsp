<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
    <body>
        <fmt:setBundle basename="outputs"/>
        <c:set var="localeCode" value="${pageContext.response.locale}"/>
        <fmt:message key="administrator.subjects_title" var="pageTitle"/>
        <jsp:include page="aheader.jsp">
            <jsp:param name="title" value="${pageTitle}"/>
        </jsp:include><br><br>
        <div class="w3-card">
            <fmt:message key="administrator.add_subject"/>
            <form action="${pageContext.request.contextPath}/app/admin/addSubject" method="post">
                <label for="enNameSubject"><fmt:message key="administrator.add_subject_english"/></label><br>
                <input type="text" id="enNameSubject" name="enNameSubject"><br>
                <label for="uaNameSubject"><fmt:message key="administrator.add_subject_ukrainian"/></label><br>
                <input type="text" id="uaNameSubject" name="uaNameSubject"><br>
                <label for="questionsSubject"><fmt:message key="administrator.add_subject_questions"/></label><br>
                <input type="number" min="0" id="questionsSubject" name="questionsSubject"><br>
                <input type="submit" value="<fmt:message key="submit.button"/>"/>
            </form>
        </div>
        <c:if test="${not empty successMessage}">
            <c:out value="${successMessage}"/>
        </c:if><br><br>
        <table class="w3-table w3-bordered w3-striped">
            <thead>
            <tr>
                <th><fmt:message key="subject.id"/></th>
                <th><fmt:message key="subject.name"/></th>
                <th><fmt:message key="subject.questions"/></th>
                <th><fmt:message key="subject.maximum"/></th>
                <th><fmt:message key="subject.minimum"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${subjects}" var="subject">
                <tr>
                    <th><c:out value="${subject.id}"/></th>
                    <c:choose>
                        <c:when test="${localeCode == 'uk'}">
                            <th><c:out value="${subject.uaName}"/></th>
                        </c:when>
                        <c:otherwise>
                            <th><c:out value="${subject.enName}"/></th>
                        </c:otherwise>
                    </c:choose>
                    <th><c:out value="${subject.questions}"/></th>
                    <th><c:out value="${subject.maximum}"/></th>
                    <th><c:out value="${subject.minimum}"/></th>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </body>
</html>