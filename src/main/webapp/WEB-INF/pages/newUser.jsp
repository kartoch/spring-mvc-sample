<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="New user page"/>
</jsp:include>

        <h1><spring:message code="new.user.main.header"/></h1>
        <form:form commandName="userForm" method="POST">
            <table>
                <tr>
                    <td><form:errors path="username"/></td>
                </tr>
                <tr>
                    <td><spring:message code="domain.user.username"/></td>
                    <td><form:input path="username"/></td>
                </tr>
                <tr>
                    <td><form:errors path="password"/></td>
                </tr>
                <tr>
                    <td><spring:message code="domain.user.password"/></td>
                    <td><form:password path="password"/></td></tr>
                <tr>
                    <td><input type="submit" value="<spring:message code="new.user.form.submit"/>"/></td>
                </tr>
            </table>
        </form:form>
<jsp:include page="footer.jsp"/>