<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN"
    "http://www.w3.org/TR/html4/strict.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><spring:message code="new.user.page.title"/></title>
    </head>
    <body>
        <h1><spring:message code="new.user.main.header"/></h1>
        <form:form commandName="user" method="POST">
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
    </body>
</html>
