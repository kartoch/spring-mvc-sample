<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="View users page"/>
</jsp:include>

        <h1><spring:message code="view.users.main.header"/></h1>

        <h4><spring:message code="view.users.greetings"/> ${userSession.username} !</h4>

        <table>
            <thead>
                <tr>
                    <td><spring:message code="domain.user.username"/></td>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <td>${user.username}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    <ul>
        <li><a href="newUser"><spring:message code="new.user.main.header"/></a></li>
        <li><a href="login?logout"><spring:message code="view.users.main.logout"/></a></li>
    </ul>

<jsp:include page="footer.jsp"/>