<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- include header.jsp and set page title --%>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Login page"/>
</jsp:include>

<%-- include message if present --%>
<form action="/login" method="post">
    <%-- include message if present --%>
    <b><c:out value="${message}"></c:out></b>
    <p>
        <label for="username">Username</label>
        <input type="text" id="username" name="username"/>
    </p>
    <p>
        <label for="password">Password</label>
        <input type="password" id="password" name="password"/>
    </p>
    <%-- add CSRF token (provided by Spring Security) --%>
    <input type="hidden"
           name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
    <button type="submit" class="btn">Log in</button>
</form>

<%-- include footer.jsp --%>
<jsp:include page="footer.jsp"/>