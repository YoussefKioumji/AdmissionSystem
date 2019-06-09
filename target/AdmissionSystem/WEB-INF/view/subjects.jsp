<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
    <body>
        <fmt:setBundle basename="outputs"/>
        <fmt:message key="subject.title" var="pageTitle"/>
        <jsp:include page="header.jsp">
            <jsp:param name="title" value="${pageTitle}"/>
        </jsp:include>
        <table>
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
                        <th><c:out value="${subject.enName}"/></th>
                        <th><c:out value="${subject.questions}"/></th>
                        <th><c:out value="${subject.maximum}"/></th>
                        <th><c:out value="${subject.minimum}"/></th>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <c:forEach begin="1" end="${numberOfPages}" varStatus="loop">
            <a href="${pageContext.request.contextPath}/app/subjects?pageNumber=${loop.index}">${loop.index}</a>
        </c:forEach>
    </body>
</html>