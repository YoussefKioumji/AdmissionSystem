<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
    <body>
        <fmt:setBundle basename="outputs"/>
        <c:set var="localeCode" value="${pageContext.response.locale}"/>
        <fmt:message key="client.subject_title" var="pageTitle"/>
        <jsp:include page="cheader.jsp">
            <jsp:param name="title" value="${pageTitle}"/>
        </jsp:include><br><br>
        <c:if test="${empty passed}">
            <c:if test="${empty choseExams}">
                <fmt:message key="client.choose_subjects"/>
                <form action="${pageContext.request.contextPath}/app/client/selectSubjects" method="post">
                    <c:forEach var="i" begin="0" end="2">
                        <br>
                        <select name="selectedSubjects">
                            <c:forEach items="${subjects}" var="subject">
                                <c:choose>
                                    <c:when test="${localeCode == 'uk'}">
                                        <option value="${subject.id}" selected><c:out value="${subject.uaName}"/></option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${subject.id}" selected><c:out value="${subject.enName}"/></option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </c:forEach>
                    <br><input type="submit" value="<fmt:message key="submit.button"/>"/>
                </form>
            </c:if>
            <c:if test="${not empty choseExams}">
                <fmt:message key="client.already_submitted_subjects"/>
            </c:if>
        </c:if>
        <c:if test="${not empty passed}">
            <fmt:message key="message.stop_admission"/>
        </c:if>
        <table class="w3-table w3-bordered w3-striped">
            <thead>
                <tr>
                    <th><fmt:message key="subject.name"/></th>
                    <th><fmt:message key="subject.questions"/></th>
                    <th><fmt:message key="subject.maximum"/></th>
                    <th><fmt:message key="subject.minimum"/></th>
                </tr>
            </thead>
                <tbody>
                <c:forEach items="${subjects}" var="subject">
                    <tr>
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