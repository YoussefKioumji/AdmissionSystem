<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
    <body>
        <fmt:setBundle basename="outputs"/>
        <c:set var="localeCode" value="${pageContext.response.locale}"/>
        <fmt:message key="administrator.exam_title" var="pageTitle"/>
        <jsp:include page="aheader.jsp">
            <jsp:param name="title" value="${pageTitle}"/>
        </jsp:include><br>
        <fmt:message key="administrator.exam_search_by_subject"/>
        <form action="${pageContext.request.contextPath}/app/admin/searchBySubject" method="post">
            <select name="searchedSubject">
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
            <br><input type="submit" value="<fmt:message key="submit.button"/>"/>
        </form>
        <table>
            <thead>
                <tr>
                    <th><fmt:message key="administrator.exam_email"/></th>
                    <th><fmt:message key="administrator.exam_subject"/></th>
                    <th><fmt:message key="administrator.exam_mark"/></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${users}" var="user">
                    <c:forEach items="${user.exams}" var="exam">
                        <tr>
                            <c:if test="${exam.mark eq 0}">
                                <th><c:out value="${user.email}"/></th>
                                <c:choose>
                                    <c:when test="${localeCode == 'uk'}">
                                        <th><c:out value="${exam.uaName}"/></th>
                                    </c:when>
                                    <c:otherwise>
                                        <th><c:out value="${exam.enName}"/></th>
                                    </c:otherwise>
                                </c:choose>
                            <th>
                                <form action="${pageContext.request.contextPath}/app/admin/checkExams" method="post">
                                    <input type="number" min="0" max="100" id="mark" name="mark">
                                    <input type="hidden" name="userEmail" value="${user.email}"/>
                                    <input type="hidden" name="subjectId" value="${exam.id}"/>
                                    <input type="submit" value="<fmt:message key="submit.button"/>"/>
                                </form>
                            </th>
                            </c:if>
                        </tr>
                    </c:forEach>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>