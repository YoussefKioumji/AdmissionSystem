<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
    <body>
        <fmt:setBundle basename="outputs"/>
        <fmt:message key="administrator.exam_title" var="pageTitle"/>
        <jsp:include page="aheader.jsp">
            <jsp:param name="title" value="${pageTitle}"/>
        </jsp:include>
        <c:forEach items="${subjects}" var="subject">
            <c:out value="${subject}"/>
        </c:forEach>
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
                            <th><c:out value="${user.email}"/></th>
                            <th><c:out value="${exam.enName}"/></th>
                            <th>
                                <form action="${pageContext.request.contextPath}/app/admin/checkExams" method="get">
                                    <input type="number" min="0" max="100" id="mark" name="mark">
                                    <input type="hidden" name="userEmail" value="${user.email}"/>
                                    <input type="hidden" name="subjectId" value="${exam.id}"/>
                                    <input type="submit" value="<fmt:message key="administrator.exam_submit"/>"/>
                                </form>
                            </th>
                        </tr>
                    </c:forEach>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>